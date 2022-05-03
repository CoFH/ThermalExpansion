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

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_SMELTER_CONTAINER;

public class MachineSmelterContainer extends TileContainer {

    public final ReconfigurableTile4Way tile;

    public MachineSmelterContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(MACHINE_SMELTER_CONTAINER, windowId, level, pos, inventory, player);
        this.tile = (ReconfigurableTile4Way) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 53, 17));
        addSlot(new SlotCoFH(tileInv, 1, 35, 17));
        addSlot(new SlotCoFH(tileInv, 2, 71, 17));

        addSlot(new SlotCoFH(tileInv, 3, 53, 53));

        addSlot(new SlotRemoveOnly(tileInv, 4, 125, 26));
        addSlot(new SlotRemoveOnly(tileInv, 5, 143, 26));
        addSlot(new SlotRemoveOnly(tileInv, 6, 125, 44));
        addSlot(new SlotRemoveOnly(tileInv, 7, 143, 44));

        addSlot(new SlotCoFH(tileInv, 8, 8, 53));

        bindAugmentSlots(tileInv, 9, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
