package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.thermal.core.util.recipes.machine.BottlerRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineBottlerScreen;
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
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_BOTTLER_BLOCK;

public class BottlerRecipeCategory extends ThermalRecipeCategory<BottlerRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public BottlerRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachineBottlerScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(MACHINE_BOTTLER_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW_FLUID);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_BUBBLE);

        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);

        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW_FLUID), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_BUBBLE), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends BottlerRecipe> getRecipeClass() {

        return BottlerRecipe.class;
    }

    @Override
    public void setIngredients(BottlerRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setInputs(VanillaTypes.FLUID, recipe.getInputFluids());
        ingredients.setOutputs(VanillaTypes.ITEM, recipe.getOutputItems());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, BottlerRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputItems = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<FluidStack>> inputFluids = ingredients.getInputs(VanillaTypes.FLUID);
        List<List<ItemStack>> outputItems = ingredients.getOutputs(VanillaTypes.ITEM);

        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();

        guiItemStacks.init(0, true, 51, 14);
        guiItemStacks.init(1, false, 114, 23);
        guiFluidStacks.init(0, true, 25, 11, 16, 40, tankSize(TANK_MEDIUM), false, tankOverlay(tankOverlay));

        if (!inputItems.isEmpty()) {
            guiItemStacks.set(0, inputItems.get(0));
        }
        guiItemStacks.set(1, outputItems.get(0));

        if (!inputFluids.isEmpty()) {
            guiFluidStacks.set(0, inputFluids.get(0));
        }
        addDefaultItemTooltipCallback(guiItemStacks, recipe.getOutputItemChances(), 1);
        addDefaultFluidTooltipCallback(guiFluidStacks);
    }

    @Override
    public void draw(BottlerRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 78, 23);
        tankBackground.draw(matrixStack, 24, 10);
        speedBackground.draw(matrixStack, 52, 34);

        if (!recipe.getInputFluids().isEmpty()) {
            RenderHelper.drawFluid(matrixStack, 78, 23, recipe.getInputFluids().get(0), 24, 16);
            progressFluidBackground.draw(matrixStack, 78, 23);
            progressFluid.draw(matrixStack, 78, 23);
        } else {
            progress.draw(matrixStack, 78, 23);
        }
        speed.draw(matrixStack, 52, 34);
    }

}
