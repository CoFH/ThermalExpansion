package cofh.thermal.expansion.init;

import cofh.thermal.expansion.inventory.container.dynamo.*;
import cofh.thermal.expansion.inventory.container.machine.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;

import static cofh.core.util.ProxyUtils.getClientPlayer;
import static cofh.core.util.ProxyUtils.getClientWorld;
import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.lib.common.ThermalIDs.*;

public class TExpContainers {

    private TExpContainers() {

    }

    public static void register() {

    }

    public static final RegistryObject<MenuType<MachineFurnaceContainer>> MACHINE_FURNACE_CONTAINER = CONTAINERS.register(ID_MACHINE_FURNACE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineFurnaceContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineSawmillContainer>> MACHINE_SAWMILL_CONTAINER = CONTAINERS.register(ID_MACHINE_SAWMILL, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineSawmillContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachinePulverizerContainer>> MACHINE_PULVERIZER_CONTAINER = CONTAINERS.register(ID_MACHINE_PULVERIZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePulverizerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineSmelterContainer>> MACHINE_SMELTER_CONTAINER = CONTAINERS.register(ID_MACHINE_SMELTER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineSmelterContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineInsolatorContainer>> MACHINE_INSOLATOR_CONTAINER = CONTAINERS.register(ID_MACHINE_INSOLATOR, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineInsolatorContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineCentrifugeContainer>> MACHINE_CENTRIFUGE_CONTAINER = CONTAINERS.register(ID_MACHINE_CENTRIFUGE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCentrifugeContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachinePressContainer>> MACHINE_PRESS_CONTAINER = CONTAINERS.register(ID_MACHINE_PRESS, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePressContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineCrucibleContainer>> MACHINE_CRUCIBLE_CONTAINER = CONTAINERS.register(ID_MACHINE_CRUCIBLE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrucibleContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineChillerContainer>> MACHINE_CHILLER_CONTAINER = CONTAINERS.register(ID_MACHINE_CHILLER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineChillerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineRefineryContainer>> MACHINE_REFINERY_CONTAINER = CONTAINERS.register(ID_MACHINE_REFINERY, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineRefineryContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachinePyrolyzerContainer>> MACHINE_PYROLYZER_CONTAINER = CONTAINERS.register(ID_MACHINE_PYROLYZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePyrolyzerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineBottlerContainer>> MACHINE_BOTTLER_CONTAINER = CONTAINERS.register(ID_MACHINE_BOTTLER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineBottlerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineBrewerContainer>> MACHINE_BREWER_CONTAINER = CONTAINERS.register(ID_MACHINE_BREWER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineBrewerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineCrystallizerContainer>> MACHINE_CRYSTALLIZER_CONTAINER = CONTAINERS.register(ID_MACHINE_CRYSTALLIZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrystallizerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<MachineCrafterContainer>> MACHINE_CRAFTER_CONTAINER = CONTAINERS.register(ID_MACHINE_CRAFTER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrafterContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));

    public static final RegistryObject<MenuType<DynamoStirlingContainer>> DYNAMO_STIRLING_CONTAINER = CONTAINERS.register(ID_DYNAMO_STIRLING, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoStirlingContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoCompressionContainer>> DYNAMO_COMPRESSION_CONTAINER = CONTAINERS.register(ID_DYNAMO_COMPRESSION, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoCompressionContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoMagmaticContainer>> DYNAMO_MAGMATIC_CONTAINER = CONTAINERS.register(ID_DYNAMO_MAGMATIC, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoMagmaticContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoNumismaticContainer>> DYNAMO_NUMISMATIC_CONTAINER = CONTAINERS.register(ID_DYNAMO_NUMISMATIC, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoNumismaticContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoLapidaryContainer>> DYNAMO_LAPIDARY_CONTAINER = CONTAINERS.register(ID_DYNAMO_LAPIDARY, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoLapidaryContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoDisenchantmentContainer>> DYNAMO_DISENCHANTMENT_CONTAINER = CONTAINERS.register(ID_DYNAMO_DISENCHANTMENT, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoDisenchantmentContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final RegistryObject<MenuType<DynamoGourmandContainer>> DYNAMO_GOURMAND_CONTAINER = CONTAINERS.register(ID_DYNAMO_GOURMAND, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoGourmandContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));

}
