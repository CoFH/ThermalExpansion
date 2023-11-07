package cofh.thermal.expansion.init.registries;

import cofh.core.common.block.TileBlockActive4Way;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.expansion.common.block.entity.dynamo.*;
import cofh.thermal.expansion.common.block.entity.machine.*;
import cofh.thermal.lib.common.block.DynamoBlock;
import net.minecraft.world.level.block.SoundType;

import java.util.function.IntSupplier;

import static cofh.lib.util.constants.BlockStatePropertiesCoFH.ACTIVE;
import static cofh.lib.util.constants.ModIds.ID_THERMAL_EXPANSION;
import static cofh.lib.util.helpers.BlockHelper.lightValue;
import static cofh.thermal.core.util.RegistrationHelper.registerAugmentableBlock;
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.*;
import static cofh.thermal.lib.init.ThermalCreativeTabs.devicesTab;
import static cofh.thermal.lib.init.ThermalIDs.*;
import static cofh.thermal.lib.util.ThermalAugmentRules.*;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;

public class TExpBlocks {

    private TExpBlocks() {

    }

    public static void register() {

        IntSupplier machineAugs = () -> ThermalCoreConfig.machineAugments;

        devicesTab(registerAugmentableBlock(ID_MACHINE_FURNACE, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 14)), MachineFurnaceTile.class, MACHINE_FURNACE_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_SAWMILL, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineSawmillTile.class, MACHINE_SAWMILL_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_PULVERIZER, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachinePulverizerTile.class, MACHINE_PULVERIZER_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_SMELTER, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 14)), MachineSmelterTile.class, MACHINE_SMELTER_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_INSOLATOR, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 15)), MachineInsolatorTile.class, MACHINE_INSOLATOR_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_CENTRIFUGE, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineCentrifugeTile.class, MACHINE_CENTRIFUGE_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_PRESS, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachinePressTile.class, MACHINE_PRESS_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_CRUCIBLE, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 14)), MachineCrucibleTile.class, MACHINE_CRUCIBLE_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_CHILLER, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineChillerTile.class, MACHINE_CHILLER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_REFINERY, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 12)), MachineRefineryTile.class, MACHINE_REFINERY_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_PYROLYZER, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 8)), MachinePyrolyzerTile.class, MACHINE_PYROLYZER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_BOTTLER, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineBottlerTile.class, MACHINE_BOTTLER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_BREWER, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 3)), MachineBrewerTile.class, MACHINE_BREWER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_CRYSTALLIZER, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), MachineCrystallizerTile.class, MACHINE_CRYSTALLIZER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_CRAFTER, () -> new TileBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineCrafterTile.class, MACHINE_CRAFTER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));

        IntSupplier dynamoAugs = () -> ThermalCoreConfig.dynamoAugments;

        devicesTab(registerAugmentableBlock(ID_DYNAMO_STIRLING, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoStirlingTile.class, DYNAMO_STIRLING_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_COMPRESSION, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoCompressionTile.class, DYNAMO_COMPRESSION_TILE), dynamoAugs, DYNAMO_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_MAGMATIC, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoMagmaticTile.class, DYNAMO_MAGMATIC_TILE), dynamoAugs, DYNAMO_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_NUMISMATIC, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoNumismaticTile.class, DYNAMO_NUMISMATIC_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_LAPIDARY, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoLapidaryTile.class, DYNAMO_LAPIDARY_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_DISENCHANTMENT, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoDisenchantmentTile.class, DYNAMO_DISENCHANTMENT_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_GOURMAND, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoGourmandTile.class, DYNAMO_GOURMAND_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
    }

}
