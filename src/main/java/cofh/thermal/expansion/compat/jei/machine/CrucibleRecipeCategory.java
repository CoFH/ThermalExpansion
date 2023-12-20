package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.thermal.core.util.managers.machine.CrucibleRecipeManager;
import cofh.thermal.core.util.recipes.machine.CrucibleRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineCrucibleScreen;
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
import static cofh.thermal.lib.util.ThermalIDs.ID_MACHINE_CRUCIBLE;

public class CrucibleRecipeCategory extends ThermalRecipeCategory<CrucibleRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public CrucibleRecipeCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<CrucibleRecipe> type) {

        super(guiHelper, icon, type);
        energyMod = () -> CrucibleRecipeManager.instance().getDefaultScale();

        background = guiHelper.drawableBuilder(MachineCrucibleScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(BLOCKS.get(ID_MACHINE_CRUCIBLE).getDescriptionId());

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
    public RecipeType<CrucibleRecipe> getRecipeType() {

        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrucibleRecipe recipe, IFocusGroup focuses) {

        List<Ingredient> inputs = recipe.getInputItems();
        List<FluidStack> outputFluids = recipe.getOutputFluids();

        builder.addSlot(RecipeIngredientRole.INPUT, 43, 15)
                .addIngredients(inputs.get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 116, 11)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(outputFluids.get(0)))
                .setFluidRenderer(tankSize(TANK_MEDIUM), false, 16, 40)
                .setOverlay(tankOverlay, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());
    }

    @Override
    public void draw(CrucibleRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        progressBackground.draw(guiGraphics, 74, 24);
        tankBackground.draw(guiGraphics, 115, 10);
        speedBackground.draw(guiGraphics, 43, 33);

        if (!recipe.getOutputFluids().isEmpty()) {
            RenderHelper.drawFluid(guiGraphics, 74, 24, recipe.getOutputFluids().get(0), 24, 16);
            progressFluidBackground.draw(guiGraphics, 74, 24);
            progressFluid.draw(guiGraphics, 74, 24);
        } else {
            progress.draw(guiGraphics, 74, 24);
        }
        speed.draw(guiGraphics, 43, 33);
    }

}
