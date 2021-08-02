package cofh.thermal.expansion.tileentity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.FalseCraftingInventory;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.Utils;
import cofh.lib.xp.EmptyXpStorage;
import cofh.thermal.core.util.managers.machine.CrafterRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachineCrafterContainer;
import cofh.thermal.lib.common.ThermalConfig;
import cofh.thermal.lib.tileentity.MachineTileProcess;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.BUCKET_VOLUME;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CRAFTER_TILE;
import static cofh.thermal.lib.common.ThermalConfig.machineAugments;

public class MachineCrafterTile extends MachineTileProcess {

    public static final int SLOT_CRAFTING_START = 11;

    protected FalseCraftingInventory craftMatrix = new FalseCraftingInventory(3, 3);
    protected CraftResultInventory craftResult = new CraftResultInventory();
    protected boolean hasRecipeChanges;
    protected boolean validRecipe;

    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected ItemStorageCoFH resultSlot = new ItemStorageCoFH();

    protected FluidStorageCoFH inputTank = new FluidStorageCoFH(TANK_MEDIUM, fluid -> filter.valid(fluid) && CrafterRecipeManager.instance().validFluid(fluid, curRecipe));

    public MachineCrafterTile() {

        super(MACHINE_CRAFTER_TILE);

        xpStorage = EmptyXpStorage.INSTANCE;

        inventory.addSlots(INPUT, 9, item -> filter.valid(item) && CrafterRecipeManager.instance().validItem(item, curRecipe));
        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        inventory.addSlots(INTERNAL, 9);
        inventory.addSlot(resultSlot, INTERNAL);

        tankInv.addTank(inputTank, INPUT);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    @Override
    public void tick() {

        if (!resultSlot.isEmpty() && craftResult.getRecipeUsed() == null) {
            setRecipe();
        }
        super.tick();
    }

    @Override
    protected int getBaseXpStorage() {

        return 0;
    }

    protected void setRecipe() {

        if (world == null || Utils.isClientWorld(world)) {
            return;
        }
        for (int i = 0; i < 9; ++i) {
            craftMatrix.setInventorySlotContents(i, inventory.get(SLOT_CRAFTING_START + i));
        }
        ICraftingRecipe craftRecipe;
        Optional<ICraftingRecipe> possibleRecipe = world.getRecipeManager().getRecipe(IRecipeType.CRAFTING, craftMatrix, world);
        if (possibleRecipe.isPresent()) {
            craftRecipe = possibleRecipe.get();
            craftResult.setInventorySlotContents(0, craftRecipe.getCraftingResult(craftMatrix));
        } else {
            craftRecipe = null;
            craftResult.setInventorySlotContents(0, ItemStack.EMPTY);
            if (isActive) {
                processOff();
            }
        }
        craftResult.setRecipeUsed(craftRecipe);
        curRecipe = CrafterRecipeManager.instance().getRecipe(craftRecipe);
        resultSlot.setItemStack(craftResult.getStackInSlot(0));
        clearRecipeChanges();
    }

    public void markRecipeChanges() {

        hasRecipeChanges = true;
        if (isActive) {
            processOff();
        }
    }

    public void clearRecipeChanges() {

        hasRecipeChanges = false;
    }

    public boolean hasRecipeChanges() {

        return hasRecipeChanges;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState) {

        if (!ThermalConfig.keepItems.get()) {
            for (int i = 0; i < invSize() - augSize() - 9 - 1; ++i) {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(i));
            }
        }
        if (!ThermalConfig.keepAugments.get()) {
            for (int i = invSize() - augSize(); i < invSize(); ++i) {
                Utils.dropItemStackIntoWorldWithRandomness(inventory.getStackInSlot(i), worldIn, pos);
            }
        }
    }

    @Override
    protected boolean canProcessStart() {

        return !hasRecipeChanges && super.canProcessStart();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = CrafterRecipeManager.instance().getRecipe(craftResult.getRecipeUsed());
        if (curRecipe != null) {
            Pair<List<Integer>, List<Integer>> inputCounts = curRecipe.getInputItemAndFluidCounts(this);
            itemInputCounts = inputCounts.getLeft();
            fluidInputCounts = inputCounts.getRight();
        }
        validRecipe = !(itemInputCounts.isEmpty() && fluidInputCounts.isEmpty());
        return validRecipe;
    }

    @Override
    protected boolean cacheRenderFluid() {

        if (curRecipe == null) {
            return false;
        }
        FluidStack prevFluid = renderFluid;
        if (!fluidInputCounts.isEmpty() && fluidInputCounts.get(0) > 0) {
            renderFluid = new FluidStack(inputTank.getFluidStack(), BUCKET_VOLUME);
        } else {
            renderFluid = FluidStack.EMPTY;
        }
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachineCrafterContainer(i, world, pos, inventory, player);
    }

    // region NETWORK

    // CONFIG
    @Override
    public PacketBuffer getConfigPacket(PacketBuffer buffer) {

        super.getConfigPacket(buffer);

        for (int i = SLOT_CRAFTING_START; i < SLOT_CRAFTING_START + 9; ++i) {
            buffer.writeItemStack(inventory.getStackInSlot(i));
        }
        return buffer;
    }

    @Override
    public void handleConfigPacket(PacketBuffer buffer) {

        super.handleConfigPacket(buffer);

        for (int i = SLOT_CRAFTING_START; i < SLOT_CRAFTING_START + 9; ++i) {
            inventory.set(i, buffer.readItemStack());
        }
        setRecipe();
    }

    // GUI
    @Override
    public PacketBuffer getGuiPacket(PacketBuffer buffer) {

        super.getGuiPacket(buffer);

        boolean hasRecipe = craftResult.getRecipeUsed() != null;
        buffer.writeBoolean(hasRecipe);
        if (hasRecipe) {
            buffer.writeResourceLocation(craftResult.getRecipeUsed().getId());
        }
        return buffer;
    }

    @Override
    public void handleGuiPacket(PacketBuffer buffer) {

        super.handleGuiPacket(buffer);

        if (buffer.readBoolean() && world != null) {
            Optional<? extends IRecipe<?>> possibleRecipe = world.getRecipeManager().getRecipe(buffer.readResourceLocation());
            possibleRecipe.ifPresent(recipe -> curRecipe = CrafterRecipeManager.instance().getRecipe(recipe));
        } else {
            curRecipe = null;
        }
    }
    // endregion
}
