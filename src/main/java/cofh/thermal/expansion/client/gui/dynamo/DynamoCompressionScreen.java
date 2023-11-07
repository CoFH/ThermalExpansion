package cofh.thermal.expansion.client.gui.dynamo;

import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.common.inventory.dynamo.DynamoCompressionMenu;
import cofh.thermal.lib.client.gui.DynamoScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class DynamoCompressionScreen extends DynamoScreen<DynamoCompressionMenu> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/fluid_dynamo.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DynamoCompressionScreen(DynamoCompressionMenu container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = appendLine(generatePanelInfo("info.thermal.dynamo_compression"), "info.thermal.dynamo.throttle");
    }

    @Override
    public void init() {

        super.init();

        addElement(setClearable(createMediumFluidStorage(this, 34, 22, tile.getTank(0)), tile, 0));
        addElement(ThermalGuiHelper.createDefaultDuration(this, 80, 35, SCALE_FLAME, tile));
    }

}
