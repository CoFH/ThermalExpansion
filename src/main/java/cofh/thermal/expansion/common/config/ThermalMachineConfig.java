package cofh.thermal.expansion.common.config;

import cofh.core.common.config.IBaseConfig;
import cofh.thermal.core.util.managers.machine.*;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

import static cofh.thermal.lib.util.ThermalFlags.getFlag;
import static cofh.thermal.lib.util.ThermalIDs.*;

public class ThermalMachineConfig implements IBaseConfig {

    @Override
    public void apply(ForgeConfigSpec.Builder builder) {

        builder.push("Machines");

        if (getFlag(ID_MACHINE_FURNACE).get()) {
            builder.push("Furnace");

            machineFurnacePower = builder
                    .comment("This sets the base power consumption (RF/t) for the Redstone Furnace.")
                    .defineInRange("Base Power", FurnaceRecipeManager.instance().getBasePower(), FurnaceRecipeManager.instance().getMinPower(), FurnaceRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_SAWMILL).get()) {
            builder.push("Sawmill");

            machineSawmillPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Sawmill.")
                    .defineInRange("Base Power", SawmillRecipeManager.instance().getBasePower(), SawmillRecipeManager.instance().getMinPower(), SawmillRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_PULVERIZER).get()) {
            builder.push("Pulverizer");

            machinePulverizerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Pulverizer.")
                    .defineInRange("Base Power", PulverizerRecipeManager.instance().getBasePower(), PulverizerRecipeManager.instance().getMinPower(), PulverizerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_SMELTER).get()) {
            builder.push("Smelter");

            machineSmelterPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Induction Smelter.")
                    .defineInRange("Base Power", SmelterRecipeManager.instance().getBasePower(), SmelterRecipeManager.instance().getMinPower(), SmelterRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_INSOLATOR).get()) {
            builder.push("Insolator");

            machineInsolatorPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Phytogenic Insolator.")
                    .defineInRange("Base Power", InsolatorRecipeManager.instance().getBasePower(), InsolatorRecipeManager.instance().getMinPower(), InsolatorRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_CENTRIFUGE).get()) {
            builder.push("Centrifuge");

            machineCentrifugePower = builder
                    .comment("This sets the base power consumption (RF/t) for the Centrifugal Separator.")
                    .defineInRange("Base Power", CentrifugeRecipeManager.instance().getBasePower(), CentrifugeRecipeManager.instance().getMinPower(), CentrifugeRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_PRESS).get()) {
            builder.push("Press");

            machinePressPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Multiservo Press.")
                    .defineInRange("Base Power", PressRecipeManager.instance().getBasePower(), PressRecipeManager.instance().getMinPower(), PressRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_CRUCIBLE).get()) {
            builder.push("Crucible");

            machineCruciblePower = builder
                    .comment("This sets the base power consumption (RF/t) for the Magma Crucible.")
                    .defineInRange("Base Power", CrucibleRecipeManager.instance().getBasePower(), CrucibleRecipeManager.instance().getMinPower(), CrucibleRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_CHILLER).get()) {
            builder.push("Chiller");

            machineChillerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Blast Chiller.")
                    .defineInRange("Base Power", ChillerRecipeManager.instance().getBasePower(), ChillerRecipeManager.instance().getMinPower(), ChillerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_REFINERY).get()) {
            builder.push("Refinery");

            machineRefineryPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Fractionating Still.")
                    .defineInRange("Base Power", RefineryRecipeManager.instance().getBasePower(), RefineryRecipeManager.instance().getMinPower(), RefineryRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_PYROLYZER).get()) {
            builder.push("Pyrolyzer");

            machinePyrolyzerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Pyrolyzer.")
                    .defineInRange("Base Power", PyrolyzerRecipeManager.instance().getBasePower(), PyrolyzerRecipeManager.instance().getMinPower(), PyrolyzerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_BOTTLER).get()) {
            builder.push("Bottler");

            machineBottlerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Fluid Encapsulator.")
                    .defineInRange("Base Power", BottlerRecipeManager.instance().getBasePower(), BottlerRecipeManager.instance().getMinPower(), BottlerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_BREWER).get()) {
            builder.push("Brewer");

            machineBrewerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Alchemical Imbuer.")
                    .defineInRange("Base Power", BrewerRecipeManager.instance().getBasePower(), BrewerRecipeManager.instance().getMinPower(), BrewerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_CRYSTALLIZER).get()) {
            builder.push("Crystallizer");

            machineCrystallizerPower = builder
                    .comment("This sets the base power consumption (RF/t) for the Crystallizer.")
                    .defineInRange("Base Power", CrystallizerRecipeManager.instance().getBasePower(), CrystallizerRecipeManager.instance().getMinPower(), CrystallizerRecipeManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_MACHINE_CRAFTER).get()) {
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
        if (machineCrystallizerPower != null) {
            CrystallizerRecipeManager.instance().setBasePower(machineCrystallizerPower.get());
        }
        if (machineCrafterPower != null) {
            CrafterRecipeManager.instance().setBasePower(machineCrafterPower.get());
        }
    }

    // region CONFIG VARIABLES
    private Supplier<Integer> machineFurnacePower;
    private Supplier<Integer> machineSawmillPower;
    private Supplier<Integer> machinePulverizerPower;
    private Supplier<Integer> machineSmelterPower;
    private Supplier<Integer> machineInsolatorPower;
    private Supplier<Integer> machineCentrifugePower;
    private Supplier<Integer> machinePressPower;
    private Supplier<Integer> machineCruciblePower;
    private Supplier<Integer> machineChillerPower;
    private Supplier<Integer> machineRefineryPower;
    private Supplier<Integer> machinePyrolyzerPower;
    private Supplier<Integer> machineBottlerPower;
    private Supplier<Integer> machineBrewerPower;
    private Supplier<Integer> machineCrystallizerPower;
    private Supplier<Integer> machineCrafterPower;
    // endregion
}
