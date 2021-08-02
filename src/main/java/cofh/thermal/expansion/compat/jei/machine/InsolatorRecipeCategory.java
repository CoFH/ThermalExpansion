package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.thermal.core.util.managers.machine.InsolatorRecipeManager;
import cofh.thermal.core.util.recipes.machine.InsolatorRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineInsolatorScreen;
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

import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.*;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_INSOLATOR_BLOCK;

public class InsolatorRecipeCategory extends ThermalRecipeCategory<InsolatorRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public InsolatorRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachineInsolatorScreen.TEXTURE, 26, 11, 130, 62)
                .addPadding(0, 0, 16, 18)
                .build();
        name = getTextComponent(MACHINE_INSOLATOR_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW_FLUID);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_SUN);

        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW_FLUID), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_SUN), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends InsolatorRecipe> getRecipeClass() {

        return InsolatorRecipe.class;
    }

    @Override
    public void setIngredients(InsolatorRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setInputs(VanillaTypes.FLUID, recipe.getInputFluids());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, InsolatorRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<FluidStack>> inputFluids = ingredients.getInputs(VanillaTypes.FLUID);
        List<List<ItemStack>> outputs = ingredients.getOutputs(VanillaTypes.ITEM);
        List<ItemStack> catalysts = InsolatorRecipeManager.instance().getCatalysts();

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

        guiItemStacks.init(0, true, 51, 5);
        guiItemStacks.init(1, true, 51, 41);

        guiItemStacks.init(2, false, 105, 14);
        guiItemStacks.init(3, false, 123, 14);
        guiItemStacks.init(4, false, 105, 32);
        guiItemStacks.init(5, false, 123, 32);

        guiFluidStacks.init(0, false, 25, 11, 16, 40, tankSize(TANK_MEDIUM), false, tankOverlay(tankOverlay));

        guiItemStacks.set(0, inputs.get(0));
        guiFluidStacks.set(0, inputFluids.get(0));

        if (recipe.isCatalyzable()) {
            guiItemStacks.set(1, catalysts);
        }
        for (int i = 0; i < outputs.size(); ++i) {
            guiItemStacks.set(i + 2, outputs.get(i));
        }
        addCatalyzedItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 2);
        addDefaultFluidTooltipCallback(guiFluidStacks);
    }

    @Override
    public void draw(InsolatorRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 76, 23);
        tankBackground.draw(matrixStack, 24, 10);
        speedBackground.draw(matrixStack, 52, 24);

        if (!recipe.getInputFluids().isEmpty()) {
            RenderHelper.drawFluid(matrixStack, 76, 23, recipe.getInputFluids().get(0), 24, 16);
            progressFluidBackground.draw(matrixStack, 76, 23);
            progressFluid.draw(matrixStack, 76, 23);
        } else {
            progress.draw(matrixStack, 76, 23);
        }
        speed.draw(matrixStack, 52, 24);
    }

}
