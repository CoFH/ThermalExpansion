package cofh.thermal.expansion.data;

import cofh.lib.util.references.ItemTagsCoFH;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.FluidTagsProvider;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;

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
        protected void registerTags() {

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
        protected void registerTags() {

            getOrCreateBuilder(ItemTagsCoFH.MACHINE_DIES).add(ITEMS.get("press_coin_die"));
            getOrCreateBuilder(ItemTagsCoFH.MACHINE_DIES).add(ITEMS.get("press_gear_die"));
            getOrCreateBuilder(ItemTagsCoFH.MACHINE_DIES).add(ITEMS.get("press_packing_2x2_die"));
            getOrCreateBuilder(ItemTagsCoFH.MACHINE_DIES).add(ITEMS.get("press_packing_3x3_die"));
            getOrCreateBuilder(ItemTagsCoFH.MACHINE_DIES).add(ITEMS.get("press_unpacking_die"));

            getOrCreateBuilder(ItemTagsCoFH.MACHINE_CASTS).add(ITEMS.get("chiller_ball_cast"));
            getOrCreateBuilder(ItemTagsCoFH.MACHINE_CASTS).add(ITEMS.get("chiller_ingot_cast"));
            getOrCreateBuilder(ItemTagsCoFH.MACHINE_CASTS).add(ITEMS.get("chiller_rod_cast"));
        }

    }

    public static class Fluid extends FluidTagsProvider {

        public Fluid(DataGenerator gen, ExistingFileHelper existingFileHelper) {

            super(gen, ID_THERMAL, existingFileHelper);
        }

        @Override
        public String getName() {

            return "Thermal Expansion: Fluid Tags";
        }

        @Override
        protected void registerTags() {

        }

    }

}

