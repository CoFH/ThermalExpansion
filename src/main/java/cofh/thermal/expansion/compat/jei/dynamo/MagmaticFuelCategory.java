package cofh.thermal.expansion.compat.jei.dynamo;

import cofh.thermal.core.util.recipes.dynamo.MagmaticFuel;
import cofh.thermal.expansion.client.gui.dynamo.DynamoMagmaticScreen;
import cofh.thermal.lib.compat.jei.Drawables;
import cofh.thermal.lib.compat.jei.ThermalFuelCategory;
import com.mojang.blaze3d.matrix.MatrixStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IGuiFluidStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

import static cofh.lib.util.constants.Constants.TANK_MEDIUM;
import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.compat.jei.TCoreJeiPlugin.*;
import static cofh.thermal.expansion.init.TExpReferences.DYNAMO_MAGMATIC_BLOCK;

public class MagmaticFuelCategory extends ThermalFuelCategory<MagmaticFuel> {

    protected IDrawableStatic tankBackground;
    protected IDrawableStatic tankOverlay;

    public MagmaticFuelCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        background = guiHelper.drawableBuilder(DynamoMagmaticScreen.TEXTURE, 26, 11, 70, 62)
                .addPadding(0, 0, 16, 78)
                .build();
        name = getTextComponent(DYNAMO_MAGMATIC_BLOCK.getTranslationKey());

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
    public void setIngredients(MagmaticFuel fuel, IIngredients ingredients) {

        ingredients.setInputs(VanillaTypes.FLUID, fuel.getInputFluids());
    }

    @Override
    public void setRecipe(IRecipeLayout layout, MagmaticFuel fuel, IIngredients ingredients) {

        List<List<FluidStack>> inputFluids = ingredients.getInputs(VanillaTypes.FLUID);

        IGuiFluidStackGroup guiFluidStacks = layout.getFluidStacks();

        guiFluidStacks.init(0, false, 34, 11, 16, 40, tankSize(TANK_MEDIUM), false, tankOverlay(tankOverlay));

        guiFluidStacks.set(0, inputFluids.get(0));

        addDefaultFluidTooltipCallback(guiFluidStacks);
    }

    @Override
    public void draw(MagmaticFuel fuel, MatrixStack matrixStack, double mouseX, double mouseY) {

        super.draw(fuel, matrixStack, mouseX, mouseY);

        tankBackground.draw(matrixStack, 33, 10);
    }

}
