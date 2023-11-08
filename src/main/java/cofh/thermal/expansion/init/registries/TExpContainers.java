package cofh.thermal.expansion.init.registries;

import cofh.thermal.expansion.common.inventory.dynamo.*;
import cofh.thermal.expansion.common.inventory.machine.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;

import static cofh.core.util.ProxyUtils.getClientPlayer;
import static cofh.core.util.ProxyUtils.getClientWorld;
import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.lib.util.ThermalIDs.*;

public class TExpContainers {

    private TExpContainers() {

    }

    public static void register() {

    }

    public static final RegistryObject<MenuType<MachineFurnaceMenu>> MACHINE_FURNACE_CONTAINER = CONTAINERS.register(ID_MACHINE_FURNACE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineFurnaceMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineSawmillMenu>> MACHINE_SAWMILL_CONTAINER = CONTAINERS.register(ID_MACHINE_SAWMILL, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineSawmillMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachinePulverizerMenu>> MACHINE_PULVERIZER_CONTAINER = CONTAINERS.register(ID_MACHINE_PULVERIZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePulverizerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineSmelterMenu>> MACHINE_SMELTER_CONTAINER = CONTAINERS.register(ID_MACHINE_SMELTER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineSmelterMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineInsolatorMenu>> MACHINE_INSOLATOR_CONTAINER = CONTAINERS.register(ID_MACHINE_INSOLATOR, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineInsolatorMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineCentrifugeMenu>> MACHINE_CENTRIFUGE_CONTAINER = CONTAINERS.register(ID_MACHINE_CENTRIFUGE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCentrifugeMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachinePressMenu>> MACHINE_PRESS_CONTAINER = CONTAINERS.register(ID_MACHINE_PRESS, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePressMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineCrucibleMenu>> MACHINE_CRUCIBLE_CONTAINER = CONTAINERS.register(ID_MACHINE_CRUCIBLE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrucibleMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineChillerMenu>> MACHINE_CHILLER_CONTAINER = CONTAINERS.register(ID_MACHINE_CHILLER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineChillerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineRefineryMenu>> MACHINE_REFINERY_CONTAINER = CONTAINERS.register(ID_MACHINE_REFINERY, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineRefineryMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachinePyrolyzerMenu>> MACHINE_PYROLYZER_CONTAINER = CONTAINERS.register(ID_MACHINE_PYROLYZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePyrolyzerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineBottlerMenu>> MACHINE_BOTTLER_CONTAINER = CONTAINERS.register(ID_MACHINE_BOTTLER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineBottlerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineBrewerMenu>> MACHINE_BREWER_CONTAINER = CONTAINERS.register(ID_MACHINE_BREWER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineBrewerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineCrystallizerMenu>> MACHINE_CRYSTALLIZER_CONTAINER = CONTAINERS.register(ID_MACHINE_CRYSTALLIZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrystallizerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineCrafterMenu>> MACHINE_CRAFTER_CONTAINER = CONTAINERS.register(ID_MACHINE_CRAFTER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrafterMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));

    public static final RegistryObject<MenuType<DynamoStirlingMenu>> DYNAMO_STIRLING_CONTAINER = CONTAINERS.register(ID_DYNAMO_STIRLING, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoStirlingMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoCompressionMenu>> DYNAMO_COMPRESSION_CONTAINER = CONTAINERS.register(ID_DYNAMO_COMPRESSION, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoCompressionMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoMagmaticMenu>> DYNAMO_MAGMATIC_CONTAINER = CONTAINERS.register(ID_DYNAMO_MAGMATIC, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoMagmaticMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoNumismaticMenu>> DYNAMO_NUMISMATIC_CONTAINER = CONTAINERS.register(ID_DYNAMO_NUMISMATIC, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoNumismaticMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoLapidaryMenu>> DYNAMO_LAPIDARY_CONTAINER = CONTAINERS.register(ID_DYNAMO_LAPIDARY, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoLapidaryMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoDisenchantmentMenu>> DYNAMO_DISENCHANTMENT_CONTAINER = CONTAINERS.register(ID_DYNAMO_DISENCHANTMENT, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoDisenchantmentMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoGourmandMenu>> DYNAMO_GOURMAND_CONTAINER = CONTAINERS.register(ID_DYNAMO_GOURMAND, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoGourmandMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));

}
