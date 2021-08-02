package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.thermal.core.util.recipes.machine.PressRecipe;
import cofh.thermal.expansion.client.gui.machine.MachinePressScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalRecipeCategory;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.*;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PRESS_BLOCK;

public class PressRecipeCategory extends ThermalRecipeCategory<PressRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public PressRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachinePressScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(MACHINE_PRESS_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW_FLUID);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_COMPACT);

        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW_FLUID), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_COMPACT), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends PressRecipe> getRecipeClass() {

        return PressRecipe.class;
    }

    @Override
    public void setIngredients(PressRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
        ingredients.setOutputs(VanillaTypes.FLUID, recipe.getOutputFluids());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, PressRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
        List<List<FluidStack>> outputFluids = ingredients.getOutputs(VanillaTypes.FLUID);

        for (int i = 0; i < outputs.size(); ++i) {
            float chance = recipe.getOutputItemChances().get(i);
            if (chance > 1.0F) {
                for (ItemStack stack : outputs.get(i)) {
                    stack.setCount((int) chance);
                }
            }
        }
        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();

        guiItemStacks.init(0, true, 42, 5);
        guiItemStacks.init(1, true, 42, 41);
        guiItemStacks.init(2, false, 105, 23);

        guiFluidStacks.init(0, false, 141, 11, 16, 40, tankSize(TANK_SMALL), false, tankOverlay(tankOverlay));

        for (int i = 0; i < inputs.size(); ++i) {
            guiItemStacks.set(i, inputs.get(i));
        }
        if (!outputs.isEmpty()) {
            guiItemStacks.set(2, outputs.get(0));
        }
        if (!outputFluids.isEmpty()) {
            guiFluidStacks.set(0, outputFluids.get(0));
        }
        addDefaultItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 2);
        addDefaultFluidTooltipCallback(guiFluidStacks);
    }

    @Override
    public void draw(PressRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 69, 23);
        tankBackground.draw(matrixStack, 140, 10);
        speedBackground.draw(matrixStack, 43, 24);

        if (!recipe.getOutputFluids().isEmpty()) {
            RenderHelper.drawFluid(matrixStack, 69, 23, recipe.getOutputFluids().get(0), 24, 16);
            progressFluidBackground.draw(matrixStack, 69, 23);
            progressFluid.draw(matrixStack, 69, 23);
        } else {
            progress.draw(matrixStack, 69, 23);
        }
        speed.draw(matrixStack, 43, 24);
    }

}
