package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.SawmillRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineSawmillScreen;
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
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static cofh.core.util.helpers.ItemHelper.cloneStack;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.defaultOutputTooltip;
import static cofh.thermal.lib.common.ThermalIDs.ID_MACHINE_SAWMILL;

public class SawmillRecipeCategory extends ThermalRecipeCategory<SawmillRecipe> {

    public SawmillRecipeCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<SawmillRecipe> type) {

        super(guiHelper, icon, type);

        background = guiHelper.drawableBuilder(MachineSawmillScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(BLOCKS.get(ID_MACHINE_SAWMILL).getDescriptionId());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_SAW);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_SAW), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public RecipeType<SawmillRecipe> getRecipeType() {

        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SawmillRecipe recipe, IFocusGroup focuses) {

        List<Ingredient> inputs = recipe.getInputItems();
        List<ItemStack> outputs = new ArrayList<>(recipe.getOutputItems().size());

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
    }

    @Override
    public void draw(SawmillRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 62, 24);
        speedBackground.draw(matrixStack, 34, 33);

        progress.draw(matrixStack, 62, 24);
        speed.draw(matrixStack, 34, 33);
    }

}
