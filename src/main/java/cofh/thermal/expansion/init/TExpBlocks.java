package cofh.thermal.expansion.init;

import cofh.core.block.TileBlockActive4Way;
import cofh.thermal.expansion.tileentity.dynamo.*;
import cofh.thermal.expansion.tileentity.machine.*;
import cofh.thermal.lib.block.TileBlockDynamo;
import cofh.thermal.lib.common.ThermalConfig;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;

import java.util.function.IntSupplier;

import static cofh.lib.util.constants.Constants.ACTIVE;
import static cofh.lib.util.constants.Constants.ID_THERMAL_EXPANSION;
import static cofh.lib.util.helpers.BlockHelper.lightValue;
import static cofh.thermal.core.ThermalCore.TILE_ENTITIES;
import static cofh.thermal.core.util.RegistrationHelper.registerAugBlock;
import static cofh.thermal.expansion.init.TExpReferences.*;
import static cofh.thermal.lib.common.ThermalAugmentRules.*;
import static cofh.thermal.lib.common.ThermalFlags.getFlag;
import static cofh.thermal.lib.common.ThermalIDs.*;
import static net.minecraft.block.AbstractBlock.Properties.of;

public class TExpBlocks {

    private TExpBlocks() {

    }

    public static void register() {

        registerTileBlocks();
        registerTileEntities();
    }

    // region HELPERS
    private static void registerTileBlocks() {

        IntSupplier machineAugs = () -> ThermalConfig.machineAugments;

        registerAugBlock(ID_MACHINE_FURNACE, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 14)), MachineFurnaceTile::new), machineAugs, MACHINE_NO_FLUID_VALIDATOR, getFlag(ID_MACHINE_FURNACE), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_SAWMILL, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 0)), MachineSawmillTile::new), machineAugs, MACHINE_NO_FLUID_VALIDATOR, getFlag(ID_MACHINE_SAWMILL), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_PULVERIZER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 0)), MachinePulverizerTile::new), machineAugs, MACHINE_NO_FLUID_VALIDATOR, getFlag(ID_MACHINE_PULVERIZER), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_SMELTER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 14)), MachineSmelterTile::new), machineAugs, MACHINE_NO_FLUID_VALIDATOR, getFlag(ID_MACHINE_SMELTER), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_INSOLATOR, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 15)), MachineInsolatorTile::new), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_INSOLATOR), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_CENTRIFUGE, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 0)), MachineCentrifugeTile::new), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_CENTRIFUGE), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_PRESS, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 0)), MachinePressTile::new), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_PRESS), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_CRUCIBLE, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 14)), MachineCrucibleTile::new), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_CRUCIBLE), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_CHILLER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 0)), MachineChillerTile::new), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_CHILLER), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_REFINERY, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 12)), MachineRefineryTile::new), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_REFINERY), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_PYROLYZER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 8)), MachinePyrolyzerTile::new), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_PYROLYZER), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_BOTTLER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 0)), MachineBottlerTile::new), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_BOTTLER), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_BREWER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 0)), MachineBrewerTile::new), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_BREWER), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_MACHINE_CRAFTER, () -> new TileBlockActive4Way(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 0)), MachineCrafterTile::new), machineAugs, MACHINE_VALIDATOR, getFlag(ID_MACHINE_CRAFTER), ID_THERMAL_EXPANSION);

        IntSupplier dynamoAugs = () -> ThermalConfig.dynamoAugments;

        registerAugBlock(ID_DYNAMO_STIRLING, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 7)), DynamoStirlingTile::new), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_STIRLING), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_DYNAMO_COMPRESSION, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 7)), DynamoCompressionTile::new), dynamoAugs, DYNAMO_VALIDATOR, getFlag(ID_DYNAMO_COMPRESSION), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_DYNAMO_MAGMATIC, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 7)), DynamoMagmaticTile::new), dynamoAugs, DYNAMO_VALIDATOR, getFlag(ID_DYNAMO_MAGMATIC), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_DYNAMO_NUMISMATIC, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 7)), DynamoNumismaticTile::new), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_NUMISMATIC), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_DYNAMO_LAPIDARY, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 7)), DynamoLapidaryTile::new), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_LAPIDARY), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_DYNAMO_DISENCHANTMENT, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 7)), DynamoDisenchantmentTile::new), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_DISENCHANTMENT), ID_THERMAL_EXPANSION);
        registerAugBlock(ID_DYNAMO_GOURMAND, () -> new TileBlockDynamo(of(Material.METAL).sound(SoundType.NETHERITE_BLOCK).strength(2.0F).harvestTool(ToolType.PICKAXE).lightLevel(lightValue(ACTIVE, 7)), DynamoGourmandTile::new), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_GOURMAND), ID_THERMAL_EXPANSION);
    }

    private static void registerTileEntities() {

        TILE_ENTITIES.register(ID_MACHINE_FURNACE, () -> TileEntityType.Builder.of(MachineFurnaceTile::new, MACHINE_FURNACE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_SAWMILL, () -> TileEntityType.Builder.of(MachineSawmillTile::new, MACHINE_SAWMILL_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PULVERIZER, () -> TileEntityType.Builder.of(MachinePulverizerTile::new, MACHINE_PULVERIZER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_SMELTER, () -> TileEntityType.Builder.of(MachineSmelterTile::new, MACHINE_SMELTER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_INSOLATOR, () -> TileEntityType.Builder.of(MachineInsolatorTile::new, MACHINE_INSOLATOR_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CENTRIFUGE, () -> TileEntityType.Builder.of(MachineCentrifugeTile::new, MACHINE_CENTRIFUGE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PRESS, () -> TileEntityType.Builder.of(MachinePressTile::new, MACHINE_PRESS_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CRUCIBLE, () -> TileEntityType.Builder.of(MachineCrucibleTile::new, MACHINE_CRUCIBLE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CHILLER, () -> TileEntityType.Builder.of(MachineChillerTile::new, MACHINE_CHILLER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_REFINERY, () -> TileEntityType.Builder.of(MachineRefineryTile::new, MACHINE_REFINERY_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PYROLYZER, () -> TileEntityType.Builder.of(MachinePyrolyzerTile::new, MACHINE_PYROLYZER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_BOTTLER, () -> TileEntityType.Builder.of(MachineBottlerTile::new, MACHINE_BOTTLER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_BREWER, () -> TileEntityType.Builder.of(MachineBrewerTile::new, MACHINE_BREWER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CRAFTER, () -> TileEntityType.Builder.of(MachineCrafterTile::new, MACHINE_CRAFTER_BLOCK).build(null));

        TILE_ENTITIES.register(ID_DYNAMO_STIRLING, () -> TileEntityType.Builder.of(DynamoStirlingTile::new, DYNAMO_STIRLING_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_COMPRESSION, () -> TileEntityType.Builder.of(DynamoCompressionTile::new, DYNAMO_COMPRESSION_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_MAGMATIC, () -> TileEntityType.Builder.of(DynamoMagmaticTile::new, DYNAMO_MAGMATIC_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_NUMISMATIC, () -> TileEntityType.Builder.of(DynamoNumismaticTile::new, DYNAMO_NUMISMATIC_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_LAPIDARY, () -> TileEntityType.Builder.of(DynamoLapidaryTile::new, DYNAMO_LAPIDARY_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_DISENCHANTMENT, () -> TileEntityType.Builder.of(DynamoDisenchantmentTile::new, DYNAMO_DISENCHANTMENT_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_GOURMAND, () -> TileEntityType.Builder.of(DynamoGourmandTile::new, DYNAMO_GOURMAND_BLOCK).build(null));
    }
    // endregion
}
