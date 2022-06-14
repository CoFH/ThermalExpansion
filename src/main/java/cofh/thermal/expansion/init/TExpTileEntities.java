package cofh.thermal.expansion.init;

import cofh.thermal.expansion.block.entity.dynamo.*;
import cofh.thermal.expansion.block.entity.machine.*;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static cofh.thermal.core.ThermalCore.TILE_ENTITIES;
import static cofh.thermal.expansion.init.TExpReferences.*;
import static cofh.thermal.lib.common.ThermalIDs.*;

public class TExpTileEntities {

    private TExpTileEntities() {

    }

    public static void register() {

        TILE_ENTITIES.register(ID_MACHINE_FURNACE, () -> BlockEntityType.Builder.of(MachineFurnaceTile::new, MACHINE_FURNACE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_SAWMILL, () -> BlockEntityType.Builder.of(MachineSawmillTile::new, MACHINE_SAWMILL_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PULVERIZER, () -> BlockEntityType.Builder.of(MachinePulverizerTile::new, MACHINE_PULVERIZER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_SMELTER, () -> BlockEntityType.Builder.of(MachineSmelterTile::new, MACHINE_SMELTER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_INSOLATOR, () -> BlockEntityType.Builder.of(MachineInsolatorTile::new, MACHINE_INSOLATOR_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CENTRIFUGE, () -> BlockEntityType.Builder.of(MachineCentrifugeTile::new, MACHINE_CENTRIFUGE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PRESS, () -> BlockEntityType.Builder.of(MachinePressTile::new, MACHINE_PRESS_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CRUCIBLE, () -> BlockEntityType.Builder.of(MachineCrucibleTile::new, MACHINE_CRUCIBLE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CHILLER, () -> BlockEntityType.Builder.of(MachineChillerTile::new, MACHINE_CHILLER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_REFINERY, () -> BlockEntityType.Builder.of(MachineRefineryTile::new, MACHINE_REFINERY_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PYROLYZER, () -> BlockEntityType.Builder.of(MachinePyrolyzerTile::new, MACHINE_PYROLYZER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_BOTTLER, () -> BlockEntityType.Builder.of(MachineBottlerTile::new, MACHINE_BOTTLER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_BREWER, () -> BlockEntityType.Builder.of(MachineBrewerTile::new, MACHINE_BREWER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CRAFTER, () -> BlockEntityType.Builder.of(MachineCrafterTile::new, MACHINE_CRAFTER_BLOCK).build(null));

        TILE_ENTITIES.register(ID_DYNAMO_STIRLING, () -> BlockEntityType.Builder.of(DynamoStirlingTile::new, DYNAMO_STIRLING_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_COMPRESSION, () -> BlockEntityType.Builder.of(DynamoCompressionTile::new, DYNAMO_COMPRESSION_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_MAGMATIC, () -> BlockEntityType.Builder.of(DynamoMagmaticTile::new, DYNAMO_MAGMATIC_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_NUMISMATIC, () -> BlockEntityType.Builder.of(DynamoNumismaticTile::new, DYNAMO_NUMISMATIC_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_LAPIDARY, () -> BlockEntityType.Builder.of(DynamoLapidaryTile::new, DYNAMO_LAPIDARY_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_DISENCHANTMENT, () -> BlockEntityType.Builder.of(DynamoDisenchantmentTile::new, DYNAMO_DISENCHANTMENT_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_GOURMAND, () -> BlockEntityType.Builder.of(DynamoGourmandTile::new, DYNAMO_GOURMAND_BLOCK).build(null));
    }

}
