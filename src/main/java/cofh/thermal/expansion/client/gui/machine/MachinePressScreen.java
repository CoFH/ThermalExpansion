package cofh.thermal.expansion.client.gui.machine;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.common.inventory.machine.MachinePressMenu;
import cofh.thermal.lib.client.gui.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class MachinePressScreen extends MachineScreen<MachinePressMenu> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/press.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachinePressScreen(MachinePressMenu container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_press");
        name = "press";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 53, 17, tile));

        addElement(createInputSlot(this, 53, 53, tile));

        addElement(createLargeOutputSlot(this, 116, 35, tile));

        addElement(setClearable(createMediumOutputFluidStorage(this, 151, 22, tile.getTank(0), tile), tile, 0));

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 79, 35, PROG_ARROW_FLUID_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 79, 35, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 53, 35, SCALE_COMPACT, tile));
    }

}
