package cofh.thermal.expansion.init.registries;

import cofh.core.common.item.ItemCoFH;
import cofh.thermal.core.common.item.SlotSealItem;
import net.minecraft.world.item.Item;

import static cofh.lib.util.Utils.itemProperties;
import static cofh.lib.util.constants.ModIds.ID_THERMAL_EXPANSION;
import static cofh.thermal.core.util.RegistrationHelper.registerItem;

public class TExpItems {

    private TExpItems() {

    }

    public static void register() {

        registerItem("slot_seal", () -> new SlotSealItem(itemProperties().stacksTo(1)).setModId(ID_THERMAL_EXPANSION));

        registerItem("press_coin_die", () -> new ItemCoFH(itemProperties().stacksTo(1)).setModId(ID_THERMAL_EXPANSION));
        registerItem("press_gear_die", () -> new ItemCoFH(itemProperties().stacksTo(1)).setModId(ID_THERMAL_EXPANSION));
        registerItem("press_packing_2x2_die", () -> new ItemCoFH(itemProperties().stacksTo(1)).setModId(ID_THERMAL_EXPANSION));
        registerItem("press_packing_3x3_die", () -> new ItemCoFH(itemProperties().stacksTo(1)).setModId(ID_THERMAL_EXPANSION));
        registerItem("press_unpacking_die", () -> new ItemCoFH(itemProperties().stacksTo(1)).setModId(ID_THERMAL_EXPANSION));

        registerItem("chiller_ball_cast", () -> new ItemCoFH(itemProperties().stacksTo(1)).setModId(ID_THERMAL_EXPANSION));
        registerItem("chiller_ingot_cast", () -> new ItemCoFH(itemProperties().stacksTo(1)).setModId(ID_THERMAL_EXPANSION));
        registerItem("chiller_rod_cast", () -> new ItemCoFH(itemProperties().stacksTo(1)).setModId(ID_THERMAL_EXPANSION));
    }

}
