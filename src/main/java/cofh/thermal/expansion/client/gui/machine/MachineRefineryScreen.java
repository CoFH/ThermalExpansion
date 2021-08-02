package cofh.thermal.expansion.client.gui.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachineRefineryContainer;
import cofh.thermal.lib.client.gui.MachineScreenReconfigurable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineRefineryScreen extends MachineScreenReconfigurable<MachineRefineryContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machines/refinery.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineRefineryScreen(MachineRefineryContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_refinery"));
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_refinery");
        name = "refinery";
    }

    @Override
    public void init() {

        super.init();

        addElement(createLargeOutputSlot(this, 107, 35, tile));

        addElement(setClearable(createSmallInputFluidStorage(this, 34, 17, tile.getTank(0), tile), tile, 0));

        addElement(setClearable(createMediumOutputFluidStorage(this, 133, 22, tile.getTank(1), tile), tile, 1));
        addElement(setClearable(createMediumOutputFluidStorage(this, 151, 22, tile.getTank(2), tile), tile, 2));

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 65, 34, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 65, 34, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 35, 53, SCALE_FLAME, tile));
    }

}
