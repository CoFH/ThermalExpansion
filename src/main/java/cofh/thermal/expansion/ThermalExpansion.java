package cofh.thermal.expansion;

import cofh.thermal.expansion.client.gui.dynamo.*;
import cofh.thermal.expansion.client.gui.machine.*;
import cofh.thermal.expansion.common.config.ThermalDynamoConfig;
import cofh.thermal.expansion.common.config.ThermalMachineConfig;
import cofh.thermal.expansion.init.registries.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static cofh.lib.util.constants.ModIds.ID_THERMAL_EXPANSION;
import static cofh.thermal.core.ThermalCore.CONFIG_MANAGER;
import static cofh.thermal.expansion.init.registries.TExpContainers.*;
import static cofh.thermal.lib.util.ThermalFlags.*;
import static cofh.thermal.lib.util.ThermalIDs.*;

@Mod (ID_THERMAL_EXPANSION)
public class ThermalExpansion {

    public ThermalExpansion() {

        setFeatureFlags();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CONFIG_MANAGER.register(modEventBus)
                .addServerConfig(new ThermalDynamoConfig())
                .addServerConfig(new ThermalMachineConfig());

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        TExpBlocks.register();
        TExpItems.register();

        TExpContainers.register();
        TExpSounds.register();
        TExpBlockEntities.register();
    }

    private void setFeatureFlags() {

        setFlag(FLAG_RESOURCE_CINNABAR, true);
        setFlag(FLAG_RESOURCE_OIL, true);

        setFlag(FLAG_AREA_AUGMENTS, true);
        setFlag(FLAG_DYNAMO_AUGMENTS, true);
        setFlag(FLAG_MACHINE_AUGMENTS, true);

        setFlag(FLAG_TOOL_COMPONENTS, true);

        setFlag(FLAG_COINS, true);
        setFlag(FLAG_PLATES, true);

        setFlag(ID_MACHINE_FRAME, true);

        setFlag(ID_DEVICE_TREE_EXTRACTOR, true);
        setFlag(ID_DEVICE_FISHER, true);
        setFlag(ID_DEVICE_COMPOSTER, true);
        setFlag(ID_DEVICE_WATER_GEN, true);
        setFlag(ID_DEVICE_ROCK_GEN, true);
        setFlag(ID_DEVICE_COLLECTOR, true);
        setFlag(ID_DEVICE_XP_CONDENSER, true);
        setFlag(ID_DEVICE_NULLIFIER, true);
        setFlag(ID_DEVICE_POTION_DIFFUSER, true);

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
        setFlag(ID_MACHINE_CRYSTALLIZER, true);
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

        event.enqueueWork(this::registerGuiFactories);
    }
    // endregion

    // region HELPERS
    private void registerGuiFactories() {

        MenuScreens.register(MACHINE_FURNACE_CONTAINER.get(), MachineFurnaceScreen::new);
        MenuScreens.register(MACHINE_SAWMILL_CONTAINER.get(), MachineSawmillScreen::new);
        MenuScreens.register(MACHINE_PULVERIZER_CONTAINER.get(), MachinePulverizerScreen::new);
        MenuScreens.register(MACHINE_SMELTER_CONTAINER.get(), MachineSmelterScreen::new);
        MenuScreens.register(MACHINE_INSOLATOR_CONTAINER.get(), MachineInsolatorScreen::new);
        MenuScreens.register(MACHINE_CENTRIFUGE_CONTAINER.get(), MachineCentrifugeScreen::new);
        MenuScreens.register(MACHINE_PRESS_CONTAINER.get(), MachinePressScreen::new);
        MenuScreens.register(MACHINE_CRUCIBLE_CONTAINER.get(), MachineCrucibleScreen::new);
        MenuScreens.register(MACHINE_CHILLER_CONTAINER.get(), MachineChillerScreen::new);
        MenuScreens.register(MACHINE_REFINERY_CONTAINER.get(), MachineRefineryScreen::new);
        MenuScreens.register(MACHINE_PYROLYZER_CONTAINER.get(), MachinePyrolyzerScreen::new);
        MenuScreens.register(MACHINE_BREWER_CONTAINER.get(), MachineBrewerScreen::new);
        MenuScreens.register(MACHINE_BOTTLER_CONTAINER.get(), MachineBottlerScreen::new);
        MenuScreens.register(MACHINE_CRYSTALLIZER_CONTAINER.get(), MachineCrystallizerScreen::new);
        MenuScreens.register(MACHINE_CRAFTER_CONTAINER.get(), MachineCrafterScreen::new);

        MenuScreens.register(DYNAMO_STIRLING_CONTAINER.get(), DynamoStirlingScreen::new);
        MenuScreens.register(DYNAMO_COMPRESSION_CONTAINER.get(), DynamoCompressionScreen::new);
        MenuScreens.register(DYNAMO_MAGMATIC_CONTAINER.get(), DynamoMagmaticScreen::new);
        MenuScreens.register(DYNAMO_NUMISMATIC_CONTAINER.get(), DynamoNumismaticScreen::new);
        MenuScreens.register(DYNAMO_LAPIDARY_CONTAINER.get(), DynamoLapidaryScreen::new);
        MenuScreens.register(DYNAMO_DISENCHANTMENT_CONTAINER.get(), DynamoDisenchantmentScreen::new);
        MenuScreens.register(DYNAMO_GOURMAND_CONTAINER.get(), DynamoGourmandScreen::new);
    }
    // endregion
}
