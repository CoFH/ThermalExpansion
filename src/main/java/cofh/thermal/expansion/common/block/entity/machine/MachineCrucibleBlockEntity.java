package cofh.thermal.expansion.common.block.entity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.client.sounds.ConditionalSoundInstance;
import cofh.lib.common.fluid.FluidStorageCoFH;
import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.machine.CrucibleRecipeManager;
import cofh.thermal.expansion.common.inventory.machine.MachineCrucibleMenu;
import cofh.thermal.lib.common.block.entity.MachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.api.StorageGroup.*;
import static cofh.lib.util.Constants.BUCKET_VOLUME;
import static cofh.lib.util.Constants.TANK_MEDIUM;
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.MACHINE_CRUCIBLE_TILE;
import static cofh.thermal.expansion.init.registries.TExpSounds.SOUND_MACHINE_CRUCIBLE;

public class MachineCrucibleBlockEntity extends MachineBlockEntity {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && CrucibleRecipeManager.instance().validRecipe(item));
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_MEDIUM);

    public MachineCrucibleBlockEntity(BlockPos pos, BlockState state) {

        super(MACHINE_CRUCIBLE_TILE.get(), pos, state);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(outputTank, OUTPUT);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return CrucibleRecipeManager.instance().getBasePower();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = CrucibleRecipeManager.instance().getRecipe(this);
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
        renderFluid = new FluidStack(curRecipe.getOutputFluids(this).get(0), BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new MachineCrucibleMenu(i, level, worldPosition, inventory, player);
    }

    @Override
    protected Object getSound() {

        return new ConditionalSoundInstance(SOUND_MACHINE_CRUCIBLE.get(), SoundSource.AMBIENT, this, () -> !remove && isActive);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputSlot.getCount() >= itemInputCounts.get(0);
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
