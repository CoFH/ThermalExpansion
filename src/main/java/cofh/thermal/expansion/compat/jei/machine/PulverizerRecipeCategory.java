package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.managers.machine.PulverizerRecipeManager;
import cofh.thermal.core.util.recipes.machine.PulverizerRecipe;
import cofh.thermal.expansion.client.gui.machine.MachinePulverizerScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static cofh.core.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.catalystTooltip;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.catalyzedOutputTooltip;
import static cofh.thermal.expansion.init.TExpReferences.MACHINE_PULVERIZER_BLOCK;

public class PulverizerRecipeCategory extends ThermalRecipeCategory<PulverizerRecipe> {

    public PulverizerRecipeCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(MachinePulverizerScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(MACHINE_PULVERIZER_BLOCK.getDescriptionId());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_CRUSH);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_CRUSH), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends PulverizerRecipe> getRecipeClass() {

        return PulverizerRecipe.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PulverizerRecipe recipe, IFocusGroup focuses) {

        List<Ingredient> inputs = recipe.getInputItems();
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

        builder.addSlot(RecipeIngredientRole.INPUT, 34, 6)
                .addIngredients(inputs.get(0));
        catalystSlot = builder.addSlot(RecipeIngredientRole.INPUT, 34, 42);

        if (recipe.isCatalyzable()) {
            catalystSlot.addItemStacks(catalysts)
                    .addTooltipCallback(catalystTooltip());
        }
        outputSlots[0] = builder.addSlot(RecipeIngredientRole.OUTPUT, 97, 15);
        outputSlots[1] = builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 15);
        outputSlots[2] = builder.addSlot(RecipeIngredientRole.OUTPUT, 97, 33);
        outputSlots[3] = builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 33);

        for (int i = 0; i < outputs.size(); ++i) {
            outputSlots[i].addItemStack(outputs.get(i))
                    .addTooltipCallback(catalyzedOutputTooltip(recipe.getOutputItemChances().get(i), recipe.isCatalyzable()));
        }
    }

    @Override
    public void draw(PulverizerRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 62, 24);
        speedBackground.draw(matrixStack, 34, 24);

        progress.draw(matrixStack, 62, 24);
        speed.draw(matrixStack, 34, 24);
    }

}
