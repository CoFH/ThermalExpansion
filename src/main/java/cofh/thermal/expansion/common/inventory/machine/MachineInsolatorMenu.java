package cofh.thermal.expansion.common.inventory.machine;

import cofh.core.common.inventory.BlockEntityCoFHMenu;
import cofh.lib.common.inventory.SlotCoFH;
import cofh.lib.common.inventory.SlotRemoveOnly;
import cofh.lib.common.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.common.block.entity.Reconfigurable4WayBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static cofh.thermal.expansion.init.registries.TExpContainers.MACHINE_INSOLATOR_CONTAINER;

public class MachineInsolatorMenu extends BlockEntityCoFHMenu {

    public final Reconfigurable4WayBlockEntity tile;

    public MachineInsolatorMenu(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(MACHINE_INSOLATOR_CONTAINER.get(), windowId, level, pos, inventory, player);
        this.tile = (Reconfigurable4WayBlockEntity) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 62, 17));

        addSlot(new SlotCoFH(tileInv, 1, 62, 53));

        addSlot(new SlotRemoveOnly(tileInv, 2, 116, 26));
        addSlot(new SlotRemoveOnly(tileInv, 3, 134, 26));
        addSlot(new SlotRemoveOnly(tileInv, 4, 116, 44));
        addSlot(new SlotRemoveOnly(tileInv, 5, 134, 44));

        addSlot(new SlotCoFH(tileInv, 6, 8, 53));

        bindAugmentSlots(tileInv, 7, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
