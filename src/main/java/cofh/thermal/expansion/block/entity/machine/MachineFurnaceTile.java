package cofh.thermal.expansion.block.entity.machine;

import cofh.lib.client.sounds.ConditionalSoundInstance;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.machine.FurnaceRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachineFurnaceContainer;
import cofh.thermal.lib.block.entity.MachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import static cofh.core.util.helpers.ItemHelper.itemsEqualWithTags;
import static cofh.lib.api.StorageGroup.*;
import static cofh.thermal.expansion.init.TExpSounds.SOUND_MACHINE_FURNACE;
import static cofh.thermal.expansion.init.TExpTileEntities.MACHINE_FURNACE_TILE;

public class MachineFurnaceTile extends MachineBlockEntity {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && FurnaceRecipeManager.instance().validRecipe(item));
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();

    public MachineFurnaceTile(BlockPos pos, BlockState state) {

        super(MACHINE_FURNACE_TILE.get(), pos, state);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return FurnaceRecipeManager.instance().getBasePower();
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
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new MachineFurnaceContainer(i, level, worldPosition, inventory, player);
    }

    @Override
    protected Object getSound() {

        return new ConditionalSoundInstance(SOUND_MACHINE_FURNACE.get(), SoundSource.AMBIENT, this, () -> !remove && isActive);
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
