package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.PulverizerCatalyst;
import cofh.thermal.lib.compat.jei.ThermalCatalystCategory;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PULVERIZER_BLOCK;

public class PulverizerCatalystCategory extends ThermalCatalystCategory<PulverizerCatalyst> {

    public PulverizerCatalystCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        name = getTextComponent(MACHINE_PULVERIZER_BLOCK.getTranslationKey()).appendString(": ").append(getTextComponent("info.thermal.catalysts"));
    }

    @Override
    public Class<? extends PulverizerCatalyst> getRecipeClass() {

        return PulverizerCatalyst.class;
    }

}
