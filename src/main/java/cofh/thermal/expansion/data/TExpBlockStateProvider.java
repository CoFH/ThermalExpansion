package cofh.thermal.expansion.data;

import cofh.lib.data.BlockStateProviderCoFH;
import cofh.lib.util.DeferredRegisterCoFH;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.BLOCKS;

public class TExpBlockStateProvider extends BlockStateProviderCoFH {

    public TExpBlockStateProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {

        super(gen, ID_THERMAL, existingFileHelper);
    }

    @Override
    public String getName() {

        return "Thermal Expansion: BlockStates";
    }

    @Override
    protected void registerStatesAndModels() {

        DeferredRegisterCoFH<Block> reg = BLOCKS;

    }

}
