package cofh.thermal.expansion.client.gui.machine;

import cofh.core.client.gui.element.ElementBase;
import cofh.core.client.gui.element.ElementButton;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.common.inventory.machine.MachineCrafterMenu;
import cofh.thermal.lib.client.gui.MachineScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_COFH_CORE;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.lib.util.helpers.SoundHelper.playClickSound;

public class MachineCrafterScreen extends MachineScreen<MachineCrafterMenu> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/crafter.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public static final String TEX_ACCEPT = ID_COFH_CORE + ":textures/gui/elements/button_accept.png";

    public MachineCrafterScreen(MachineCrafterMenu container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_crafter");
        name = "crafter";
        imageHeight = 190;
    }

    @Override
    public void init() {

        super.init();

        for (int i = 0; i < 9; ++i) {
            addElement(createInputSlot(this, 8 + i * 18, 77, tile));
        }
        addElement(createLargeOutputSlot(this, 143, 21, tile));

        addElement(setClearable(createMediumInputFluidStorage(this, 29, 22, tile.getTank(0), tile), tile, 0));

        addElement(ThermalGuiHelper.createDefaultFluidProgress(this, 110, 22, PROG_ARROW_FLUID_RIGHT, tile));
        addElement(ThermalGuiHelper.createDefaultProgress(this, 110, 22, PROG_ARROW_RIGHT, tile));

        ElementBase setRecipe = new ElementButton(this, 142, 52) {

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {

                if (enabled()) {
                    menu.setRecipe();
                    playClickSound(menu.hasValidRecipe() ? 0.8F : 0.6F);
                }
                return enabled();
            }
        }
                .setName("SetRecipe")
                .setSize(18, 18)
                .setTexture(TEX_ACCEPT, 54, 18)
                .setEnabled(menu.tile::hasRecipeChanges);

        addElement(setRecipe);
    }

}
