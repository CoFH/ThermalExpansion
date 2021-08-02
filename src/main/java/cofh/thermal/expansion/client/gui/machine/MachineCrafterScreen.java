package cofh.thermal.expansion.client.gui.machine;

import cofh.core.client.gui.element.ElementBase;
import cofh.core.client.gui.element.ElementButton;
import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.machine.MachineCrafterContainer;
import cofh.thermal.lib.client.gui.MachineScreenReconfigurable;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_COFH_CORE;
import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.lib.util.helpers.SoundHelper.playClickSound;

public class MachineCrafterScreen extends MachineScreenReconfigurable<MachineCrafterContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/machines/crafter.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public static final String TEX_ACCEPT = ID_COFH_CORE + ":textures/gui/elements/button_accept.png";

    public MachineCrafterScreen(MachineCrafterContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.machine_crafter"));
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.machine_crafter");
        name = "crafter";
        ySize = 190;
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

        ElementBase setRecipe = new ElementButton(this, 142, 52)
                .setName("SetRecipe")
                .setSize(18, 18)
                .setTexture(TEX_ACCEPT, 54, 18)
                .setEnabled(container.tile::hasRecipeChanges);

        addElement(setRecipe);
    }

    @Override
    public boolean handleElementButtonClick(String buttonName, int mouseButton) {

        if (buttonName.equalsIgnoreCase("SetRecipe")) {
            container.setRecipe();
            playClickSound(container.hasValidRecipe() ? 0.8F : 0.6F);
        }
        return true;
    }

    protected void addButton() {

        ElementBase setRecipe = new ElementButton(this, 95, 47)
                .setName("SetRecipe")
                .setSize(18, 18)
                .setTexture(TEX_ACCEPT, 54, 18)
                .setEnabled(container.tile::hasRecipeChanges);

        addElement(setRecipe);
    }

}
