package cofh.thermal.expansion.init;

import cofh.thermal.expansion.inventory.container.dynamo.*;
import cofh.thermal.expansion.inventory.container.machine.*;
import cofh.thermal.expansion.tileentity.dynamo.*;
import cofh.thermal.expansion.tileentity.machine.*;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
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
    public static final TileEntityType<DynamoStirlingTile> DYNAMO_STIRLING_TILE = null;
    @ObjectHolder (ID_DYNAMO_STIRLING)
    public static final ContainerType<DynamoStirlingContainer> DYNAMO_STIRLING_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_COMPRESSION)
    public static final Block DYNAMO_COMPRESSION_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_COMPRESSION)
    public static final TileEntityType<DynamoCompressionTile> DYNAMO_COMPRESSION_TILE = null;
    @ObjectHolder (ID_DYNAMO_COMPRESSION)
    public static final ContainerType<DynamoCompressionContainer> DYNAMO_COMPRESSION_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_MAGMATIC)
    public static final Block DYNAMO_MAGMATIC_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_MAGMATIC)
    public static final TileEntityType<DynamoMagmaticTile> DYNAMO_MAGMATIC_TILE = null;
    @ObjectHolder (ID_DYNAMO_MAGMATIC)
    public static final ContainerType<DynamoMagmaticContainer> DYNAMO_MAGMATIC_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_NUMISMATIC)
    public static final Block DYNAMO_NUMISMATIC_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_NUMISMATIC)
    public static final TileEntityType<DynamoNumismaticTile> DYNAMO_NUMISMATIC_TILE = null;
    @ObjectHolder (ID_DYNAMO_NUMISMATIC)
    public static final ContainerType<DynamoNumismaticContainer> DYNAMO_NUMISMATIC_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_LAPIDARY)
    public static final Block DYNAMO_LAPIDARY_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_LAPIDARY)
    public static final TileEntityType<DynamoLapidaryTile> DYNAMO_LAPIDARY_TILE = null;
    @ObjectHolder (ID_DYNAMO_LAPIDARY)
    public static final ContainerType<DynamoLapidaryContainer> DYNAMO_LAPIDARY_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_DISENCHANTMENT)
    public static final Block DYNAMO_DISENCHANTMENT_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_DISENCHANTMENT)
    public static final TileEntityType<DynamoDisenchantmentTile> DYNAMO_DISENCHANTMENT_TILE = null;
    @ObjectHolder (ID_DYNAMO_DISENCHANTMENT)
    public static final ContainerType<DynamoDisenchantmentContainer> DYNAMO_DISENCHANTMENT_CONTAINER = null;

    @ObjectHolder (ID_DYNAMO_GOURMAND)
    public static final Block DYNAMO_GOURMAND_BLOCK = null;
    @ObjectHolder (ID_DYNAMO_GOURMAND)
    public static final TileEntityType<DynamoGourmandTile> DYNAMO_GOURMAND_TILE = null;
    @ObjectHolder (ID_DYNAMO_GOURMAND)
    public static final ContainerType<DynamoGourmandContainer> DYNAMO_GOURMAND_CONTAINER = null;
    // endregion

    // region MACHINES
    @ObjectHolder (ID_MACHINE_FURNACE)
    public static final Block MACHINE_FURNACE_BLOCK = null;
    @ObjectHolder (ID_MACHINE_FURNACE)
    public static final TileEntityType<MachineFurnaceTile> MACHINE_FURNACE_TILE = null;
    @ObjectHolder (ID_MACHINE_FURNACE)
    public static final ContainerType<MachineFurnaceContainer> MACHINE_FURNACE_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_SAWMILL)
    public static final Block MACHINE_SAWMILL_BLOCK = null;
    @ObjectHolder (ID_MACHINE_SAWMILL)
    public static final TileEntityType<MachineSawmillTile> MACHINE_SAWMILL_TILE = null;
    @ObjectHolder (ID_MACHINE_SAWMILL)
    public static final ContainerType<MachineSawmillContainer> MACHINE_SAWMILL_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_PULVERIZER)
    public static final Block MACHINE_PULVERIZER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_PULVERIZER)
    public static final TileEntityType<MachinePulverizerTile> MACHINE_PULVERIZER_TILE = null;
    @ObjectHolder (ID_MACHINE_PULVERIZER)
    public static final ContainerType<MachinePulverizerContainer> MACHINE_PULVERIZER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_SMELTER)
    public static final Block MACHINE_SMELTER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_SMELTER)
    public static final TileEntityType<MachineSmelterTile> MACHINE_SMELTER_TILE = null;
    @ObjectHolder (ID_MACHINE_SMELTER)
    public static final ContainerType<MachineSmelterContainer> MACHINE_SMELTER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_INSOLATOR)
    public static final Block MACHINE_INSOLATOR_BLOCK = null;
    @ObjectHolder (ID_MACHINE_INSOLATOR)
    public static final TileEntityType<MachineInsolatorTile> MACHINE_INSOLATOR_TILE = null;
    @ObjectHolder (ID_MACHINE_INSOLATOR)
    public static final ContainerType<MachineInsolatorContainer> MACHINE_INSOLATOR_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_CENTRIFUGE)
    public static final Block MACHINE_CENTRIFUGE_BLOCK = null;
    @ObjectHolder (ID_MACHINE_CENTRIFUGE)
    public static final TileEntityType<MachineCentrifugeTile> MACHINE_CENTRIFUGE_TILE = null;
    @ObjectHolder (ID_MACHINE_CENTRIFUGE)
    public static final ContainerType<MachineCentrifugeContainer> MACHINE_CENTRIFUGE_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_PRESS)
    public static final Block MACHINE_PRESS_BLOCK = null;
    @ObjectHolder (ID_MACHINE_PRESS)
    public static final TileEntityType<MachinePressTile> MACHINE_PRESS_TILE = null;
    @ObjectHolder (ID_MACHINE_PRESS)
    public static final ContainerType<MachinePressContainer> MACHINE_PRESS_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_CRUCIBLE)
    public static final Block MACHINE_CRUCIBLE_BLOCK = null;
    @ObjectHolder (ID_MACHINE_CRUCIBLE)
    public static final TileEntityType<MachineCrucibleTile> MACHINE_CRUCIBLE_TILE = null;
    @ObjectHolder (ID_MACHINE_CRUCIBLE)
    public static final ContainerType<MachineCrucibleContainer> MACHINE_CRUCIBLE_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_CHILLER)
    public static final Block MACHINE_CHILLER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_CHILLER)
    public static final TileEntityType<MachineChillerTile> MACHINE_CHILLER_TILE = null;
    @ObjectHolder (ID_MACHINE_CHILLER)
    public static final ContainerType<MachineChillerContainer> MACHINE_CHILLER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_REFINERY)
    public static final Block MACHINE_REFINERY_BLOCK = null;
    @ObjectHolder (ID_MACHINE_REFINERY)
    public static final TileEntityType<MachineRefineryTile> MACHINE_REFINERY_TILE = null;
    @ObjectHolder (ID_MACHINE_REFINERY)
    public static final ContainerType<MachineRefineryContainer> MACHINE_REFINERY_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_PYROLYZER)
    public static final Block MACHINE_PYROLYZER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_PYROLYZER)
    public static final TileEntityType<MachinePyrolyzerTile> MACHINE_PYROLYZER_TILE = null;
    @ObjectHolder (ID_MACHINE_PYROLYZER)
    public static final ContainerType<MachinePyrolyzerContainer> MACHINE_PYROLYZER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_BREWER)
    public static final Block MACHINE_BREWER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_BREWER)
    public static final TileEntityType<MachineBrewerTile> MACHINE_BREWER_TILE = null;
    @ObjectHolder (ID_MACHINE_BREWER)
    public static final ContainerType<MachineBrewerContainer> MACHINE_BREWER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_BOTTLER)
    public static final Block MACHINE_BOTTLER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_BOTTLER)
    public static final TileEntityType<MachineBottlerTile> MACHINE_BOTTLER_TILE = null;
    @ObjectHolder (ID_MACHINE_BOTTLER)
    public static final ContainerType<MachineBottlerContainer> MACHINE_BOTTLER_CONTAINER = null;

    @ObjectHolder (ID_MACHINE_CRAFTER)
    public static final Block MACHINE_CRAFTER_BLOCK = null;
    @ObjectHolder (ID_MACHINE_CRAFTER)
    public static final TileEntityType<MachineCrafterTile> MACHINE_CRAFTER_TILE = null;
    @ObjectHolder (ID_MACHINE_CRAFTER)
    public static final ContainerType<MachineCrafterContainer> MACHINE_CRAFTER_CONTAINER = null;
    // endregion
}
