package cofh.thermal.expansion.block.entity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.client.audio.ConditionalSound;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.machine.CrucibleRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachineCrucibleContainer;
import cofh.thermal.lib.tileentity.MachineTileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.BUCKET_VOLUME;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CRUCIBLE_TILE;
import static cofh.thermal.expansion.init.TExpSounds.SOUND_MACHINE_CRUCIBLE;

public class MachineCrucibleTile extends MachineTileBase {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && CrucibleRecipeManager.instance().validRecipe(item));
    protected FluidStorageCoFH outputTank = new FluidStorageCoFH(TANK_MEDIUM);

    public MachineCrucibleTile(BlockPos pos, BlockState state) {

        super(MACHINE_CRUCIBLE_TILE, pos, state);

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

        return new MachineCrucibleContainer(i, level, worldPosition, inventory, player);
    }

    @Override
    protected Object getSound() {

        return new ConditionalSound(SOUND_MACHINE_CRUCIBLE, SoundSource.AMBIENT, this, () -> !remove && isActive);
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
