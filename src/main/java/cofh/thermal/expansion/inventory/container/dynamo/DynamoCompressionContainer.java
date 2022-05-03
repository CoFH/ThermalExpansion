package cofh.thermal.expansion.inventory.container.dynamo;

import cofh.core.inventory.container.TileContainer;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.tileentity.DynamoTileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_COMPRESSION_CONTAINER;

public class DynamoCompressionContainer extends TileContainer {

    public final DynamoTileBase tile;

    public DynamoCompressionContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(DYNAMO_COMPRESSION_CONTAINER, windowId, level, pos, inventory, player);
        this.tile = (DynamoTileBase) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        bindAugmentSlots(tileInv, 0, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
