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
import static cofh.lib.util.helpers.BlockHelper.lightValue;
import static cofh.thermal.core.ThermalCore.TILE_ENTITIES;
import static cofh.thermal.lib.common.ThermalIDs.*;
import static cofh.thermal.core.util.RegistrationHelper.registerAugBlock;
import static cofh.thermal.expansion.init.TExpReferences.*;
import static cofh.thermal.lib.common.ThermalAugmentRules.*;
import static cofh.thermal.lib.common.ThermalFlags.getFlag;
import static net.minecraft.block.AbstractBlock.Properties.create;

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

        registerAugBlock(ID_MACHINE_FURNACE, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 14)), MachineFurnaceTile::new), machineAugs, MACHINE_NO_FLUID_VALIDATOR);
        registerAugBlock(ID_MACHINE_SAWMILL, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 0)), MachineSawmillTile::new), machineAugs, MACHINE_NO_FLUID_VALIDATOR);
        registerAugBlock(ID_MACHINE_PULVERIZER, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 0)), MachinePulverizerTile::new), machineAugs, MACHINE_NO_FLUID_VALIDATOR);
        registerAugBlock(ID_MACHINE_SMELTER, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 14)), MachineSmelterTile::new), machineAugs, MACHINE_NO_FLUID_VALIDATOR);
        registerAugBlock(ID_MACHINE_INSOLATOR, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 15)), MachineInsolatorTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_CENTRIFUGE, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 0)), MachineCentrifugeTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_PRESS, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 0)), MachinePressTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_CRUCIBLE, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 14)), MachineCrucibleTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_CHILLER, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 0)), MachineChillerTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_REFINERY, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 12)), MachineRefineryTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_PYROLYZER, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 8)), MachinePyrolyzerTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_BOTTLER, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 0)), MachineBottlerTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_BREWER, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 0)), MachineBrewerTile::new), machineAugs, MACHINE_VALIDATOR);
        registerAugBlock(ID_MACHINE_CRAFTER, () -> new TileBlockActive4Way(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 0)), MachineCrafterTile::new), machineAugs, MACHINE_VALIDATOR);

        IntSupplier dynamoAugs = () -> ThermalConfig.dynamoAugments;

        registerAugBlock(ID_DYNAMO_STIRLING, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 7)), DynamoStirlingTile::new), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_STIRLING));
        registerAugBlock(ID_DYNAMO_COMPRESSION, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 7)), DynamoCompressionTile::new), dynamoAugs, DYNAMO_VALIDATOR, getFlag(ID_DYNAMO_COMPRESSION));
        registerAugBlock(ID_DYNAMO_MAGMATIC, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 7)), DynamoMagmaticTile::new), dynamoAugs, DYNAMO_VALIDATOR, getFlag(ID_DYNAMO_MAGMATIC));
        registerAugBlock(ID_DYNAMO_NUMISMATIC, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 7)), DynamoNumismaticTile::new), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_NUMISMATIC));
        registerAugBlock(ID_DYNAMO_LAPIDARY, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 7)), DynamoLapidaryTile::new), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_LAPIDARY));
        registerAugBlock(ID_DYNAMO_DISENCHANTMENT, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 7)), DynamoDisenchantmentTile::new), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_DISENCHANTMENT));
        registerAugBlock(ID_DYNAMO_GOURMAND, () -> new TileBlockDynamo(create(Material.IRON).sound(SoundType.NETHERITE).hardnessAndResistance(2.0F).harvestTool(ToolType.PICKAXE).setLightLevel(lightValue(ACTIVE, 7)), DynamoGourmandTile::new), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, getFlag(ID_DYNAMO_GOURMAND));
    }

    private static void registerTileEntities() {

        TILE_ENTITIES.register(ID_MACHINE_FURNACE, () -> TileEntityType.Builder.create(MachineFurnaceTile::new, MACHINE_FURNACE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_SAWMILL, () -> TileEntityType.Builder.create(MachineSawmillTile::new, MACHINE_SAWMILL_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PULVERIZER, () -> TileEntityType.Builder.create(MachinePulverizerTile::new, MACHINE_PULVERIZER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_SMELTER, () -> TileEntityType.Builder.create(MachineSmelterTile::new, MACHINE_SMELTER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_INSOLATOR, () -> TileEntityType.Builder.create(MachineInsolatorTile::new, MACHINE_INSOLATOR_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CENTRIFUGE, () -> TileEntityType.Builder.create(MachineCentrifugeTile::new, MACHINE_CENTRIFUGE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PRESS, () -> TileEntityType.Builder.create(MachinePressTile::new, MACHINE_PRESS_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CRUCIBLE, () -> TileEntityType.Builder.create(MachineCrucibleTile::new, MACHINE_CRUCIBLE_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CHILLER, () -> TileEntityType.Builder.create(MachineChillerTile::new, MACHINE_CHILLER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_REFINERY, () -> TileEntityType.Builder.create(MachineRefineryTile::new, MACHINE_REFINERY_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_PYROLYZER, () -> TileEntityType.Builder.create(MachinePyrolyzerTile::new, MACHINE_PYROLYZER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_BOTTLER, () -> TileEntityType.Builder.create(MachineBottlerTile::new, MACHINE_BOTTLER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_BREWER, () -> TileEntityType.Builder.create(MachineBrewerTile::new, MACHINE_BREWER_BLOCK).build(null));
        TILE_ENTITIES.register(ID_MACHINE_CRAFTER, () -> TileEntityType.Builder.create(MachineCrafterTile::new, MACHINE_CRAFTER_BLOCK).build(null));

        TILE_ENTITIES.register(ID_DYNAMO_STIRLING, () -> TileEntityType.Builder.create(DynamoStirlingTile::new, DYNAMO_STIRLING_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_COMPRESSION, () -> TileEntityType.Builder.create(DynamoCompressionTile::new, DYNAMO_COMPRESSION_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_MAGMATIC, () -> TileEntityType.Builder.create(DynamoMagmaticTile::new, DYNAMO_MAGMATIC_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_NUMISMATIC, () -> TileEntityType.Builder.create(DynamoNumismaticTile::new, DYNAMO_NUMISMATIC_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_LAPIDARY, () -> TileEntityType.Builder.create(DynamoLapidaryTile::new, DYNAMO_LAPIDARY_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_DISENCHANTMENT, () -> TileEntityType.Builder.create(DynamoDisenchantmentTile::new, DYNAMO_DISENCHANTMENT_BLOCK).build(null));
        TILE_ENTITIES.register(ID_DYNAMO_GOURMAND, () -> TileEntityType.Builder.create(DynamoGourmandTile::new, DYNAMO_GOURMAND_BLOCK).build(null));
    }
    // endregion
}
