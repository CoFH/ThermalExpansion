package cofh.thermal.expansion.common.block.entity.dynamo;

import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.dynamo.StirlingFuelManager;
import cofh.thermal.expansion.common.inventory.dynamo.DynamoStirlingContainer;
import cofh.thermal.lib.common.block.entity.DynamoBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import static cofh.lib.api.StorageGroup.INPUT;
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.DYNAMO_STIRLING_TILE;

public class DynamoStirlingTile extends DynamoBlockEntity {

    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(item -> filter.valid(item) && StirlingFuelManager.instance().validFuel(item));

    public DynamoStirlingTile(BlockPos pos, BlockState state) {

        super(DYNAMO_STIRLING_TILE.get(), pos, state);

        inventory.addSlot(fuelSlot, INPUT);

        addAugmentSlots(ThermalCoreConfig.dynamoAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return StirlingFuelManager.instance().getBasePower();
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return StirlingFuelManager.instance().getEnergy(fuelSlot.getItemStack()) > 0;
    }

    @Override
    protected void processStart() {

        fuel += fuelMax = Math.round(StirlingFuelManager.instance().getEnergy(fuelSlot.getItemStack()) * energyMod);
        fuelSlot.consume(1);
    }
    // endregion

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new DynamoStirlingContainer(i, level, worldPosition, inventory, player);
    }

}
