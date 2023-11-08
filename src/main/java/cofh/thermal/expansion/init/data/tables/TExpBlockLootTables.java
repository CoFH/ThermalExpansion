package cofh.thermal.expansion.init.data.tables;

import cofh.lib.init.data.loot.BlockLootSubProviderCoFH;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.lib.util.ThermalIDs.*;

public class TExpBlockLootTables extends BlockLootSubProviderCoFH {

    @Override
    protected void generate() {

        var regBlocks = BLOCKS;
        var regItems = ITEMS;

        createSyncDropTable(regBlocks.get(ID_MACHINE_FURNACE));
        createSyncDropTable(regBlocks.get(ID_MACHINE_SAWMILL));
        createSyncDropTable(regBlocks.get(ID_MACHINE_PULVERIZER));
        createSyncDropTable(regBlocks.get(ID_MACHINE_SMELTER));
        createSyncDropTable(regBlocks.get(ID_MACHINE_INSOLATOR));
        createSyncDropTable(regBlocks.get(ID_MACHINE_CENTRIFUGE));
        createSyncDropTable(regBlocks.get(ID_MACHINE_PRESS));
        createSyncDropTable(regBlocks.get(ID_MACHINE_CRUCIBLE));
        createSyncDropTable(regBlocks.get(ID_MACHINE_CHILLER));
        createSyncDropTable(regBlocks.get(ID_MACHINE_REFINERY));
        createSyncDropTable(regBlocks.get(ID_MACHINE_PYROLYZER));
        createSyncDropTable(regBlocks.get(ID_MACHINE_BOTTLER));
        createSyncDropTable(regBlocks.get(ID_MACHINE_BREWER));
        createSyncDropTable(regBlocks.get(ID_MACHINE_CRYSTALLIZER));
        createSyncDropTable(regBlocks.get(ID_MACHINE_CRAFTER));

        createSyncDropTable(regBlocks.get(ID_DYNAMO_STIRLING));
        createSyncDropTable(regBlocks.get(ID_DYNAMO_COMPRESSION));
        createSyncDropTable(regBlocks.get(ID_DYNAMO_MAGMATIC));
        createSyncDropTable(regBlocks.get(ID_DYNAMO_NUMISMATIC));
        createSyncDropTable(regBlocks.get(ID_DYNAMO_LAPIDARY));
        createSyncDropTable(regBlocks.get(ID_DYNAMO_DISENCHANTMENT));
        createSyncDropTable(regBlocks.get(ID_DYNAMO_GOURMAND));
    }

}
