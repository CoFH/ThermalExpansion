package cofh.thermal.expansion.inventory.container.dynamo;

import cofh.core.inventory.container.TileContainer;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.tileentity.DynamoTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_COMPRESSION_CONTAINER;

public class DynamoCompressionContainer extends TileContainer {

    public final DynamoTileBase tile;

    public DynamoCompressionContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(DYNAMO_COMPRESSION_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (DynamoTileBase) world.getTileEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        bindAugmentSlots(tileInv, 0, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
