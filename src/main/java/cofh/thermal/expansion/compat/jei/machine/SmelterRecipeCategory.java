package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.managers.machine.SmelterRecipeManager;
import cofh.thermal.core.util.recipes.machine.SmelterRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineSmelterScreen;
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
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_SMELTER_BLOCK;

public class SmelterRecipeCategory extends ThermalRecipeCategory<SmelterRecipe> {

    public SmelterRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachineSmelterScreen.TEXTURE, 26, 11, 140, 62)
                .addPadding(0, 0, 16, 8)
                .build();
        name = getTextComponent(MACHINE_SMELTER_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends SmelterRecipe> getRecipeClass() {

        return SmelterRecipe.class;
    }

    @Override
    public void setIngredients(SmelterRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, SmelterRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
        List<ItemStack> catalysts = SmelterRecipeManager.instance().getCatalysts();

        for (int i = 0; i < outputs.size(); ++i) {
            float chance = recipe.getOutputItemChances().get(i);
            if (chance > 1.0F) {
                for (ItemStack stack : outputs.get(i)) {
                    stack.setCount((int) chance);
                }
            }
        }
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();

        guiItemStacks.init(0, true, 42, 5);
        guiItemStacks.init(1, true, 24, 5);
        guiItemStacks.init(2, true, 60, 5);
        guiItemStacks.init(3, true, 42, 41);

        guiItemStacks.init(4, false, 114, 14);
        guiItemStacks.init(5, false, 132, 14);
        guiItemStacks.init(6, false, 114, 32);
        guiItemStacks.init(7, false, 132, 32);

        for (int i = 0; i < inputs.size(); ++i) {
            guiItemStacks.set(i, inputs.get(i));
        }
        if (recipe.isCatalyzable()) {
            guiItemStacks.set(3, catalysts);
        }
        for (int i = 0; i < outputs.size(); ++i) {
            guiItemStacks.set(i + 4, outputs.get(i));
        }
        addCatalyzedItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 4);
    }

    @Override
    public void draw(SmelterRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 84, 23);
        speedBackground.draw(matrixStack, 43, 24);

        progress.draw(matrixStack, 84, 23);
        speed.draw(matrixStack, 43, 24);
    }

}
