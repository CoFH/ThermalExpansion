package cofh.thermal.expansion.common.block.entity.dynamo;

import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.core.util.managers.dynamo.DisenchantmentFuelManager;
import cofh.thermal.expansion.common.inventory.dynamo.DynamoDisenchantmentMenu;
import cofh.thermal.lib.common.block.entity.DynamoBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

import static cofh.lib.api.StorageGroup.INPUT;
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.DYNAMO_DISENCHANTMENT_TILE;

public class DynamoDisenchantmentBlockEntity extends DynamoBlockEntity {

    protected ItemStorageCoFH fuelSlot = new ItemStorageCoFH(item -> filter.valid(item) && DisenchantmentFuelManager.instance().validFuel(item));

    public DynamoDisenchantmentBlockEntity(BlockPos pos, BlockState state) {

        super(DYNAMO_DISENCHANTMENT_TILE.get(), pos, state);

        inventory.addSlot(fuelSlot, INPUT);
        addAugmentSlots(ThermalCoreConfig.dynamoAugments);
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

        int fuelVal = Math.round(DisenchantmentFuelManager.instance().getEnergy(fuelSlot.getItemStack()) * energyMod);
        processTick = Math.min(baseProcessTick, fuelVal);
        fuel += fuelMax = fuelVal;
        fuelSlot.consume(1);
    }
    // endregion

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {

        return new DynamoDisenchantmentMenu(i, level, worldPosition, inventory, player);
    }

}
