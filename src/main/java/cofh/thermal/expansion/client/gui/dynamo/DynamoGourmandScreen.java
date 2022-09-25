package cofh.thermal.expansion.client.gui.dynamo;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoGourmandContainer;
import cofh.thermal.lib.client.gui.DynamoScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class DynamoGourmandScreen extends DynamoScreenBase<DynamoGourmandContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/item_dynamo.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DynamoGourmandScreen(DynamoGourmandContainer container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.dynamo_gourmand"));
        texture = TEXTURE;
        info = appendLine(generatePanelInfo("info.thermal.dynamo_gourmand"), "info.thermal.dynamo.throttle");
    }

    @Override
    public void init() {

        super.init();

        addElement(ThermalGuiHelper.createDefaultDuration(this, 80, 35, SCALE_ALCHEMY, tile));
    }

}
