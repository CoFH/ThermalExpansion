package cofh.thermal.expansion.block.entity.machine;

import cofh.lib.client.sounds.ConditionalSoundInstance;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.machine.SawmillRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachineSawmillContainer;
import cofh.thermal.lib.block.entity.MachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import static cofh.lib.api.StorageGroup.*;
import static cofh.thermal.expansion.init.TExpBlockEntities.MACHINE_SAWMILL_TILE;
import static cofh.thermal.expansion.init.TExpSounds.SOUND_MACHINE_SAWMILL;

public class MachineSawmillTile extends MachineBlockEntity {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && SawmillRecipeManager.instance().validRecipe(item));

    public MachineSawmillTile(BlockPos pos, BlockState state) {

        super(MACHINE_SAWMILL_TILE.get(), pos, state);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlots(OUTPUT, 4);
        inventory.addSlot(chargeSlot, INTERNAL);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
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

        return new ConditionalSoundInstance(SOUND_MACHINE_SAWMILL.get(), SoundSource.AMBIENT, this, () -> !remove && isActive);
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
