package cofh.thermal.expansion.config;

import cofh.lib.config.IBaseConfig;
import cofh.thermal.core.util.managers.machine.*;
import net.minecraftforge.common.ForgeConfigSpec;

import static cofh.thermal.lib.common.ThermalFlags.getFlag;
import static cofh.thermal.lib.common.ThermalIDs.*;

public class ThermalMachineConfig implements IBaseConfig {

    @Override
    public void apply(ForgeConfigSpec.Builder builder) {

        builder.push("Machines");

        if (getFlag(ID_MACHINE_FURNACE).getAsBoolean()) {
            builder.push("Furnace");

            machineFurnacePower = builder
                    .comment("This sets the base power consumption (RF/t) for the Redstone Furnace.")
                    .defineInRange("Base Power", FurnaceRecipeManager.instance().getBasePower(), FurnaceRecipeManager.instance().getMinPower(), FurnaceRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_SAWMILL).getAsBoolean()) {
            builder.push("Sawmill");

            machineSawmillPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Sawmill.")
                    .defineInRange("Base Power", SawmillRecipeManager.instance().getBasePower(), SawmillRecipeManager.instance().getMinPower(), SawmillRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_PULVERIZER).getAsBoolean()) {
            builder.push("Pulverizer");

            machinePulverizerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Pulverizer.")
                    .defineInRange("Base Power", PulverizerRecipeManager.instance().getBasePower(), PulverizerRecipeManager.instance().getMinPower(), PulverizerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_SMELTER).getAsBoolean()) {
            builder.push("Smelter");

            machineSmelterPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Induction Smelter.")
                    .defineInRange("Base Power", SmelterRecipeManager.instance().getBasePower(), SmelterRecipeManager.instance().getMinPower(), SmelterRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_INSOLATOR).getAsBoolean()) {
            builder.push("Insolator");

            machineInsolatorPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Phytogenic Insolator.")
                    .defineInRange("Base Power", InsolatorRecipeManager.instance().getBasePower(), InsolatorRecipeManager.instance().getMinPower(), InsolatorRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_CENTRIFUGE).getAsBoolean()) {
            builder.push("Centrifuge");

            machineCentrifugePower = builder
                    .comment("This sets the base power consumption (RF/t) for the Centrifugal Separator.")
                    .defineInRange("Base Power", CentrifugeRecipeManager.instance().getBasePower(), CentrifugeRecipeManager.instance().getMinPower(), CentrifugeRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_PRESS).getAsBoolean()) {
            builder.push("Press");

            machinePressPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Multiservo Press.")
                    .defineInRange("Base Power", PressRecipeManager.instance().getBasePower(), PressRecipeManager.instance().getMinPower(), PressRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_CRUCIBLE).getAsBoolean()) {
            builder.push("Crucible");

            machineCruciblePower = builder
                    .comment("This sets the base power consumption (RF/t) for the Magma Crucible.")
                    .defineInRange("Base Power", CrucibleRecipeManager.instance().getBasePower(), CrucibleRecipeManager.instance().getMinPower(), CrucibleRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_CHILLER).getAsBoolean()) {
            builder.push("Chiller");

            machineChillerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Blast Chiller.")
                    .defineInRange("Base Power", ChillerRecipeManager.instance().getBasePower(), ChillerRecipeManager.instance().getMinPower(), ChillerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_FURNACE).getAsBoolean()) {
            builder.push("Refinery");

            machineRefineryPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Fractionating Still.")
                    .defineInRange("Base Power", RefineryRecipeManager.instance().getBasePower(), RefineryRecipeManager.instance().getMinPower(), RefineryRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_PYROLYZER).getAsBoolean()) {
            builder.push("Pyrolyzer");

            machinePyrolyzerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Pyrolyzer.")
                    .defineInRange("Base Power", PyrolyzerRecipeManager.instance().getBasePower(), PyrolyzerRecipeManager.instance().getMinPower(), PyrolyzerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_BOTTLER).getAsBoolean()) {
            builder.push("Bottler");

            machineBottlerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Fluid Encapsulator.")
                    .defineInRange("Base Power", BottlerRecipeManager.instance().getBasePower(), BottlerRecipeManager.instance().getMinPower(), BottlerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_BREWER).getAsBoolean()) {
            builder.push("Brewer");

            machineBrewerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Alchemical Imbuer.")
                    .defineInRange("Base Power", BrewerRecipeManager.instance().getBasePower(), BrewerRecipeManager.instance().getMinPower(), BrewerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_CRAFTER).getAsBoolean()) {
            builder.push("Crafter");

            machineCrafterPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Sequential Fabricator.")
                    .defineInRange("Base Power", CrafterRecipeManager.instance().getBasePower(), CrafterRecipeManager.instance().getMinPower(), CrafterRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        builder.pop();
    }

    @Override
    public void refresh() {

        if (machineFurnacePower != null) {
            FurnaceRecipeManager.instance().setBasePower(machineFurnacePower.get());
        }
        if (machineSawmillPower != null) {
            SawmillRecipeManager.instance().setBasePower(machineSawmillPower.get());
        }
        if (machinePulverizerPower != null) {
            PulverizerRecipeManager.instance().setBasePower(machinePulverizerPower.get());
        }
        if (machineSmelterPower != null) {
            SmelterRecipeManager.instance().setBasePower(machineSmelterPower.get());
        }
        if (machineInsolatorPower != null) {
            InsolatorRecipeManager.instance().setBasePower(machineInsolatorPower.get());
        }
        if (machineCentrifugePower != null) {
            CentrifugeRecipeManager.instance().setBasePower(machineCentrifugePower.get());
        }
        if (machinePressPower != null) {
            PressRecipeManager.instance().setBasePower(machinePressPower.get());
        }
        if (machineCruciblePower != null) {
            CrucibleRecipeManager.instance().setBasePower(machineCruciblePower.get());
        }
        if (machineChillerPower != null) {
            ChillerRecipeManager.instance().setBasePower(machineChillerPower.get());
        }
        if (machineRefineryPower != null) {
            RefineryRecipeManager.instance().setBasePower(machineRefineryPower.get());
        }
        if (machinePyrolyzerPower != null) {
            PyrolyzerRecipeManager.instance().setBasePower(machinePyrolyzerPower.get());
        }
        if (machineBottlerPower != null) {
            BottlerRecipeManager.instance().setBasePower(machineBottlerPower.get());
        }
        if (machineBrewerPower != null) {
            BrewerRecipeManager.instance().setBasePower(machineBrewerPower.get());
        }
        if (machineCrafterPower != null) {
            CrafterRecipeManager.instance().setBasePower(machineCrafterPower.get());
        }
    }

    // region CONFIG VARIABLES
    private ForgeConfigSpec.IntValue machineFurnacePower;
    private ForgeConfigSpec.IntValue machineSawmillPower;
    private ForgeConfigSpec.IntValue machinePulverizerPower;
    private ForgeConfigSpec.IntValue machineSmelterPower;
    private ForgeConfigSpec.IntValue machineInsolatorPower;
    private ForgeConfigSpec.IntValue machineCentrifugePower;
    private ForgeConfigSpec.IntValue machinePressPower;
    private ForgeConfigSpec.IntValue machineCruciblePower;
    private ForgeConfigSpec.IntValue machineChillerPower;
    private ForgeConfigSpec.IntValue machineRefineryPower;
    private ForgeConfigSpec.IntValue machinePyrolyzerPower;
    private ForgeConfigSpec.IntValue machineBottlerPower;
    private ForgeConfigSpec.IntValue machineBrewerPower;
    private ForgeConfigSpec.IntValue machineCrafterPower;
    // endregion
}
