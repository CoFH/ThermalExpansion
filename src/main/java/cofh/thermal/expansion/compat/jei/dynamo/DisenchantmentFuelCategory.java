package cofh.thermal.expansion.compat.jei.dynamo;

import cofh.thermal.core.util.recipes.dynamo.*;
import cofh.thermal.expansion.client.gui.dynamo.DynamoLapidaryScreen;
import cofh.thermal.lib.compat.jei.*;
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
import static cofh.thermal.expansion.init.TExpReferences.*;

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
    public void setIngredients(DisenchantmentFuel fuel, IIngredients ingredients) {

        ingredients.setInputIngredients(fuel.getInputItems());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, DisenchantmentFuel fuel, IIngredients ingredients) {

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);

        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();

        guiItemStacks.init(0, true, 33, 23);

        guiItemStacks.set(0, inputs.get(0));
    }

}
