package cofh.thermal.expansion.compat.jei.dynamo;

import cofh.lib.fluid.FluidIngredient;
import cofh.thermal.core.util.recipes.dynamo.MagmaticFuel;
import cofh.thermal.expansion.client.gui.dynamo.DynamoMagmaticScreen;
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

import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.defaultFluidTooltip;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.tankSize;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_MAGMATIC_BLOCK;

public class MagmaticFuelCategory extends ThermalFuelCategory<MagmaticFuel> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public MagmaticFuelCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(DynamoMagmaticScreen.TEXTURE, 26, 11, 70, 62)
                .addPadding(0, 0, 16, 78)
                .build();
        name = getTextComponent(DYNAMO_MAGMATIC_BLOCK.getDescriptionId());

        durationBackground = Drawables.getDrawables(guiHelper).getScale(Drawables.SCALE_FLAME);
        tankBackground = Drawables.getDrawables(guiHelper).getTank(Drawables.TANK_MEDIUM);
        tankOverlay = Drawables.getDrawables(guiHelper).getTankOverlay(Drawables.TANK_MEDIUM);
        duration = guiHelper.createAnimatedDrawable(Drawables.getDrawables(guiHelper).getScaleFill(Drawables.SCALE_FLAME), 400, IDrawableAnimated.StartDirection.TOP, true);
    }

    @Override
    public Class<? extends MagmaticFuel> getRecipeClass() {

        return MagmaticFuel.class;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MagmaticFuel fuel, IFocusGroup focuses) {

        List<FluidIngredient> inputs = fuel.getInputFluids();

        builder.addSlot(RecipeIngredientRole.INPUT, 34, 11)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(inputs.get(0).getFluids()))
                .setFluidRenderer(tankSize(TANK_MEDIUM), false, 16, 40)
                .setOverlay(tankOverlay, 0, 0)
                .addTooltipCallback(defaultFluidTooltip());
    }

    @Override
    public void draw(MagmaticFuel fuel, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {

        super.draw(fuel, recipeSlotsView, matrixStack, mouseX, mouseY);
        tankBackground.draw(matrixStack, 33, 10);
    }

}
