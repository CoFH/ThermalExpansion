package cofh.thermal.expansion.tileentity.dynamo;

import cofh.lib.inventory.ItemStorageCoFH;
import cofh.thermal.core.util.managers.dynamo.DisenchantmentFuelManager;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoDisenchantmentContainer;
import cofh.thermal.lib.tileentity.DynamoTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

import javax.annotation.Nullable;

import static cofh.lib.util.StorageGroup.INPUT;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_DISENCHANTMENT_TILE;
import static cofh.thermal.lib.common.ThermalConfig.dynamoAugments;

public class DynamoDisenchantmentTile extends DynamoTileBase {

    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(item -> filter.valid(item) && DisenchantmentFuelManager.instance().validFuel(item));

    public DynamoDisenchantmentTile() {

        super(DYNAMO_DISENCHANTMENT_TILE);

        inventory.addSlot(fuelSlot, INPUT);
        addAugmentSlots(dynamoAugments);
        initHandlers();
    }

    @Override
    protected int getBaseProcessTick() {

        return DisenchantmentFuelManager.instance().getBasePower();
    }

    // region PROCESS
    @Override
    protected boolean canProcessStart() {

        return DisenchantmentFuelManager.instance().getEnergy(fuelSlot.getItemStack()) > 0;
    }

    @Override
    protected void processStart() {

        fuel += fuelMax = Math.round(DisenchantmentFuelManager.instance().getEnergy(fuelSlot.getItemStack()) * energyMod);
        fuelSlot.consume(1);
    }
    // endregion

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity player) {

        return new DynamoDisenchantmentContainer(i, level, worldPosition, inventory, player);
    }

}
