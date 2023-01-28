package cofh.thermal.expansion.client.gui.machine;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachineCentrifugeContainer;
import cofh.thermal.lib.client.gui.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class MachineCentrifugeScreen extends MachineScreen<MachineCentrifugeContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/centrifuge.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineCentrifugeScreen(MachineCentrifugeContainer container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_centrifuge");
        name = "centrifuge";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 44, 26, tile));

        addElement(createOutputSlot(this, 107, 26, tile));
        addElement(createOutputSlot(this, 125, 26, tile));
        addElement(createOutputSlot(this, 107, 44, tile));
        addElement(createOutputSlot(this, 125, 44, tile));

        addElement(setClearable(createMediumOutputFluidStorage(this, 151, 22, tile.getTank(0), tile), tile, 0));

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 72, 35, PROG_ARROW_FLUID_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 72, 35, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 44, 44, SCALE_SPIN, tile));
    }

}
