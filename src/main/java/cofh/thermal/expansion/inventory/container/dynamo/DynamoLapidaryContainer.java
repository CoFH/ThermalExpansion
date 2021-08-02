package cofh.thermal.expansion.inventory.container.dynamo;

import cofh.core.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.tileentity.DynamoTileBase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_LAPIDARY_CONTAINER;

public class DynamoLapidaryContainer extends TileContainer {

    public final DynamoTileBase tile;

    public DynamoLapidaryContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(DYNAMO_LAPIDARY_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (DynamoTileBase) world.getTileEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 44, 35));

        bindAugmentSlots(tileInv, 1, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
