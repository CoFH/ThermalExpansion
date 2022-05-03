package cofh.thermal.expansion.tileentity.machine;

import cofh.lib.client.audio.ConditionalSound;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.util.managers.machine.SawmillRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachineSawmillContainer;
import cofh.thermal.lib.tileentity.MachineTileProcess;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_SAWMILL_TILE;
import static cofh.thermal.expansion.init.TExpSounds.SOUND_MACHINE_SAWMILL;
import static cofh.thermal.lib.common.ThermalConfig.machineAugments;

public class MachineSawmillTile extends MachineTileProcess {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && SawmillRecipeManager.instance().validRecipe(item));

    public MachineSawmillTile(BlockPos pos, BlockState state) {

        super(MACHINE_SAWMILL_TILE, pos, state);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlots(OUTPUT, 4);
        inventory.addSlot(chargeSlot, INTERNAL);

        addAugmentSlots(machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return SawmillRecipeManager.instance().getBasePower();
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
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new MachineSawmillContainer(i, level, worldPosition, inventory, player);
    }

    @Override
    protected Object getSound() {

        return new ConditionalSound(SOUND_MACHINE_SAWMILL, SoundSource.AMBIENT, this, () -> !remove && isActive);
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
