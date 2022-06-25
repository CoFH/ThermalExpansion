package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.util.helpers.RenderHelper;
import cofh.lib.fluid.FluidIngredient;
import cofh.thermal.core.util.managers.machine.PulverizerRecipeManager;
import cofh.thermal.core.util.recipes.machine.InsolatorRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineInsolatorScreen;
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

import java.util.ArrayList;
import java.util.List;

import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.lib.util.helpers.ItemHelper.cloneStack;
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
        name = getTextComponent(MACHINE_INSOLATOR_BLOCK.getDescriptionId());

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
    public void setRecipe(IRecipeLayoutBuilder builder, InsolatorRecipe recipe, IFocusGroup focuses) {

        List<Ingredient> inputs = recipe.getInputItems();
        List<FluidIngredient> inputFluids = recipe.getInputFluids();
        List<ItemStack> outputs = new ArrayList<>(recipe.getOutputItems().size());
        List<ItemStack> catalysts = PulverizerRecipeManager.instance().getCatalysts();

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
    public void draw(InsolatorRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 76, 24);
        tankBackground.draw(matrixStack, 24, 10);
        speedBackground.draw(matrixStack, 52, 24);

        if (!recipe.getInputFluids().isEmpty()) {
            RenderHelper.drawFluid(matrixStack, 76, 23, recipe.getInputFluids().get(0).getFluids()[0], 24, 16);
            progressFluidBackground.draw(matrixStack, 76, 24);
            progressFluid.draw(matrixStack, 76, 24);
        } else {
            progress.draw(matrixStack, 76, 24);
        }
        speed.draw(matrixStack, 52, 24);
    }

}
