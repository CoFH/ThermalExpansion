package cofh.thermal.expansion.tileentity.machine;

import cofh.lib.client.audio.ConditionalSound;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.util.managers.machine.SawmillRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachineSawmillContainer;
import cofh.thermal.lib.tileentity.MachineTileProcess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.SoundCategory;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_SAWMILL_TILE;
import static cofh.thermal.expansion.init.TExpSounds.SOUND_MACHINE_SAWMILL;
import static cofh.thermal.lib.common.ThermalConfig.machineAugments;

public class MachineSawmillTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && SawmillRecipeManager.instance().validRecipe(item));

    public MachineSawmillTile() {

        super(MACHINE_SAWMILL_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlots(OUTPUT, 4);
        inventory.addSlot(chargeSlot, INTERNAL);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = SawmillRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachineSawmillContainer(i, world, pos, inventory, player);
    }

    @Override
    protected Object getSound() {

        return new ConditionalSound(SOUND_MACHINE_SAWMILL, SoundCategory.AMBIENT, this, () -> !removed && isActive);
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
