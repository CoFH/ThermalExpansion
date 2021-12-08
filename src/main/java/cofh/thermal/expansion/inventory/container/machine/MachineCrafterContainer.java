package cofh.thermal.expansion.inventory.container.machine;

import cofh.core.inventory.container.TileContainer;
import cofh.core.network.packet.server.TileConfigPacket;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.container.slot.SlotFalseCopy;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.lib.util.Utils;
import cofh.thermal.expansion.tileentity.machine.MachineCrafterTile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CRAFTER_CONTAINER;

public class MachineCrafterContainer extends TileContainer {

    public final MachineCrafterTile tile;
    private final CraftingInventory craftMatrix = new CraftingInventory(this, 3, 3);
    private final CraftResultInventory craftResult = new CraftResultInventory();
    private final PlayerEntity player;

    private final boolean initialized;

    public MachineCrafterContainer(int windowId, World world, BlockPos pos, PlayerInventory inventory, PlayerEntity player) {

        super(MACHINE_CRAFTER_CONTAINER, windowId, world, pos, inventory, player);
        this.tile = (MachineCrafterTile) world.getBlockEntity(pos);
        InvWrapperCoFH tileInv = new InvWrapperCoFH(this.tile.getItemInv());
        this.player = inventory.player;

        for (int i = 0; i < 9; ++i) {
            addSlot(new SlotCoFH(tileInv, i, 8 + i * 18, 77));
        }
        addSlot(new SlotRemoveOnly(tileInv, 9, 143, 21));

        addSlot(new SlotCoFH(tileInv, 10, 8, 53));

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                addSlot(new SlotFalseCopy(craftMatrix, j + i * 3, 53 + j * 18, 17 + i * 18));
            }
        }
        addSlot(new CraftingResultSlot(player, craftMatrix, craftResult, 0, 114, 44) {

            @Override
            public boolean mayPickup(PlayerEntity player) {

                return false;
            }
        });

        bindAugmentSlots(tileInv, 21, this.tile.augSize());
        bindPlayerInventory(inventory);

        if (Utils.isServerWorld(world)) {
            for (int i = 0; i < 9; ++i) {
                craftMatrix.setItem(i, tile.getItemInv().get(MachineCrafterTile.SLOT_CRAFTING_START + i));
            }
            calcCraftingGrid();
        }
        initialized = true;
    }

    @Override
    protected int getPlayerInventoryVerticalOffset() {

        return 108;
    }

    @Override
    public void removed(PlayerEntity playerIn) {

        super.removed(playerIn);
        tile.clearRecipeChanges();
    }

    @Override
    public void slotsChanged(IInventory inventoryIn) {

        super.slotsChanged(inventoryIn);
        slotChangedCraftingGrid();
    }

    public boolean hasValidRecipe() {

        return !craftResult.getItem(0).isEmpty();
    }

    public void setRecipe() {

        for (int i = 0; i < craftMatrix.getContainerSize(); ++i) {
            tile.getItemInv().set(MachineCrafterTile.SLOT_CRAFTING_START + i, craftMatrix.getItem(i));
        }
        TileConfigPacket.sendToServer(tile);
        tile.clearRecipeChanges();
    }

    protected void slotChangedCraftingGrid() {

        if (syncing || !initialized) {
            return;
        }
        World world = tile.getLevel();
        if (Utils.isServerWorld(world)) {
            ServerPlayerEntity playerMP = (ServerPlayerEntity) player;
            ItemStack stack = ItemStack.EMPTY;
            Optional<ICraftingRecipe> possibleRecipe = world.getRecipeManager().getRecipeFor(IRecipeType.CRAFTING, craftMatrix, world);
            if (possibleRecipe.isPresent()) {
                stack = possibleRecipe.get().assemble(craftMatrix);
                craftResult.setRecipeUsed(craftResult.getRecipeUsed());
            }
            tile.markRecipeChanges();
            craftResult.setItem(0, stack);
            playerMP.connection.send(new SSetSlotPacket(this.containerId, 20, stack));
        } else {
            calcCraftingGrid();
            tile.markRecipeChanges();
        }
    }

    protected void calcCraftingGrid() {

        World world = tile.getLevel();
        ItemStack stack = ItemStack.EMPTY;
        if (world != null) {
            Optional<ICraftingRecipe> possibleRecipe = world.getRecipeManager().getRecipeFor(IRecipeType.CRAFTING, craftMatrix, world);
            if (possibleRecipe.isPresent()) {
                craftResult.setRecipeUsed(possibleRecipe.get());
                stack = possibleRecipe.get().assemble(craftMatrix);
            }
        }
        craftResult.setItem(0, stack);
    }

}
