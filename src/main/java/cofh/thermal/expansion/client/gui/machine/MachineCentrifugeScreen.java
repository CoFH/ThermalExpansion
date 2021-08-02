package cofh.thermal.expansion.client.gui.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachineCentrifugeContainer;
import cofh.thermal.lib.client.gui.MachineScreenReconfigurable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineCentrifugeScreen extends MachineScreenReconfigurable<MachineCentrifugeContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machines/centrifuge.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineCentrifugeScreen(MachineCentrifugeContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_centrifuge"));
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

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 72, 34, PROG_ARROW_FLUID_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 72, 34, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 44, 44, SCALE_SPIN, tile));
    }

}
