package cofh.thermal.expansion.client.gui.machine;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.common.inventory.machine.MachineInsolatorMenu;
import cofh.thermal.lib.client.gui.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class MachineInsolatorScreen extends MachineScreen<MachineInsolatorMenu> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/insolator.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineInsolatorScreen(MachineInsolatorMenu container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
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

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 85, 35, PROG_ARROW_FLUID_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 85, 35, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 62, 35, SCALE_SUN, tile));
    }

}
