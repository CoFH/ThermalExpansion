package cofh.thermal.expansion.common.block.entity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.common.fluid.FluidStorageCoFH;
import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.machine.BrewerRecipeManager;
import cofh.thermal.expansion.common.inventory.machine.MachineBrewerMenu;
import cofh.thermal.lib.common.block.entity.MachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.api.StorageGroup.*;
import static cofh.lib.util.Constants.*;
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.MACHINE_BREWER_TILE;

public class MachineBrewerBlockEntity extends MachineBlockEntity {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && BrewerRecipeManager.instance().validItem(item));
    protected FluidStorageCoFH inputTank = new FluidStorageCoFH(TANK_SMALL, fluid -> filter.valid(fluid) && BrewerRecipeManager.instance().validFluid(fluid));
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_MEDIUM);

    public MachineBrewerBlockEntity(BlockPos pos, BlockState state) {

        super(MACHINE_BREWER_TILE.get(), pos, state);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(inputTank, INPUT);
        tankInv.addTank(outputTank, OUTPUT);

        renderFluid = new FluidStack(Fluids.WATER, BUCKET_VOLUME);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return BrewerRecipeManager.instance().getBasePower();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = BrewerRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
            fluidInputCounts = curRecipe.getInputFluidCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected boolean cacheRenderFluid() {

        if (curRecipe == null) {
            return false;
        }
        FluidStack prevFluid = renderFluid;
        renderFluid = new FluidStack(curRecipe.getOutputFluids(this).get(0), BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new MachineBrewerMenu(i, level, worldPosition, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputSlot.getCount() >= itemInputCounts.get(0) && inputTank.getAmount() >= fluidInputCounts.get(0);
    }

    @Override
    protected boolean validateOutputs() {

        if (curRecipe == null && !cacheRecipe()) {
            return false;
        }
        if (outputTank.isEmpty()) {
            return true;
        }
        FluidStack output = outputTank.getFluidStack();
        FluidStack recipeOutput = curRecipe.getOutputFluids(this).get(0);
        if (outputTank.getSpace() < recipeOutput.getAmount()) {
            return false;
        }
        return FluidHelper.fluidsEqual(output, recipeOutput);
    }
    // endregion
}
