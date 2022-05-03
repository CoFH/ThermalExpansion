package cofh.thermal.expansion.init;

import cofh.core.item.ItemCoFH;
import cofh.thermal.core.item.SlotSealItem;
import cofh.thermal.lib.common.ThermalItemGroups;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import static cofh.lib.util.constants.Constants.ID_THERMAL_EXPANSION;
import static cofh.thermal.core.util.RegistrationHelper.registerItem;

public class TExpItems {

    private TExpItems() {

    }

    public static void register() {

        CreativeModeTab group = ThermalItemGroups.THERMAL_ITEMS;

        registerItem("slot_seal", () -> new SlotSealItem(new Item.Properties().stacksTo(1).tab(group)).setModId(ID_THERMAL_EXPANSION));

        registerItem("press_coin_die", () -> new ItemCoFH(new Item.Properties().stacksTo(1).tab(group)).setModId(ID_THERMAL_EXPANSION));
        registerItem("press_gear_die", () -> new ItemCoFH(new Item.Properties().stacksTo(1).tab(group)).setModId(ID_THERMAL_EXPANSION));
        registerItem("press_packing_2x2_die", () -> new ItemCoFH(new Item.Properties().stacksTo(1).tab(group)).setModId(ID_THERMAL_EXPANSION));
        registerItem("press_packing_3x3_die", () -> new ItemCoFH(new Item.Properties().stacksTo(1).tab(group)).setModId(ID_THERMAL_EXPANSION));
        registerItem("press_unpacking_die", () -> new ItemCoFH(new Item.Properties().stacksTo(1).tab(group)).setModId(ID_THERMAL_EXPANSION));

        registerItem("chiller_ball_cast", () -> new ItemCoFH(new Item.Properties().stacksTo(1).tab(group)).setModId(ID_THERMAL_EXPANSION));
        registerItem("chiller_ingot_cast", () -> new ItemCoFH(new Item.Properties().stacksTo(1).tab(group)).setModId(ID_THERMAL_EXPANSION));
        registerItem("chiller_rod_cast", () -> new ItemCoFH(new Item.Properties().stacksTo(1).tab(group)).setModId(ID_THERMAL_EXPANSION));
    }

}
