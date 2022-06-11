package cofh.thermal.expansion.compat.jei.dynamo;

import cofh.thermal.core.util.recipes.dynamo.GourmandFuel;
import cofh.thermal.expansion.client.gui.dynamo.DynamoGourmandScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalFuelCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_GOURMAND_BLOCK;

public class GourmandFuelCategory extends ThermalFuelCategory<GourmandFuel> {

    public GourmandFuelCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(DynamoGourmandScreen.TEXTURE, 26, 11, 70, 62)
                .addPadding(0, 0, 16, 78)
                .build();
        name = getTextComponent(DYNAMO_GOURMAND_BLOCK.getDescriptionId());

        durationBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_ALCHEMY);
        duration = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_ALCHEMY), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends GourmandFuel> getRecipeClass() {

        return GourmandFuel.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, GourmandFuel fuel, IFocusGroup focuses) {

        List<Ingredient> inputs = fuel.getInputItems();

        builder.addSlot(RecipeIngredientRole.INPUT, 34, 24)
                .addIngredients(inputs.get(0));
    }

}
