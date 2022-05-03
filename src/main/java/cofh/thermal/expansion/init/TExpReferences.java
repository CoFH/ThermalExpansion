package cofh.thermal.expansion.init;

import cofh.thermal.expansion.inventory.container.dynamo.*;
import cofh.thermal.expansion.inventory.container.machine.*;
import cofh.thermal.expansion.tileentity.dynamo.*;
import cofh.thermal.expansion.tileentity.machine.*;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.ObjectHolder;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.lib.common.ThermalIDs.*;

@ObjectHolder (ID_THERMAL)
public class TExpReferences {

    private TExpReferences() {

    }

    // region DYNAMOS
    @ObjectHolder (ID_DYNAMO_STIRLING)
    public static final Block DYNAMO_STIRLING_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_STIRLING)
    public static final BlockEntityType<DynamoStirlingTile> DYNAMO_STIRLING_TILE = null;
    @ObjectHolder (ID_DYNAMO_STIRLING)
    public static final MenuType<DynamoStirlingContainer> DYNAMO_STIRLING_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_COMPRESSION)
    public static final Block DYNAMO_COMPRESSION_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_COMPRESSION)
    public static final BlockEntityType<DynamoCompressionTile> DYNAMO_COMPRESSION_TILE = null;
    @ObjectHolder (ID_DYNAMO_COMPRESSION)
    public static final MenuType<DynamoCompressionContainer> DYNAMO_COMPRESSION_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_MAGMATIC)
    public static final Block DYNAMO_MAGMATIC_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_MAGMATIC)
    public static final BlockEntityType<DynamoMagmaticTile> DYNAMO_MAGMATIC_TILE = null;
    @ObjectHolder (ID_DYNAMO_MAGMATIC)
    public static final MenuType<DynamoMagmaticContainer> DYNAMO_MAGMATIC_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_NUMISMATIC)
    public static final Block DYNAMO_NUMISMATIC_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_NUMISMATIC)
    public static final BlockEntityType<DynamoNumismaticTile> DYNAMO_NUMISMATIC_TILE = null;
    @ObjectHolder (ID_DYNAMO_NUMISMATIC)
    public static final MenuType<DynamoNumismaticContainer> DYNAMO_NUMISMATIC_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_LAPIDARY)
    public static final Block DYNAMO_LAPIDARY_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_LAPIDARY)
    public static final BlockEntityType<DynamoLapidaryTile> DYNAMO_LAPIDARY_TILE = null;
    @ObjectHolder (ID_DYNAMO_LAPIDARY)
    public static final MenuType<DynamoLapidaryContainer> DYNAMO_LAPIDARY_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_DISENCHANTMENT)
    public static final Block DYNAMO_DISENCHANTMENT_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_DISENCHANTMENT)
    public static final BlockEntityType<DynamoDisenchantmentTile> DYNAMO_DISENCHANTMENT_TILE = null;
    @ObjectHolder (ID_DYNAMO_DISENCHANTMENT)
    public static final MenuType<DynamoDisenchantmentContainer> DYNAMO_DISENCHANTMENT_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_GOURMAND)
    public static final Block DYNAMO_GOURMAND_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_GOURMAND)
    public static final BlockEntityType<DynamoGourmandTile> DYNAMO_GOURMAND_TILE = null;
    @ObjectHolder (ID_DYNAMO_GOURMAND)
    public static final MenuType<DynamoGourmandContainer> DYNAMO_GOURMAND_CONTAINER = null;
    // endregion

    // region MACHINES
    @ObjectHolder (ID_MACHINE_FURNACE)
    public static final Block MACHINE_FURNACE_BLOCK = null;
    @ObjectHolder (ID_MACHINE_FURNACE)
    public static final BlockEntityType<MachineFurnaceTile> MACHINE_FURNACE_TILE = null;
    @ObjectHolder (ID_MACHINE_FURNACE)
    public static final MenuType<MachineFurnaceContainer> MACHINE_FURNACE_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_SAWMILL)
    public static final Block MACHINE_SAWMILL_BLOCK = null;
    @ObjectHolder (ID_MACHINE_SAWMILL)
    public static final BlockEntityType<MachineSawmillTile> MACHINE_SAWMILL_TILE = null;
    @ObjectHolder (ID_MACHINE_SAWMILL)
    public static final MenuType<MachineSawmillContainer> MACHINE_SAWMILL_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_PULVERIZER)
    public static final Block MACHINE_PULVERIZER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_PULVERIZER)
    public static final BlockEntityType<MachinePulverizerTile> MACHINE_PULVERIZER_TILE = null;
    @ObjectHolder (ID_MACHINE_PULVERIZER)
    public static final MenuType<MachinePulverizerContainer> MACHINE_PULVERIZER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_SMELTER)
    public static final Block MACHINE_SMELTER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_SMELTER)
    public static final BlockEntityType<MachineSmelterTile> MACHINE_SMELTER_TILE = null;
    @ObjectHolder (ID_MACHINE_SMELTER)
    public static final MenuType<MachineSmelterContainer> MACHINE_SMELTER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_INSOLATOR)
    public static final Block MACHINE_INSOLATOR_BLOCK = null;
    @ObjectHolder (ID_MACHINE_INSOLATOR)
    public static final BlockEntityType<MachineInsolatorTile> MACHINE_INSOLATOR_TILE = null;
    @ObjectHolder (ID_MACHINE_INSOLATOR)
    public static final MenuType<MachineInsolatorContainer> MACHINE_INSOLATOR_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_CENTRIFUGE)
    public static final Block MACHINE_CENTRIFUGE_BLOCK = null;
    @ObjectHolder (ID_MACHINE_CENTRIFUGE)
    public static final BlockEntityType<MachineCentrifugeTile> MACHINE_CENTRIFUGE_TILE = null;
    @ObjectHolder (ID_MACHINE_CENTRIFUGE)
    public static final MenuType<MachineCentrifugeContainer> MACHINE_CENTRIFUGE_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_PRESS)
    public static final Block MACHINE_PRESS_BLOCK = null;
    @ObjectHolder (ID_MACHINE_PRESS)
    public static final BlockEntityType<MachinePressTile> MACHINE_PRESS_TILE = null;
    @ObjectHolder (ID_MACHINE_PRESS)
    public static final MenuType<MachinePressContainer> MACHINE_PRESS_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_CRUCIBLE)
    public static final Block MACHINE_CRUCIBLE_BLOCK = null;
    @ObjectHolder (ID_MACHINE_CRUCIBLE)
    public static final BlockEntityType<MachineCrucibleTile> MACHINE_CRUCIBLE_TILE = null;
    @ObjectHolder (ID_MACHINE_CRUCIBLE)
    public static final MenuType<MachineCrucibleContainer> MACHINE_CRUCIBLE_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_CHILLER)
    public static final Block MACHINE_CHILLER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_CHILLER)
    public static final BlockEntityType<MachineChillerTile> MACHINE_CHILLER_TILE = null;
    @ObjectHolder (ID_MACHINE_CHILLER)
    public static final MenuType<MachineChillerContainer> MACHINE_CHILLER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_REFINERY)
    public static final Block MACHINE_REFINERY_BLOCK = null;
    @ObjectHolder (ID_MACHINE_REFINERY)
    public static final BlockEntityType<MachineRefineryTile> MACHINE_REFINERY_TILE = null;
    @ObjectHolder (ID_MACHINE_REFINERY)
    public static final MenuType<MachineRefineryContainer> MACHINE_REFINERY_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_PYROLYZER)
    public static final Block MACHINE_PYROLYZER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_PYROLYZER)
    public static final BlockEntityType<MachinePyrolyzerTile> MACHINE_PYROLYZER_TILE = null;
    @ObjectHolder (ID_MACHINE_PYROLYZER)
    public static final MenuType<MachinePyrolyzerContainer> MACHINE_PYROLYZER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_BREWER)
    public static final Block MACHINE_BREWER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_BREWER)
    public static final BlockEntityType<MachineBrewerTile> MACHINE_BREWER_TILE = null;
    @ObjectHolder (ID_MACHINE_BREWER)
    public static final MenuType<MachineBrewerContainer> MACHINE_BREWER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_BOTTLER)
    public static final Block MACHINE_BOTTLER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_BOTTLER)
    public static final BlockEntityType<MachineBottlerTile> MACHINE_BOTTLER_TILE = null;
    @ObjectHolder (ID_MACHINE_BOTTLER)
    public static final MenuType<MachineBottlerContainer> MACHINE_BOTTLER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_CRAFTER)
    public static final Block MACHINE_CRAFTER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_CRAFTER)
    public static final BlockEntityType<MachineCrafterTile> MACHINE_CRAFTER_TILE = null;
    @ObjectHolder (ID_MACHINE_CRAFTER)
    public static final MenuType<MachineCrafterContainer> MACHINE_CRAFTER_CONTAINER = null;
    // endregion
}
