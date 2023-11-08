package cofh.thermal.expansion.init.data.providers;

import cofh.thermal.lib.util.references.ThermalTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.lib.util.ThermalIDs.*;

public class TExpTagsProvider {

    public static class Block extends BlockTagsProvider {

        public Block(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {

            super(output, lookupProvider, ID_THERMAL, existingFileHelper);
        }

        @SuppressWarnings ("unchecked")
        @Override
        protected void addTags(HolderLookup.Provider pProvider) {

            // region TILE BLOCKS
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_STIRLING));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_COMPRESSION));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_MAGMATIC));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_NUMISMATIC));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_LAPIDARY));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_DISENCHANTMENT));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_GOURMAND));

            tag(ThermalTags.Blocks.DYNAMOS).add(BLOCKS.get(ID_DYNAMO_STIRLING));
            tag(ThermalTags.Blocks.DYNAMOS).add(BLOCKS.get(ID_DYNAMO_COMPRESSION));
            tag(ThermalTags.Blocks.DYNAMOS).add(BLOCKS.get(ID_DYNAMO_MAGMATIC));
            tag(ThermalTags.Blocks.DYNAMOS).add(BLOCKS.get(ID_DYNAMO_NUMISMATIC));
            tag(ThermalTags.Blocks.DYNAMOS).add(BLOCKS.get(ID_DYNAMO_LAPIDARY));
            tag(ThermalTags.Blocks.DYNAMOS).add(BLOCKS.get(ID_DYNAMO_DISENCHANTMENT));
            tag(ThermalTags.Blocks.DYNAMOS).add(BLOCKS.get(ID_DYNAMO_GOURMAND));

            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_FURNACE));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_SAWMILL));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_PULVERIZER));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_SMELTER));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_INSOLATOR));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_CENTRIFUGE));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_PRESS));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_CRUCIBLE));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_CHILLER));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_REFINERY));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_PYROLYZER));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_BOTTLER));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_BREWER));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_CRYSTALLIZER));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_CRAFTER));

            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_FURNACE));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_SAWMILL));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_PULVERIZER));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_SMELTER));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_INSOLATOR));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_CENTRIFUGE));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_PRESS));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_CRUCIBLE));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_CHILLER));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_REFINERY));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_PYROLYZER));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_BOTTLER));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_BREWER));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_CRYSTALLIZER));
            tag(ThermalTags.Blocks.MACHINES).add(BLOCKS.get(ID_MACHINE_CRAFTER));
            // endregion
        }

    }

    public static class Item extends ItemTagsProvider {

        public Item(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagsProvider.TagLookup<net.minecraft.world.level.block.Block>> pBlockTags, ExistingFileHelper existingFileHelper) {

            super(pOutput, pLookupProvider, pBlockTags, ID_THERMAL, existingFileHelper);
        }

        @SuppressWarnings ("unchecked")
        @Override
        protected void addTags(HolderLookup.Provider pProvider) {

            copy(ThermalTags.Blocks.DYNAMOS, ThermalTags.Items.DYNAMOS);
            copy(ThermalTags.Blocks.MACHINES, ThermalTags.Items.MACHINES);

            tag(ThermalTags.Items.MACHINE_DIES).add(ITEMS.get("press_coin_die"));
            tag(ThermalTags.Items.MACHINE_DIES).add(ITEMS.get("press_gear_die"));
            tag(ThermalTags.Items.MACHINE_DIES).add(ITEMS.get("press_packing_2x2_die"));
            tag(ThermalTags.Items.MACHINE_DIES).add(ITEMS.get("press_packing_3x3_die"));
            tag(ThermalTags.Items.MACHINE_DIES).add(ITEMS.get("press_unpacking_die"));

            tag(ThermalTags.Items.MACHINE_CASTS).add(ITEMS.get("chiller_ball_cast"));
            tag(ThermalTags.Items.MACHINE_CASTS).add(ITEMS.get("chiller_ingot_cast"));
            tag(ThermalTags.Items.MACHINE_CASTS).add(ITEMS.get("chiller_rod_cast"));
        }

    }

}

