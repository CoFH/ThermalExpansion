package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.lib.common.fluid.FluidIngredient;
import cofh.thermal.core.util.managers.machine.InsolatorRecipeManager;
import cofh.thermal.core.util.recipes.machine.InsolatorRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineInsolatorScreen;
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

import java.util.ArrayList;
import java.util.List;

import static cofh.core.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.util.Constants.TANK_MEDIUM;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.*;
import static cofh.thermal.lib.util.ThermalIDs.ID_MACHINE_INSOLATOR;

public class InsolatorRecipeCategory extends ThermalRecipeCategory<InsolatorRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public InsolatorRecipeCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<InsolatorRecipe> type) {

        super(guiHelper, icon, type);
        energyMod = () -> InsolatorRecipeManager.instance().getDefaultScale();

        background = guiHelper.drawableBuilder(MachineInsolatorScreen.TEXTURE, 26, 11, 130, 62)
                .addPadding(0, 0, 16, 18)
                .build();
        name = getTextComponent(BLOCKS.get(ID_MACHINE_INSOLATOR).getDescriptionId());

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
    public RecipeType<InsolatorRecipe> getRecipeType() {

        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, InsolatorRecipe recipe, IFocusGroup focuses) {

        List<Ingredient> inputs = recipe.getInputItems();
        List<FluidIngredient> inputFluids = recipe.getInputFluids();
        List<ItemStack> outputs = new ArrayList<>(recipe.getOutputItems().size());
        List<ItemStack> catalysts = InsolatorRecipeManager.instance().getCatalysts();

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
        IRecipeSlotBuilder catalystSlot;

        builder.addSlot(RecipeIngredientRole.INPUT, 52, 6)
                .addIngredients(inputs.get(0));
        catalystSlot = builder.addSlot(RecipeIngredientRole.INPUT, 52, 42);

        if (recipe.isCatalyzable()) {
            catalystSlot.addItemStacks(catalysts)
                    .addTooltipCallback(catalystTooltip());
        }
        outputSlots[0] = builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 15);
        outputSlots[1] = builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 15);
        outputSlots[2] = builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 33);
        outputSlots[3] = builder.addSlot(RecipeIngredientRole.OUTPUT, 124, 33);

        for (int i = 0; i < outputs.size(); ++i) {
            outputSlots[i].addItemStack(outputs.get(i))
                    .addTooltipCallback(catalyzedOutputTooltip(recipe.getOutputItemChances().get(i), recipe.isCatalyzable()));
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 25, 11)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(inputFluids.get(0).getFluids()))
                .setFluidRenderer(tankSize(TANK_MEDIUM), false, 16, 40)
                .setOverlay(tankOverlay, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());
    }

    @Override
    public void draw(InsolatorRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        progressBackground.draw(guiGraphics, 76, 24);
        tankBackground.draw(guiGraphics, 24, 10);
        speedBackground.draw(guiGraphics, 52, 24);

        if (!recipe.getInputFluids().isEmpty() && recipe.getInputFluids().get(0).getFluids().length > 0) {
            RenderHelper.drawFluid(guiGraphics, 76, 24, recipe.getInputFluids().get(0).getFluids()[0], 24, 16);
            progressFluidBackground.draw(guiGraphics, 76, 24);
            progressFluid.draw(guiGraphics, 76, 24);
        } else {
            progress.draw(guiGraphics, 76, 24);
        }
        speed.draw(guiGraphics, 52, 24);
    }

}
