package cofh.thermal.expansion.client.gui.machine;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachineSawmillContainer;
import cofh.thermal.lib.client.gui.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class MachineSawmillScreen extends MachineScreen<MachineSawmillContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/sawmill.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineSawmillScreen(MachineSawmillContainer container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_sawmill");
        name = "sawmill";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 44, 26, tile));

        addElement(createOutputSlot(this, 107, 26, tile));
        addElement(createOutputSlot(this, 125, 26, tile));
        addElement(createOutputSlot(this, 107, 44, tile));
        addElement(createOutputSlot(this, 125, 44, tile));

        addElement(ThermalGuiHelper.createDefaultProgress(this, 72, 35, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 44, 44, SCALE_SAW, tile));
    }

}
