package cofh.thermal.expansion.init.data.providers;

import cofh.lib.init.data.ItemModelProviderCoFH;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.lib.util.ThermalIDs.*;

public class TExpItemModelProvider extends ItemModelProviderCoFH {

    public TExpItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {

        super(output, ID_THERMAL, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        registerBlockItemModels();

        var reg = ITEMS;

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

        var reg = BLOCKS;

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
        blockItem(reg.getSup(ID_MACHINE_PYROLYZER));
        blockItem(reg.getSup(ID_MACHINE_BREWER));
        blockItem(reg.getSup(ID_MACHINE_BOTTLER));
        blockItem(reg.getSup(ID_MACHINE_BREWER));
        blockItem(reg.getSup(ID_MACHINE_CRYSTALLIZER));
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
