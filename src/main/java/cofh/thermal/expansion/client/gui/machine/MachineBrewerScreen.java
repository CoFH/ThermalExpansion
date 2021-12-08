package cofh.thermal.expansion.client.gui.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachineBrewerContainer;
import cofh.thermal.lib.client.gui.MachineScreenReconfigurable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineBrewerScreen extends MachineScreenReconfigurable<MachineBrewerContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/brewer.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineBrewerScreen(MachineBrewerContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_brewer"));
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

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 88, 34, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 88, 34, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 62, 44, SCALE_ALCHEMY, tile));
    }

}
