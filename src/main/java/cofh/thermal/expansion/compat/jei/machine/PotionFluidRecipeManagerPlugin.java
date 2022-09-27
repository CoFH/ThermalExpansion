package cofh.thermal.expansion.compat.jei.machine;

import cofh.core.fluid.PotionFluid;
import cofh.lib.fluid.FluidIngredient;
import cofh.thermal.core.util.managers.machine.BottlerRecipeManager;
import cofh.thermal.core.util.recipes.machine.BottlerRecipe;
import cofh.thermal.expansion.compat.jei.TExpJeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.advanced.IRecipeManagerPlugin;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static cofh.core.init.CoreFluids.POTION_FLUID;
import static cofh.lib.util.Constants.BOTTLE_VOLUME;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;

public class PotionFluidRecipeManagerPlugin implements IRecipeManagerPlugin {

    @Override
    public <V> List<RecipeType<?>> getRecipeTypes(IFocus<V> focus) {

        return List.of(TExpJeiPlugin.BOTTLER);
    }

    @Override
    public <T, V> List<T> getRecipes(IRecipeCategory<T> recipeCategory, IFocus<V> focus) {

        if (recipeCategory instanceof BottlerRecipeCategory) {
            List<BottlerRecipe> retList = new ArrayList<>();
            if (focus.getRole() == RecipeIngredientRole.INPUT) {
                var fluidIngredient = focus.getTypedValue().getIngredient(ForgeTypes.FLUID_STACK);
                if (fluidIngredient.isPresent() && fluidIngredient.get().getFluid() == POTION_FLUID.get()) {
                    FluidStack fluid = fluidIngredient.get();
                    if (fluid.hasTag()) {
                        ItemStack item = new ItemStack(Items.POTION);
                        item.setTag(fluid.getTag().copy());
                        retList.add(getDynamicBottlerPotionRecipe(item, fluid));
                    }
                }
            } else if (focus.getRole() == RecipeIngredientRole.OUTPUT) {
                var ingredient = focus.getTypedValue().getIngredient(VanillaTypes.ITEM);
                if (ingredient.isPresent() && ingredient.get().getItem() == Items.POTION) {
                    ItemStack item = ingredient.get();
                    if (item.hasTag()) {
                        FluidStack fluid = PotionFluid.getPotionFluidFromItem(BOTTLE_VOLUME, item);
                        retList.add(getDynamicBottlerPotionRecipe(item, fluid));
                    }
                }
            }
            return (List<T>) retList;
        }
        return List.of();
    }

    @Override
    public <T> List<T> getRecipes(IRecipeCategory<T> recipeCategory) {

        if (recipeCategory instanceof BottlerRecipeCategory) {
            if (bottlerRecipes.isEmpty()) {
                for (Potion potion : ForgeRegistries.POTIONS) {
                    if (potion != null && potion != Potions.WATER && potion != Potions.EMPTY) {
                        FluidStack fluid = PotionFluid.getPotionAsFluid(250, potion);
                        if (fluid.isEmpty()) {
                            continue;
                        }
                        ItemStack item = new ItemStack(Items.POTION);
                        item.setTag(fluid.getTag());
                        bottlerRecipes.add(getDynamicBottlerPotionRecipe(item, fluid));
                    }
                }
            }
            return (List<T>) bottlerRecipes;
        }
        return List.of();
    }

    @Override
    public <V> List<ResourceLocation> getRecipeCategoryUids(IFocus<V> focus) {

        return List.of(TExpJeiPlugin.BOTTLER.getUid());
    }

    // region HELPERS
    private final List<BottlerRecipe> bottlerRecipes = new ArrayList<>();

    @NotNull
    private BottlerRecipe getDynamicBottlerPotionRecipe(ItemStack item, FluidStack fluid) {

        return new BottlerRecipe(new ResourceLocation(ID_THERMAL, "bottler_potion_" + item.hashCode()),
                BottlerRecipeManager.instance().getDefaultEnergy(), 0.0F,
                List.of(Ingredient.of(Items.GLASS_BOTTLE)),
                List.of(FluidIngredient.of(fluid).setAmount(BOTTLE_VOLUME)),
                List.of(item),
                List.of(1.0F),
                List.of()
        );
    }
    // endregion
}
