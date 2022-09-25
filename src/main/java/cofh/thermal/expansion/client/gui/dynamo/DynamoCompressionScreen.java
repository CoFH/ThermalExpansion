package cofh.thermal.expansion.client.gui.dynamo;

import cofh.lib.util.helpers.StringHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.expansion.inventory.container.dynamo.DynamoCompressionContainer;
import cofh.thermal.lib.client.gui.DynamoScreenBase;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class DynamoCompressionScreen extends DynamoScreenBase<DynamoCompressionContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/fluid_dynamo.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DynamoCompressionScreen(DynamoCompressionContainer container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, StringHelper.getTextComponent("block.thermal.dynamo_compression"));
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
