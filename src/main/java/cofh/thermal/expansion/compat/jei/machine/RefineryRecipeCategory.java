package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.lib.common.fluid.FluidIngredient;
import cofh.thermal.core.util.managers.machine.RefineryRecipeManager;
import cofh.thermal.core.util.recipes.machine.RefineryRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineRefineryScreen;
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
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cofh.core.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.util.Constants.TANK_MEDIUM;
import static cofh.lib.util.Constants.TANK_SMALL;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.*;
import static cofh.thermal.lib.util.ThermalIDs.ID_MACHINE_REFINERY;

public class RefineryRecipeCategory extends ThermalRecipeCategory<RefineryRecipe> {

    protected IDrawableStatic tankInput;
    protected IDrawableStatic tankOutputA;
    protected IDrawableStatic tankOutputB;

    protected IDrawableStatic inputOverlay;
    protected IDrawableStatic outputOverlayA;
    protected IDrawableStatic outputOverlayB;

    public RefineryRecipeCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<RefineryRecipe> type) {

        super(guiHelper, icon, type);
        energyMod = () -> RefineryRecipeManager.instance().getDefaultScale();

        background = guiHelper.drawableBuilder(MachineRefineryScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(BLOCKS.get(ID_MACHINE_REFINERY).getDescriptionId());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_DROP);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_DROP);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);

        tankInput = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_SMALL);
        tankOutputA = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOutputB = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);

        inputOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_SMALL);
        outputOverlayA = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);
        outputOverlayB = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_DROP), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_DROP), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public RecipeType<RefineryRecipe> getRecipeType() {

        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RefineryRecipe recipe, IFocusGroup focuses) {

        List<FluidIngredient> inputFluids = recipe.getInputFluids();
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
        IRecipeSlotBuilder outputSlot = builder.addSlot(RecipeIngredientRole.OUTPUT, 97, 24);
        if (!outputs.isEmpty()) {
            outputSlot.addItemStack(outputs.get(0))
                    .addTooltipCallback(defaultOutputTooltip(recipe.getOutputItemChances().get(0)));
        }

        builder.addSlot(RecipeIngredientRole.INPUT, 29, 6)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(inputFluids.get(0).getFluids()))
                .setFluidRenderer(tankSize(TANK_SMALL), false, 16, 32)
                .setOverlay(inputOverlay, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 126, 12)
                .addIngredients(ForgeTypes.FLUID_STACK, outputFluids.isEmpty() ? Collections.emptyList() : List.of(outputFluids.get(0)))
                .setFluidRenderer(tankSize(TANK_MEDIUM), false, 16, 40)
                .setOverlay(outputOverlayA, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 144, 12)
                .addIngredients(ForgeTypes.FLUID_STACK, outputFluids.size() < 2 ? Collections.emptyList() : List.of(outputFluids.get(1)))
                .setFluidRenderer(tankSize(TANK_MEDIUM), false, 16, 40)
                .setOverlay(outputOverlayB, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());
    }

    @Override
    public void draw(RefineryRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        progressBackground.draw(guiGraphics, 57, 22);
        tankInput.draw(guiGraphics, 28, 5);
        tankOutputA.draw(guiGraphics, 125, 11);
        tankOutputB.draw(guiGraphics, 143, 11);
        speedBackground.draw(guiGraphics, 29, 40);

        if (!recipe.getInputFluids().isEmpty() && recipe.getInputFluids().get(0).getFluids().length > 0) {
            RenderHelper.drawFluid(guiGraphics, 57, 22, recipe.getInputFluids().get(0).getFluids()[0], 24, 16);
            progressFluidBackground.draw(guiGraphics, 57, 22);
            progressFluid.draw(guiGraphics, 57, 22);
        } else {
            progress.draw(guiGraphics, 57, 22);
        }
        speed.draw(guiGraphics, 29, 40);
    }

}
