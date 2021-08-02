package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.SawmillRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineSawmillScreen;
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
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_SAWMILL_BLOCK;

public class SawmillRecipeCategory extends ThermalRecipeCategory<SawmillRecipe> {

    public SawmillRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachineSawmillScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(MACHINE_SAWMILL_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_SAW);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_SAW), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends SawmillRecipe> getRecipeClass() {

        return SawmillRecipe.class;
    }

    @Override
    public void setIngredients(SawmillRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, SawmillRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);

        for (int i = 0; i < outputs.size(); ++i) {
            float chance = recipe.getOutputItemChances().get(i);
            if (chance > 1.0F) {
                for (ItemStack stack : outputs.get(i)) {
                    stack.setCount((int) chance);
                }
            }
        }
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();

        guiItemStacks.init(0, true, 33, 14);
        guiItemStacks.init(1, false, 96, 14);
        guiItemStacks.init(2, false, 114, 14);
        guiItemStacks.init(3, false, 96, 32);
        guiItemStacks.init(4, false, 114, 32);

        guiItemStacks.set(0, inputs.get(0));

        for (int i = 0; i < outputs.size(); ++i) {
            guiItemStacks.set(i + 1, outputs.get(i));
        }
        addDefaultItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 1);
    }

    @Override
    public void draw(SawmillRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 62, 23);
        speedBackground.draw(matrixStack, 34, 33);

        progress.draw(matrixStack, 62, 23);
        speed.draw(matrixStack, 34, 33);
    }

}
