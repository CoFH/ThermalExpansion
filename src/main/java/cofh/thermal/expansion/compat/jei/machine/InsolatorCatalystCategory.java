package cofh.thermal.expansion.compat.jei.machine;

import cofh.thermal.core.util.recipes.machine.InsolatorCatalyst;
import cofh.thermal.lib.compat.jei.ThermalCatalystCategory;
import mezz.jei.api.helpers.IGuiHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import static cofh.lib.util.helpers.StringHelper.getTextComponent;
import static cofh.thermal.core.ThermalCore.BLOCKS;
import static cofh.thermal.lib.common.ThermalIDs.ID_MACHINE_INSOLATOR;

public class InsolatorCatalystCategory extends ThermalCatalystCategory<InsolatorCatalyst> {

    public InsolatorCatalystCategory(IGuiHelper guiHelper, ItemStack icon, ResourceLocation uid) {

        super(guiHelper, icon, uid);

        name = getTextComponent(BLOCKS.get(ID_MACHINE_INSOLATOR).getDescriptionId()).append(": ").append(getTextComponent("info.thermal.catalysts"));
    }

    @Override
    public Class<? extends InsolatorCatalyst> getRecipeClass() {

        return InsolatorCatalyst.class;
    }

}
