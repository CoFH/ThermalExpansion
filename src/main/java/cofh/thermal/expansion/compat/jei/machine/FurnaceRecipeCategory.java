package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.FurnaceRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineFurnaceScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalRecipeCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.defaultOutputTooltip;
import static cofh.thermal.lib.common.ThermalIDs.ID_MACHINE_FURNACE;

public class FurnaceRecipeCategory extends ThermalRecipeCategory<FurnaceRecipe> {

    public FurnaceRecipeCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<FurnaceRecipe> type) {

        super(guiHelper, icon, type);

        background = guiHelper.drawableBuilder(MachineFurnaceScreen.TEXTURE, 26, 11, 124, 62)
                .addPadding(0, 0, 16, 24)
                .build();
        name = getTextComponent(BLOCKS.get(ID_MACHINE_FURNACE).getDescriptionId());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public RecipeType<FurnaceRecipe> getRecipeType() {

        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FurnaceRecipe recipe, IFocusGroup focuses) {

        List<Ingredient> inputs = recipe.getInputItems();
        List<ItemStack> outputs = recipe.getOutputItems();

        builder.addSlot(RecipeIngredientRole.INPUT, 43, 15)
                .addIngredients(inputs.get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 106, 24)
                .addItemStack(outputs.get(0))
                .addTooltipCallback(defaultOutputTooltip(recipe.getOutputItemChances().get(0)));
    }

    @Override
    public void draw(FurnaceRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, matrixStack, mouseX, mouseY);

        progressBackground.draw(matrixStack, 69, 24);
        speedBackground.draw(matrixStack, 43, 33);

        progress.draw(matrixStack, 69, 24);
        speed.draw(matrixStack, 43, 33);
    }

}
