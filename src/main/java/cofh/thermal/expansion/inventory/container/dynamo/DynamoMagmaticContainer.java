package cofh.thermal.expansion.inventory.container.dynamo;

import cofh.core.inventory.container.TileContainer;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.tileentity.DynamoTileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_MAGMATIC_CONTAINER;

public class DynamoMagmaticContainer extends TileContainer {

    public final DynamoTileBase tile;

    public DynamoMagmaticContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(DYNAMO_MAGMATIC_CONTAINER, windowId, level, pos, inventory, player);
        this.tile = (DynamoTileBase) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        bindAugmentSlots(tileInv, 0, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
