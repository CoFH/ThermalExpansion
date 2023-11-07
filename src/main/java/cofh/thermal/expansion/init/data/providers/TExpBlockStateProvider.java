package cofh.thermal.expansion.init.data.providers;

import cofh.lib.init.data.BlockStateProviderCoFH;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;

public class TExpBlockStateProvider extends BlockStateProviderCoFH {

    public TExpBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {

        super(output, ID_THERMAL, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        var reg = BLOCKS;

    }

}
