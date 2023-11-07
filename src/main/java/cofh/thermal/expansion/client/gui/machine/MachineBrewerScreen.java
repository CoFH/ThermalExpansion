package cofh.thermal.expansion.client.gui.machine;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.common.inventory.machine.MachineBrewerMenu;
import cofh.thermal.lib.client.gui.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class MachineBrewerScreen extends MachineScreen<MachineBrewerMenu> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/brewer.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineBrewerScreen(MachineBrewerMenu container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_brewer");
        name = "brewer";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 62, 26, tile));

        addElement(setClearable(createMediumInputFluidStorage(this, 34, 22, tile.getTank(0), tile), tile, 0));

        addElement(setClearable(createMediumOutputFluidStorage(this, 125, 22, tile.getTank(1), tile), tile, 1));

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 88, 35, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 88, 35, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 62, 44, SCALE_ALCHEMY, tile));
    }

}
