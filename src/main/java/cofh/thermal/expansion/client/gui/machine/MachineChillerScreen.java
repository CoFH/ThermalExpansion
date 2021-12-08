package cofh.thermal.expansion.client.gui.machine;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachineChillerContainer;
import cofh.thermal.lib.client.gui.MachineScreenReconfigurable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class MachineChillerScreen extends MachineScreenReconfigurable<MachineChillerContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/chiller.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineChillerScreen(MachineChillerContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_chiller"));
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_chiller");
        name = "chiller";
    }

    @Override
    public void init() {

        super.init();

        addElement(createInputSlot(this, 62, 26, tile));

        addElement(createLargeOutputSlot(this, 125, 35, tile));

        addElement(setClearable(createMediumInputFluidStorage(this, 34, 22, tile.getTank(0), tile), tile, 0));

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 88, 34, PROG_ARROW_FLUID_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 88, 34, PROG_ARROW_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 62, 44, SCALE_SNOWFLAKE, tile));
    }

}
