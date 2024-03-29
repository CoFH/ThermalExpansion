package cofh.thermal.expansion.block.entity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.item.SlotSealItem;
import cofh.thermal.core.util.managers.machine.PressRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachinePressContainer;
import cofh.thermal.lib.block.entity.MachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.api.StorageGroup.*;
import static cofh.lib.util.Constants.BUCKET_VOLUME;
import static cofh.lib.util.Constants.TANK_SMALL;
import static cofh.thermal.expansion.init.TExpTileEntities.MACHINE_PRESS_TILE;
import static cofh.thermal.lib.util.references.ThermalTags.Items.MACHINE_DIES;

public class MachinePressTile extends MachineBlockEntity {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && PressRecipeManager.instance().validInput(item));
    protected ItemStorageCoFH dieSlot = new ItemStorageCoFH(item -> item.getItem() instanceof SlotSealItem || filter.valid(item) && PressRecipeManager.instance().validDie(item));
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_SMALL);

    public MachinePressTile(BlockPos pos, BlockState state) {

        super(MACHINE_PRESS_TILE.get(), pos, state);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(dieSlot, INPUT);
        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(outputTank, OUTPUT);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return PressRecipeManager.instance().getBasePower();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = PressRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected boolean cacheRenderFluid() {

        if (curRecipe == null) {
            return false;
        }
        FluidStack prevFluid = renderFluid;
        List<FluidStack> recipeOutputFluids = curRecipe.getOutputFluids(this);
        renderFluid = recipeOutputFluids.isEmpty() ? FluidStack.EMPTY : new FluidStack(recipeOutputFluids.get(0), BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }

    @Override
    protected void resolveInputs() {

        // Input Items
        inputSlot.modify(-itemInputCounts.get(0));

        if (itemInputCounts.size() > 1 && !dieSlot.getItemStack().is(MACHINE_DIES)) {
            dieSlot.modify(-itemInputCounts.get(1));
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new MachinePressContainer(i, level, worldPosition, inventory, player);
    }

}
