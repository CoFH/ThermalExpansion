package cofh.thermal.expansion.init;

import cofh.thermal.expansion.inventory.container.dynamo.*;
import cofh.thermal.expansion.inventory.container.machine.*;
import net.minecraftforge.common.extensions.IForgeMenuType;

import static cofh.core.util.ProxyUtils.getClientPlayer;
import static cofh.core.util.ProxyUtils.getClientWorld;
import static cofh.thermal.core.ThermalCore.CONTAINERS;
import static cofh.thermal.lib.common.ThermalIDs.*;

public class TExpContainers {

    private TExpContainers() {

    }

    public static void register() {

        CONTAINERS.register(ID_MACHINE_FURNACE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineFurnaceContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_SAWMILL, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineSawmillContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PULVERIZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePulverizerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_SMELTER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineSmelterContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_INSOLATOR, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineInsolatorContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CENTRIFUGE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCentrifugeContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PRESS, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePressContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CRUCIBLE, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrucibleContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CHILLER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineChillerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_REFINERY, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineRefineryContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_PYROLYZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachinePyrolyzerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_BOTTLER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineBottlerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_BREWER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineBrewerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CRYSTALLIZER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrystallizerContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_MACHINE_CRAFTER, () -> IForgeMenuType.create((windowId, inv, data) -> new MachineCrafterContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));

        CONTAINERS.register(ID_DYNAMO_STIRLING, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoStirlingContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_COMPRESSION, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoCompressionContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_MAGMATIC, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoMagmaticContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_NUMISMATIC, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoNumismaticContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_LAPIDARY, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoLapidaryContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_DISENCHANTMENT, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoDisenchantmentContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
        CONTAINERS.register(ID_DYNAMO_GOURMAND, () -> IForgeMenuType.create((windowId, inv, data) -> new DynamoGourmandContainer(windowId, getClientWorld(), data.readBlockPos(), inv, getClientPlayer())));
    }

}
