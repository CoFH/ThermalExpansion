package cofh.thermal.expansion;

import cofh.thermal.expansion.client.gui.dynamo.*;
import cofh.thermal.expansion.client.gui.machine.*;
import cofh.thermal.expansion.init.TExpBlocks;
import cofh.thermal.expansion.init.TExpContainers;
import cofh.thermal.expansion.init.TExpItems;
import cofh.thermal.expansion.init.TExpSounds;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.Constants.ID_THERMAL_EXPANSION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.expansion.init.TExpReferences.*;
import static cofh.thermal.lib.common.ThermalFlags.*;
import static cofh.thermal.lib.common.ThermalIDs.*;

@Mod (ID_THERMAL_EXPANSION)
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

        setFlag(ID_MACHINE_FURNACE, true);
        setFlag(ID_MACHINE_SAWMILL, true);
        setFlag(ID_MACHINE_PULVERIZER, true);
        setFlag(ID_MACHINE_SMELTER, true);
        setFlag(ID_MACHINE_INSOLATOR, true);
        setFlag(ID_MACHINE_CENTRIFUGE, true);
        setFlag(ID_MACHINE_PRESS, true);
        setFlag(ID_MACHINE_CRUCIBLE, true);
        setFlag(ID_MACHINE_CHILLER, true);
        setFlag(ID_MACHINE_REFINERY, true);
        setFlag(ID_MACHINE_PYROLYZER, true);
        setFlag(ID_MACHINE_BOTTLER, true);
        setFlag(ID_MACHINE_BREWER, true);
        setFlag(ID_MACHINE_CRAFTER, true);

        setFlag(ID_ENERGY_CELL_FRAME, true);
        setFlag(ID_ENERGY_CELL, true);

        setFlag(ID_FLUID_CELL_FRAME, true);
        setFlag(ID_FLUID_CELL, true);

        //        setFlag(ID_ITEM_CELL_FRAME, true);
        //        setFlag(ID_ITEM_CELL, true);

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

        MenuScreens.register(MACHINE_FURNACE_CONTAINER, MachineFurnaceScreen::new);
        MenuScreens.register(MACHINE_SAWMILL_CONTAINER, MachineSawmillScreen::new);
        MenuScreens.register(MACHINE_PULVERIZER_CONTAINER, MachinePulverizerScreen::new);
        MenuScreens.register(MACHINE_SMELTER_CONTAINER, MachineSmelterScreen::new);
        MenuScreens.register(MACHINE_INSOLATOR_CONTAINER, MachineInsolatorScreen::new);
        MenuScreens.register(MACHINE_CENTRIFUGE_CONTAINER, MachineCentrifugeScreen::new);
        MenuScreens.register(MACHINE_PRESS_CONTAINER, MachinePressScreen::new);
        MenuScreens.register(MACHINE_CRUCIBLE_CONTAINER, MachineCrucibleScreen::new);
        MenuScreens.register(MACHINE_CHILLER_CONTAINER, MachineChillerScreen::new);
        MenuScreens.register(MACHINE_REFINERY_CONTAINER, MachineRefineryScreen::new);
        MenuScreens.register(MACHINE_PYROLYZER_CONTAINER, MachinePyrolyzerScreen::new);
        MenuScreens.register(MACHINE_BREWER_CONTAINER, MachineBrewerScreen::new);
        MenuScreens.register(MACHINE_BOTTLER_CONTAINER, MachineBottlerScreen::new);
        MenuScreens.register(MACHINE_CRAFTER_CONTAINER, MachineCrafterScreen::new);

        MenuScreens.register(DYNAMO_STIRLING_CONTAINER, DynamoStirlingScreen::new);
        MenuScreens.register(DYNAMO_COMPRESSION_CONTAINER, DynamoCompressionScreen::new);
        MenuScreens.register(DYNAMO_MAGMATIC_CONTAINER, DynamoMagmaticScreen::new);
        MenuScreens.register(DYNAMO_NUMISMATIC_CONTAINER, DynamoNumismaticScreen::new);
        MenuScreens.register(DYNAMO_LAPIDARY_CONTAINER, DynamoLapidaryScreen::new);
        MenuScreens.register(DYNAMO_DISENCHANTMENT_CONTAINER, DynamoDisenchantmentScreen::new);
        MenuScreens.register(DYNAMO_GOURMAND_CONTAINER, DynamoGourmandScreen::new);
    }

    private void registerRenderLayers() {

        RenderType cutout = RenderType.cutout();

        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_FURNACE), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_SAWMILL), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_PULVERIZER), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_SMELTER), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_INSOLATOR), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_CENTRIFUGE), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_PRESS), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_CRUCIBLE), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_CHILLER), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_REFINERY), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_PYROLYZER), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_BREWER), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_BOTTLER), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_MACHINE_CRAFTER), cutout);

        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_DYNAMO_STIRLING), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_DYNAMO_COMPRESSION), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_DYNAMO_MAGMATIC), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_DYNAMO_NUMISMATIC), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_DYNAMO_LAPIDARY), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_DYNAMO_DISENCHANTMENT), cutout);
        ItemBlockRenderTypes.setRenderLayer(BLOCKS.get(ID_DYNAMO_GOURMAND), cutout);
    }
    // endregion
}
