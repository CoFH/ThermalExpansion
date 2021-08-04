package cofh.thermal.expansion;

import cofh.thermal.expansion.client.gui.dynamo.*;
import cofh.thermal.expansion.client.gui.machine.*;
import cofh.thermal.expansion.init.TExpBlocks;
import cofh.thermal.expansion.init.TExpContainers;
import cofh.thermal.expansion.init.TExpItems;
import cofh.thermal.expansion.init.TExpSounds;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_EXPANSION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.common.ThermalIDs.*;
import static cofh.thermal.expansion.init.TExpReferences.*;
import static cofh.thermal.lib.common.ThermalFlags.*;

@Mod(ID_THERMAL_EXPANSION)
public class ThermalExpansion {

    public ThermalExpansion() {

        setFeatureFlags();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        TExpBlocks.register();
        TExpItems.register();

        TExpContainers.register();
        TExpSounds.register();
    }

    private void setFeatureFlags() {

        setFlag(FLAG_RESOURCE_CINNABAR, true);
        setFlag(FLAG_RESOURCE_OIL, true);

        setFlag(FLAG_DYNAMO_AUGMENTS, true);
        setFlag(FLAG_MACHINE_AUGMENTS, true);

        setFlag(FLAG_TOOL_COMPONENTS, true);

        setFlag(FLAG_COINS, true);
        setFlag(FLAG_PLATES, true);

        setFlag(ID_MACHINE_FRAME, true);

        setFlag(ID_DEVICE_TREE_EXTRACTOR, true);
        setFlag(ID_DEVICE_WATER_GEN, true);
        setFlag(ID_DEVICE_ROCK_GEN, true);
        setFlag(ID_DEVICE_COLLECTOR, true);
        setFlag(ID_DEVICE_NULLIFIER, true);

        setFlag(ID_DYNAMO_STIRLING, true);
        setFlag(ID_DYNAMO_COMPRESSION, true);
        setFlag(ID_DYNAMO_MAGMATIC, true);
        setFlag(ID_DYNAMO_NUMISMATIC, true);
        setFlag(ID_DYNAMO_LAPIDARY, true);
        setFlag(ID_DYNAMO_DISENCHANTMENT, true);
        setFlag(ID_DYNAMO_GOURMAND, true);

        setFlag(ID_ENERGY_CELL_FRAME, true);
        setFlag(ID_ENERGY_CELL, true);

        setFlag(ID_FLUID_CELL_FRAME, true);
        setFlag(ID_FLUID_CELL, true);

        setFlag(FLAG_XP_STORAGE_AUGMENT, true);
    }

    // region INITIALIZATION
    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event) {

        this.registerGuiFactories();
        this.registerRenderLayers();
    }
    // endregion

    // region HELPERS
    private void registerGuiFactories() {

        ScreenManager.registerFactory(MACHINE_FURNACE_CONTAINER, MachineFurnaceScreen::new);
        ScreenManager.registerFactory(MACHINE_SAWMILL_CONTAINER, MachineSawmillScreen::new);
        ScreenManager.registerFactory(MACHINE_PULVERIZER_CONTAINER, MachinePulverizerScreen::new);
        ScreenManager.registerFactory(MACHINE_SMELTER_CONTAINER, MachineSmelterScreen::new);
        ScreenManager.registerFactory(MACHINE_INSOLATOR_CONTAINER, MachineInsolatorScreen::new);
        ScreenManager.registerFactory(MACHINE_CENTRIFUGE_CONTAINER, MachineCentrifugeScreen::new);
        ScreenManager.registerFactory(MACHINE_PRESS_CONTAINER, MachinePressScreen::new);
        ScreenManager.registerFactory(MACHINE_CRUCIBLE_CONTAINER, MachineCrucibleScreen::new);
        ScreenManager.registerFactory(MACHINE_CHILLER_CONTAINER, MachineChillerScreen::new);
        ScreenManager.registerFactory(MACHINE_REFINERY_CONTAINER, MachineRefineryScreen::new);
        ScreenManager.registerFactory(MACHINE_PYROLYZER_CONTAINER, MachinePyrolyzerScreen::new);
        ScreenManager.registerFactory(MACHINE_BREWER_CONTAINER, MachineBrewerScreen::new);
        ScreenManager.registerFactory(MACHINE_BOTTLER_CONTAINER, MachineBottlerScreen::new);
        ScreenManager.registerFactory(MACHINE_CRAFTER_CONTAINER, MachineCrafterScreen::new);

        ScreenManager.registerFactory(DYNAMO_STIRLING_CONTAINER, DynamoStirlingScreen::new);
        ScreenManager.registerFactory(DYNAMO_COMPRESSION_CONTAINER, DynamoCompressionScreen::new);
        ScreenManager.registerFactory(DYNAMO_MAGMATIC_CONTAINER, DynamoMagmaticScreen::new);
        ScreenManager.registerFactory(DYNAMO_NUMISMATIC_CONTAINER, DynamoNumismaticScreen::new);
        ScreenManager.registerFactory(DYNAMO_LAPIDARY_CONTAINER, DynamoLapidaryScreen::new);
        ScreenManager.registerFactory(DYNAMO_DISENCHANTMENT_CONTAINER, DynamoDisenchantmentScreen::new);
        ScreenManager.registerFactory(DYNAMO_GOURMAND_CONTAINER, DynamoGourmandScreen::new);
    }

    private void registerRenderLayers() {

        RenderType cutout = RenderType.getCutout();

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_FURNACE), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_SAWMILL), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_PULVERIZER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_SMELTER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_INSOLATOR), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_CENTRIFUGE), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_PRESS), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_CRUCIBLE), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_CHILLER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_REFINERY), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_PYROLYZER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_BREWER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_BOTTLER), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_MACHINE_CRAFTER), cutout);

        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_STIRLING), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_COMPRESSION), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_MAGMATIC), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_NUMISMATIC), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_LAPIDARY), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_DISENCHANTMENT), cutout);
        RenderTypeLookup.setRenderLayer(BLOCKS.get(ID_DYNAMO_GOURMAND), cutout);
    }
    // endregion
}
