package cofh.thermal.expansion.client.gui.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachineInsolatorContainer;
import cofh.thermal.lib.client.gui.MachineScreenReconfigurable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineInsolatorScreen extends MachineScreenReconfigurable<MachineInsolatorContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machines/insolator.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineInsolatorScreen(MachineInsolatorContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_insolator"));
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_insolator");
        name = "insolator";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 62, 17, tile));

        addElement(createInputSlot(this, 62, 53, tile));

        addElement(createOutputSlot(this, 116, 26, tile));
        addElement(createOutputSlot(this, 134, 26, tile));
        addElement(createOutputSlot(this, 116, 44, tile));
        addElement(createOutputSlot(this, 134, 44, tile));

        addElement(setClearable(createMediumInputFluidStorage(this, 34, 22, tile.getTank(0), tile), tile, 0));

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 85, 34, PROG_ARROW_FLUID_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 85, 34, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 62, 35, SCALE_SUN, tile));
    }

}
