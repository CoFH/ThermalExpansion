package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.FurnaceRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineFurnaceScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalRecipeCategory;
import com.mojang.blaze3d.matrix.MatrixStack;
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
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_FURNACE_BLOCK;

public class FurnaceRecipeCategory extends ThermalRecipeCategory<FurnaceRecipe> {

    public FurnaceRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachineFurnaceScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(MACHINE_FURNACE_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends FurnaceRecipe> getRecipeClass() {

        return FurnaceRecipe.class;
    }

    @Override
    public void setIngredients(FurnaceRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, FurnaceRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();

        guiItemStacks.init(0, true, 42, 14);
        guiItemStacks.init(1, false, 105, 23);

        guiItemStacks.set(0, inputs.get(0));
        guiItemStacks.set(1, outputs.get(0));

        addDefaultItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 1);
    }

    @Override
    public void draw(FurnaceRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 69, 23);
        speedBackground.draw(matrixStack, 43, 33);

        progress.draw(matrixStack, 69, 23);
        speed.draw(matrixStack, 43, 33);
    }

}
