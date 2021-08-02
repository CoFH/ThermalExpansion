package cofh.thermal.expansion.data;

import cofh.lib.data.LootTableProviderCoFH;
import cofh.lib.util.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.expansion.init.TExpIDs.*;

public class TExpLootTableProvider extends LootTableProviderCoFH {

    public TExpLootTableProvider(DataGenerator gen) {

        super(gen);
    }

    @Override
    public String getName() {

        return "Thermal Expansion: Loot Tables";
    }

    @Override
    protected void addTables() {

        DeferredRegisterCoFH<Block> regBlocks = BLOCKS;
        DeferredRegisterCoFH<Item> regItems = ITEMS;

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
        createSyncDropTable(regBlocks.get(ID_MACHINE_CRAFTER));

        createSyncDropTable(regBlocks.get(ID_DYNAMO_STIRLING));
        createSyncDropTable(regBlocks.get(ID_DYNAMO_COMPRESSION));
        createSyncDropTable(regBlocks.get(ID_DYNAMO_MAGMATIC));
        createSyncDropTable(regBlocks.get(ID_DYNAMO_NUMISMATIC));
        createSyncDropTable(regBlocks.get(ID_DYNAMO_LAPIDARY));
    }

}
