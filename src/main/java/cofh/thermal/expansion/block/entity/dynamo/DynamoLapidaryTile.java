package cofh.thermal.expansion.block.entity.dynamo;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.dynamo.LapidaryFuelManager;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoLapidaryContainer;
import cofh.thermal.lib.tileentity.DynamoTileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import static cofh.lib.api.StorageGroup.INPUT;
import static cofh.thermal.expansion.init.TExpTileEntities.DYNAMO_LAPIDARY_TILE;

public class DynamoLapidaryTile extends DynamoTileBase {

    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(item -> filter.valid(item) && LapidaryFuelManager.instance().validFuel(item));

    public DynamoLapidaryTile(BlockPos pos, BlockState state) {

        super(DYNAMO_LAPIDARY_TILE.get(), pos, state);

        inventory.addSlot(fuelSlot, INPUT);

        addAugmentSlots(ThermalCoreConfig.dynamoAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return LapidaryFuelManager.instance().getBasePower();
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return LapidaryFuelManager.instance().getEnergy(fuelSlot.getItemStack()) > 0;
    }

    @Override
    protected void processStart() {

        fuel += fuelMax = Math.round(LapidaryFuelManager.instance().getEnergy(fuelSlot.getItemStack()) * energyMod);
        fuelSlot.consume(1);
    }
    // endregion

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new DynamoLapidaryContainer(i, level, worldPosition, inventory, player);
    }

}
