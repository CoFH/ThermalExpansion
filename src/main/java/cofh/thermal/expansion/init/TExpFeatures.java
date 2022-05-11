package cofh.thermal.expansion.init;

import cofh.lib.config.world.OreConfig;
import cofh.thermal.core.config.ThermalWorldConfig;
import cofh.thermal.lib.common.ThermalFeatures;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

import static cofh.lib.util.constants.Constants.ID_THERMAL_EXPANSION;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.common.ThermalFeatures.*;

@Mod.EventBusSubscriber (modid = ID_THERMAL_EXPANSION)
public class TExpFeatures {

    private TExpFeatures() {

    }

    public static void register() {

        ThermalFeatures.registerDefaultTriangleOreFeature("cinnabar_ore");

        Supplier<OreConfig> oilSandConfig = () -> ThermalWorldConfig.getOreConfig("oil_sand");
        configuredOilSand = CONFIGURED_FEATURES.register("oil_sand", () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(getOilSandReplacements(), oilSandConfig.get().getSize())));
        placedOilSand = PLACED_FEATURES.register("oil_sand", () -> new PlacedFeature(configuredOilSand.getHolder().get(),
                List.of(CountPlacement.of(oilSandConfig.get().getCount()),
                        InSquarePlacement.spread(),
                        BiomeFilter.biome(),
                        // DimensionPlacement.of(oreConfig.get().getDimensions()),
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(oilSandConfig.get().getMinY()), VerticalAnchor.absolute(oilSandConfig.get().getMaxY()))
                )
        ));
    }

    @SubscribeEvent (priority = EventPriority.HIGH)
    public static void onBiomeLoad(BiomeLoadingEvent event) {

        Biome.BiomeCategory category = event.getCategory();
        if (category == Biome.BiomeCategory.DESERT || category == Biome.BiomeCategory.MESA) {
            event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(placedOilSand.getHolder().get());
        }
    }

    private static RegistryObject<ConfiguredFeature<?, ?>> configuredOilSand;
    private static RegistryObject<PlacedFeature> placedOilSand;

    // region HELPERS
    private static List<OreConfiguration.TargetBlockState> getOilSandReplacements() {

        return List.of(OreConfiguration.target(SAND, BLOCKS.get("oil_sand").defaultBlockState()), OreConfiguration.target(RED_SAND, BLOCKS.get("oil_red_sand").defaultBlockState()));
    }
    // endregion
}
