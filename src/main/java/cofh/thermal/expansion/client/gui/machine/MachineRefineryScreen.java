package cofh.thermal.expansion.client.gui.machine;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.common.inventory.machine.MachineRefineryMenu;
import cofh.thermal.lib.client.gui.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class MachineRefineryScreen extends MachineScreen<MachineRefineryMenu> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/refinery.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public MachineRefineryScreen(MachineRefineryMenu container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
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

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 65, 35, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 65, 35, PROG_DROP_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultSpeed(this, 35, 53, SCALE_FLAME, tile));
    }

}
