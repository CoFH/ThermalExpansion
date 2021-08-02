package cofh.thermal.expansion.init;

import cofh.core.item.ItemCoFH;
import cofh.thermal.lib.common.ThermalItemGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static cofh.thermal.core.util.RegistrationHelper.registerItem;

public class TExpItems {

    private TExpItems() {

    }

    public static void register() {

        ItemGroup group = ThermalItemGroups.THERMAL_ITEMS;

        registerItem("press_coin_die", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("press_gear_die", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("press_packing_2x2_die", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("press_packing_3x3_die", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("press_unpacking_die", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));

        registerItem("chiller_ball_cast", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("chiller_ingot_cast", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));
        registerItem("chiller_rod_cast", () -> new ItemCoFH(new Item.Properties().maxStackSize(1).group(group)));
    }

}
