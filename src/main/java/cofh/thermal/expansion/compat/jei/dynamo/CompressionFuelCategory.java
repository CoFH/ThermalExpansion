package cofh.thermal.expansion.compat.jei.dynamo;

import cofh.lib.fluid.FluidIngredient;
import cofh.thermal.core.util.recipes.dynamo.CompressionFuel;
import cofh.thermal.expansion.client.gui.dynamo.DynamoCompressionScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalFuelCategory;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;

import static cofh.lib.util.Constants.TANK_MEDIUM;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.defaultFluidTooltip;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.tankSize;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_COMPRESSION_BLOCK;

public class CompressionFuelCategory extends ThermalFuelCategory<CompressionFuel> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public CompressionFuelCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(DynamoCompressionScreen.TEXTURE, 26, 11, 70, 62)
                .addPadding(0, 0, 16, 78)
                .build();
        name = getTextComponent(DYNAMO_COMPRESSION_BLOCK.getDescriptionId());

        durationBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);
        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);
        duration = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends CompressionFuel> getRecipeClass() {

        return CompressionFuel.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CompressionFuel fuel, IFocusGroup focuses) {

        List<FluidIngredient> inputs = fuel.getInputFluids();

        builder.addSlot(RecipeIngredientRole.INPUT, 34, 11)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(inputs.get(0).getFluids()))
                .setFluidRenderer(tankSize(TANK_MEDIUM), false, 16, 40)
                .setOverlay(tankOverlay, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());
    }

    @Override
    public void draw(CompressionFuel recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {

        super.draw(recipe, recipeSlotsView, matrixStack, mouseX, mouseY);
        tankBackground.draw(matrixStack, 33, 10);
    }

}
