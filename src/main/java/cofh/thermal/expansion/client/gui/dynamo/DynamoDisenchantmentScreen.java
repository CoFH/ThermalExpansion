package cofh.thermal.expansion.client.gui.dynamo;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.common.inventory.dynamo.DynamoDisenchantmentMenu;
import cofh.thermal.lib.client.gui.DynamoScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class DynamoDisenchantmentScreen extends DynamoScreen<DynamoDisenchantmentMenu> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/item_dynamo.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DynamoDisenchantmentScreen(DynamoDisenchantmentMenu container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = appendLine(generatePanelInfo("info.thermal.dynamo_disenchantment"), "info.thermal.dynamo.throttle");
    }

    @Override
    public void init() {

        super.init();

        addElement(ThermalGuiHelper.createDefaultDuration(this, 80, 35, SCALE_FLAME_GREEN, tile));
    }

}
