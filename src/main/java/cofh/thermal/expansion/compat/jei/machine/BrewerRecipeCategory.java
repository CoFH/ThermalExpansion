package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.lib.common.fluid.FluidIngredient;
import cofh.thermal.core.util.managers.machine.BrewerRecipeManager;
import cofh.thermal.core.util.recipes.machine.BrewerRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineBrewerScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalRecipeCategory;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
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

import java.util.List;

import static cofh.lib.util.Constants.TANK_MEDIUM;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.defaultFluidTooltip;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.tankSize;
import static cofh.thermal.lib.util.ThermalIDs.ID_MACHINE_BREWER;

public class BrewerRecipeCategory extends ThermalRecipeCategory<BrewerRecipe> {

    protected IDrawableStatic tankInput;
    protected IDrawableStatic tankOutput;

    protected IDrawableStatic inputOverlay;
    protected IDrawableStatic outputOverlay;

    public BrewerRecipeCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<BrewerRecipe> type) {

        super(guiHelper, icon, type);
        energyMod = () -> BrewerRecipeManager.instance().getDefaultScale();

        background = guiHelper.drawableBuilder(MachineBrewerScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(BLOCKS.get(ID_MACHINE_BREWER).getDescriptionId());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_DROP);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_DROP);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_ALCHEMY);

        tankInput = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOutput = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);

        inputOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);
        outputOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_DROP), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_DROP), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_ALCHEMY), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public RecipeType<BrewerRecipe> getRecipeType() {

        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BrewerRecipe recipe, IFocusGroup focuses) {

        List<Ingredient> inputs = recipe.getInputItems();
        List<FluidIngredient> inputFluids = recipe.getInputFluids();
        List<FluidStack> outputFluids = recipe.getOutputFluids();

        builder.addSlot(RecipeIngredientRole.INPUT, 52, 15)
                .addIngredients(inputs.get(0));

        builder.addSlot(RecipeIngredientRole.INPUT, 25, 11)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(inputFluids.get(0).getFluids()))
                .setFluidRenderer(tankSize(TANK_MEDIUM), false, 16, 40)
                .setOverlay(inputOverlay, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 11)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(outputFluids.get(0)))
                .setFluidRenderer(tankSize(TANK_MEDIUM), false, 16, 40)
                .setOverlay(outputOverlay, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());
    }

    @Override
    public void draw(BrewerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        progressBackground.draw(guiGraphics, 78, 24);
        tankInput.draw(guiGraphics, 24, 10);
        tankOutput.draw(guiGraphics, 115, 10);
        speedBackground.draw(guiGraphics, 52, 34);

        if (!recipe.getInputFluids().isEmpty() && recipe.getInputFluids().get(0).getFluids().length > 0) {
            RenderHelper.drawFluid(guiGraphics, 78, 24, recipe.getInputFluids().get(0).getFluids()[0], 24, 16);
            progressFluidBackground.draw(guiGraphics, 78, 24);
            progressFluid.draw(guiGraphics, 78, 24);
        } else {
            progress.draw(guiGraphics, 78, 24);
        }
        speed.draw(guiGraphics, 52, 34);
    }

}
