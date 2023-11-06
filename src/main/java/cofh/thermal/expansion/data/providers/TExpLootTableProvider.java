package cofh.thermal.expansion.data.providers;

import cofh.lib.data.LootTableProviderCoFH;
import cofh.thermal.expansion.data.tables.TExpBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class TExpLootTableProvider extends LootTableProviderCoFH {

    public TExpLootTableProvider(PackOutput output) {

        super(output, List.of(
                new SubProviderEntry(TExpBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }

}
