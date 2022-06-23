package cofh.thermal.expansion.init;

import cofh.core.content.block.TileBlockActive4Way;
import cofh.thermal.core.config.ThermalCoreConfig;
import cofh.thermal.expansion.block.entity.dynamo.*;
import cofh.thermal.expansion.block.entity.machine.*;
import cofh.thermal.lib.block.TileBlockDynamo;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

import java.util.function.IntSupplier;

import static cofh.lib.util.constants.BlockStatePropertiesCoFH.ACTIVE;
import static cofh.lib.util.constants.ModIds.ID_THERMAL_EXPANSION;
import static cofh.lib.util.helpers.BlockHelper.lightValue;
import static cofh.thermal.core.util.RegistrationHelper.registerAugmentableBlock;
import static cofh.thermal.expansion.init.TExpTileEntities.*;
import static cofh.thermal.lib.common.ThermalAugmentRules.*;
import static cofh.thermal.lib.common.ThermalFlags.getFlag;
import static cofh.thermal.lib.common.ThermalIDs.*;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;

public class TExpBlocks {

    private TExpBlocks() {

    }

    public static void register() {

        IntSupplier machineAugs = () -> ThermalCoreConfig.machineAugments;

        registerAugmentableBlock(ID_MACHINE_FURNACE, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 14)), MachineFurnaceTile.class, MACHINE_FURNACE_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, getFlag(ID_MACHINE_FURNACE), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_SAWMILL, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineSawmillTile.class, MACHINE_SAWMILL_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, getFlag(ID_MACHINE_SAWMILL), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_PULVERIZER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachinePulverizerTile.class, MACHINE_PULVERIZER_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, getFlag(ID_MACHINE_PULVERIZER), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_SMELTER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 14)), MachineSmelterTile.class, MACHINE_SMELTER_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, getFlag(ID_MACHINE_SMELTER), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_INSOLATOR, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 15)), MachineInsolatorTile.class, MACHINE_INSOLATOR_TILE), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_INSOLATOR), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_CENTRIFUGE, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineCentrifugeTile.class, MACHINE_CENTRIFUGE_TILE), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_CENTRIFUGE), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_PRESS, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachinePressTile.class, MACHINE_PRESS_TILE), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_PRESS), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_CRUCIBLE, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 14)), MachineCrucibleTile.class, MACHINE_CRUCIBLE_TILE), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_CRUCIBLE), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_CHILLER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineChillerTile.class, MACHINE_CHILLER_TILE), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_CHILLER), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_REFINERY, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 12)), MachineRefineryTile.class, MACHINE_REFINERY_TILE), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_REFINERY), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_PYROLYZER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 8)), MachinePyrolyzerTile.class, MACHINE_PYROLYZER_TILE), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_PYROLYZER), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_BOTTLER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineBottlerTile.class, MACHINE_BOTTLER_TILE), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_BOTTLER), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_BREWER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineBrewerTile.class, MACHINE_BREWER_TILE), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_BREWER), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_MACHINE_CRAFTER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineCrafterTile.class, MACHINE_CRAFTER_TILE), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_CRAFTER), ID_THERMAL_EXPANSION);

        IntSupplier dynamoAugs = () -> ThermalCoreConfig.dynamoAugments;

        registerAugmentableBlock(ID_DYNAMO_STIRLING, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoStirlingTile.class, DYNAMO_STIRLING_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_STIRLING), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_DYNAMO_COMPRESSION, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoCompressionTile.class, DYNAMO_COMPRESSION_TILE), dynamoAugs, DYNAMO_VALIDATOR, getFlag(ID_DYNAMO_COMPRESSION), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_DYNAMO_MAGMATIC, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoMagmaticTile.class, DYNAMO_MAGMATIC_TILE), dynamoAugs, DYNAMO_VALIDATOR, getFlag(ID_DYNAMO_MAGMATIC), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_DYNAMO_NUMISMATIC, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoNumismaticTile.class, DYNAMO_NUMISMATIC_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_NUMISMATIC), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_DYNAMO_LAPIDARY, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoLapidaryTile.class, DYNAMO_LAPIDARY_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_LAPIDARY), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_DYNAMO_DISENCHANTMENT, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoDisenchantmentTile.class, DYNAMO_DISENCHANTMENT_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_DISENCHANTMENT), ID_THERMAL_EXPANSION);
        registerAugmentableBlock(ID_DYNAMO_GOURMAND, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoGourmandTile.class, DYNAMO_GOURMAND_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_GOURMAND), ID_THERMAL_EXPANSION);
    }

}
