package cofh.thermal.expansion.init.registries;

import cofh.thermal.expansion.common.block.entity.dynamo.*;
import cofh.thermal.expansion.common.block.entity.machine.*;
import cofh.thermal.lib.common.block.entity.AugmentableBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

import java.util.List;
import java.util.function.Supplier;

import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.BLOCK_ENTITIES;
import static cofh.thermal.lib.util.ThermalIDs.*;

public class TExpBlockEntities {

    private TExpBlockEntities() {

    }

    public static void register() {

    }

    public static void capabilitySetup(RegisterCapabilitiesEvent event) {

        var entities = List.of(
                MACHINE_FURNACE_TILE.get(),
                MACHINE_SAWMILL_TILE.get(),
                MACHINE_PULVERIZER_TILE.get(),
                MACHINE_SMELTER_TILE.get(),
                MACHINE_INSOLATOR_TILE.get(),
                MACHINE_CENTRIFUGE_TILE.get(),
                MACHINE_PRESS_TILE.get(),
                MACHINE_CRUCIBLE_TILE.get(),
                MACHINE_CHILLER_TILE.get(),
                MACHINE_REFINERY_TILE.get(),
                MACHINE_PYROLYZER_TILE.get(),
                MACHINE_BOTTLER_TILE.get(),
                MACHINE_BREWER_TILE.get(),
                MACHINE_CRYSTALLIZER_TILE.get(),
                MACHINE_CRAFTER_TILE.get(),

                DYNAMO_STIRLING_TILE.get(),
                DYNAMO_COMPRESSION_TILE.get(),
                DYNAMO_MAGMATIC_TILE.get(),
                DYNAMO_NUMISMATIC_TILE.get(),
                DYNAMO_LAPIDARY_TILE.get(),
                DYNAMO_DISENCHANTMENT_TILE.get(),
                DYNAMO_GOURMAND_TILE.get()
        );

        for (var type : entities) {
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, type, (blockEntity, side) -> ((AugmentableBlockEntity) blockEntity).getItemHandlerCapability(side));
            event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, type, (blockEntity, side) -> ((AugmentableBlockEntity) blockEntity).getFluidHandlerCapability(side));
            event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, type, (blockEntity, side) -> ((AugmentableBlockEntity) blockEntity).getEnergyCapability(side));
        }
    }

    public static final Supplier<BlockEntityType<?>> MACHINE_FURNACE_TILE = BLOCK_ENTITIES.register(ID_MACHINE_FURNACE, () -> BlockEntityType.Builder.of(MachineFurnaceBlockEntity::new, BLOCKS.get(ID_MACHINE_FURNACE)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_SAWMILL_TILE = BLOCK_ENTITIES.register(ID_MACHINE_SAWMILL, () -> BlockEntityType.Builder.of(MachineSawmillBlockEntity::new, BLOCKS.get(ID_MACHINE_SAWMILL)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_PULVERIZER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_PULVERIZER, () -> BlockEntityType.Builder.of(MachinePulverizerBlockEntity::new, BLOCKS.get(ID_MACHINE_PULVERIZER)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_SMELTER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_SMELTER, () -> BlockEntityType.Builder.of(MachineSmelterBlockEntity::new, BLOCKS.get(ID_MACHINE_SMELTER)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_INSOLATOR_TILE = BLOCK_ENTITIES.register(ID_MACHINE_INSOLATOR, () -> BlockEntityType.Builder.of(MachineInsolatorBlockEntity::new, BLOCKS.get(ID_MACHINE_INSOLATOR)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_CENTRIFUGE_TILE = BLOCK_ENTITIES.register(ID_MACHINE_CENTRIFUGE, () -> BlockEntityType.Builder.of(MachineCentrifugeBlockEntity::new, BLOCKS.get(ID_MACHINE_CENTRIFUGE)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_PRESS_TILE = BLOCK_ENTITIES.register(ID_MACHINE_PRESS, () -> BlockEntityType.Builder.of(MachinePressBlockEntity::new, BLOCKS.get(ID_MACHINE_PRESS)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_CRUCIBLE_TILE = BLOCK_ENTITIES.register(ID_MACHINE_CRUCIBLE, () -> BlockEntityType.Builder.of(MachineCrucibleBlockEntity::new, BLOCKS.get(ID_MACHINE_CRUCIBLE)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_CHILLER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_CHILLER, () -> BlockEntityType.Builder.of(MachineChillerBlockEntity::new, BLOCKS.get(ID_MACHINE_CHILLER)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_REFINERY_TILE = BLOCK_ENTITIES.register(ID_MACHINE_REFINERY, () -> BlockEntityType.Builder.of(MachineRefineryBlockEntity::new, BLOCKS.get(ID_MACHINE_REFINERY)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_PYROLYZER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_PYROLYZER, () -> BlockEntityType.Builder.of(MachinePyrolyzerBlockEntity::new, BLOCKS.get(ID_MACHINE_PYROLYZER)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_BOTTLER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_BOTTLER, () -> BlockEntityType.Builder.of(MachineBottlerBlockEntity::new, BLOCKS.get(ID_MACHINE_BOTTLER)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_BREWER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_BREWER, () -> BlockEntityType.Builder.of(MachineBrewerBlockEntity::new, BLOCKS.get(ID_MACHINE_BREWER)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_CRYSTALLIZER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_CRYSTALLIZER, () -> BlockEntityType.Builder.of(MachineCrystallizerBlockEntity::new, BLOCKS.get(ID_MACHINE_CRYSTALLIZER)).build(null));
    public static final Supplier<BlockEntityType<?>> MACHINE_CRAFTER_TILE = BLOCK_ENTITIES.register(ID_MACHINE_CRAFTER, () -> BlockEntityType.Builder.of(MachineCrafterBlockEntity::new, BLOCKS.get(ID_MACHINE_CRAFTER)).build(null));

    public static final Supplier<BlockEntityType<?>> DYNAMO_STIRLING_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_STIRLING, () -> BlockEntityType.Builder.of(DynamoStirlingBlockEntity::new, BLOCKS.get(ID_DYNAMO_STIRLING)).build(null));
    public static final Supplier<BlockEntityType<?>> DYNAMO_COMPRESSION_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_COMPRESSION, () -> BlockEntityType.Builder.of(DynamoCompressionBlockEntity::new, BLOCKS.get(ID_DYNAMO_COMPRESSION)).build(null));
    public static final Supplier<BlockEntityType<?>> DYNAMO_MAGMATIC_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_MAGMATIC, () -> BlockEntityType.Builder.of(DynamoMagmaticBlockEntity::new, BLOCKS.get(ID_DYNAMO_MAGMATIC)).build(null));
    public static final Supplier<BlockEntityType<?>> DYNAMO_NUMISMATIC_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_NUMISMATIC, () -> BlockEntityType.Builder.of(DynamoNumismaticBlockEntity::new, BLOCKS.get(ID_DYNAMO_NUMISMATIC)).build(null));
    public static final Supplier<BlockEntityType<?>> DYNAMO_LAPIDARY_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_LAPIDARY, () -> BlockEntityType.Builder.of(DynamoLapidaryBlockEntity::new, BLOCKS.get(ID_DYNAMO_LAPIDARY)).build(null));
    public static final Supplier<BlockEntityType<?>> DYNAMO_DISENCHANTMENT_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_DISENCHANTMENT, () -> BlockEntityType.Builder.of(DynamoDisenchantmentBlockEntity::new, BLOCKS.get(ID_DYNAMO_DISENCHANTMENT)).build(null));
    public static final Supplier<BlockEntityType<?>> DYNAMO_GOURMAND_TILE = BLOCK_ENTITIES.register(ID_DYNAMO_GOURMAND, () -> BlockEntityType.Builder.of(DynamoGourmandBlockEntity::new, BLOCKS.get(ID_DYNAMO_GOURMAND)).build(null));

}
