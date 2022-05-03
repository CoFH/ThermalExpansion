package cofh.thermal.expansion.inventory.container.machine;

import cofh.core.inventory.container.TileContainer;
import cofh.core.network.packet.server.TileConfigPacket;
import cofh.lib.inventory.container.slot.SlotCoFH;
import cofh.lib.inventory.container.slot.SlotFalseCopy;
import cofh.lib.inventory.container.slot.SlotRemoveOnly;
import cofh.lib.inventory.wrapper.InvWrapperCoFH;
import cofh.lib.util.Utils;
import cofh.thermal.expansion.tileentity.machine.MachineCrafterTile;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Optional;

import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CRAFTER_CONTAINER;

public class MachineCrafterContainer extends TileContainer {

    public final MachineCrafterTile tile;
    private final CraftingContainer craftMatrix = new CraftingContainer(this, 3, 3);
    private final ResultContainer craftResult = new ResultContainer();
    private final Player player;

    private final boolean initialized;

    public MachineCrafterContainer(int windowId, Level level, BlockPos pos, Inventory inventory, Player player) {

        super(MACHINE_CRAFTER_CONTAINER, windowId, level, pos, inventory, player);
        this.tile = (MachineCrafterTile) level.getBlockEntity(pos);
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
        addSlot(new ResultSlot(player, craftMatrix, craftResult, 0, 114, 44) {

            @Override
            public boolean mayPickup(Player player) {

                return false;
            }
        });

        bindAugmentSlots(tileInv, 21, this.tile.augSize());
        bindPlayerInventory(inventory);

        if (Utils.isServerWorld(level)) {
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
    public void removed(Player playerIn) {

        super.removed(playerIn);
        tile.clearRecipeChanges();
    }

    @Override
    public void slotsChanged(Container inventoryIn) {

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
        Level level = tile.getLevel();
        if (Utils.isServerWorld(level)) {
            ServerPlayer playerMP = (ServerPlayer) player;
            ItemStack stack = ItemStack.EMPTY;
            Optional<CraftingRecipe> possibleRecipe = level.getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftMatrix, level);
            if (possibleRecipe.isPresent()) {
                stack = possibleRecipe.get().assemble(craftMatrix);
                craftResult.setRecipeUsed(craftResult.getRecipeUsed());
            }
            tile.markRecipeChanges();
            craftResult.setItem(0, stack);
            playerMP.connection.send(new ClientboundContainerSetSlotPacket(this.containerId, this.incrementStateId(), 20, stack));
        } else {
            calcCraftingGrid();
            tile.markRecipeChanges();
        }
    }

    protected void calcCraftingGrid() {

        Level level = tile.getLevel();
        ItemStack stack = ItemStack.EMPTY;
        if (level != null) {
            Optional<CraftingRecipe> possibleRecipe = level.getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftMatrix, level);
            if (possibleRecipe.isPresent()) {
                craftResult.setRecipeUsed(possibleRecipe.get());
                stack = possibleRecipe.get().assemble(craftMatrix);
            }
        }
        craftResult.setItem(0, stack);
    }

}
