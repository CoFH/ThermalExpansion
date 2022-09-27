package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.SmelterCatalyst;
import cofh.thermal.lib.compat.jei.ThermalCatalystCategory;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.common.ThermalIDs.ID_MACHINE_SMELTER;

public class SmelterCatalystCategory extends ThermalCatalystCategory<SmelterCatalyst> {

    public SmelterCatalystCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        name = getTextComponent(BLOCKS.get(ID_MACHINE_SMELTER).getDescriptionId()).append(": ").append(getTextComponent("info.thermal.catalysts"));
    }

    @Override
    public Class<? extends SmelterCatalyst> getRecipeClass() {

        return SmelterCatalyst.class;
    }

}
