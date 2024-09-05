package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.SmelterCatalyst;
import cofh.thermal.lib.compat.jei.ThermalCatalystCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.util.ThermalIDs.ID_MACHINE_SMELTER;

public class SmelterCatalystCategory extends ThermalCatalystCategory<RecipeHolder<SmelterCatalyst>> {

    public SmelterCatalystCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<RecipeHolder<SmelterCatalyst>> type) {

        super(guiHelper, icon, type);

        name = getTextComponent(BLOCKS.get(ID_MACHINE_SMELTER).getDescriptionId()).append(": ").append(getTextComponent("info.thermal.catalysts"));
    }

    @Override
    public RecipeType<RecipeHolder<SmelterCatalyst>> getRecipeType() {

        return type;
    }

}
