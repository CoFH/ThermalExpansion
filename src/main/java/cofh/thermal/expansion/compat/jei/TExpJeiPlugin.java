package cofh.thermal.expansion.compat.jei;

import cofh.thermal.core.util.managers.dynamo.DisenchantmentFuelManager;
import cofh.thermal.core.util.managers.dynamo.GourmandFuelManager;
import cofh.thermal.core.util.managers.dynamo.StirlingFuelManager;
import cofh.thermal.core.util.managers.machine.*;
import cofh.thermal.core.util.recipes.dynamo.*;
import cofh.thermal.core.util.recipes.machine.*;
import cofh.thermal.expansion.client.gui.dynamo.*;
import cofh.thermal.expansion.client.gui.machine.*;
import cofh.thermal.expansion.compat.jei.dynamo.*;
import cofh.thermal.expansion.compat.jei.machine.*;
import cofh.thermal.expansion.compat.jei.plugins.PotionFluidRecipeManagerPlugin;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.ArrayList;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.registries.TCoreRecipeTypes.*;
import static cofh.thermal.lib.util.ThermalIDs.*;

@JeiPlugin
public class TExpJeiPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

        RecipeManager recipeManager = getRecipeManager();
        if (recipeManager == null) {
            // TODO: Log an error.
            return;
        }
        registration.addRecipes(FURNACE_TYPE, recipeManager.getAllRecipesFor(FURNACE_RECIPE.get()));
        registration.addRecipes(FURNACE_TYPE, FurnaceRecipeManager.instance().getConvertedRecipes());
        registration.addRecipes(SAWMILL_TYPE, recipeManager.getAllRecipesFor(SAWMILL_RECIPE.get()));
        registration.addRecipes(SAWMILL_TYPE, SawmillRecipeManager.instance().getConvertedRecipes());
        registration.addRecipes(PULVERIZER_TYPE, recipeManager.getAllRecipesFor(PULVERIZER_RECIPE.get()));
        registration.addRecipes(PULVERIZER_TYPE, new ArrayList<>(recipeManager.getAllRecipesFor(PULVERIZER_RECYCLE_RECIPE.get())));
        registration.addRecipes(PULVERIZER_TYPE, PulverizerRecipeManager.instance().getConvertedRecipes());
        registration.addRecipes(SMELTER_TYPE, recipeManager.getAllRecipesFor(SMELTER_RECIPE.get()));
        registration.addRecipes(SMELTER_TYPE, new ArrayList<>(recipeManager.getAllRecipesFor(SMELTER_RECYCLE_RECIPE.get())));
        registration.addRecipes(SMELTER_TYPE, SmelterRecipeManager.instance().getConvertedRecipes());
        registration.addRecipes(INSOLATOR_TYPE, recipeManager.getAllRecipesFor(INSOLATOR_RECIPE.get()));
        registration.addRecipes(CENTRIFUGE_TYPE, recipeManager.getAllRecipesFor(CENTRIFUGE_RECIPE.get()));
        registration.addRecipes(PRESS_TYPE, recipeManager.getAllRecipesFor(PRESS_RECIPE.get()));
        registration.addRecipes(CRUCIBLE_TYPE, recipeManager.getAllRecipesFor(CRUCIBLE_RECIPE.get()));
        registration.addRecipes(CHILLER_TYPE, recipeManager.getAllRecipesFor(CHILLER_RECIPE.get()));
        registration.addRecipes(REFINERY_TYPE, recipeManager.getAllRecipesFor(REFINERY_RECIPE.get()));
        registration.addRecipes(PYROLYZER_TYPE, recipeManager.getAllRecipesFor(PYROLYZER_RECIPE.get()));
        registration.addRecipes(BOTTLER_TYPE, recipeManager.getAllRecipesFor(BOTTLER_RECIPE.get()));
        registration.addRecipes(BOTTLER_TYPE, BottlerRecipeManager.instance().getConvertedRecipes());
        registration.addRecipes(BREWER_TYPE, recipeManager.getAllRecipesFor(BREWER_RECIPE.get()));
        registration.addRecipes(BREWER_TYPE, BrewerRecipeManager.instance().getConvertedRecipes());
        registration.addRecipes(CRYSTALLIZER_TYPE, recipeManager.getAllRecipesFor(CRYSTALLIZER_RECIPE.get()));

        registration.addRecipes(PULVERIZER_CATALYST_TYPE, recipeManager.getAllRecipesFor(PULVERIZER_CATALYST.get()));
        registration.addRecipes(SMELTER_CATALYST_TYPE, recipeManager.getAllRecipesFor(SMELTER_CATALYST.get()));
        registration.addRecipes(INSOLATOR_CATALYST_TYPE, recipeManager.getAllRecipesFor(INSOLATOR_CATALYST.get()));

        registration.addRecipes(STIRLING_FUEL_TYPE, recipeManager.getAllRecipesFor(STIRLING_FUEL.get()));
        registration.addRecipes(STIRLING_FUEL_TYPE, StirlingFuelManager.instance().getConvertedFuels());
        registration.addRecipes(COMPRESSION_FUEL_TYPE, recipeManager.getAllRecipesFor(COMPRESSION_FUEL.get()));
        registration.addRecipes(MAGMATIC_FUEL_TYPE, recipeManager.getAllRecipesFor(MAGMATIC_FUEL.get()));
        registration.addRecipes(NUMISMATIC_FUEL_TYPE, recipeManager.getAllRecipesFor(NUMISMATIC_FUEL.get()));
        registration.addRecipes(LAPIDARY_FUEL_TYPE, recipeManager.getAllRecipesFor(LAPIDARY_FUEL.get()));
        registration.addRecipes(DISENCHANTMENT_FUEL_TYPE, recipeManager.getAllRecipesFor(DISENCHANTMENT_FUEL.get()));
        registration.addRecipes(DISENCHANTMENT_FUEL_TYPE, DisenchantmentFuelManager.instance().getConvertedFuels());
        registration.addRecipes(GOURMAND_FUEL_TYPE, recipeManager.getAllRecipesFor(GOURMAND_FUEL.get()));
        registration.addRecipes(GOURMAND_FUEL_TYPE, GourmandFuelManager.instance().getConvertedFuels());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

        registration.addRecipeCategories(new FurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_FURNACE)), FURNACE_TYPE));
        registration.addRecipeCategories(new SawmillRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_SAWMILL)), SAWMILL_TYPE));
        registration.addRecipeCategories(new PulverizerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_PULVERIZER)), PULVERIZER_TYPE));
        registration.addRecipeCategories(new SmelterRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_SMELTER)), SMELTER_TYPE));
        registration.addRecipeCategories(new InsolatorRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_INSOLATOR)), INSOLATOR_TYPE));
        registration.addRecipeCategories(new CentrifugeRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_CENTRIFUGE)), CENTRIFUGE_TYPE));
        registration.addRecipeCategories(new PressRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_PRESS)), PRESS_TYPE));
        registration.addRecipeCategories(new CrucibleRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_CRUCIBLE)), CRUCIBLE_TYPE));
        registration.addRecipeCategories(new ChillerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_CHILLER)), CHILLER_TYPE));
        registration.addRecipeCategories(new RefineryRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_REFINERY)), REFINERY_TYPE));
        registration.addRecipeCategories(new PyrolyzerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_PYROLYZER)), PYROLYZER_TYPE));
        registration.addRecipeCategories(new BottlerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_BOTTLER)), BOTTLER_TYPE));
        registration.addRecipeCategories(new BrewerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_BREWER)), BREWER_TYPE));
        registration.addRecipeCategories(new CrystallizerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_CRYSTALLIZER)), CRYSTALLIZER_TYPE));

        registration.addRecipeCategories(new PulverizerCatalystCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(ITEMS.get("basalz_powder")), PULVERIZER_CATALYST_TYPE));
        registration.addRecipeCategories(new SmelterCatalystCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(ITEMS.get("cinnabar")), SMELTER_CATALYST_TYPE));
        registration.addRecipeCategories(new InsolatorCatalystCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(ITEMS.get("phytogro")), INSOLATOR_CATALYST_TYPE));

        registration.addRecipeCategories(new StirlingFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_STIRLING)), STIRLING_FUEL_TYPE));
        registration.addRecipeCategories(new CompressionFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_COMPRESSION)), COMPRESSION_FUEL_TYPE));
        registration.addRecipeCategories(new MagmaticFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_MAGMATIC)), MAGMATIC_FUEL_TYPE));
        registration.addRecipeCategories(new NumismaticFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_NUMISMATIC)), NUMISMATIC_FUEL_TYPE));
        registration.addRecipeCategories(new LapidaryFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_LAPIDARY)), LAPIDARY_FUEL_TYPE));
        registration.addRecipeCategories(new DisenchantmentFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_DISENCHANTMENT)), DISENCHANTMENT_FUEL_TYPE));
        registration.addRecipeCategories(new GourmandFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_GOURMAND)), GOURMAND_FUEL_TYPE));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

        int progressY = 35;
        int progressW = 24;
        int progressH = 16;

        registration.addRecipeClickArea(MachineFurnaceScreen.class, 79, progressY, progressW, progressH, FURNACE_TYPE);
        registration.addRecipeClickArea(MachineSawmillScreen.class, 72, progressY, progressW, progressH, SAWMILL_TYPE);
        registration.addRecipeClickArea(MachinePulverizerScreen.class, 72, progressY, progressW, progressH, PULVERIZER_TYPE);
        registration.addRecipeClickArea(MachineSmelterScreen.class, 94, progressY, progressW, progressH, SMELTER_TYPE);
        registration.addRecipeClickArea(MachineInsolatorScreen.class, 85, progressY, progressW, progressH, INSOLATOR_TYPE);
        registration.addRecipeClickArea(MachineCentrifugeScreen.class, 72, progressY, progressW, progressH, CENTRIFUGE_TYPE);
        registration.addRecipeClickArea(MachinePressScreen.class, 79, progressY, progressW, progressH, PRESS_TYPE);
        registration.addRecipeClickArea(MachineCrucibleScreen.class, 84, progressY, progressW, progressH, CRUCIBLE_TYPE);
        registration.addRecipeClickArea(MachineChillerScreen.class, 88, progressY, progressW, progressH, CHILLER_TYPE);
        registration.addRecipeClickArea(MachineRefineryScreen.class, 65, progressY, progressW, progressH, REFINERY_TYPE);
        registration.addRecipeClickArea(MachinePyrolyzerScreen.class, 72, progressY, progressW, progressH, PYROLYZER_TYPE);
        registration.addRecipeClickArea(MachineBottlerScreen.class, 88, progressY, progressW, progressH, BOTTLER_TYPE);
        registration.addRecipeClickArea(MachineBrewerScreen.class, 88, progressY, progressW, progressH, BREWER_TYPE);
        registration.addRecipeClickArea(MachineCrystallizerScreen.class, 105, progressY, progressW, progressH, CRYSTALLIZER_TYPE);
        registration.addRecipeClickArea(MachineCrystallizerScreen.class, 110, 22, progressW, progressH, RecipeTypes.CRAFTING);

        registration.addRecipeClickArea(MachinePulverizerScreen.class, 44, progressY, 16, 16, PULVERIZER_CATALYST_TYPE);
        registration.addRecipeClickArea(MachineSmelterScreen.class, 53, progressY, 16, 16, SMELTER_CATALYST_TYPE);
        registration.addRecipeClickArea(MachineInsolatorScreen.class, 62, progressY, 16, 16, INSOLATOR_CATALYST_TYPE);

        registration.addRecipeClickArea(DynamoStirlingScreen.class, 80, progressY, progressH, progressH, STIRLING_FUEL_TYPE);
        registration.addRecipeClickArea(DynamoCompressionScreen.class, 80, progressY, progressH, progressH, COMPRESSION_FUEL_TYPE);
        registration.addRecipeClickArea(DynamoMagmaticScreen.class, 80, progressY, progressH, progressH, MAGMATIC_FUEL_TYPE);
        registration.addRecipeClickArea(DynamoNumismaticScreen.class, 80, progressY, progressH, progressH, NUMISMATIC_FUEL_TYPE);
        registration.addRecipeClickArea(DynamoLapidaryScreen.class, 80, progressY, progressH, progressH, LAPIDARY_FUEL_TYPE);
        registration.addRecipeClickArea(DynamoDisenchantmentScreen.class, 80, progressY, progressH, progressH, DISENCHANTMENT_FUEL_TYPE);
        registration.addRecipeClickArea(DynamoGourmandScreen.class, 80, progressY, progressH, progressH, GOURMAND_FUEL_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_FURNACE)), FURNACE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_SAWMILL)), SAWMILL_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_PULVERIZER)), PULVERIZER_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_SMELTER)), SMELTER_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_INSOLATOR)), INSOLATOR_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_CENTRIFUGE)), CENTRIFUGE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_PRESS)), PRESS_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_CRUCIBLE)), CRUCIBLE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_CHILLER)), CHILLER_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_REFINERY)), REFINERY_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_PYROLYZER)), PYROLYZER_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_BOTTLER)), BOTTLER_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_BREWER)), BREWER_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_CRYSTALLIZER)), CRYSTALLIZER_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_CRAFTER)), RecipeTypes.CRAFTING);

        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_PULVERIZER)), PULVERIZER_CATALYST_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_SMELTER)), SMELTER_CATALYST_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_INSOLATOR)), INSOLATOR_CATALYST_TYPE);

        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_STIRLING)), STIRLING_FUEL_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_COMPRESSION)), COMPRESSION_FUEL_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_MAGMATIC)), MAGMATIC_FUEL_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_NUMISMATIC)), NUMISMATIC_FUEL_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_LAPIDARY)), LAPIDARY_FUEL_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_DISENCHANTMENT)), DISENCHANTMENT_FUEL_TYPE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_GOURMAND)), GOURMAND_FUEL_TYPE);
    }

    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {

        registration.addRecipeManagerPlugin(PotionFluidRecipeManagerPlugin.INSTANCE);
    }

    @Override
    public ResourceLocation getPluginUid() {

        return new ResourceLocation(ID_THERMAL, "expansion");
    }

    // region HELPERS
    private RecipeManager getRecipeManager() {

        RecipeManager recipeManager = null;
        ClientLevel level = Minecraft.getInstance().level;
        if (level != null) {
            recipeManager = level.getRecipeManager();
        }
        return recipeManager;
    }
    // endregion

    // region RECIPE TYPES
    public static final RecipeType<FurnaceRecipe> FURNACE_TYPE = new RecipeType<>(FURNACE_RECIPE.getId(), FurnaceRecipe.class);
    public static final RecipeType<SawmillRecipe> SAWMILL_TYPE = new RecipeType<>(SAWMILL_RECIPE.getId(), SawmillRecipe.class);
    public static final RecipeType<PulverizerRecipe> PULVERIZER_TYPE = new RecipeType<>(PULVERIZER_RECIPE.getId(), PulverizerRecipe.class);
    public static final RecipeType<SmelterRecipe> SMELTER_TYPE = new RecipeType<>(SMELTER_RECIPE.getId(), SmelterRecipe.class);
    public static final RecipeType<InsolatorRecipe> INSOLATOR_TYPE = new RecipeType<>(INSOLATOR_RECIPE.getId(), InsolatorRecipe.class);
    public static final RecipeType<CentrifugeRecipe> CENTRIFUGE_TYPE = new RecipeType<>(CENTRIFUGE_RECIPE.getId(), CentrifugeRecipe.class);
    public static final RecipeType<PressRecipe> PRESS_TYPE = new RecipeType<>(PRESS_RECIPE.getId(), PressRecipe.class);
    public static final RecipeType<CrucibleRecipe> CRUCIBLE_TYPE = new RecipeType<>(CRUCIBLE_RECIPE.getId(), CrucibleRecipe.class);
    public static final RecipeType<ChillerRecipe> CHILLER_TYPE = new RecipeType<>(CHILLER_RECIPE.getId(), ChillerRecipe.class);
    public static final RecipeType<RefineryRecipe> REFINERY_TYPE = new RecipeType<>(REFINERY_RECIPE.getId(), RefineryRecipe.class);
    public static final RecipeType<PyrolyzerRecipe> PYROLYZER_TYPE = new RecipeType<>(PYROLYZER_RECIPE.getId(), PyrolyzerRecipe.class);
    public static final RecipeType<BottlerRecipe> BOTTLER_TYPE = new RecipeType<>(BOTTLER_RECIPE.getId(), BottlerRecipe.class);
    public static final RecipeType<BrewerRecipe> BREWER_TYPE = new RecipeType<>(BREWER_RECIPE.getId(), BrewerRecipe.class);
    public static final RecipeType<CrystallizerRecipe> CRYSTALLIZER_TYPE = new RecipeType<>(CRYSTALLIZER_RECIPE.getId(), CrystallizerRecipe.class);

    public static final RecipeType<PulverizerCatalyst> PULVERIZER_CATALYST_TYPE = new RecipeType<>(PULVERIZER_CATALYST.getId(), PulverizerCatalyst.class);
    public static final RecipeType<SmelterCatalyst> SMELTER_CATALYST_TYPE = new RecipeType<>(SMELTER_CATALYST.getId(), SmelterCatalyst.class);
    public static final RecipeType<InsolatorCatalyst> INSOLATOR_CATALYST_TYPE = new RecipeType<>(INSOLATOR_CATALYST.getId(), InsolatorCatalyst.class);

    public static final RecipeType<StirlingFuel> STIRLING_FUEL_TYPE = new RecipeType<>(STIRLING_FUEL.getId(), StirlingFuel.class);
    public static final RecipeType<CompressionFuel> COMPRESSION_FUEL_TYPE = new RecipeType<>(COMPRESSION_FUEL.getId(), CompressionFuel.class);
    public static final RecipeType<MagmaticFuel> MAGMATIC_FUEL_TYPE = new RecipeType<>(MAGMATIC_FUEL.getId(), MagmaticFuel.class);
    public static final RecipeType<NumismaticFuel> NUMISMATIC_FUEL_TYPE = new RecipeType<>(NUMISMATIC_FUEL.getId(), NumismaticFuel.class);
    public static final RecipeType<LapidaryFuel> LAPIDARY_FUEL_TYPE = new RecipeType<>(LAPIDARY_FUEL.getId(), LapidaryFuel.class);
    public static final RecipeType<DisenchantmentFuel> DISENCHANTMENT_FUEL_TYPE = new RecipeType<>(DISENCHANTMENT_FUEL.getId(), DisenchantmentFuel.class);
    public static final RecipeType<GourmandFuel> GOURMAND_FUEL_TYPE = new RecipeType<>(GOURMAND_FUEL.getId(), GourmandFuel.class);
    // endregion
}
