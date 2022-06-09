package cofh.thermal.expansion.block.entity.machine;

import cofh.lib.client.audio.ConditionalSound;
import cofh.lib.inventory.ItemStorageCoFH;
import cofh.lib.util.helpers.MathHelper;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.item.SlotSealItem;
import cofh.thermal.core.util.managers.machine.PulverizerRecipeManager;
import cofh.thermal.expansion.inventory.container.machine.MachinePulverizerContainer;
import cofh.thermal.lib.tileentity.MachineTileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.*;
import static cofh.lib.util.constants.NBTTags.TAG_AUGMENT_FEATURE_CYCLE_PROCESS;
import static cofh.lib.util.helpers.AugmentableHelper.getAttributeMod;
import static cofh.lib.util.helpers.ItemHelper.itemsEqualWithTags;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PULVERIZER_TILE;
import static cofh.thermal.expansion.init.TExpSounds.SOUND_MACHINE_PULVERIZER;

public class MachinePulverizerTile extends MachineTileBase {

    protected ItemStorageCoFH inputSlot = new ItemStorageCoFH(item -> filter.valid(item) && PulverizerRecipeManager.instance().validRecipe(item));
    protected ItemStorageCoFH catalystSlot = new ItemStorageCoFH(item -> item.getItem() instanceof SlotSealItem || PulverizerRecipeManager.instance().validCatalyst(item));

    public MachinePulverizerTile(BlockPos pos, BlockState state) {

        super(MACHINE_PULVERIZER_TILE, pos, state);

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

        return new MachinePulverizerContainer(i, level, worldPosition, inventory, player);
    }

    @Override
    protected Object getSound() {

        return new ConditionalSound(SOUND_MACHINE_PULVERIZER, SoundSource.AMBIENT, this, () -> !remove && isActive);
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
