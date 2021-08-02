package cofh.thermal.expansion.tileentity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.util.managers.machine.PyrolyzerRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachinePyrolyzerContainer;
import cofh.thermal.lib.tileentity.MachineTileProcess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;
import java.util.List;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.BUCKET_VOLUME;
import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PYROLYZER_TILE;
import static cofh.thermal.lib.common.ThermalConfig.machineAugments;

public class MachinePyrolyzerTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && PyrolyzerRecipeManager.instance().validRecipe(item));
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_SMALL);

    public MachinePyrolyzerTile() {

        super(MACHINE_PYROLYZER_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlots(OUTPUT, 4);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(outputTank, OUTPUT);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    // TODO: Adjust when recipes have min/max power.
    @Override
    protected int getBaseProcessTick() {

        return BASE_PROCESS_TICK / 4;
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = PyrolyzerRecipeManager.instance().getRecipe(this);
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

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachinePyrolyzerContainer(i, world, pos, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputSlot.getCount() >= itemInputCounts.get(0);
    }
    // endregion
}
