package cofh.thermal.expansion.inventory.container.machine;

import cofh.core.inventory.container.TileContainer;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.thermal.lib.tileentity.ReconfigurableTile4Way;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_BREWER_CONTAINER;

public class MachineBrewerContainer extends TileContainer {

    public final ReconfigurableTile4Way tile;

    public MachineBrewerContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(MACHINE_BREWER_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (ReconfigurableTile4Way) world.getTileEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());

        addSlot(new SlotCoFH(tileInv, 0, 62, 26));

        addSlot(new SlotCoFH(tileInv, 1, 8, 53));

        bindAugmentSlots(tileInv, 2, this.tile.augSize());
        bindPlayerInventory(inventory);
    }

}
