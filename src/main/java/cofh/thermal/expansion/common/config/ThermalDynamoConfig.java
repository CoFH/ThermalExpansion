package cofh.thermal.expansion.common.config;

import cofh.core.common.config.IBaseConfig;
import cofh.thermal.core.util.managers.dynamo.*;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.function.Supplier;

import static cofh.thermal.lib.util.ThermalFlags.getFlag;
import static cofh.thermal.lib.util.ThermalIDs.*;

public class ThermalDynamoConfig implements IBaseConfig {

    @Override
    public void apply(ForgeConfigSpec.Builder builder) {

        builder.push("Dynamos");

        if (getFlag(ID_DYNAMO_STIRLING).get()) {
            builder.push("Stirling");

            dynamoStirlingPower = builder
                    .comment("This sets the base power generation (RF/t) for the Stirling Dynamo.")
                    .defineInRange("Base Power", StirlingFuelManager.instance().getBasePower(), StirlingFuelManager.instance().getMinPower(), StirlingFuelManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_DYNAMO_COMPRESSION).get()) {
            builder.push("Compression");

            dynamoCompressionPower = builder
                    .comment("This sets the base power generation (RF/t) for the Compression Dynamo.")
                    .defineInRange("Base Power", CompressionFuelManager.instance().getBasePower(), CompressionFuelManager.instance().getMinPower(), CompressionFuelManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_DYNAMO_MAGMATIC).get()) {
            builder.push("Magmatic");

            dynamoMagmaticPower = builder
                    .comment("This sets the base power generation (RF/t) for the Magmatic Dynamo.")
                    .defineInRange("Base Power", MagmaticFuelManager.instance().getBasePower(), MagmaticFuelManager.instance().getMinPower(), MagmaticFuelManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_DYNAMO_NUMISMATIC).get()) {
            builder.push("Numismatic");

            dynamoNumismaticPower = builder
                    .comment("This sets the base power generation (RF/t) for the Numismatic Dynamo.")
                    .defineInRange("Base Power", NumismaticFuelManager.instance().getBasePower(), NumismaticFuelManager.instance().getMinPower(), NumismaticFuelManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_DYNAMO_LAPIDARY).get()) {
            builder.push("Lapidary");

            dynamoLapidaryPower = builder
                    .comment("This sets the base power generation (RF/t) for the Lapidary Dynamo.")
                    .defineInRange("Base Power", LapidaryFuelManager.instance().getBasePower(), LapidaryFuelManager.instance().getMinPower(), LapidaryFuelManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_DYNAMO_DISENCHANTMENT).get()) {
            builder.push("Disenchantment");

            dynamoDisenchantmentPower = builder
                    .comment("This sets the base power generation (RF/t) for the Disenchantment Dynamo.")
                    .defineInRange("Base Power", DisenchantmentFuelManager.instance().getBasePower(), DisenchantmentFuelManager.instance().getMinPower(), DisenchantmentFuelManager.instance().getMaxPower());

            builder.pop();
        }
        if (getFlag(ID_DYNAMO_GOURMAND).get()) {
            builder.push("Gourmand");

            dynamoGourmandPower = builder
                    .comment("This sets the base power generation (RF/t) for the Gourmand Dynamo.")
                    .defineInRange("Base Power", GourmandFuelManager.instance().getBasePower(), GourmandFuelManager.instance().getMinPower(), GourmandFuelManager.instance().getMaxPower());

            builder.pop();
        }
        builder.pop();
    }

    @Override
    public void refresh() {

        if (dynamoStirlingPower != null) {
            StirlingFuelManager.instance().setBasePower(dynamoStirlingPower.get());
        }
        if (dynamoCompressionPower != null) {
            CompressionFuelManager.instance().setBasePower(dynamoCompressionPower.get());
        }
        if (dynamoMagmaticPower != null) {
            MagmaticFuelManager.instance().setBasePower(dynamoMagmaticPower.get());
        }
        if (dynamoNumismaticPower != null) {
            NumismaticFuelManager.instance().setBasePower(dynamoNumismaticPower.get());
        }
        if (dynamoLapidaryPower != null) {
            LapidaryFuelManager.instance().setBasePower(dynamoLapidaryPower.get());
        }
        if (dynamoDisenchantmentPower != null) {
            DisenchantmentFuelManager.instance().setBasePower(dynamoDisenchantmentPower.get());
        }
        if (dynamoGourmandPower != null) {
            GourmandFuelManager.instance().setBasePower(dynamoGourmandPower.get());
        }
    }

    // region CONFIG VARIABLES
    private Supplier<Integer> dynamoStirlingPower;
    private Supplier<Integer> dynamoCompressionPower;
    private Supplier<Integer> dynamoMagmaticPower;
    private Supplier<Integer> dynamoNumismaticPower;
    private Supplier<Integer> dynamoLapidaryPower;
    private Supplier<Integer> dynamoDisenchantmentPower;
    private Supplier<Integer> dynamoGourmandPower;
    // endregion
}
