package cofh.thermal.expansion.data;

import cofh.lib.data.ItemModelProviderCoFH;
import cofh.lib.util.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.lib.common.ThermalIDs.*;

public class TExpItemModelProvider extends ItemModelProviderCoFH {

    public TExpItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {

        super(generator, ID_THERMAL, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Expansion: Item Models";
    }

    @Override
    protected void registerModels() {

        registerBlockItemModels();

        DeferredRegisterCoFH<Item> reg = ITEMS;

        generated(reg.getSup("slot_seal"));

        generated(reg.getSup("press_coin_die"));
        generated(reg.getSup("press_gear_die"));
        generated(reg.getSup("press_packing_2x2_die"));
        generated(reg.getSup("press_packing_3x3_die"));
        generated(reg.getSup("press_unpacking_die"));

        generated(reg.getSup("chiller_ball_cast"));
        generated(reg.getSup("chiller_ingot_cast"));
        generated(reg.getSup("chiller_rod_cast"));
    }

    private void registerBlockItemModels() {

        DeferredRegisterCoFH<Block> reg = BLOCKS;

        blockItem(reg.getSup(ID_MACHINE_FURNACE));
        blockItem(reg.getSup(ID_MACHINE_SAWMILL));
        blockItem(reg.getSup(ID_MACHINE_PULVERIZER));
        blockItem(reg.getSup(ID_MACHINE_SMELTER));
        blockItem(reg.getSup(ID_MACHINE_INSOLATOR));
        blockItem(reg.getSup(ID_MACHINE_CENTRIFUGE));
        blockItem(reg.getSup(ID_MACHINE_PRESS));
        blockItem(reg.getSup(ID_MACHINE_CRUCIBLE));
        blockItem(reg.getSup(ID_MACHINE_CHILLER));
        blockItem(reg.getSup(ID_MACHINE_REFINERY));
        blockItem(reg.getSup(ID_MACHINE_BREWER));
        blockItem(reg.getSup(ID_MACHINE_BOTTLER));
        blockItem(reg.getSup(ID_MACHINE_BREWER));
        blockItem(reg.getSup(ID_MACHINE_CRAFTER));

        blockItem(reg.getSup(ID_DYNAMO_STIRLING));
        blockItem(reg.getSup(ID_DYNAMO_COMPRESSION));
        blockItem(reg.getSup(ID_DYNAMO_MAGMATIC));
        blockItem(reg.getSup(ID_DYNAMO_NUMISMATIC));
        blockItem(reg.getSup(ID_DYNAMO_LAPIDARY));
        blockItem(reg.getSup(ID_DYNAMO_DISENCHANTMENT));
        blockItem(reg.getSup(ID_DYNAMO_GOURMAND));
    }

}
