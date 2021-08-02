package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.thermal.core.util.recipes.machine.CrucibleRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineCrucibleScreen;
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
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_CRUCIBLE_BLOCK;

public class CrucibleRecipeCategory extends ThermalRecipeCategory<CrucibleRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public CrucibleRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachineCrucibleScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(MACHINE_CRUCIBLE_BLOCK.getTranslationKey());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_DROP);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_DROP);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);

        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_DROP), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_DROP), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends CrucibleRecipe> getRecipeClass() {

        return CrucibleRecipe.class;
    }

    @Override
    public void setIngredients(CrucibleRecipe recipe, IIngredients ingredients) {

        ingredients.setInputIngredients(recipe.getInputItems());
        ingredients.setOutputs(VanillaTypes.FLUID, recipe.getOutputFluids());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, CrucibleRecipe recipe, IIngredients ingredients) {

        List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
        List<List<FluidStack>> outputFluids = ingredients.getOutputs(VanillaTypes.FLUID);

        IGuiItemStackGroup guiItemStacks = layout.getItemStacks();
        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();

        guiItemStacks.init(0, true, 42, 14);
        guiFluidStacks.init(0, false, 116, 11, 16, 40, tankSize(TANK_MEDIUM), false, tankOverlay(tankOverlay));

        guiItemStacks.set(0, inputs.get(0));
        guiFluidStacks.set(0, outputFluids.get(0));

        addDefaultFluidTooltipCallback(guiFluidStacks);
    }

    @Override
    public void draw(CrucibleRecipe recipe, MatrixStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 74, 23);
        tankBackground.draw(matrixStack, 115, 10);
        speedBackground.draw(matrixStack, 43, 33);

        if (!recipe.getOutputFluids().isEmpty()) {
            RenderHelper.drawFluid(matrixStack, 74, 23, recipe.getOutputFluids().get(0), 24, 16);
            progressFluidBackground.draw(matrixStack, 74, 23);
            progressFluid.draw(matrixStack, 74, 23);
        } else {
            progress.draw(matrixStack, 74, 23);
        }
        speed.draw(matrixStack, 43, 33);
    }

}
