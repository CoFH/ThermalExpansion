package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.thermal.core.util.recipes.machine.PyrolyzerRecipe;
import cofh.thermal.expansion.client.gui.machine.MachinePyrolyzerScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cofh.lib.util.constants.Constants.TANK_SMALL;
import static cofh.lib.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.*;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PYROLYZER_BLOCK;

public class PyrolyzerRecipeCategory extends ThermalRecipeCategory<PyrolyzerRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public PyrolyzerRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachinePyrolyzerScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(MACHINE_PYROLYZER_BLOCK.getDescriptionId());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW_FLUID);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);

        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW_FLUID), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends PyrolyzerRecipe> getRecipeClass() {

        return PyrolyzerRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PyrolyzerRecipe recipe, IFocusGroup focuses) {

        List<Ingredient> inputs = recipe.getInputItems();
        List<ItemStack> outputs = new ArrayList<>(recipe.getOutputItems().size());
        List<FluidStack> outputFluids = recipe.getOutputFluids();

        for (ItemStack stack : recipe.getOutputItems()) {
            outputs.add(cloneStack(stack));
        }
        for (int i = 0; i < outputs.size(); ++i) {
            float chance = recipe.getOutputItemChances().get(i);
            if (chance > 1.0F) {
                outputs.get(i).setCount((int) chance);
            }
        }
        IRecipeSlotBuilder[] outputSlots = new IRecipeSlotBuilder[4];

        builder.addSlot(RecipeIngredientRole.INPUT, 34, 15)
                .addIngredients(inputs.get(0));

        outputSlots[0] = builder.addSlot(RecipeIngredientRole.OUTPUT, 97, 15);
        outputSlots[1] = builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 15);
        outputSlots[2] = builder.addSlot(RecipeIngredientRole.OUTPUT, 97, 33);
        outputSlots[3] = builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 33);

        for (int i = 0; i < outputs.size(); ++i) {
            outputSlots[i].addItemStack(outputs.get(i))
                    .addTooltipCallback(defaultOutputTooltip(recipe.getOutputItemChances().get(i)));
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 141, 11)
                .addIngredients(ForgeTypes.FLUID_STACK, outputFluids.isEmpty() ? Collections.emptyList() : List.of(outputFluids.get(0)))
                .setFluidRenderer(tankSize(TANK_SMALL), false, 16, 40)
                .setOverlay(tankOverlay, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());
    }

    @Override
    public void draw(PyrolyzerRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 62, 24);
        tankBackground.draw(matrixStack, 140, 10);
        speedBackground.draw(matrixStack, 34, 33);

        if (!recipe.getOutputFluids().isEmpty()) {
            RenderHelper.drawFluid(matrixStack, 62, 24, recipe.getOutputFluids().get(0), 24, 16);
            progressFluidBackground.draw(matrixStack, 62, 24);
            progressFluid.draw(matrixStack, 62, 24);
        } else {
            progress.draw(matrixStack, 62, 24);
        }
        speed.draw(matrixStack, 34, 33);
    }

}
