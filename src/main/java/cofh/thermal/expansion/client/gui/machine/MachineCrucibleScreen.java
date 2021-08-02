package cofh.thermal.expansion.client.gui.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachineCrucibleContainer;
import cofh.thermal.lib.client.gui.MachineScreenReconfigurable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineCrucibleScreen extends MachineScreenReconfigurable<MachineCrucibleContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machines/crucible.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineCrucibleScreen(MachineCrucibleContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_crucible"));
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_crucible");
        name = "crucible";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 53, 26, tile));

        addElement(setClearable(createMediumOutputFluidStorage(this, 125, 22, tile.getTank(0), tile), tile, 0));

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 84, 34, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 84, 34, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 53, 44, SCALE_FLAME, tile));
    }

}
