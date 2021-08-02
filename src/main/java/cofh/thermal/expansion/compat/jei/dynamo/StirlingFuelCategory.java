package cofh.thermal.expansion.compat.jei.dynamo;

import cofh.thermal.core.util.recipes.dynamo.StirlingFuel;
import cofh.thermal.expansion.client.gui.dynamo.DynamoStirlingScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalFuelCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.List;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_STIRLING_BLOCK;

public class StirlingFuelCategory extends ThermalFuelCategory<StirlingFuel> {

    public StirlingFuelCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(DynamoStirlingScreen.TEXTURE, 26, 11, 70, 62)
                .addPadding(0, 0, 16, 78)
                .build();
        name = getTextComponent(DYNAMO_STIRLING_BLOCK.getTranslationKey());

        durationBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);

        duration = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends StirlingFuel> getRecipeClass() {

        return StirlingFuel.class;
    }

    @Override
    public void setIngredients(StirlingFuel fuel, IIngredients ingredients) {

        ingredients.setInputIngredients(fuel.getInputItems());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, StirlingFuel fuel, IIngredients ingredients) {

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);

        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();

        guiItemStacks.init(0, true, 33, 23);

        guiItemStacks.set(0, inputs.get(0));
    }

}
