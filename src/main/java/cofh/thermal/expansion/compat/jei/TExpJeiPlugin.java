package cofh.thermal.expansion.compat.jei;

import cofh.thermal.core.util.managers.dynamo.StirlingFuelManager;
import cofh.thermal.core.util.managers.machine.BottlerRecipeManager;
import cofh.thermal.core.util.managers.machine.BrewerRecipeManager;
import cofh.thermal.core.util.managers.machine.FurnaceRecipeManager;
import cofh.thermal.expansion.client.gui.dynamo.*;
import cofh.thermal.expansion.client.gui.machine.*;
import cofh.thermal.expansion.compat.jei.dynamo.*;
import cofh.thermal.expansion.compat.jei.machine.*;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.core.init.TCoreRecipeTypes.*;
import static cofh.thermal.expansion.init.TExpReferences.*;

@JeiPlugin
public class TExpJeiPlugin implements IModPlugin {

    @Override
    public void registerRecipes(IRecipeRegistration registration) {

        RecipeManager recipeManager = getRecipeManager();
        if (recipeManager == null) {
            // TODO: Log an error.
            return;
        }
        registration.addRecipes(recipeManager.getRecipes(RECIPE_FURNACE).values(), ID_RECIPE_FURNACE);
        registration.addRecipes(FurnaceRecipeManager.instance().getConvertedRecipes(), ID_RECIPE_FURNACE);

        registration.addRecipes(recipeManager.getRecipes(RECIPE_SAWMILL).values(), ID_RECIPE_SAWMILL);

        registration.addRecipes(recipeManager.getRecipes(RECIPE_PULVERIZER).values(), ID_RECIPE_PULVERIZER);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_PULVERIZER_RECYCLE).values(), ID_RECIPE_PULVERIZER);
        registration.addRecipes(recipeManager.getRecipes(CATALYST_PULVERIZER).values(), ID_CATALYST_PULVERIZER);

        registration.addRecipes(recipeManager.getRecipes(RECIPE_SMELTER).values(), ID_RECIPE_SMELTER);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_SMELTER_RECYCLE).values(), ID_RECIPE_SMELTER);
        registration.addRecipes(recipeManager.getRecipes(CATALYST_SMELTER).values(), ID_CATALYST_SMELTER);

        registration.addRecipes(recipeManager.getRecipes(RECIPE_INSOLATOR).values(), ID_RECIPE_INSOLATOR);
        registration.addRecipes(recipeManager.getRecipes(CATALYST_INSOLATOR).values(), ID_CATALYST_INSOLATOR);

        registration.addRecipes(recipeManager.getRecipes(RECIPE_CENTRIFUGE).values(), ID_RECIPE_CENTRIFUGE);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_PRESS).values(), ID_RECIPE_PRESS);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_CRUCIBLE).values(), ID_RECIPE_CRUCIBLE);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_CHILLER).values(), ID_RECIPE_CHILLER);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_REFINERY).values(), ID_RECIPE_REFINERY);
        registration.addRecipes(recipeManager.getRecipes(RECIPE_PYROLYZER).values(), ID_RECIPE_PYROLYZER);

        registration.addRecipes(recipeManager.getRecipes(RECIPE_BREWER).values(), ID_RECIPE_BREWER);
        registration.addRecipes(BrewerRecipeManager.instance().getConvertedRecipes(), ID_RECIPE_BREWER);

        registration.addRecipes(recipeManager.getRecipes(RECIPE_BOTTLER).values(), ID_RECIPE_BOTTLER);
        registration.addRecipes(BottlerRecipeManager.instance().getConvertedRecipes(), ID_RECIPE_BOTTLER);

        registration.addRecipes(recipeManager.getRecipes(FUEL_STIRLING).values(), ID_FUEL_STIRLING);
        registration.addRecipes(StirlingFuelManager.instance().getConvertedFuels(), ID_FUEL_STIRLING);
        registration.addRecipes(recipeManager.getRecipes(FUEL_COMPRESSION).values(), ID_FUEL_COMPRESSION);
        registration.addRecipes(recipeManager.getRecipes(FUEL_MAGMATIC).values(), ID_FUEL_MAGMATIC);
        registration.addRecipes(recipeManager.getRecipes(FUEL_NUMISMATIC).values(), ID_FUEL_NUMISMATIC);
        registration.addRecipes(recipeManager.getRecipes(FUEL_LAPIDARY).values(), ID_FUEL_LAPIDARY);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {

        registration.addRecipeCategories(new FurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_FURNACE_BLOCK), ID_RECIPE_FURNACE));
        registration.addRecipeCategories(new SawmillRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_SAWMILL_BLOCK), ID_RECIPE_SAWMILL));

        registration.addRecipeCategories(new PulverizerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_PULVERIZER_BLOCK), ID_RECIPE_PULVERIZER));
        registration.addRecipeCategories(new PulverizerCatalystCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(ITEMS.get("basalz_powder")), ID_CATALYST_PULVERIZER));

        registration.addRecipeCategories(new SmelterRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_SMELTER_BLOCK), ID_RECIPE_SMELTER));
        registration.addRecipeCategories(new SmelterCatalystCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(ITEMS.get("cinnabar")), ID_CATALYST_SMELTER));

        registration.addRecipeCategories(new InsolatorRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_INSOLATOR_BLOCK), ID_RECIPE_INSOLATOR));
        registration.addRecipeCategories(new InsolatorCatalystCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(ITEMS.get("phytogro")), ID_CATALYST_INSOLATOR));

        registration.addRecipeCategories(new CentrifugeRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_CENTRIFUGE_BLOCK), ID_RECIPE_CENTRIFUGE));
        registration.addRecipeCategories(new PressRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_PRESS_BLOCK), ID_RECIPE_PRESS));
        registration.addRecipeCategories(new CrucibleRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_CRUCIBLE_BLOCK), ID_RECIPE_CRUCIBLE));
        registration.addRecipeCategories(new ChillerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_CHILLER_BLOCK), ID_RECIPE_CHILLER));
        registration.addRecipeCategories(new RefineryRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_REFINERY_BLOCK), ID_RECIPE_REFINERY));
        registration.addRecipeCategories(new PyrolyzerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_PYROLYZER_BLOCK), ID_RECIPE_PYROLYZER));
        registration.addRecipeCategories(new BrewerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_BREWER_BLOCK), ID_RECIPE_BREWER));
        registration.addRecipeCategories(new BottlerRecipeCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(MACHINE_BOTTLER_BLOCK), ID_RECIPE_BOTTLER));

        registration.addRecipeCategories(new StirlingFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(DYNAMO_STIRLING_BLOCK), ID_FUEL_STIRLING));
        registration.addRecipeCategories(new CompressionFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(DYNAMO_COMPRESSION_BLOCK), ID_FUEL_COMPRESSION));
        registration.addRecipeCategories(new MagmaticFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(DYNAMO_MAGMATIC_BLOCK), ID_FUEL_MAGMATIC));
        registration.addRecipeCategories(new NumismaticFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(DYNAMO_NUMISMATIC_BLOCK), ID_FUEL_NUMISMATIC));
        registration.addRecipeCategories(new LapidaryFuelCategory(registration.getJeiHelpers().getGuiHelper(), new ItemStack(DYNAMO_LAPIDARY_BLOCK), ID_FUEL_LAPIDARY));
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {

        int progressY = 34;
        int progressW = 24;
        int progressH = 16;

        registration.addRecipeClickArea(MachineFurnaceScreen.class, 79, progressY, progressW, progressH, ID_RECIPE_FURNACE);
        registration.addRecipeClickArea(MachineSawmillScreen.class, 72, progressY, progressW, progressH, ID_RECIPE_SAWMILL);

        registration.addRecipeClickArea(MachinePulverizerScreen.class, 72, progressY, progressW, progressH, ID_RECIPE_PULVERIZER);
        registration.addRecipeClickArea(MachinePulverizerScreen.class, 72, progressY, progressW, progressH, ID_CATALYST_PULVERIZER);

        registration.addRecipeClickArea(MachineSmelterScreen.class, 94, progressY, progressW, progressH, ID_RECIPE_SMELTER);
        registration.addRecipeClickArea(MachineSmelterScreen.class, 94, progressY, progressW, progressH, ID_CATALYST_SMELTER);

        registration.addRecipeClickArea(MachineInsolatorScreen.class, 85, progressY, progressW, progressH, ID_RECIPE_INSOLATOR);
        registration.addRecipeClickArea(MachineInsolatorScreen.class, 85, progressY, progressW, progressH, ID_CATALYST_INSOLATOR);

        registration.addRecipeClickArea(MachineCentrifugeScreen.class, 72, progressY, progressW, progressH, ID_RECIPE_CENTRIFUGE);
        registration.addRecipeClickArea(MachinePressScreen.class, 79, progressY, progressW, progressH, ID_RECIPE_PRESS);
        registration.addRecipeClickArea(MachineCrucibleScreen.class, 84, progressY, progressW, progressH, ID_RECIPE_CRUCIBLE);
        registration.addRecipeClickArea(MachineChillerScreen.class, 88, progressY, progressW, progressH, ID_RECIPE_CHILLER);
        registration.addRecipeClickArea(MachineRefineryScreen.class, 65, progressY, progressW, progressH, ID_RECIPE_REFINERY);
        registration.addRecipeClickArea(MachinePyrolyzerScreen.class, 72, progressY, progressW, progressH, ID_RECIPE_PYROLYZER);
        registration.addRecipeClickArea(MachineBrewerScreen.class, 88, progressY, progressW, progressH, ID_RECIPE_BREWER);
        registration.addRecipeClickArea(MachineBottlerScreen.class, 88, progressY, progressW, progressH, ID_RECIPE_BOTTLER);

        registration.addRecipeClickArea(DynamoStirlingScreen.class, 80, progressY, progressH, progressH, ID_FUEL_STIRLING);
        registration.addRecipeClickArea(DynamoCompressionScreen.class, 80, progressY, progressH, progressH, ID_FUEL_COMPRESSION);
        registration.addRecipeClickArea(DynamoMagmaticScreen.class, 80, progressY, progressH, progressH, ID_FUEL_MAGMATIC);
        registration.addRecipeClickArea(DynamoNumismaticScreen.class, 80, progressY, progressH, progressH, ID_FUEL_NUMISMATIC);
        registration.addRecipeClickArea(DynamoLapidaryScreen.class, 80, progressY, progressH, progressH, ID_FUEL_LAPIDARY);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

        registration.addRecipeCatalyst(new ItemStack(MACHINE_FURNACE_BLOCK), ID_RECIPE_FURNACE);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_SAWMILL_BLOCK), ID_RECIPE_SAWMILL);

        registration.addRecipeCatalyst(new ItemStack(MACHINE_PULVERIZER_BLOCK), ID_RECIPE_PULVERIZER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_PULVERIZER_BLOCK), ID_CATALYST_PULVERIZER);

        registration.addRecipeCatalyst(new ItemStack(MACHINE_SMELTER_BLOCK), ID_RECIPE_SMELTER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_SMELTER_BLOCK), ID_CATALYST_SMELTER);

        registration.addRecipeCatalyst(new ItemStack(MACHINE_INSOLATOR_BLOCK), ID_RECIPE_INSOLATOR);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_INSOLATOR_BLOCK), ID_CATALYST_INSOLATOR);

        registration.addRecipeCatalyst(new ItemStack(MACHINE_CENTRIFUGE_BLOCK), ID_RECIPE_CENTRIFUGE);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_PRESS_BLOCK), ID_RECIPE_PRESS);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_CRUCIBLE_BLOCK), ID_RECIPE_CRUCIBLE);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_CHILLER_BLOCK), ID_RECIPE_CHILLER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_REFINERY_BLOCK), ID_RECIPE_REFINERY);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_PYROLYZER_BLOCK), ID_RECIPE_PYROLYZER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_BREWER_BLOCK), ID_RECIPE_BREWER);
        registration.addRecipeCatalyst(new ItemStack(MACHINE_BOTTLER_BLOCK), ID_RECIPE_BOTTLER);

        registration.addRecipeCatalyst(new ItemStack(DYNAMO_STIRLING_BLOCK), ID_FUEL_STIRLING);
        registration.addRecipeCatalyst(new ItemStack(DYNAMO_COMPRESSION_BLOCK), ID_FUEL_COMPRESSION);
        registration.addRecipeCatalyst(new ItemStack(DYNAMO_MAGMATIC_BLOCK), ID_FUEL_MAGMATIC);
        registration.addRecipeCatalyst(new ItemStack(DYNAMO_NUMISMATIC_BLOCK), ID_FUEL_NUMISMATIC);
        registration.addRecipeCatalyst(new ItemStack(DYNAMO_LAPIDARY_BLOCK), ID_FUEL_LAPIDARY);
    }

    @Override
    public ResourceLocation getPluginUid() {

        return new ResourceLocation(ID_THERMAL, "expansion");
    }

    // region HELPERS
    private RecipeManager getRecipeManager() {

        RecipeManager recipeManager = null;
        ClientWorld world = Minecraft.getInstance().world;
        if (world != null) {
            recipeManager = world.getRecipeManager();
        }
        return recipeManager;
    }
    // endregion
}
