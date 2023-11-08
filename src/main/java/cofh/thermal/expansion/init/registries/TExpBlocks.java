package cofh.thermal.expansion.init.registries;

import cofh.core.common.block.EntityBlockActive4Way;
import cofh.thermal.core.common.config.ThermalCoreConfig;
import cofh.thermal.expansion.common.block.entity.dynamo.*;
import cofh.thermal.expansion.common.block.entity.machine.*;
import cofh.thermal.lib.common.block.DynamoBlock;
import net.minecraft.world.level.block.SoundType;

import java.util.function.IntSupplier;

import static cofh.lib.util.constants.BlockStatePropertiesCoFH.ACTIVE;
import static cofh.lib.util.constants.ModIds.ID_THERMAL_EXPANSION;
import static cofh.lib.util.helpers.BlockHelper.lightValue;
import static cofh.thermal.core.init.registries.ThermalCreativeTabs.devicesTab;
import static cofh.thermal.core.util.RegistrationHelper.registerAugmentableBlock;
import static cofh.thermal.expansion.init.registries.TExpBlockEntities.*;
import static cofh.thermal.lib.util.ThermalAugmentRules.*;
import static cofh.thermal.lib.util.ThermalIDs.*;
import static net.minecraft.world.level.block.state.BlockBehaviour.Properties.of;

public class TExpBlocks {

    private TExpBlocks() {

    }

    public static void register() {

        IntSupplier machineAugs = () -> ThermalCoreConfig.machineAugments;

        devicesTab(registerAugmentableBlock(ID_MACHINE_FURNACE, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 14)), MachineFurnaceBlockEntity.class, MACHINE_FURNACE_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_SAWMILL, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineSawmillBlockEntity.class, MACHINE_SAWMILL_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_PULVERIZER, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachinePulverizerBlockEntity.class, MACHINE_PULVERIZER_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_SMELTER, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 14)), MachineSmelterBlockEntity.class, MACHINE_SMELTER_TILE), machineAugs, MACHINE_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_INSOLATOR, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 15)), MachineInsolatorBlockEntity.class, MACHINE_INSOLATOR_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_CENTRIFUGE, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineCentrifugeBlockEntity.class, MACHINE_CENTRIFUGE_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_PRESS, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachinePressBlockEntity.class, MACHINE_PRESS_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_CRUCIBLE, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 14)), MachineCrucibleBlockEntity.class, MACHINE_CRUCIBLE_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_CHILLER, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineChillerBlockEntity.class, MACHINE_CHILLER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_REFINERY, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 12)), MachineRefineryBlockEntity.class, MACHINE_REFINERY_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_PYROLYZER, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 8)), MachinePyrolyzerBlockEntity.class, MACHINE_PYROLYZER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_BOTTLER, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineBottlerBlockEntity.class, MACHINE_BOTTLER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_BREWER, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 3)), MachineBrewerBlockEntity.class, MACHINE_BREWER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_CRYSTALLIZER, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), MachineCrystallizerBlockEntity.class, MACHINE_CRYSTALLIZER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_MACHINE_CRAFTER, () -> new EntityBlockActive4Way(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 0)), MachineCrafterBlockEntity.class, MACHINE_CRAFTER_TILE), machineAugs, MACHINE_VALIDATOR, ID_THERMAL_EXPANSION));

        IntSupplier dynamoAugs = () -> ThermalCoreConfig.dynamoAugments;

        devicesTab(registerAugmentableBlock(ID_DYNAMO_STIRLING, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoStirlingBlockEntity.class, DYNAMO_STIRLING_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_COMPRESSION, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoCompressionBlockEntity.class, DYNAMO_COMPRESSION_TILE), dynamoAugs, DYNAMO_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_MAGMATIC, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoMagmaticBlockEntity.class, DYNAMO_MAGMATIC_TILE), dynamoAugs, DYNAMO_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_NUMISMATIC, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoNumismaticBlockEntity.class, DYNAMO_NUMISMATIC_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_LAPIDARY, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoLapidaryBlockEntity.class, DYNAMO_LAPIDARY_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_DISENCHANTMENT, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoDisenchantmentBlockEntity.class, DYNAMO_DISENCHANTMENT_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
        devicesTab(registerAugmentableBlock(ID_DYNAMO_GOURMAND, () -> new DynamoBlock(of().sound(SoundType.NETHERITE_BLOCK).strength(2.0F).lightLevel(lightValue(ACTIVE, 7)), DynamoGourmandBlockEntity.class, DYNAMO_GOURMAND_TILE), dynamoAugs, DYNAMO_NO_FLUID_VALIDATOR, ID_THERMAL_EXPANSION));
    }

}
