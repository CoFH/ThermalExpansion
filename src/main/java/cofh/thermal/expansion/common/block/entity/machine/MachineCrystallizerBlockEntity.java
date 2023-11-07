package cofh.thermal.expansion.common.block.entity.machine;

import cofh.core.util.helpers.FluidHelper;
import cofh.lib.common.fluid.FluidStorageCoFH;
import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.core.common.item.SlotSealItem;
import cofh.thermal.core.util.managers.machine.CrystallizerRecipeManager;
import cofh.thermal.expansion.common.inventory.machine.MachineCrystallizerMenu;
import cofh.thermal.lib.common.block.entity.MachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nullable;

import static cofh.core.util.helpers.ItemHelper.itemsEqual;
import static cofh.lib.api.StorageGroup.*;
import static cofh.lib.util.Constants.BUCKET_VOLUME;
import static cofh.lib.util.Constants.TANK_MEDIUM;
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.MACHINE_CRYSTALLIZER_TILE;

public class MachineCrystallizerBlockEntity extends MachineBlockEntity {

    protected ItemStorageCoFH[] inputSlots = new ItemStorageCoFH[2];
    protected ItemStorageCoFH outputSlot = new ItemStorageCoFH();
    protected FluidStorageCoFH inputTank = new FluidStorageCoFH(TANK_MEDIUM, fluid -> filter.valid(fluid) && CrystallizerRecipeManager.instance().validFluid(fluid));

    public MachineCrystallizerBlockEntity(BlockPos pos, BlockState state) {

        super(MACHINE_CRYSTALLIZER_TILE.get(), pos, state);

        inputSlots[0] = new ItemStorageCoFH(item -> item.getItem() instanceof SlotSealItem || filter.valid(item) && CrystallizerRecipeManager.instance().validItem(item) && !itemsEqual(item, inputSlots[1].getItemStack()));
        inputSlots[1] = new ItemStorageCoFH(item -> item.getItem() instanceof SlotSealItem || filter.valid(item) && CrystallizerRecipeManager.instance().validItem(item) && !itemsEqual(item, inputSlots[0].getItemStack()));

        for (int i = 0; i < 2; ++i) {
            inventory.addSlot(inputSlots[i], INPUT);
        }
        inventory.addSlot(outputSlot, OUTPUT);
        inventory.addSlot(chargeSlot, INTERNAL);

        tankInv.addTank(inputTank, INPUT);

        addAugmentSlots(ThermalCoreConfig.machineAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return CrystallizerRecipeManager.instance().getBasePower();
    }

    @Override
    protected boolean cacheRecipe() {

        curRecipe = CrystallizerRecipeManager.instance().getRecipe(this);
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
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new MachineCrystallizerMenu(i, level, worldPosition, inventory, player);
    }

    //    @Override
    //    protected Object getSound() {
    //
    //        return new ConditionalSoundInstance(SOUND_MACHINE_CRYSTALLIZER.get(), SoundSource.AMBIENT, this, () -> !remove && isActive);
    //    }

}
