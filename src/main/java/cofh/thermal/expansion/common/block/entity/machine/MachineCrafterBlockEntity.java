package cofh.thermal.expansion.common.block.entity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.core.util.helpers.InventoryHelper;
import cofh.lib.common.fluid.FluidStorageCoFH;
import cofh.lib.common.inventory.FalseCraftingContainer;
import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.lib.common.xp.EmptyXpStorage;
import cofh.lib.util.Utils;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.core.common.item.SlotSealItem;
import cofh.thermal.core.util.managers.machine.CrafterRecipeManager;
import cofh.thermal.expansion.common.inventory.machine.MachineCrafterMenu;
import cofh.thermal.lib.common.block.entity.MachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static cofh.core.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.api.StorageGroup.*;
import static cofh.lib.util.Constants.BUCKET_VOLUME;
import static cofh.lib.util.Constants.TANK_MEDIUM;
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.MACHINE_CRAFTER_TILE;

public class MachineCrafterBlockEntity extends MachineBlockEntity {

    public static final int SLOT_CRAFTING_START = 11;

    protected FalseCraftingContainer craftMatrix = new FalseCraftingContainer(3, 3);
    protected ResultContainer craftResult = new ResultContainer();
    protected boolean hasRecipeChanges;
    protected boolean validRecipe;

    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected ItemStorageCoFH resultSlot = new ItemStorageCoFH();

    protected FluidStorageCoFH inputTank = new FluidStorageCoFH(TANK_MEDIUM, fluid -> filter.valid(fluid) && CrafterRecipeManager.instance().validFluid(fluid, curRecipe));

    public MachineCrafterBlockEntity(BlockPos pos, BlockState state) {

        super(MACHINE_CRAFTER_TILE.get(), pos, state);

        xpStorage = EmptyXpStorage.INSTANCE;

        inventory.addSlots(INPUT, 9, item -> item.getItem() instanceof SlotSealItem || filter.valid(item) && CrafterRecipeManager.instance().validItem(item, curRecipe));
        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        inventory.addSlots(INTERNAL, 9);
        inventory.addSlot(resultSlot, INTERNAL);

        tankInv.addTank(inputTank, INPUT);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return CrafterRecipeManager.instance().getBasePower();
    }

    @Override
    public void tickServer() {

        if (!resultSlot.isEmpty() && craftResult.getRecipeUsed() == null) {
            setRecipe();
        }
        super.tickServer();
    }

    @Override
    protected int getBaseXpStorage() {

        return 0;
    }

    protected void setRecipe() {

        if (level == null || Utils.isClientWorld(level)) {
            return;
        }
        for (int i = 0; i < 9; ++i) {
            craftMatrix.setItem(i, inventory.get(SLOT_CRAFTING_START + i));
        }
        CraftingRecipe craftRecipe;
        Optional<CraftingRecipe> possibleRecipe = level.getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftMatrix, level);
        if (possibleRecipe.isPresent()) {
            craftRecipe = possibleRecipe.get();
            craftResult.setItem(0, craftRecipe.assemble(craftMatrix, level.registryAccess()));
        } else {
            craftRecipe = null;
            craftResult.setItem(0, ItemStack.EMPTY);
            if (isActive) {
                processOff();
            }
        }
        craftResult.setRecipeUsed(craftRecipe);
        curRecipe = CrafterRecipeManager.instance().getRecipe(craftRecipe, level.registryAccess());
        resultSlot.setItemStack(craftResult.getItem(0));
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
    public void onReplaced(BlockState state, Level levelIn, BlockPos pos, BlockState newState) {

        if (!ThermalCoreConfig.keepItems.get()) {
            for (int i = 0; i < invSize() - augSize() - 9 - 1; ++i) {
                Utils.dropItemStackIntoWorldWithRandomness(inventory.getStackInSlot(i), levelIn, pos);
            }
        }
        if (!ThermalCoreConfig.keepAugments.get()) {
            for (int i = invSize() - augSize(); i < invSize(); ++i) {
                Utils.dropItemStackIntoWorldWithRandomness(inventory.getStackInSlot(i), levelIn, pos);
            }
        }
    }

    @Override
    protected boolean canProcessStart() {

        return !hasRecipeChanges && super.canProcessStart();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = CrafterRecipeManager.instance().getRecipe(craftResult.getRecipeUsed(), level.registryAccess());
        if (curRecipe == null) {
            return false;
        }
        Pair<List<Integer>, List<Integer>> inputCounts = curRecipe.getInputItemAndFluidCounts(this);
        itemInputCounts = inputCounts.getLeft();
        fluidInputCounts = inputCounts.getRight();

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

    // region HELPERS
    @Override
    protected boolean validateInputs() {

        if (skipValidate) {
            return true;
        }
        return super.validateInputs();
    }

    @Override
    protected void resolveInputs() {

        // Input Items
        for (int i = 0; i < itemInputCounts.size(); ++i) {
            ItemStack stackInSlot = inputSlots().get(i).getItemStack();
            ItemStack toAdd = ItemStack.EMPTY;

            // If max stack size is 1, normal consume behavior will work
            if (stackInSlot.getMaxStackSize() > 1 && stackInSlot.hasCraftingRemainingItem()) {
                toAdd = cloneStack(stackInSlot.getCraftingRemainingItem(), itemInputCounts.get(i));
            }
            inputSlots().get(i).consume(itemInputCounts.get(i));

            // Add crafting remainder items to input slots
            if (!toAdd.isEmpty()) {
                skipValidate = true;
                InventoryHelper.insertStackIntoInventory(inventory.getHandler(INPUT), toAdd, false);
                skipValidate = false;
            }
        }
        // Input Fluids
        for (int i = 0; i < fluidInputCounts.size(); ++i) {
            inputTanks().get(i).modify(-fluidInputCounts.get(i));
        }
    }

    private boolean skipValidate = false; // Used specifically to prevent input validation during remaining item addition
    // endregion

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new MachineCrafterMenu(i, level, worldPosition, inventory, player);
    }

    // region NETWORK

    // CONFIG
    @Override
    public FriendlyByteBuf getConfigPacket(FriendlyByteBuf buffer) {

        super.getConfigPacket(buffer);

        for (int i = SLOT_CRAFTING_START; i < SLOT_CRAFTING_START + 9; ++i) {
            buffer.writeItem(inventory.getStackInSlot(i));
        }
        return buffer;
    }

    @Override
    public void handleConfigPacket(FriendlyByteBuf buffer) {

        super.handleConfigPacket(buffer);

        for (int i = SLOT_CRAFTING_START; i < SLOT_CRAFTING_START + 9; ++i) {
            inventory.set(i, buffer.readItem());
        }
        setRecipe();
        markChunkUnsaved();
    }

    // GUI
    @Override
    public FriendlyByteBuf getGuiPacket(FriendlyByteBuf buffer) {

        super.getGuiPacket(buffer);

        boolean hasRecipe = craftResult.getRecipeUsed() != null;
        buffer.writeBoolean(hasRecipe);
        if (hasRecipe) {
            buffer.writeResourceLocation(craftResult.getRecipeUsed().getId());
        }
        return buffer;
    }

    @Override
    public void handleGuiPacket(FriendlyByteBuf buffer) {

        super.handleGuiPacket(buffer);

        if (buffer.readBoolean() && level != null) {
            Optional<? extends Recipe<?>> possibleRecipe = level.getRecipeManager().byKey(buffer.readResourceLocation());
            possibleRecipe.ifPresent(recipe -> curRecipe = CrafterRecipeManager.instance().getRecipe(recipe, level.registryAccess()));
        } else {
            curRecipe = null;
        }
    }
    // endregion
}
