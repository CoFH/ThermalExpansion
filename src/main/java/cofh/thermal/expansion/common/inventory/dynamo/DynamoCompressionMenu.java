package cofh.thermal.expansion.common.inventory.dynamo;

import cofh.core.common.inventory.BlockEntityCoFHMenu;
import cofh.lib.common.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.common.block.entity.DynamoBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static cofh.thermal.expansion.init.registries.TExpContainers.DYNAMO_COMPRESSION_CONTAINER;

public class DynamoCompressionMenu extends BlockEntityCoFHMenu {

    public final DynamoBlockEntity tile;

    public DynamoCompressionMenu(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(DYNAMO_COMPRESSION_CONTAINER.get(), windowId, level, pos, inventory, player);
        this.tile = (DynamoBlockEntity) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        bindAugmentSlots(tileInv, 0, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
