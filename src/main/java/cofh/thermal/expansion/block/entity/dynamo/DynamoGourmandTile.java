package cofh.thermal.expansion.block.entity.dynamo;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.dynamo.GourmandFuelManager;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoGourmandContainer;
import cofh.thermal.lib.tileentity.DynamoTileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_GOURMAND_TILE;

public class DynamoGourmandTile extends DynamoTileBase {

    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(item -> filter.valid(item) && GourmandFuelManager.instance().validFuel(item));

    public DynamoGourmandTile(BlockPos pos, BlockState state) {

        super(DYNAMO_GOURMAND_TILE, pos, state);

        inventory.addSlot(fuelSlot, INPUT);
        addAugmentSlots(ThermalCoreConfig.dynamoAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return GourmandFuelManager.instance().getBasePower();
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return GourmandFuelManager.instance().getEnergy(fuelSlot.getItemStack()) > 0;
    }

    @Override
    protected void processStart() {

        fuel += fuelMax = Math.round(GourmandFuelManager.instance().getEnergy(fuelSlot.getItemStack()) * energyMod);
        fuelSlot.consume(1);
    }
    // endregion

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new DynamoGourmandContainer(i, level, worldPosition, inventory, player);
    }

}
