package cofh.thermal.expansion.common.inventory.dynamo;

import cofh.core.common.inventory.TileCoFHContainer;
import cofh.lib.common.inventory.SlotCoFH;
import cofh.lib.common.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.common.block.entity.DynamoBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static cofh.thermal.expansion.init.registries.TExpContainers.DYNAMO_LAPIDARY_CONTAINER;

public class DynamoLapidaryContainer extends TileCoFHContainer {

    public final DynamoBlockEntity tile;

    public DynamoLapidaryContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(DYNAMO_LAPIDARY_CONTAINER.get(), windowId, level, pos, inventory, player);
        this.tile = (DynamoBlockEntity) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 44, 35));

        bindAugmentSlots(tileInv, 1, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
