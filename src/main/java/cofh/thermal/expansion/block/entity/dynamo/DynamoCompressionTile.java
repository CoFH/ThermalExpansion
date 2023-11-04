package cofh.thermal.expansion.block.entity.dynamo;

import cofh.core.network.packet.client.TileStatePacket;
import cofh.core.util.helpers.FluidHelper;
import cofh.lib.fluid.FluidStorageCoFH;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.dynamo.CompressionFuelManager;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoCompressionContainer;
import cofh.thermal.lib.block.entity.DynamoBlockEntity;
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
import static cofh.thermal.expansion.init.TExpBlockEntities.DYNAMO_COMPRESSION_TILE;
import static cofh.thermal.lib.util.managers.SingleFluidFuelManager.FLUID_FUEL_AMOUNT;

public class DynamoCompressionTile extends DynamoBlockEntity {

    protected FluidStorageCoFH fuelTank = new FluidStorageCoFH(TANK_SMALL, fluid -> filter.valid(fluid) && CompressionFuelManager.instance().validFuel(fluid));

    public DynamoCompressionTile(BlockPos pos, BlockState state) {

        super(DYNAMO_COMPRESSION_TILE.get(), pos, state);

        tankInv.addTank(fuelTank, INPUT);

        renderFluid = new FluidStack(Fluids.WATER, BUCKET_VOLUME);

        addAugmentSlots(ThermalCoreConfig.dynamoAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return CompressionFuelManager.instance().getBasePower();
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return CompressionFuelManager.instance().getEnergy(fuelTank.getFluidStack()) > 0 && fuelTank.getAmount() >= FLUID_FUEL_AMOUNT;
    }

    @Override
    protected void processStart() {

        if (cacheRenderFluid()) {
            TileStatePacket.sendToClient(this);
        }
        fuel += fuelMax = Math.round(CompressionFuelManager.instance().getEnergy(fuelTank.getFluidStack()) * energyMod);
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

        return new DynamoCompressionContainer(i, level, worldPosition, inventory, player);
    }

    @Nonnull
    @Override
    public ModelData getModelData() {

        return ModelData.builder()
                .with(FLUID, renderFluid)
                .build();
    }

}
