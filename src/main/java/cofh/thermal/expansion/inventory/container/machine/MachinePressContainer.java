package cofh.thermal.expansion.inventory.container.machine;

import cofh.core.content.inventory.container.TileContainer;
import cofh.lib.content.inventory.container.slot.SlotCoFH;
import cofh.lib.content.inventory.container.slot.SlotRemoveOnly;
import cofh.lib.content.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.tileentity.ReconfigurableTile4Way;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import static cofh.thermal.expansion.init.TExpContainers.MACHINE_PRESS_CONTAINER;

public class MachinePressContainer extends TileContainer {

    public final ReconfigurableTile4Way tile;

    public MachinePressContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(MACHINE_PRESS_CONTAINER.get(), windowId, level, pos, inventory, player);
        this.tile = (ReconfigurableTile4Way) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 53, 17));

        addSlot(new SlotCoFH(tileInv, 1, 53, 53));

        addSlot(new SlotRemoveOnly(tileInv, 2, 116, 35));

        addSlot(new SlotCoFH(tileInv, 3, 8, 53));

        bindAugmentSlots(tileInv, 4, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
