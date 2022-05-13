package cofh.thermal.expansion.data;

import cofh.lib.data.RecipeProviderCoFH;
import cofh.lib.util.references.CoFHTags;
import cofh.thermal.lib.common.ThermalFlags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.lib.common.ThermalIDs.*;

public class TExpRecipeProvider extends RecipeProviderCoFH {

    public TExpRecipeProvider(DataGenerator generatorIn) {

        super(generatorIn, ID_THERMAL);
        manager = ThermalFlags.manager();
    }

    @Override
    public String getName() {

        return "Thermal Expansion: Recipes";
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        generateMachineRecipes(consumer);
        generateDynamoRecipes(consumer);

        generateCraftingRecipes(consumer);
    }

    private void generateMachineRecipes(Consumer<FinishedRecipe> consumer) {

        var reg = ITEMS;

        Item machineFrame = reg.get("machine_frame");
        Item rfCoil = reg.get("rf_coil");

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_FURNACE))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_COPPER)
                .define('P', rfCoil)
                .define('X', Tags.Items.DUSTS_REDSTONE)
                .define('Y', Blocks.BRICKS)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_SAWMILL))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_COPPER)
                .define('P', rfCoil)
                .define('X', reg.get("saw_blade"))
                .define('Y', Tags.Items.STONE)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_PULVERIZER))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_COPPER)
                .define('P', rfCoil)
                .define('X', Blocks.PISTON)
                .define('Y', net.minecraft.world.item.Items.FLINT)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_SMELTER))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_INVAR)
                .define('P', rfCoil)
                .define('X', Blocks.BLAST_FURNACE)
                .define('Y', Tags.Items.SAND)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_INSOLATOR))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_LUMIUM)
                .define('P', rfCoil)
                .define('X', Blocks.DIRT)
                .define('Y', Tags.Items.GLASS)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_CENTRIFUGE))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_CONSTANTAN)
                .define('P', rfCoil)
                .define('X', net.minecraft.world.item.Items.COMPASS)
                .define('Y', CoFHTags.Items.INGOTS_TIN)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_PRESS))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_CONSTANTAN)
                .define('P', rfCoil)
                .define('X', Tags.Items.STORAGE_BLOCKS_IRON)
                .define('Y', CoFHTags.Items.INGOTS_BRONZE)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_CRUCIBLE))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_INVAR)
                .define('P', rfCoil)
                .define('X', Tags.Items.GLASS)
                .define('Y', Blocks.NETHER_BRICKS)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_CHILLER))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_INVAR)
                .define('P', rfCoil)
                .define('X', Tags.Items.GLASS)
                .define('Y', Blocks.PACKED_ICE)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_REFINERY))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_INVAR)
                .define('P', rfCoil)
                .define('X', Tags.Items.GLASS)
                .define('Y', Tags.Items.INGOTS_COPPER)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_PYROLYZER))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_CONSTANTAN)
                .define('P', rfCoil)
                .define('X', net.minecraft.world.item.Items.BLAZE_ROD)
                .define('Y', net.minecraft.world.item.Items.NETHER_BRICKS)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_BREWER))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_CONSTANTAN)
                .define('P', rfCoil)
                .define('X', Blocks.BREWING_STAND)
                .define('Y', Tags.Items.GLASS)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_BOTTLER))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_COPPER)
                .define('P', rfCoil)
                .define('X', net.minecraft.world.item.Items.BUCKET)
                .define('Y', Tags.Items.GLASS)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_MACHINE_CRAFTER))
                .define('C', machineFrame)
                .define('I', CoFHTags.Items.GEARS_COPPER)
                .define('P', rfCoil)
                .define('X', net.minecraft.world.item.Items.CRAFTING_TABLE)
                .define('Y', CoFHTags.Items.INGOTS_TIN)
                .pattern(" X ")
                .pattern("YCY")
                .pattern("IPI")
                .unlockedBy("has_machine_frame", has(machineFrame))
                .save(consumer);
    }

    private void generateDynamoRecipes(Consumer<FinishedRecipe> consumer) {

        var reg = ITEMS;

        Item rfCoil = reg.get("rf_coil");

        ShapedRecipeBuilder.shaped(reg.get(ID_DYNAMO_STIRLING))
                .define('C', rfCoil)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('G', CoFHTags.Items.GEARS_IRON)
                .define('X', Tags.Items.DUSTS_REDSTONE)
                .define('Y', Tags.Items.STONE)
                .pattern(" C ")
                .pattern("IGI")
                .pattern("YXY")
                .unlockedBy("has_rf_coil", has(rfCoil))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_DYNAMO_COMPRESSION))
                .define('C', rfCoil)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('G', CoFHTags.Items.GEARS_BRONZE)
                .define('X', Tags.Items.DUSTS_REDSTONE)
                .define('Y', CoFHTags.Items.INGOTS_BRONZE)
                .pattern(" C ")
                .pattern("IGI")
                .pattern("YXY")
                .unlockedBy("has_rf_coil", has(rfCoil))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_DYNAMO_MAGMATIC))
                .define('C', rfCoil)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('G', CoFHTags.Items.GEARS_INVAR)
                .define('X', Tags.Items.DUSTS_REDSTONE)
                .define('Y', CoFHTags.Items.INGOTS_INVAR)
                .pattern(" C ")
                .pattern("IGI")
                .pattern("YXY")
                .unlockedBy("has_rf_coil", has(rfCoil))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_DYNAMO_NUMISMATIC))
                .define('C', rfCoil)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('G', CoFHTags.Items.GEARS_TIN)
                .define('X', Tags.Items.DUSTS_REDSTONE)
                .define('Y', CoFHTags.Items.INGOTS_CONSTANTAN)
                .pattern(" C ")
                .pattern("IGI")
                .pattern("YXY")
                .unlockedBy("has_rf_coil", has(rfCoil))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_DYNAMO_LAPIDARY))
                .define('C', rfCoil)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('G', CoFHTags.Items.GEARS_GOLD)
                .define('X', Tags.Items.DUSTS_REDSTONE)
                .define('Y', Tags.Items.GEMS_LAPIS)
                .pattern(" C ")
                .pattern("IGI")
                .pattern("YXY")
                .unlockedBy("has_rf_coil", has(rfCoil))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_DYNAMO_DISENCHANTMENT))
                .define('C', rfCoil)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('G', CoFHTags.Items.GEARS_SILVER)
                .define('X', Tags.Items.DUSTS_REDSTONE)
                .define('Y', net.minecraft.world.item.Items.EXPERIENCE_BOTTLE)
                .pattern(" C ")
                .pattern("IGI")
                .pattern("YXY")
                .unlockedBy("has_rf_coil", has(rfCoil))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get(ID_DYNAMO_GOURMAND))
                .define('C', rfCoil)
                .define('I', Tags.Items.INGOTS_IRON)
                .define('G', CoFHTags.Items.GEARS_COPPER)
                .define('X', Tags.Items.DUSTS_REDSTONE)
                .define('Y', CoFHTags.Items.INGOTS_TIN)
                .pattern(" C ")
                .pattern("IGI")
                .pattern("YXY")
                .unlockedBy("has_rf_coil", has(rfCoil))
                .save(consumer);
    }

    private void generateCraftingRecipes(Consumer<FinishedRecipe> consumer) {

        var reg = ITEMS;

        ShapedRecipeBuilder.shaped(reg.get("slot_seal"))
                .define('P', CoFHTags.Items.PLATES_IRON)
                .define('i', Tags.Items.NUGGETS_IRON)
                .pattern("i i")
                .pattern(" P ")
                .pattern("i i")
                .unlockedBy("has_iron_plate", has(CoFHTags.Items.PLATES_IRON))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get("press_coin_die"))
                .define('P', CoFHTags.Items.PLATES_INVAR)
                .define('X', Tags.Items.GEMS_EMERALD)
                .pattern(" P ")
                .pattern("PXP")
                .pattern(" P ")
                .unlockedBy("has_invar_plate", has(CoFHTags.Items.PLATES_INVAR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get("press_gear_die"))
                .define('P', CoFHTags.Items.PLATES_INVAR)
                .define('X', CoFHTags.Items.GEARS_DIAMOND)
                .pattern(" P ")
                .pattern("PXP")
                .pattern(" P ")
                .unlockedBy("has_invar_plate", has(CoFHTags.Items.PLATES_INVAR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get("press_packing_2x2_die"))
                .define('C', CoFHTags.Items.PLATES_CONSTANTAN)
                .define('I', CoFHTags.Items.PLATES_INVAR)
                .define('X', ItemTags.PLANKS)
                .pattern(" C ")
                .pattern("IXI")
                .pattern(" C ")
                .unlockedBy("has_invar_plate", has(CoFHTags.Items.PLATES_INVAR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get("press_packing_3x3_die"))
                .define('C', CoFHTags.Items.PLATES_CONSTANTAN)
                .define('I', CoFHTags.Items.PLATES_INVAR)
                .define('X', ItemTags.PLANKS)
                .pattern(" I ")
                .pattern("CXC")
                .pattern(" I ")
                .unlockedBy("has_invar_plate", has(CoFHTags.Items.PLATES_INVAR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get("press_unpacking_die"))
                .define('C', CoFHTags.Items.PLATES_CONSTANTAN)
                .define('I', CoFHTags.Items.PLATES_INVAR)
                .define('X', ItemTags.PLANKS)
                .pattern("C I")
                .pattern(" X ")
                .pattern("I C")
                .unlockedBy("has_invar_plate", has(CoFHTags.Items.PLATES_INVAR))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get("chiller_ball_cast"))
                .define('P', CoFHTags.Items.PLATES_BRONZE)
                .define('X', net.minecraft.world.item.Items.MAGMA_CREAM)
                .pattern(" P ")
                .pattern("PXP")
                .pattern(" P ")
                .unlockedBy("has_bronze_plate", has(CoFHTags.Items.PLATES_BRONZE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get("chiller_ingot_cast"))
                .define('P', CoFHTags.Items.PLATES_BRONZE)
                .define('X', net.minecraft.world.item.Items.NETHER_BRICK)
                .pattern(" P ")
                .pattern("PXP")
                .pattern(" P ")
                .unlockedBy("has_bronze_plate", has(CoFHTags.Items.PLATES_BRONZE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(reg.get("chiller_rod_cast"))
                .define('P', CoFHTags.Items.PLATES_BRONZE)
                .define('X', net.minecraft.world.item.Items.BLAZE_ROD)
                .pattern(" P ")
                .pattern("PXP")
                .pattern(" P ")
                .unlockedBy("has_bronze_plate", has(CoFHTags.Items.PLATES_BRONZE))
                .save(consumer);
    }

}
