package cofh.thermal.expansion.compat.jei;

import cofh.thermal.core.util.managers.dynamo.DisenchantmentFuelManager;
import cofh.thermal.core.util.managers.dynamo.GourmandFuelManager;
import cofh.thermal.core.util.managers.dynamo.StirlingFuelManager;
import cofh.thermal.core.util.managers.machine.BottlerRecipeManager;
import cofh.thermal.core.util.managers.machine.BrewerRecipeManager;
import cofh.thermal.core.util.managers.machine.FurnaceRecipeManager;
import cofh.thermal.core.util.recipes.dynamo.*;
import cofh.thermal.core.util.recipes.machine.*;
import cofh.thermal.expansion.client.gui.dynamo.*;
import cofh.thermal.expansion.client.gui.machine.*;
import cofh.thermal.expansion.compat.jei.dynamo.*;
import cofh.thermal.expansion.compat.jei.machine.*;
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
import static cofh.thermal.core.init.TCoreRecipeTypes.*;
import static cofh.thermal.lib.common.ThermalIDs.*;

@JeiPlugin
public class TExpJeiPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

        RecipeManager recipeManager = getRecipeManager();
        if (recipeManager == null) {
            // TODO: Log an error.
            return;
        }
        registration.addRecipes(FURNACE, recipeManager.getAllRecipesFor(RECIPE_FURNACE));
        registration.addRecipes(FURNACE, FurnaceRecipeManager.instance().getConvertedRecipes());

        registration.addRecipes(SAWMILL, recipeManager.getAllRecipesFor(RECIPE_SAWMILL));

        registration.addRecipes(PULVERIZER, recipeManager.getAllRecipesFor(RECIPE_PULVERIZER));
        registration.addRecipes(PULVERIZER, new ArrayList<>(recipeManager.getAllRecipesFor(RECIPE_PULVERIZER_RECYCLE)));
        registration.addRecipes(PULVERIZER_CATALYST, recipeManager.getAllRecipesFor(CATALYST_PULVERIZER));

        registration.addRecipes(SMELTER, recipeManager.getAllRecipesFor(RECIPE_SMELTER));
        registration.addRecipes(SMELTER, new ArrayList<>(recipeManager.getAllRecipesFor(RECIPE_SMELTER_RECYCLE)));
        registration.addRecipes(SMELTER_CATALYST, recipeManager.getAllRecipesFor(CATALYST_SMELTER));

        registration.addRecipes(INSOLATOR, recipeManager.getAllRecipesFor(RECIPE_INSOLATOR));
        registration.addRecipes(INSOLATOR_CATALYST, recipeManager.getAllRecipesFor(CATALYST_INSOLATOR));

        registration.addRecipes(CENTRIFUGE, recipeManager.getAllRecipesFor(RECIPE_CENTRIFUGE));
        registration.addRecipes(PRESS, recipeManager.getAllRecipesFor(RECIPE_PRESS));
        registration.addRecipes(CRUCIBLE, recipeManager.getAllRecipesFor(RECIPE_CRUCIBLE));
        registration.addRecipes(CHILLER, recipeManager.getAllRecipesFor(RECIPE_CHILLER));
        registration.addRecipes(REFINERY, recipeManager.getAllRecipesFor(RECIPE_REFINERY));
        registration.addRecipes(PYROLYZER, recipeManager.getAllRecipesFor(RECIPE_PYROLYZER));

        registration.addRecipes(BREWER, recipeManager.getAllRecipesFor(RECIPE_BREWER));
        registration.addRecipes(BREWER, BrewerRecipeManager.instance().getConvertedRecipes());

        registration.addRecipes(BOTTLER, recipeManager.getAllRecipesFor(RECIPE_BOTTLER));
        registration.addRecipes(BOTTLER, BottlerRecipeManager.instance().getConvertedRecipes());

        registration.addRecipes(CRYSTALLIZER, recipeManager.getAllRecipesFor(RECIPE_CRYSTALLIZER));

        registration.addRecipes(STIRLING_FUEL, recipeManager.getAllRecipesFor(FUEL_STIRLING));
        registration.addRecipes(STIRLING_FUEL, StirlingFuelManager.instance().getConvertedFuels());

        registration.addRecipes(COMPRESSION_FUEL, recipeManager.getAllRecipesFor(FUEL_COMPRESSION));
        registration.addRecipes(MAGMATIC_FUEL, recipeManager.getAllRecipesFor(FUEL_MAGMATIC));
        registration.addRecipes(NUMISMATIC_FUEL, recipeManager.getAllRecipesFor(FUEL_NUMISMATIC));
        registration.addRecipes(LAPIDARY_FUEL, recipeManager.getAllRecipesFor(FUEL_LAPIDARY));

        registration.addRecipes(DISENCHANTMENT_FUEL, recipeManager.getAllRecipesFor(FUEL_DISENCHANTMENT));
        registration.addRecipes(DISENCHANTMENT_FUEL, DisenchantmentFuelManager.instance().getConvertedFuels());

        registration.addRecipes(GOURMAND_FUEL, recipeManager.getAllRecipesFor(FUEL_GOURMAND));
        registration.addRecipes(GOURMAND_FUEL, GourmandFuelManager.instance().getConvertedFuels());
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

        registration.addRecipeCategories(new FurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_FURNACE)), ID_RECIPE_FURNACE));
        registration.addRecipeCategories(new SawmillRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_SAWMILL)), ID_RECIPE_SAWMILL));
        registration.addRecipeCategories(new PulverizerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_PULVERIZER)), ID_RECIPE_PULVERIZER));
        registration.addRecipeCategories(new SmelterRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_SMELTER)), ID_RECIPE_SMELTER));
        registration.addRecipeCategories(new InsolatorRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_INSOLATOR)), ID_RECIPE_INSOLATOR));
        registration.addRecipeCategories(new CentrifugeRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_CENTRIFUGE)), ID_RECIPE_CENTRIFUGE));
        registration.addRecipeCategories(new PressRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_PRESS)), ID_RECIPE_PRESS));
        registration.addRecipeCategories(new CrucibleRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_CRUCIBLE)), ID_RECIPE_CRUCIBLE));
        registration.addRecipeCategories(new ChillerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_CHILLER)), ID_RECIPE_CHILLER));
        registration.addRecipeCategories(new RefineryRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_REFINERY)), ID_RECIPE_REFINERY));
        registration.addRecipeCategories(new PyrolyzerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_PYROLYZER)), ID_RECIPE_PYROLYZER));
        registration.addRecipeCategories(new BrewerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_BREWER)), ID_RECIPE_BREWER));
        registration.addRecipeCategories(new BottlerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_BOTTLER)), ID_RECIPE_BOTTLER));
        registration.addRecipeCategories(new CrystallizerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_MACHINE_CRYSTALLIZER)), ID_RECIPE_CRYSTALLIZER));

        registration.addRecipeCategories(new PulverizerCatalystCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(ITEMS.get("basalz_powder")), ID_CATALYST_PULVERIZER));
        registration.addRecipeCategories(new SmelterCatalystCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(ITEMS.get("cinnabar")), ID_CATALYST_SMELTER));
        registration.addRecipeCategories(new InsolatorCatalystCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(ITEMS.get("phytogro")), ID_CATALYST_INSOLATOR));

        registration.addRecipeCategories(new StirlingFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_STIRLING)), ID_FUEL_STIRLING));
        registration.addRecipeCategories(new CompressionFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_COMPRESSION)), ID_FUEL_COMPRESSION));
        registration.addRecipeCategories(new MagmaticFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_MAGMATIC)), ID_FUEL_MAGMATIC));
        registration.addRecipeCategories(new NumismaticFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_NUMISMATIC)), ID_FUEL_NUMISMATIC));
        registration.addRecipeCategories(new LapidaryFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_LAPIDARY)), ID_FUEL_LAPIDARY));
        registration.addRecipeCategories(new DisenchantmentFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_DISENCHANTMENT)), ID_FUEL_DISENCHANTMENT));
        registration.addRecipeCategories(new GourmandFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(BLOCKS.get(ID_DYNAMO_GOURMAND)), ID_FUEL_GOURMAND));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

        int progressY = 35;
        int progressW = 24;
        int progressH = 16;

        registration.addRecipeClickArea(MachineFurnaceScreen.class, 79, progressY, progressW, progressH, FURNACE);
        registration.addRecipeClickArea(MachineSawmillScreen.class, 72, progressY, progressW, progressH, SAWMILL);
        registration.addRecipeClickArea(MachinePulverizerScreen.class, 72, progressY, progressW, progressH, PULVERIZER);
        registration.addRecipeClickArea(MachineSmelterScreen.class, 94, progressY, progressW, progressH, SMELTER);
        registration.addRecipeClickArea(MachineInsolatorScreen.class, 85, progressY, progressW, progressH, INSOLATOR);
        registration.addRecipeClickArea(MachineCentrifugeScreen.class, 72, progressY, progressW, progressH, CENTRIFUGE);
        registration.addRecipeClickArea(MachinePressScreen.class, 79, progressY, progressW, progressH, PRESS);
        registration.addRecipeClickArea(MachineCrucibleScreen.class, 84, progressY, progressW, progressH, CRUCIBLE);
        registration.addRecipeClickArea(MachineChillerScreen.class, 88, progressY, progressW, progressH, CHILLER);
        registration.addRecipeClickArea(MachineRefineryScreen.class, 65, progressY, progressW, progressH, REFINERY);
        registration.addRecipeClickArea(MachinePyrolyzerScreen.class, 72, progressY, progressW, progressH, PYROLYZER);
        registration.addRecipeClickArea(MachineBottlerScreen.class, 88, progressY, progressW, progressH, BOTTLER);
        registration.addRecipeClickArea(MachineBrewerScreen.class, 88, progressY, progressW, progressH, BREWER);
        registration.addRecipeClickArea(MachineCrystallizerScreen.class, 105, progressY, progressW, progressH, CRYSTALLIZER);
        registration.addRecipeClickArea(MachineCrystallizerScreen.class, 110, 22, progressW, progressH, RecipeTypes.CRAFTING);

        registration.addRecipeClickArea(MachinePulverizerScreen.class, 72, progressY, progressW, progressH, PULVERIZER_CATALYST);
        registration.addRecipeClickArea(MachineSmelterScreen.class, 94, progressY, progressW, progressH, SMELTER_CATALYST);
        registration.addRecipeClickArea(MachineInsolatorScreen.class, 85, progressY, progressW, progressH, INSOLATOR_CATALYST);

        registration.addRecipeClickArea(DynamoStirlingScreen.class, 80, progressY, progressH, progressH, STIRLING_FUEL);
        registration.addRecipeClickArea(DynamoCompressionScreen.class, 80, progressY, progressH, progressH, COMPRESSION_FUEL);
        registration.addRecipeClickArea(DynamoMagmaticScreen.class, 80, progressY, progressH, progressH, MAGMATIC_FUEL);
        registration.addRecipeClickArea(DynamoNumismaticScreen.class, 80, progressY, progressH, progressH, NUMISMATIC_FUEL);
        registration.addRecipeClickArea(DynamoLapidaryScreen.class, 80, progressY, progressH, progressH, LAPIDARY_FUEL);
        registration.addRecipeClickArea(DynamoDisenchantmentScreen.class, 80, progressY, progressH, progressH, DISENCHANTMENT_FUEL);
        registration.addRecipeClickArea(DynamoGourmandScreen.class, 80, progressY, progressH, progressH, GOURMAND_FUEL);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_FURNACE)), FURNACE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_SAWMILL)), SAWMILL);

        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_PULVERIZER)), PULVERIZER);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_PULVERIZER)), PULVERIZER_CATALYST);

        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_SMELTER)), SMELTER);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_SMELTER)), SMELTER_CATALYST);

        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_INSOLATOR)), INSOLATOR);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_INSOLATOR)), INSOLATOR_CATALYST);

        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_CENTRIFUGE)), CENTRIFUGE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_PRESS)), PRESS);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_CRUCIBLE)), CRUCIBLE);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_CHILLER)), CHILLER);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_REFINERY)), REFINERY);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_PYROLYZER)), PYROLYZER);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_BOTTLER)), BOTTLER);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_BREWER)), BREWER);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_CRYSTALLIZER)), CRYSTALLIZER);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_MACHINE_CRAFTER)), RecipeTypes.CRAFTING);

        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_STIRLING)), STIRLING_FUEL);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_COMPRESSION)), COMPRESSION_FUEL);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_MAGMATIC)), MAGMATIC_FUEL);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_NUMISMATIC)), NUMISMATIC_FUEL);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_LAPIDARY)), LAPIDARY_FUEL);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_DISENCHANTMENT)), DISENCHANTMENT_FUEL);
        registration.addRecipeCatalyst(new ItemStack(BLOCKS.get(ID_DYNAMO_GOURMAND)), GOURMAND_FUEL);
    }

    @Override
    public void registerAdvanced(IAdvancedRegistration registration) {

        registration.addRecipeManagerPlugin(new PotionFluidRecipeManagerPlugin());
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
    public static final RecipeType<FurnaceRecipe> FURNACE = new RecipeType<>(ID_RECIPE_FURNACE, FurnaceRecipe.class);
    public static final RecipeType<SawmillRecipe> SAWMILL = new RecipeType<>(ID_RECIPE_SAWMILL, SawmillRecipe.class);
    public static final RecipeType<PulverizerRecipe> PULVERIZER = new RecipeType<>(ID_RECIPE_PULVERIZER, PulverizerRecipe.class);
    public static final RecipeType<SmelterRecipe> SMELTER = new RecipeType<>(ID_RECIPE_SMELTER, SmelterRecipe.class);
    public static final RecipeType<InsolatorRecipe> INSOLATOR = new RecipeType<>(ID_RECIPE_INSOLATOR, InsolatorRecipe.class);
    public static final RecipeType<CentrifugeRecipe> CENTRIFUGE = new RecipeType<>(ID_RECIPE_CENTRIFUGE, CentrifugeRecipe.class);
    public static final RecipeType<PressRecipe> PRESS = new RecipeType<>(ID_RECIPE_PRESS, PressRecipe.class);
    public static final RecipeType<CrucibleRecipe> CRUCIBLE = new RecipeType<>(ID_RECIPE_CRUCIBLE, CrucibleRecipe.class);
    public static final RecipeType<ChillerRecipe> CHILLER = new RecipeType<>(ID_RECIPE_CHILLER, ChillerRecipe.class);
    public static final RecipeType<RefineryRecipe> REFINERY = new RecipeType<>(ID_RECIPE_REFINERY, RefineryRecipe.class);
    public static final RecipeType<PyrolyzerRecipe> PYROLYZER = new RecipeType<>(ID_RECIPE_PYROLYZER, PyrolyzerRecipe.class);
    public static final RecipeType<BottlerRecipe> BOTTLER = new RecipeType<>(ID_RECIPE_BOTTLER, BottlerRecipe.class);
    public static final RecipeType<CrystallizerRecipe> CRYSTALLIZER = new RecipeType<>(ID_RECIPE_CRYSTALLIZER, CrystallizerRecipe.class);
    public static final RecipeType<BrewerRecipe> BREWER = new RecipeType<>(ID_RECIPE_BREWER, BrewerRecipe.class);

    public static final RecipeType<PulverizerCatalyst> PULVERIZER_CATALYST = new RecipeType<>(ID_CATALYST_PULVERIZER, PulverizerCatalyst.class);
    public static final RecipeType<SmelterCatalyst> SMELTER_CATALYST = new RecipeType<>(ID_CATALYST_SMELTER, SmelterCatalyst.class);
    public static final RecipeType<InsolatorCatalyst> INSOLATOR_CATALYST = new RecipeType<>(ID_CATALYST_INSOLATOR, InsolatorCatalyst.class);

    public static final RecipeType<StirlingFuel> STIRLING_FUEL = new RecipeType<>(ID_FUEL_STIRLING, StirlingFuel.class);
    public static final RecipeType<CompressionFuel> COMPRESSION_FUEL = new RecipeType<>(ID_FUEL_COMPRESSION, CompressionFuel.class);
    public static final RecipeType<MagmaticFuel> MAGMATIC_FUEL = new RecipeType<>(ID_FUEL_MAGMATIC, MagmaticFuel.class);
    public static final RecipeType<NumismaticFuel> NUMISMATIC_FUEL = new RecipeType<>(ID_FUEL_NUMISMATIC, NumismaticFuel.class);
    public static final RecipeType<LapidaryFuel> LAPIDARY_FUEL = new RecipeType<>(ID_FUEL_LAPIDARY, LapidaryFuel.class);
    public static final RecipeType<DisenchantmentFuel> DISENCHANTMENT_FUEL = new RecipeType<>(ID_FUEL_DISENCHANTMENT, DisenchantmentFuel.class);
    public static final RecipeType<GourmandFuel> GOURMAND_FUEL = new RecipeType<>(ID_FUEL_GOURMAND, GourmandFuel.class);
    // endregion
}
