package cofh.thermal.expansion.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static cofh.lib.util.constants.ModIds.ID_THERMAL_EXPANSION;

@Mod.EventBusSubscriber (bus = Mod.EventBusSubscriber.Bus.MOD, modid = ID_THERMAL_EXPANSION)
public class TExpDataGen {

    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) {

        DataGenerator gen = event.getGenerator();
        ExistingFileHelper exFileHelper = event.getExistingFileHelper();

        TExpTagsProvider.Block blockTags = new TExpTagsProvider.Block(gen, exFileHelper);

        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new TExpTagsProvider.Item(gen, blockTags, exFileHelper));

        gen.addProvider(event.includeServer(), new TExpLootTableProvider(gen));
        gen.addProvider(event.includeServer(), new TExpRecipeProvider(gen));

        gen.addProvider(event.includeClient(), new TExpBlockStateProvider(gen, exFileHelper));
        gen.addProvider(event.includeClient(), new TExpItemModelProvider(gen, exFileHelper));
    }

}
