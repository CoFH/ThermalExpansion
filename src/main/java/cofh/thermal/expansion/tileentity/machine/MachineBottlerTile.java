package cofh.thermal.expansion.tileentity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.client.audio.ConditionalSound;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.util.managers.machine.BottlerRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachineBottlerContainer;
import cofh.thermal.lib.tileentity.MachineTileProcess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.BUCKET_VOLUME;
import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_BOTTLER_TILE;
import static cofh.thermal.expansion.init.TExpSounds.SOUND_MACHINE_BOTTLER;
import static cofh.thermal.lib.common.ThermalConfig.machineAugments;

public class MachineBottlerTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && BottlerRecipeManager.instance().validItem(item));
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected FluidStorageCoFH inputTank = new FluidStorageCoFH(TANK_MEDIUM, fluid -> filter.valid(fluid) && BottlerRecipeManager.instance().validFluid(fluid));

    public MachineBottlerTile() {

        super(MACHINE_BOTTLER_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(inputTank, INPUT);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = BottlerRecipeManager.instance().getRecipe(this);
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
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachineBottlerContainer(i, world, pos, inventory, player);
    }

    @Override
    protected Object getSound() {

        return new ConditionalSound(SOUND_MACHINE_BOTTLER, SoundCategory.AMBIENT, this, () -> !removed && isActive);
    }

}
