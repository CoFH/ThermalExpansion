package cofh.thermal.expansion.compat.jei;

import cofh.thermal.core.util.recipes.dynamo.*;
import cofh.thermal.core.util.recipes.machine.*;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;

import static cofh.thermal.core.init.registries.TCoreRecipeTypes.*;

public class TExpJeiRecipeTypes {

    private TExpJeiRecipeTypes() {

    }

    public static final RecipeType<RecipeHolder<FurnaceRecipe>> FURNACE_TYPE = RecipeType.createFromVanilla(FURNACE_RECIPE.get());
    public static final RecipeType<RecipeHolder<SawmillRecipe>> SAWMILL_TYPE = RecipeType.createFromVanilla(SAWMILL_RECIPE.get());
    public static final RecipeType<RecipeHolder<PulverizerRecipe>> PULVERIZER_TYPE = RecipeType.createFromVanilla(PULVERIZER_RECIPE.get());
    public static final RecipeType<RecipeHolder<SmelterRecipe>> SMELTER_TYPE = RecipeType.createFromVanilla(SMELTER_RECIPE.get());
    public static final RecipeType<RecipeHolder<InsolatorRecipe>> INSOLATOR_TYPE = RecipeType.createFromVanilla(INSOLATOR_RECIPE.get());
    public static final RecipeType<RecipeHolder<CentrifugeRecipe>> CENTRIFUGE_TYPE = RecipeType.createFromVanilla(CENTRIFUGE_RECIPE.get());
    public static final RecipeType<RecipeHolder<PressRecipe>> PRESS_TYPE = RecipeType.createFromVanilla(PRESS_RECIPE.get());
    public static final RecipeType<RecipeHolder<CrucibleRecipe>> CRUCIBLE_TYPE = RecipeType.createFromVanilla(CRUCIBLE_RECIPE.get());
    public static final RecipeType<RecipeHolder<ChillerRecipe>> CHILLER_TYPE = RecipeType.createFromVanilla(CHILLER_RECIPE.get());
    public static final RecipeType<RecipeHolder<RefineryRecipe>> REFINERY_TYPE = RecipeType.createFromVanilla(REFINERY_RECIPE.get());
    public static final RecipeType<RecipeHolder<PyrolyzerRecipe>> PYROLYZER_TYPE = RecipeType.createFromVanilla(PYROLYZER_RECIPE.get());
    public static final RecipeType<RecipeHolder<BottlerRecipe>> BOTTLER_TYPE = RecipeType.createFromVanilla(BOTTLER_RECIPE.get());
    public static final RecipeType<RecipeHolder<BrewerRecipe>> BREWER_TYPE = RecipeType.createFromVanilla(BREWER_RECIPE.get());
    public static final RecipeType<RecipeHolder<CrystallizerRecipe>> CRYSTALLIZER_TYPE = RecipeType.createFromVanilla(CRYSTALLIZER_RECIPE.get());

    public static final RecipeType<RecipeHolder<PulverizerCatalyst>> PULVERIZER_CATALYST_TYPE = RecipeType.createFromVanilla(PULVERIZER_CATALYST.get());
    public static final RecipeType<RecipeHolder<SmelterCatalyst>> SMELTER_CATALYST_TYPE = RecipeType.createFromVanilla(SMELTER_CATALYST.get());
    public static final RecipeType<RecipeHolder<InsolatorCatalyst>> INSOLATOR_CATALYST_TYPE = RecipeType.createFromVanilla(INSOLATOR_CATALYST.get());

    public static final RecipeType<RecipeHolder<StirlingFuel>> STIRLING_FUEL_TYPE = RecipeType.createFromVanilla(STIRLING_FUEL.get());
    public static final RecipeType<RecipeHolder<CompressionFuel>> COMPRESSION_FUEL_TYPE = RecipeType.createFromVanilla(COMPRESSION_FUEL.get());
    public static final RecipeType<RecipeHolder<MagmaticFuel>> MAGMATIC_FUEL_TYPE = RecipeType.createFromVanilla(MAGMATIC_FUEL.get());
    public static final RecipeType<RecipeHolder<NumismaticFuel>> NUMISMATIC_FUEL_TYPE = RecipeType.createFromVanilla(NUMISMATIC_FUEL.get());
    public static final RecipeType<RecipeHolder<LapidaryFuel>> LAPIDARY_FUEL_TYPE = RecipeType.createFromVanilla(LAPIDARY_FUEL.get());
    public static final RecipeType<RecipeHolder<DisenchantmentFuel>> DISENCHANTMENT_FUEL_TYPE = RecipeType.createFromVanilla(DISENCHANTMENT_FUEL.get());
    public static final RecipeType<RecipeHolder<GourmandFuel>> GOURMAND_FUEL_TYPE = RecipeType.createFromVanilla(GOURMAND_FUEL.get());

}
