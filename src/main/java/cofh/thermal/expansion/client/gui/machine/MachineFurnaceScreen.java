package cofh.thermal.expansion.client.gui.machine;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.common.inventory.machine.MachineFurnaceMenu;
import cofh.thermal.lib.client.gui.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class MachineFurnaceScreen extends MachineScreen<MachineFurnaceMenu> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/furnace.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineFurnaceScreen(MachineFurnaceMenu container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_furnace");
        name = "furnace";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 53, 26, tile));

        addElement(createLargeOutputSlot(this, 116, 35, tile));

        addElement(ThermalGuiHelper.createDefaultProgress(this, 79, 35, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 53, 44, SCALE_FLAME, tile));
    }

}
