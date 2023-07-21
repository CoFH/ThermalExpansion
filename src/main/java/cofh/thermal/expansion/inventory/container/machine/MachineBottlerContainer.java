package cofh.thermal.expansion.inventory.container.machine;

import cofh.core.inventory.container.TileCoFHContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.block.entity.Reconfigurable4WayBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static cofh.thermal.expansion.init.TExpContainers.MACHINE_BOTTLER_CONTAINER;

public class MachineBottlerContainer extends TileCoFHContainer {

    public final Reconfigurable4WayBlockEntity tile;

    public MachineBottlerContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(MACHINE_BOTTLER_CONTAINER.get(), windowId, level, pos, inventory, player);
        this.tile = (Reconfigurable4WayBlockEntity) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 62, 26));

        addSlot(new SlotRemoveOnly(tileInv, 1, 125, 35));

        addSlot(new SlotCoFH(tileInv, 2, 8, 53));

        bindAugmentSlots(tileInv, 3, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
