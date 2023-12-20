package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.managers.machine.SmelterRecipeManager;
import cofh.thermal.core.util.recipes.machine.SmelterRecipe;
import cofh.thermal.expansion.client.gui.machine.MachineSmelterScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalRecipeCategory;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
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
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.catalystTooltip;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.catalyzedOutputTooltip;
import static cofh.thermal.lib.util.ThermalIDs.ID_MACHINE_SMELTER;

public class SmelterRecipeCategory extends ThermalRecipeCategory<SmelterRecipe> {

    public SmelterRecipeCategory(IGuiHelper guiHelper, ItemStack icon, RecipeType<SmelterRecipe> type) {

        super(guiHelper, icon, type);
        energyMod = () -> SmelterRecipeManager.instance().getDefaultScale();

        background = guiHelper.drawableBuilder(MachineSmelterScreen.TEXTURE, 26, 11, 140, 62)
                .addPadding(0, 0, 16, 8)
                .build();
        name = getTextComponent(BLOCKS.get(ID_MACHINE_SMELTER).getDescriptionId());

        progressBackground = Drawables.getDrawables(guiHelper).getProgress(Drawables.PROGRESS_ARROW);
        speedBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);

        progress = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getProgressFill(Drawables.PROGRESS_ARROW), 200, IDrawableAnimated.StartDirection.LEFT, false);
        speed = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public RecipeType<SmelterRecipe> getRecipeType() {

        return type;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, SmelterRecipe recipe, IFocusGroup focuses) {

        List<Ingredient> inputs = recipe.getInputItems();
        List<ItemStack> outputs = new ArrayList<>(recipe.getOutputItems().size());
        List<ItemStack> catalysts = SmelterRecipeManager.instance().getCatalysts();

        for (ItemStack stack : recipe.getOutputItems()) {
            outputs.add(cloneStack(stack));
        }
        for (int i = 0; i < outputs.size(); ++i) {
            float chance = recipe.getOutputItemChances().get(i);
            if (chance > 1.0F) {
                outputs.get(i).setCount((int) chance);
            }
        }
        IRecipeSlotBuilder[] inputSlots = new IRecipeSlotBuilder[3];
        IRecipeSlotBuilder[] outputSlots = new IRecipeSlotBuilder[4];
        IRecipeSlotBuilder catalystSlot;

        inputSlots[0] = builder.addSlot(RecipeIngredientRole.INPUT, 43, 6);
        inputSlots[1] = builder.addSlot(RecipeIngredientRole.INPUT, 25, 6);
        inputSlots[2] = builder.addSlot(RecipeIngredientRole.INPUT, 61, 6);
        catalystSlot = builder.addSlot(RecipeIngredientRole.INPUT, 43, 42);

        for (int i = 0; i < inputs.size(); ++i) {
            inputSlots[i].addIngredients(inputs.get(i));
        }
        if (recipe.isCatalyzable()) {
            catalystSlot.addItemStacks(catalysts)
                    .addTooltipCallback(catalystTooltip());
        }
        outputSlots[0] = builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 15);
        outputSlots[1] = builder.addSlot(RecipeIngredientRole.OUTPUT, 133, 15);
        outputSlots[2] = builder.addSlot(RecipeIngredientRole.OUTPUT, 115, 33);
        outputSlots[3] = builder.addSlot(RecipeIngredientRole.OUTPUT, 133, 33);

        for (int i = 0; i < outputs.size(); ++i) {
            outputSlots[i].addItemStack(outputs.get(i))
                    .addTooltipCallback(catalyzedOutputTooltip(recipe.getOutputItemChances().get(i), recipe.isCatalyzable()));
        }
    }

    @Override
    public void draw(SmelterRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);

        progressBackground.draw(guiGraphics, 84, 24);
        speedBackground.draw(guiGraphics, 43, 24);

        progress.draw(guiGraphics, 84, 24);
        speed.draw(guiGraphics, 43, 24);
    }

}
