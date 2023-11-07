package cofh.thermal.expansion.client.gui.machine;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.common.inventory.machine.MachineSmelterMenu;
import cofh.thermal.lib.client.gui.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class MachineSmelterScreen extends MachineScreen<MachineSmelterMenu> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/smelter.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineSmelterScreen(MachineSmelterMenu container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_smelter");
        name = "smelter";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 35, 17, tile));
        addElement(createInputSlot(this, 53, 17, tile));
        addElement(createInputSlot(this, 71, 17, tile));

        addElement(createInputSlot(this, 53, 53, tile));

        addElement(createOutputSlot(this, 125, 26, tile));
        addElement(createOutputSlot(this, 143, 26, tile));
        addElement(createOutputSlot(this, 125, 44, tile));
        addElement(createOutputSlot(this, 143, 44, tile));

        addElement(ThermalGuiHelper.createDefaultProgress(this, 94, 35, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 53, 35, SCALE_FLAME, tile));
    }

}
