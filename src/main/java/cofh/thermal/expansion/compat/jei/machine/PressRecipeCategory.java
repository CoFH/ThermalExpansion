package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.thermal.core.util.managers.machine.PressRecipeManager;
import cofh.thermal.core.util.recipes.machine.PressRecipe;
import cofh.thermal.expansion.client.gui.machine.MachinePressScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalRecipeCategory;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cofh.core.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.util.Constants.TANK_SMALL;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.*;
import static cofh.thermal.lib.util.ThermalIDs.ID_MACHINE_PRESS;

public class PressRecipeCategory extends ThermalRecipeCategory<PressRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public PressRecipeCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<PressRecipe> type) {

        super(guiHelper, icon, type);
        energyMod = () -> PressRecipeManager.instance().getDefaultScale();

        background = guiHelper.drawableBuilder(MachinePressScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(BLOCKS.get(ID_MACHINE_PRESS).getDescriptionId());

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
    public RecipeType<PressRecipe> getRecipeType() {

        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PressRecipe recipe, IFocusGroup focuses) {

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
        builder.addSlot(RecipeIngredientRole.INPUT, 43, 6)
                .addIngredients(inputs.get(0));

        builder.addSlot(RecipeIngredientRole.INPUT, 43, 42)
                .addIngredients(inputs.size() < 2 ? Ingredient.EMPTY : inputs.get(1));

        IRecipeSlotBuilder outputSlot = builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 24);
        if (!outputs.isEmpty()) {
            outputSlot.addItemStack(outputs.get(0))
                    .addTooltipCallback(defaultOutputTooltip(recipe.getOutputItemChances().get(0)));
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 141, 11)
                .addIngredients(ForgeTypes.FLUID_STACK, outputFluids.isEmpty() ? Collections.emptyList() : List.of(outputFluids.get(0)))
                .setFluidRenderer(tankSize(TANK_SMALL), false, 16, 40)
                .setOverlay(tankOverlay, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());
    }

    @Override
    public void draw(PressRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        progressBackground.draw(guiGraphics, 69, 24);
        tankBackground.draw(guiGraphics, 140, 10);
        speedBackground.draw(guiGraphics, 43, 24);

        if (!recipe.getOutputFluids().isEmpty()) {
            RenderHelper.drawFluid(guiGraphics, 69, 24, recipe.getOutputFluids().get(0), 24, 16);
            progressFluidBackground.draw(guiGraphics, 69, 24);
            progressFluid.draw(guiGraphics, 69, 24);
        } else {
            progress.draw(guiGraphics, 69, 24);
        }
        speed.draw(guiGraphics, 43, 24);
    }

}
