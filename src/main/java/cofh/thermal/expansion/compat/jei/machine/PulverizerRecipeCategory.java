package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.managers.machine.PulverizerRecipeManager;
import cofh.thermal.core.util.recipes.machine.PulverizerRecipe;
import cofh.thermal.expansion.client.gui.machine.MachinePulverizerScreen;
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
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PULVERIZER_BLOCK;

public class PulverizerRecipeCategory extends ThermalRecipeCategory<PulverizerRecipe> {

    public PulverizerRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachinePulverizerScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(MACHINE_PULVERIZER_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_CRUSH);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_CRUSH), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends PulverizerRecipe> getRecipeClass() {

        return PulverizerRecipe.class;
    }

    @Override
    public void setIngredients(PulverizerRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, PulverizerRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
        List<ItemStack> catalysts = PulverizerRecipeManager.instance().getCatalysts();

        for (int i = 0; i < outputs.size(); ++i) {
            float chance = recipe.getOutputItemChances().get(i);
            if (chance > 1.0F) {
                for (ItemStack stack : outputs.get(i)) {
                    stack.setCount((int) chance);
                }
            }
        }
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();

        guiItemStacks.init(0, true, 33, 5);
        guiItemStacks.init(1, true, 33, 41);

        guiItemStacks.init(2, false, 96, 14);
        guiItemStacks.init(3, false, 114, 14);
        guiItemStacks.init(4, false, 96, 32);
        guiItemStacks.init(5, false, 114, 32);

        guiItemStacks.set(0, inputs.get(0));

        if (recipe.isCatalyzable()) {
            guiItemStacks.set(1, catalysts);
        }
        for (int i = 0; i < outputs.size(); ++i) {
            guiItemStacks.set(i + 2, outputs.get(i));
        }
        addCatalyzedItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 2);
    }

    @Override
    public void draw(PulverizerRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 62, 23);
        speedBackground.draw(matrixStack, 34, 24);

        progress.draw(matrixStack, 62, 23);
        speed.draw(matrixStack, 34, 24);
    }

}
