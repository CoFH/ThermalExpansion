package cofh.thermal.expansion.tileentity.machine;

import cofh.lib.client.audio.ConditionalSound;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.util.managers.machine.FurnaceRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachineFurnaceContainer;
import cofh.thermal.lib.tileentity.MachineTileProcess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.helpers.ItemHelper.itemsEqualWithTags;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_FURNACE_TILE;
import static cofh.thermal.expansion.init.TExpSounds.SOUND_MACHINE_FURNACE;
import static cofh.thermal.lib.common.ThermalConfig.machineAugments;

public class MachineFurnaceTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && FurnaceRecipeManager.instance().validRecipe(item));
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();

    public MachineFurnaceTile() {

        super(MACHINE_FURNACE_TILE);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = FurnaceRecipeManager.instance().getRecipe(this);
        if (curRecipe != null) {
            itemInputCounts = curRecipe.getInputItemCounts(this);
        }
        return curRecipe != null;
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new MachineFurnaceContainer(i, world, pos, inventory, player);
    }

    @Override
    protected Object getSound() {

        return new ConditionalSound(SOUND_MACHINE_FURNACE, SoundCategory.AMBIENT, this, () -> !removed && isActive);
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
        ItemStack output = outputSlot.getItemStack();
        if (output.isEmpty()) {
            return true;
        }
        ItemStack recipeOutput = curRecipe.getOutputItems(this).get(0);
        if (output.getCount() >= output.getMaxStackSize()) {
            return false;
        }
        return itemsEqualWithTags(output, recipeOutput);
    }
    // endregion
}
