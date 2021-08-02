package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.SmelterCatalyst;
import cofh.thermal.lib.compat.jei.ThermalCatalystCategory;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_SMELTER_BLOCK;

public class SmelterCatalystCategory extends ThermalCatalystCategory<SmelterCatalyst> {

    public SmelterCatalystCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        name = getTextComponent(MACHINE_SMELTER_BLOCK.getTranslationKey()).appendString(": ").append(getTextComponent("info.thermal.catalysts"));
    }

    @Override
    public Class<? extends SmelterCatalyst> getRecipeClass() {

        return SmelterCatalyst.class;
    }

}
