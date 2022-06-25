package cofh.thermal.expansion.client.gui.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachinePulverizerContainer;
import cofh.thermal.lib.client.gui.MachineScreenReconfigurable;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachinePulverizerScreen extends MachineScreenReconfigurable<MachinePulverizerContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/pulverizer.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachinePulverizerScreen(MachinePulverizerContainer container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_pulverizer"));
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_pulverizer");
        name = "pulverizer";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 44, 17, tile));

        addElement(createInputSlot(this, 44, 53, tile));

        addElement(createOutputSlot(this, 107, 26, tile));
        addElement(createOutputSlot(this, 125, 26, tile));
        addElement(createOutputSlot(this, 107, 44, tile));
        addElement(createOutputSlot(this, 125, 44, tile));

        addElement(ThermalGuiHelper.createDefaultProgress(this, 72, 35, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 44, 35, SCALE_CRUSH, tile));
    }

}
