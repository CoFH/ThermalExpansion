package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.thermal.core.util.recipes.machine.CentrifugeRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineCentrifugeScreen;
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
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CENTRIFUGE_BLOCK;

public class CentrifugeRecipeCategory extends ThermalRecipeCategory<CentrifugeRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public CentrifugeRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachineCentrifugeScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(MACHINE_CENTRIFUGE_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW_FLUID);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_SPIN);

        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW_FLUID), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_SPIN), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends CentrifugeRecipe> getRecipeClass() {

        return CentrifugeRecipe.class;
    }

    @Override
    public void setIngredients(CentrifugeRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
        ingredients.setOutputs(VanillaTypes.FLUID, recipe.getOutputFluids());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, CentrifugeRecipe recipe, IIngredients ingredients) {

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

        guiItemStacks.init(0, true, 33, 14);
        guiItemStacks.init(1, false, 96, 14);
        guiItemStacks.init(2, false, 114, 14);
        guiItemStacks.init(3, false, 96, 32);
        guiItemStacks.init(4, false, 114, 32);

        guiFluidStacks.init(0, false, 141, 11, 16, 40, tankSize(TANK_SMALL), false, tankOverlay(tankOverlay));

        guiItemStacks.set(0, inputs.get(0));
        for (int i = 0; i < outputs.size(); ++i) {
            guiItemStacks.set(i + 1, outputs.get(i));
        }
        if (!outputFluids.isEmpty()) {
            guiFluidStacks.set(0, outputFluids.get(0));
        }
        addDefaultItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 1);
        addDefaultFluidTooltipCallback(guiFluidStacks);
    }

    @Override
    public void draw(CentrifugeRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 62, 23);
        tankBackground.draw(matrixStack, 140, 10);
        speedBackground.draw(matrixStack, 34, 33);

        if (!recipe.getOutputFluids().isEmpty()) {
            RenderHelper.drawFluid(matrixStack, 62, 23, recipe.getOutputFluids().get(0), 24, 16);
            progressFluidBackground.draw(matrixStack, 62, 23);
            progressFluid.draw(matrixStack, 62, 23);
        } else {
            progress.draw(matrixStack, 62, 23);
        }
        speed.draw(matrixStack, 34, 33);
    }

}
