package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.lib.common.fluid.FluidIngredient;
import cofh.thermal.core.util.managers.machine.CrystallizerRecipeManager;
import cofh.thermal.core.util.recipes.machine.CrystallizerRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineCrystallizerScreen;
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

import java.util.List;

import static cofh.lib.util.Constants.TANK_MEDIUM;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.*;
import static cofh.thermal.lib.util.ThermalIDs.ID_MACHINE_CRYSTALLIZER;

public class CrystallizerRecipeCategory extends ThermalRecipeCategory<CrystallizerRecipe> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public CrystallizerRecipeCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<CrystallizerRecipe> type) {

        super(guiHelper, icon, type);
        energyMod = () -> CrystallizerRecipeManager.instance().getDefaultScale();

        background = guiHelper.drawableBuilder(MachineCrystallizerScreen.TEXTURE, 26, 11, 144, 62)
                .addPadding(0, 0, 16, 4)
                .build();
        name = getTextComponent(BLOCKS.get(ID_MACHINE_CRYSTALLIZER).getDescriptionId());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        progressFluidBackground = Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW_FLUID);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_ALCHEMY);

        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        progressFluid = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW_FLUID), 200, IDrawableAnimated.StartDirection.LEFT, true);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_ALCHEMY), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public RecipeType<CrystallizerRecipe> getRecipeType() {

        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrystallizerRecipe recipe, IFocusGroup focuses) {

        List<Ingredient> inputs = recipe.getInputItems();
        List<FluidIngredient> inputFluids = recipe.getInputFluids();
        List<ItemStack> outputs = recipe.getOutputItems();

        builder.addSlot(RecipeIngredientRole.INPUT, 52, 15)
                .addIngredients(inputs.get(0));

        if (inputs.size() > 1) {
            builder.addSlot(RecipeIngredientRole.INPUT, 70, 15)
                    .addIngredients(inputs.get(1));
        }
        builder.addSlot(RecipeIngredientRole.OUTPUT, 133, 24)
                .addItemStack(outputs.get(0))
                .addTooltipCallback(defaultOutputTooltip(recipe.getOutputItemChances().get(0)));

        builder.addSlot(RecipeIngredientRole.INPUT, 25, 11)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(inputFluids.get(0).getFluids()))
                .setFluidRenderer(tankSize(TANK_MEDIUM), false, 16, 40)
                .setOverlay(tankOverlay, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());
    }

    @Override
    public void draw(CrystallizerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        progressBackground.draw(guiGraphics, 95, 24);
        tankBackground.draw(guiGraphics, 24, 10);
        speedBackground.draw(guiGraphics, 61, 34);

        if (!recipe.getInputFluids().isEmpty() && recipe.getInputFluids().get(0).getFluids().length > 0) {
            RenderHelper.drawFluid(guiGraphics, 95, 24, recipe.getInputFluids().get(0).getFluids()[0], 24, 16);
            progressFluidBackground.draw(guiGraphics, 95, 24);
            progressFluid.draw(guiGraphics, 95, 24);
        } else {
            progress.draw(guiGraphics, 95, 24);
        }
        speed.draw(guiGraphics, 61, 34);
    }

}
