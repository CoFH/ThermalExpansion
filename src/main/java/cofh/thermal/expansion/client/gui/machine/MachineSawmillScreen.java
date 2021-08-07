package cofh.thermal.expansion.client.gui.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachineSawmillContainer;
import cofh.thermal.lib.client.gui.MachineScreenReconfigurable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineSawmillScreen extends MachineScreenReconfigurable<MachineSawmillContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/sawmill.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineSawmillScreen(MachineSawmillContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_sawmill"));
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

        addElement(ThermalGuiHelper.createDefaultProgress(this, 72, 34, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 44, 44, SCALE_SAW, tile));
    }

}
