package cofh.thermal.expansion.init;

import cofh.core.util.ProxyUtils;
import cofh.thermal.expansion.inventory.container.dynamo.*;
import cofh.thermal.expansion.inventory.container.machine.*;
import net.minecraftforge.common.extensions.IForgeMenuType;

import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.lib.common.ThermalIDs.*;

public class TExpContainers {

    private TExpContainers() {

    }

    public static void register() {

        CONTAINERS.register(ID_MACHINE_FURNACE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineFurnaceContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_SAWMILL, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineSawmillContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PULVERIZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePulverizerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_SMELTER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineSmelterContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_INSOLATOR, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineInsolatorContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CENTRIFUGE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCentrifugeContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PRESS, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePressContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CRUCIBLE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrucibleContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CHILLER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineChillerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_REFINERY, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineRefineryContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PYROLYZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePyrolyzerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_BOTTLER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineBottlerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_BREWER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineBrewerContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CRAFTER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrafterContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));

        CONTAINERS.register(ID_DYNAMO_STIRLING, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoStirlingContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_COMPRESSION, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoCompressionContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_MAGMATIC, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoMagmaticContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_NUMISMATIC, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoNumismaticContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_LAPIDARY, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoLapidaryContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_DISENCHANTMENT, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoDisenchantmentContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_GOURMAND, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoGourmandContainer(windowId, ProxyUtils.getClientWorld(), data.readBlockPos(), inv, ProxyUtils.getClientPlayer())));
    }

}
