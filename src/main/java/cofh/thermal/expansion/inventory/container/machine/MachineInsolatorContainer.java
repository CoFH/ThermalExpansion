package cofh.thermal.expansion.inventory.container.machine;

import cofh.core.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.tileentity.ReconfigurableTile4Way;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static cofh.thermal.expansion.init.TExpContainers.MACHINE_INSOLATOR_CONTAINER;

public class MachineInsolatorContainer extends TileContainer {

    public final ReconfigurableTile4Way tile;

    public MachineInsolatorContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(MACHINE_INSOLATOR_CONTAINER.get(), windowId, level, pos, inventory, player);
        this.tile = (ReconfigurableTile4Way) level.getBlockEntity(pos);
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
