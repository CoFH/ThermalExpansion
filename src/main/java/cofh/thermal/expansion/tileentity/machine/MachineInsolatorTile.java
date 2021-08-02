package cofh.thermal.expansion.tileentity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.MathHelper;
import cofh.thermal.core.util.managers.machine.InsolatorRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachineInsolatorContainer;
import cofh.thermal.lib.tileentity.MachineTileProcess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.Constants.BUCKET_VOLUME;
import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.lib.util.constants.NBTTags.TAG_AUGMENT_FEATURE_CYCLE_PROCESS;
import static cofh.lib.util.helpers.AugmentableHelper.getAttributeMod;
import static cofh.lib.util.helpers.ItemHelper.itemsEqualWithTags;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_INSOLATOR_TILE;
import static cofh.thermal.lib.common.ThermalConfig.machineAugments;

public class MachineInsolatorTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && InsolatorRecipeManager.instance().validRecipe(item));
    protected ItemStorageCoFH catalystSlot = new ItemStorageCoFH(InsolatorRecipeManager.instance()::validCatalyst);
    protected FluidStorageCoFH waterTank = new FluidStorageCoFH(TANK_SMALL, FluidHelper.IS_WATER);

    public MachineInsolatorTile() {

        super(MACHINE_INSOLATOR_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(catalystSlot, CATALYST);
        inventory.addSlots(OUTPUT, 4);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(waterTank, INPUT);

        renderFluid = new FluidStack(Fluids.WATER, BUCKET_VOLUME);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = InsolatorRecipeManager.instance().getRecipe(this);
        curCatalyst = InsolatorRecipeManager.instance().getCatalyst(catalystSlot);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
            fluidInputCounts = curRecipe.getInputFluidCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected void resolveInputs() {

        // Input Items
        int primaryCount = itemInputCounts.get(0);
        if (cyclicProcessingFeature) {
            boolean recycled = false;
            ItemStack input = inputSlot.getItemStack();
            for (ItemStorageCoFH slot : outputSlots()) {
                if (itemsEqualWithTags(slot.getItemStack(), input) && slot.getCount() >= primaryCount) {
                    slot.modify(-primaryCount);
                    recycled = true;
                    break;
                }
            }
            if (!recycled) {
                inputSlot.modify(-primaryCount);
            }
        } else {
            inputSlot.modify(-primaryCount);
        }
        int decrement = itemInputCounts.size() > 1 ? itemInputCounts.get(1) : 0;
        if (decrement > 0) {
            if (catalystSlot.getItemStack().isDamageable()) {
                if (catalystSlot.getItemStack().attemptDamageItem(decrement, MathHelper.RANDOM, null)) {
                    catalystSlot.modify(-1);
                }
            } else {
                catalystSlot.modify(-decrement);
            }
        }
        // Input Fluids
        if (!fluidInputCounts.isEmpty()) {
            waterTank.modify(-fluidInputCounts.get(0));
        }
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachineInsolatorContainer(i, world, pos, inventory, player);
    }

    // region OPTIMIZATION
    @Override
    protected boolean validateInputs() {

        if (!cacheRecipe()) {
            return false;
        }
        return inputSlot.getCount() >= itemInputCounts.get(0) && (fluidInputCounts.isEmpty() || waterTank.getAmount() >= fluidInputCounts.get(0));
    }
    // endregion

    // region AUGMENTS
    protected boolean cyclicProcessingFeature = false;

    @Override
    protected void resetAttributes() {

        super.resetAttributes();

        cyclicProcessingFeature = false;
    }

    @Override
    protected void setAttributesFromAugment(CompoundNBT augmentData) {

        super.setAttributesFromAugment(augmentData);

        cyclicProcessingFeature |= getAttributeMod(augmentData, TAG_AUGMENT_FEATURE_CYCLE_PROCESS) > 0;
    }
    // endregion
}
