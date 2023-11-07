package cofh.thermal.expansion.init.registries;

import cofh.thermal.expansion.common.block.entity.dynamo.*;
import cofh.thermal.expansion.common.block.entity.machine.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.RegistryObject;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.BLOCK_ENTITIES;
import static cofh.thermal.lib.init.ThermalIDs.*;

public class TExpBlockEntities {

    private TExpBlockEntities() {

    }

    public static void register() {

    }

    public static final RegistryObject<BlockEntityType<?>> MACHINE_FURNACE_TILE = BLOCK_ENTITIES.register(ID_MACHINE_FURNACE, () -> BlockEntityType.Builder.of(MachineFurnaceTile::new, BLOCKS.get(ID_MACHINE_FURNACE)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_SAWMILL_TILE = BLOCK_ENTITIES.register(ID_MACHINE_SAWMILL, () -> BlockEntityType.Builder.of(MachineSawmillTile::new, BLOCKS.get(ID_MACHINE_SAWMILL)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_PULVERIZER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_PULVERIZER, () -> BlockEntityType.Builder.of(MachinePulverizerTile::new, BLOCKS.get(ID_MACHINE_PULVERIZER)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_SMELTER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_SMELTER, () -> BlockEntityType.Builder.of(MachineSmelterTile::new, BLOCKS.get(ID_MACHINE_SMELTER)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_INSOLATOR_TILE = BLOCK_ENTITIES.register(ID_MACHINE_INSOLATOR, () -> BlockEntityType.Builder.of(MachineInsolatorTile::new, BLOCKS.get(ID_MACHINE_INSOLATOR)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_CENTRIFUGE_TILE = BLOCK_ENTITIES.register(ID_MACHINE_CENTRIFUGE, () -> BlockEntityType.Builder.of(MachineCentrifugeTile::new, BLOCKS.get(ID_MACHINE_CENTRIFUGE)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_PRESS_TILE = BLOCK_ENTITIES.register(ID_MACHINE_PRESS, () -> BlockEntityType.Builder.of(MachinePressTile::new, BLOCKS.get(ID_MACHINE_PRESS)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_CRUCIBLE_TILE = BLOCK_ENTITIES.register(ID_MACHINE_CRUCIBLE, () -> BlockEntityType.Builder.of(MachineCrucibleTile::new, BLOCKS.get(ID_MACHINE_CRUCIBLE)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_CHILLER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_CHILLER, () -> BlockEntityType.Builder.of(MachineChillerTile::new, BLOCKS.get(ID_MACHINE_CHILLER)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_REFINERY_TILE = BLOCK_ENTITIES.register(ID_MACHINE_REFINERY, () -> BlockEntityType.Builder.of(MachineRefineryTile::new, BLOCKS.get(ID_MACHINE_REFINERY)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_PYROLYZER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_PYROLYZER, () -> BlockEntityType.Builder.of(MachinePyrolyzerTile::new, BLOCKS.get(ID_MACHINE_PYROLYZER)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_BOTTLER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_BOTTLER, () -> BlockEntityType.Builder.of(MachineBottlerTile::new, BLOCKS.get(ID_MACHINE_BOTTLER)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_BREWER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_BREWER, () -> BlockEntityType.Builder.of(MachineBrewerTile::new, BLOCKS.get(ID_MACHINE_BREWER)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_CRYSTALLIZER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_CRYSTALLIZER, () -> BlockEntityType.Builder.of(MachineCrystallizerTile::new, BLOCKS.get(ID_MACHINE_CRYSTALLIZER)).build(null));
    public static final RegistryObject<BlockEntityType<?>> MACHINE_CRAFTER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_CRAFTER, () -> BlockEntityType.Builder.of(MachineCrafterTile::new, BLOCKS.get(ID_MACHINE_CRAFTER)).build(null));

    public static final RegistryObject<BlockEntityType<?>> DYNAMO_STIRLING_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_STIRLING, () -> BlockEntityType.Builder.of(DynamoStirlingTile::new, BLOCKS.get(ID_DYNAMO_STIRLING)).build(null));
    public static final RegistryObject<BlockEntityType<?>> DYNAMO_COMPRESSION_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_COMPRESSION, () -> BlockEntityType.Builder.of(DynamoCompressionTile::new, BLOCKS.get(ID_DYNAMO_COMPRESSION)).build(null));
    public static final RegistryObject<BlockEntityType<?>> DYNAMO_MAGMATIC_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_MAGMATIC, () -> BlockEntityType.Builder.of(DynamoMagmaticTile::new, BLOCKS.get(ID_DYNAMO_MAGMATIC)).build(null));
    public static final RegistryObject<BlockEntityType<?>> DYNAMO_NUMISMATIC_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_NUMISMATIC, () -> BlockEntityType.Builder.of(DynamoNumismaticTile::new, BLOCKS.get(ID_DYNAMO_NUMISMATIC)).build(null));
    public static final RegistryObject<BlockEntityType<?>> DYNAMO_LAPIDARY_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_LAPIDARY, () -> BlockEntityType.Builder.of(DynamoLapidaryTile::new, BLOCKS.get(ID_DYNAMO_LAPIDARY)).build(null));
    public static final RegistryObject<BlockEntityType<?>> DYNAMO_DISENCHANTMENT_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_DISENCHANTMENT, () -> BlockEntityType.Builder.of(DynamoDisenchantmentTile::new, BLOCKS.get(ID_DYNAMO_DISENCHANTMENT)).build(null));
    public static final RegistryObject<BlockEntityType<?>> DYNAMO_GOURMAND_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_GOURMAND, () -> BlockEntityType.Builder.of(DynamoGourmandTile::new, BLOCKS.get(ID_DYNAMO_GOURMAND)).build(null));

}
