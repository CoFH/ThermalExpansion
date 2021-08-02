package cofh.thermal.expansion.tileentity.dynamo;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.util.managers.dynamo.NumismaticFuelManager;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoNumismaticContainer;
import cofh.thermal.lib.tileentity.DynamoTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_NUMISMATIC_TILE;
import static cofh.thermal.lib.common.ThermalConfig.dynamoAugments;

public class DynamoNumismaticTile extends DynamoTileBase {

    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(item -> filter.valid(item) && NumismaticFuelManager.instance().validFuel(item));

    public DynamoNumismaticTile() {

        super(DYNAMO_NUMISMATIC_TILE);

        inventory.addSlot(fuelSlot, INPUT);

        addAugmentSlots(dynamoAugments);
        initHandlers();
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return NumismaticFuelManager.instance().getEnergy(fuelSlot.getItemStack()) > 0;
    }

    @Override
    protected void processStart() {

        fuel += fuelMax = Math.round(NumismaticFuelManager.instance().getEnergy(fuelSlot.getItemStack()) * energyMod);
        fuelSlot.consume(1);
    }
    // endregion

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DynamoNumismaticContainer(i, world, pos, inventory, player);
    }

}
