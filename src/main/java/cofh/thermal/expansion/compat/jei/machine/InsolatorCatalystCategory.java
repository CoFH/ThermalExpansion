package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.InsolatorCatalyst;
import cofh.thermal.lib.compat.jei.ThermalCatalystCategory;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.util.ThermalIDs.ID_MACHINE_INSOLATOR;

public class InsolatorCatalystCategory extends ThermalCatalystCategory<RecipeHolder<InsolatorCatalyst>> {

    public InsolatorCatalystCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<RecipeHolder<InsolatorCatalyst>> type) {

        super(guiHelper, icon, type);

        name = getTextComponent(BLOCKS.get(ID_MACHINE_INSOLATOR).getDescriptionId()).append(": ").append(getTextComponent("info.thermal.catalysts"));
    }

    @Override
    public RecipeType<RecipeHolder<InsolatorCatalyst>> getRecipeType() {

        return type;
    }

}
