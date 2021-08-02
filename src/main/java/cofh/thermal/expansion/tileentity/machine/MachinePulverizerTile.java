package cofh.thermal.expansion.tileentity.machine;

import cofh.lib.client.audio.ConditionalSound;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.MathHelper;
import cofh.thermal.core.util.managers.machine.PulverizerRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachinePulverizerContainer;
import cofh.thermal.lib.tileentity.MachineTileProcess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.NBTTags.TAG_AUGMENT_FEATURE_CYCLE_PROCESS;
import static cofh.lib.util.helpers.AugmentableHelper.getAttributeMod;
import static cofh.lib.util.helpers.ItemHelper.itemsEqualWithTags;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PULVERIZER_TILE;
import static cofh.thermal.expansion.init.TExpSounds.SOUND_MACHINE_PULVERIZER;
import static cofh.thermal.lib.common.ThermalConfig.machineAugments;

public class MachinePulverizerTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && PulverizerRecipeManager.instance().validRecipe(item));
    protected ItemStorageCoFH catalystSlot = new ItemStorageCoFH(PulverizerRecipeManager.instance()::validCatalyst);

    public MachinePulverizerTile() {

        super(MACHINE_PULVERIZER_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(catalystSlot, CATALYST);
        inventory.addSlots(OUTPUT, 4);
        inventory.addSlot(chargeSlot, INTERNAL);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = PulverizerRecipeManager.instance().getRecipe(this);
        curCatalyst = PulverizerRecipeManager.instance().getCatalyst(catalystSlot);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Override
    protected void resolveInputs() {

        // Input Items
        inputSlot.modify(-itemInputCounts.get(0));

        if (cyclicProcessingFeature && !catalystSlot.isEmpty() && !catalystSlot.isFull()) {
            ItemStack catalyst = catalystSlot.getItemStack();
            for (ItemStorageCoFH slot : outputSlots()) {
                if (itemsEqualWithTags(slot.getItemStack(), catalyst)) {
                    slot.modify(-1);
                    catalystSlot.modify(1);
                    break;
                }
            }
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
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachinePulverizerContainer(i, world, pos, inventory, player);
    }

    @Override
    protected Object getSound() {

        return new ConditionalSound(SOUND_MACHINE_PULVERIZER, SoundCategory.AMBIENT, this, () -> !removed && isActive);
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
