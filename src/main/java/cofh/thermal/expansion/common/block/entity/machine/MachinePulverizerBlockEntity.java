package cofh.thermal.expansion.common.block.entity.machine;

import cofh.lib.client.sounds.ConditionalSoundInstance;
import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.MathHelper;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.core.common.item.SlotSealItem;
import cofh.thermal.core.util.managers.machine.PulverizerRecipeManager;
import cofh.thermal.expansion.common.inventory.machine.MachinePulverizerMenu;
import cofh.thermal.lib.common.block.entity.MachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import static cofh.core.util.helpers.AugmentableHelper.getAttributeMod;
import static cofh.core.util.helpers.ItemHelper.itemsEqualWithTags;
import static cofh.lib.api.StorageGroup.*;
import static cofh.lib.util.constants.NBTTags.TAG_AUGMENT_FEATURE_CYCLE_PROCESS;
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.MACHINE_PULVERIZER_TILE;
import static cofh.thermal.expansion.init.registries.TExpSounds.SOUND_MACHINE_PULVERIZER;

public class MachinePulverizerBlockEntity extends MachineBlockEntity {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && PulverizerRecipeManager.instance().validRecipe(item));
    protected ItemStorageCoFH catalystSlot = new ItemStorageCoFH(item -> item.getItem() instanceof SlotSealItem || PulverizerRecipeManager.instance().validCatalyst(item));

    public MachinePulverizerBlockEntity(BlockPos pos, BlockState state) {

        super(MACHINE_PULVERIZER_TILE.get(), pos, state);

        inventory.addSlot(inputSlot, INPUT);
        inventory.addSlot(catalystSlot, CATALYST);
        inventory.addSlots(OUTPUT, 4);
        inventory.addSlot(chargeSlot, INTERNAL);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return PulverizerRecipeManager.instance().getBasePower();
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
            if (catalystSlot.getItemStack().isDamageableItem()) {
                if (catalystSlot.getItemStack().hurt(decrement, MathHelper.RANDOM, null)) {
                    catalystSlot.modify(-1);
                }
            } else {
                catalystSlot.modify(-decrement);
            }
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new MachinePulverizerMenu(i, level, worldPosition, inventory, player);
    }

    @Override
    protected Object getSound() {

        return new ConditionalSoundInstance(SOUND_MACHINE_PULVERIZER.get(), SoundSource.AMBIENT, this, () -> !remove && isActive);
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
    protected void setAttributesFromAugment(CompoundTag augmentData) {

        super.setAttributesFromAugment(augmentData);

        cyclicProcessingFeature |= getAttributeMod(augmentData, TAG_AUGMENT_FEATURE_CYCLE_PROCESS) > 0;
    }
    // endregion
}
