package cofh.thermal.expansion.init.registries;

import cofh.thermal.expansion.common.inventory.dynamo.*;
import cofh.thermal.expansion.common.inventory.machine.*;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;

import static cofh.core.util.ProxyUtils.getClientPlayer;
import static cofh.core.util.ProxyUtils.getClientWorld;
import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.lib.util.ThermalIDs.*;

public class TExpContainers {

    private TExpContainers() {

    }

    public static void register() {

    }

    public static final DeferredHolder<MenuType<?>, MenuType<MachineFurnaceMenu>> MACHINE_FURNACE_CONTAINER = CONTAINERS.register(ID_MACHINE_FURNACE, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineFurnaceMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineSawmillMenu>> MACHINE_SAWMILL_CONTAINER = CONTAINERS.register(ID_MACHINE_SAWMILL, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineSawmillMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachinePulverizerMenu>> MACHINE_PULVERIZER_CONTAINER = CONTAINERS.register(ID_MACHINE_PULVERIZER, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachinePulverizerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineSmelterMenu>> MACHINE_SMELTER_CONTAINER = CONTAINERS.register(ID_MACHINE_SMELTER, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineSmelterMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineInsolatorMenu>> MACHINE_INSOLATOR_CONTAINER = CONTAINERS.register(ID_MACHINE_INSOLATOR, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineInsolatorMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineCentrifugeMenu>> MACHINE_CENTRIFUGE_CONTAINER = CONTAINERS.register(ID_MACHINE_CENTRIFUGE, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineCentrifugeMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachinePressMenu>> MACHINE_PRESS_CONTAINER = CONTAINERS.register(ID_MACHINE_PRESS, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachinePressMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineCrucibleMenu>> MACHINE_CRUCIBLE_CONTAINER = CONTAINERS.register(ID_MACHINE_CRUCIBLE, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineCrucibleMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineChillerMenu>> MACHINE_CHILLER_CONTAINER = CONTAINERS.register(ID_MACHINE_CHILLER, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineChillerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineRefineryMenu>> MACHINE_REFINERY_CONTAINER = CONTAINERS.register(ID_MACHINE_REFINERY, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineRefineryMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachinePyrolyzerMenu>> MACHINE_PYROLYZER_CONTAINER = CONTAINERS.register(ID_MACHINE_PYROLYZER, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachinePyrolyzerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineBottlerMenu>> MACHINE_BOTTLER_CONTAINER = CONTAINERS.register(ID_MACHINE_BOTTLER, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineBottlerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineBrewerMenu>> MACHINE_BREWER_CONTAINER = CONTAINERS.register(ID_MACHINE_BREWER, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineBrewerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineCrystallizerMenu>> MACHINE_CRYSTALLIZER_CONTAINER = CONTAINERS.register(ID_MACHINE_CRYSTALLIZER, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineCrystallizerMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<MachineCrafterMenu>> MACHINE_CRAFTER_CONTAINER = CONTAINERS.register(ID_MACHINE_CRAFTER, () -> IMenuTypeExtension.create((windowId, inv, data) -> new MachineCrafterMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));

    public static final DeferredHolder<MenuType<?>, MenuType<DynamoStirlingMenu>> DYNAMO_STIRLING_CONTAINER = CONTAINERS.register(ID_DYNAMO_STIRLING, () -> IMenuTypeExtension.create((windowId, inv, data) -> new DynamoStirlingMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<DynamoCompressionMenu>> DYNAMO_COMPRESSION_CONTAINER = CONTAINERS.register(ID_DYNAMO_COMPRESSION, () -> IMenuTypeExtension.create((windowId, inv, data) -> new DynamoCompressionMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<DynamoMagmaticMenu>> DYNAMO_MAGMATIC_CONTAINER = CONTAINERS.register(ID_DYNAMO_MAGMATIC, () -> IMenuTypeExtension.create((windowId, inv, data) -> new DynamoMagmaticMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<DynamoNumismaticMenu>> DYNAMO_NUMISMATIC_CONTAINER = CONTAINERS.register(ID_DYNAMO_NUMISMATIC, () -> IMenuTypeExtension.create((windowId, inv, data) -> new DynamoNumismaticMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<DynamoLapidaryMenu>> DYNAMO_LAPIDARY_CONTAINER = CONTAINERS.register(ID_DYNAMO_LAPIDARY, () -> IMenuTypeExtension.create((windowId, inv, data) -> new DynamoLapidaryMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<DynamoDisenchantmentMenu>> DYNAMO_DISENCHANTMENT_CONTAINER = CONTAINERS.register(ID_DYNAMO_DISENCHANTMENT, () -> IMenuTypeExtension.create((windowId, inv, data) -> new DynamoDisenchantmentMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    public static final DeferredHolder<MenuType<?>, MenuType<DynamoGourmandMenu>> DYNAMO_GOURMAND_CONTAINER = CONTAINERS.register(ID_DYNAMO_GOURMAND, () -> IMenuTypeExtension.create((windowId, inv, data) -> new DynamoGourmandMenu(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));

}
