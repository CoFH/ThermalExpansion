package cofh.thermal.expansion.inventory.container.dynamo;

import cofh.core.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.tileentity.DynamoTileBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_NUMISMATIC_CONTAINER;

public class DynamoNumismaticContainer extends TileContainer {

    public final DynamoTileBase tile;

    public DynamoNumismaticContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(DYNAMO_NUMISMATIC_CONTAINER, windowId, level, pos, inventory, player);
        this.tile = (DynamoTileBase) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 44, 35));

        bindAugmentSlots(tileInv, 1, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
