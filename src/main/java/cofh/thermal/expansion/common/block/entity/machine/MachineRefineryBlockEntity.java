package cofh.thermal.expansion.common.block.entity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.common.fluid.FluidStorageCoFH;
import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.machine.RefineryRecipeManager;
import cofh.thermal.expansion.common.inventory.machine.MachineRefineryMenu;
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
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.MACHINE_REFINERY_TILE;

public class MachineRefineryBlockEntity extends MachineBlockEntity {

    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected FluidStorageCoFH inputTank = new FluidStorageCoFH(TANK_SMALL, fluid -> filter.valid(fluid) && RefineryRecipeManager.instance().validRecipe(fluid));
    protected FluidStorageCoFH outputTankA = new FluidStorageCoFH(TANK_MEDIUM);
    protected FluidStorageCoFH outputTankB = new FluidStorageCoFH(TANK_MEDIUM);

    public MachineRefineryBlockEntity(BlockPos pos, BlockState state) {

        super(MACHINE_REFINERY_TILE.get(), pos, state);

        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(inputTank, INPUT);
        tankInv.addTank(outputTankA, OUTPUT);
        tankInv.addTank(outputTankB, OUTPUT);

        renderFluid = new FluidStack(Fluids.WATER, BUCKET_VOLUME);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return RefineryRecipeManager.instance().getBasePower();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = RefineryRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            fluidInputCounts = curRecipe.getInputFluidCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected boolean cacheRenderFluid() {

        if (curRecipe == null) {
            return false;
        }
        if (inputTank.isEmpty()) {
            // This should definitely never happen, but who knows.
            return false;
        }
        FluidStack prevFluid = renderFluid;
        renderFluid = new FluidStack(inputTank.getFluidStack(), BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new MachineRefineryMenu(i, level, worldPosition, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputTank.getAmount() >= fluidInputCounts.get(0);
    }
    // endregion
}
