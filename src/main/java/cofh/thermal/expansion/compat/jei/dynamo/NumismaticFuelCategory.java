package cofh.thermal.expansion.compat.jei.dynamo;

import cofh.thermal.core.util.recipes.dynamo.NumismaticFuel;
import cofh.thermal.expansion.client.gui.dynamo.DynamoNumismaticScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalFuelCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.common.ThermalIDs.ID_DYNAMO_NUMISMATIC;

public class NumismaticFuelCategory extends ThermalFuelCategory<NumismaticFuel> {

    public NumismaticFuelCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<NumismaticFuel> type) {

        super(guiHelper, icon, type);

        background = guiHelper.drawableBuilder(DynamoNumismaticScreen.TEXTURE, 26, 11, 70, 62)
                .addPadding(0, 0, 16, 78)
                .build();
        name = getTextComponent(BLOCKS.get(ID_DYNAMO_NUMISMATIC).getDescriptionId());

        durationBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME_GREEN);
        duration = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME_GREEN), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public RecipeType<NumismaticFuel> getRecipeType() {

        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, NumismaticFuel fuel, IFocusGroup focuses) {

        List<Ingredient> inputs = fuel.getInputItems();

        builder.addSlot(RecipeIngredientRole.INPUT, 34, 24)
                .addIngredients(inputs.get(0));
    }

}
