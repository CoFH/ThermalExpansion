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

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CRYSTALLIZER_CONTAINER;

public class MachineCrystallizerContainer extends TileContainer {

    public final ReconfigurableTile4Way tile;

    public MachineCrystallizerContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(MACHINE_CRYSTALLIZER_CONTAINER, windowId, level, pos, inventory, player);
        this.tile = (ReconfigurableTile4Way) level.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 62, 26));
        addSlot(new SlotCoFH(tileInv, 1, 80, 26));

        addSlot(new SlotRemoveOnly(tileInv, 2, 143, 35));

        addSlot(new SlotCoFH(tileInv, 3, 8, 53));

        bindAugmentSlots(tileInv, 4, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
