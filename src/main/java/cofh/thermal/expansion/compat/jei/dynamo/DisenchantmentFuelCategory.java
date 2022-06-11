package cofh.thermal.expansion.compat.jei.dynamo;

import cofh.thermal.core.util.recipes.dynamo.DisenchantmentFuel;
import cofh.thermal.expansion.client.gui.dynamo.DynamoLapidaryScreen;
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
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_DISENCHANTMENT_BLOCK;

public class DisenchantmentFuelCategory extends ThermalFuelCategory<DisenchantmentFuel> {

    public DisenchantmentFuelCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(DynamoLapidaryScreen.TEXTURE, 26, 11, 70, 62)
                .addPadding(0, 0, 16, 78)
                .build();
        name = getTextComponent(DYNAMO_DISENCHANTMENT_BLOCK.getDescriptionId());

        durationBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME_GREEN);
        duration = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME_GREEN), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends DisenchantmentFuel> getRecipeClass() {

        return DisenchantmentFuel.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, DisenchantmentFuel fuel, IFocusGroup focuses) {

        List<Ingredient> inputs = fuel.getInputItems();

        builder.addSlot(RecipeIngredientRole.INPUT, 34, 24)
                .addIngredients(inputs.get(0));
    }

}
