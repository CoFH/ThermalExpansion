package cofh.thermal.expansion.client.gui.dynamo;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoLapidaryContainer;
import cofh.thermal.lib.client.gui.DynamoScreenBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.Constants.ID_THERMAL;

public class DynamoLapidaryScreen extends DynamoScreenBase<DynamoLapidaryContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/dynamos/lapidary.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DynamoLapidaryScreen(DynamoLapidaryContainer container, PlayerInventory inv, ITextComponent titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.dynamo_lapidary"));
        texture = TEXTURE;
        info = appendLine(generatePanelInfo("info.thermal.dynamo_lapidary"), "info.thermal.dynamo.throttle");
    }

    @Override
    public void init() {

        super.init();

        addElement(ThermalGuiHelper.createDefaultDuration(this, 80, 35, SCALE_FLAME_GREEN, tile));
    }

}
