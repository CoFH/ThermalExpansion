package cofh.thermal.expansion.client.gui.machine;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.common.inventory.machine.MachineCrucibleMenu;
import cofh.thermal.lib.client.gui.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class MachineCrucibleScreen extends MachineScreen<MachineCrucibleMenu> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/crucible.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineCrucibleScreen(MachineCrucibleMenu container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_crucible");
        name = "crucible";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 53, 26, tile));

        addElement(setClearable(createMediumOutputFluidStorage(this, 125, 22, tile.getTank(0), tile), tile, 0));

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 84, 35, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 84, 35, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 53, 44, SCALE_FLAME, tile));
    }

}
