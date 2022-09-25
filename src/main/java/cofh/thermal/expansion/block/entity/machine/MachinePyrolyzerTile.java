package cofh.thermal.expansion.block.entity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.machine.PyrolyzerRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachinePyrolyzerContainer;
import cofh.thermal.lib.tileentity.MachineTileBase;
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
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PYROLYZER_TILE;

public class MachinePyrolyzerTile extends MachineTileBase {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && PyrolyzerRecipeManager.instance().validRecipe(item));
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_SMALL);

    public MachinePyrolyzerTile(BlockPos pos, BlockState state) {

        super(MACHINE_PYROLYZER_TILE, pos, state);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlots(OUTPUT, 4);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(outputTank, OUTPUT);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return PyrolyzerRecipeManager.instance().getBasePower();
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
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new MachinePyrolyzerContainer(i, level, worldPosition, inventory, player);
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
