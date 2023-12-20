package cofh.thermal.expansion.common.block.entity.dynamo;

import cofh.core.common.network.packet.client.TileStatePacket;
import cofh.core.util.helpers.FluidHelper;
import cofh.lib.common.fluid.FluidStorageCoFH;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.dynamo.MagmaticFuelManager;
import cofh.thermal.expansion.common.inventory.dynamo.DynamoMagmaticMenu;
import cofh.thermal.lib.common.block.entity.DynamoBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cofh.core.client.renderer.model.ModelUtils.FLUID;
import static cofh.lib.api.StorageGroup.INPUT;
import static cofh.lib.util.Constants.BUCKET_VOLUME;
import static cofh.lib.util.Constants.TANK_SMALL;
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.DYNAMO_MAGMATIC_TILE;
import static cofh.thermal.lib.util.managers.SingleFluidFuelManager.FLUID_FUEL_AMOUNT;

public class DynamoMagmaticBlockEntity extends DynamoBlockEntity {

    protected FluidStorageCoFH fuelTank = new FluidStorageCoFH(TANK_SMALL, fluid -> filter.valid(fluid) && MagmaticFuelManager.instance().validFuel(fluid));

    public DynamoMagmaticBlockEntity(BlockPos pos, BlockState state) {

        super(DYNAMO_MAGMATIC_TILE.get(), pos, state);

        tankInv.addTank(fuelTank, INPUT);

        renderFluid = new FluidStack(Fluids.LAVA, BUCKET_VOLUME);

        addAugmentSlots(ThermalCoreConfig.dynamoAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return MagmaticFuelManager.instance().getBasePower();
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return MagmaticFuelManager.instance().getEnergy(fuelTank.getFluidStack()) > 0 && fuelTank.getAmount() >= FLUID_FUEL_AMOUNT;
    }

    @Override
    protected void processStart() {

        if (cacheRenderFluid()) {
            TileStatePacket.sendToClient(this);
        }
        int fuelVal = Math.round(MagmaticFuelManager.instance().getEnergy(fuelTank.getFluidStack()) * energyMod);
        processTick = Math.min(baseProcessTick, fuelVal);
        fuel += fuelMax = fuelVal;
        fuelTank.modify(-FLUID_FUEL_AMOUNT);
    }

    @Override
    protected boolean cacheRenderFluid() {

        FluidStack prevFluid = renderFluid;
        renderFluid = new FluidStack(fuelTank.getFluidStack(), BUCKET_VOLUME);
        return !FluidHelper.fluidsEqual(renderFluid, prevFluid);
    }
    // endregion

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new DynamoMagmaticMenu(i, level, worldPosition, inventory, player);
    }

    @Nonnull
    @Override
    public ModelData getModelData() {

        return ModelData.builder()
                .with(FLUID, renderFluid)
                .build();
    }

}
