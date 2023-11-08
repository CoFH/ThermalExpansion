package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.PulverizerCatalyst;
import cofh.thermal.lib.compat.jei.ThermalCatalystCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.util.ThermalIDs.ID_MACHINE_PULVERIZER;

public class PulverizerCatalystCategory extends ThermalCatalystCategory<PulverizerCatalyst> {

    public PulverizerCatalystCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<PulverizerCatalyst> type) {

        super(guiHelper, icon, type);

        name = getTextComponent(BLOCKS.get(ID_MACHINE_PULVERIZER).getDescriptionId()).append(": ").append(getTextComponent("info.thermal.catalysts"));
    }

    @Override
    public RecipeType<PulverizerCatalyst> getRecipeType() {

        return type;
    }

}
