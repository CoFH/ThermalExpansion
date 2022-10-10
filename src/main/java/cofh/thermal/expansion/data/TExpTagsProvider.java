package cofh.thermal.expansion.data;

import cofh.thermal.lib.util.references.ThermalTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.lib.common.ThermalIDs.*;

public class TExpTagsProvider {

    public static class Block extends BlockTagsProvider {

        public Block(DataGenerator gen, ExistingFileHelper existingFileHelper) {

            super(gen, ID_THERMAL, existingFileHelper);
        }

        @Override
        public String getName() {

            return "Thermal Expansion: Block Tags";
        }

        @Override
        protected void addTags() {

            // region TILE BLOCKS
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_STIRLING));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_COMPRESSION));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_MAGMATIC));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_NUMISMATIC));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_LAPIDARY));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_DISENCHANTMENT));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_DYNAMO_GOURMAND));

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
            // tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_ENCHANTER));
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BLOCKS.get(ID_MACHINE_CRAFTER));
            // endregion
        }

    }

    public static class Item extends ItemTagsProvider {

        public Item(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {

            super(gen, blockTagProvider, ID_THERMAL, existingFileHelper);
        }

        @Override
        public String getName() {

            return "Thermal Expansion: Item Tags";
        }

        @Override
        protected void addTags() {

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

