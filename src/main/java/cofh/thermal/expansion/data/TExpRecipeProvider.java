package cofh.thermal.expansion.data;

import cofh.lib.data.RecipeProviderCoFH;
import cofh.lib.util.DeferredRegisterCoFH;
import cofh.lib.util.references.ItemTagsCoFH;
import cofh.thermal.lib.common.ThermalFlags;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

import static cofh.lib.util.constants.Constants.ID_THERMAL;
import static cofh.thermal.core.ThermalCore.ITEMS;
import static cofh.thermal.expansion.init.TExpIDs.*;

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
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

        generateMachineRecipes(consumer);
        generateDynamoRecipes(consumer);

        generateCraftingRecipes(consumer);
    }

    private void generateMachineRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Item> reg = ITEMS;

        Item machineFrame = reg.get("machine_frame");
        Item rfCoil = reg.get("rf_coil");

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_FURNACE))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_COPPER)
                .key('P', rfCoil)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', Blocks.BRICKS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_SAWMILL))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_COPPER)
                .key('P', rfCoil)
                .key('X', reg.get("saw_blade"))
                .key('Y', Tags.Items.STONE)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_PULVERIZER))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_COPPER)
                .key('P', rfCoil)
                .key('X', Blocks.PISTON)
                .key('Y', Items.FLINT)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_SMELTER))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_INVAR)
                .key('P', rfCoil)
                .key('X', Blocks.BLAST_FURNACE)
                .key('Y', Tags.Items.SAND)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_INSOLATOR))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_LUMIUM)
                .key('P', rfCoil)
                .key('X', Blocks.DIRT)
                .key('Y', Tags.Items.GLASS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_CENTRIFUGE))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_CONSTANTAN)
                .key('P', rfCoil)
                .key('X', Items.COMPASS)
                .key('Y', ItemTagsCoFH.INGOTS_TIN)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_PRESS))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_CONSTANTAN)
                .key('P', rfCoil)
                .key('X', Tags.Items.STORAGE_BLOCKS_IRON)
                .key('Y', ItemTagsCoFH.INGOTS_BRONZE)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_CRUCIBLE))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_INVAR)
                .key('P', rfCoil)
                .key('X', Tags.Items.GLASS)
                .key('Y', Blocks.NETHER_BRICKS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_CHILLER))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_INVAR)
                .key('P', rfCoil)
                .key('X', Tags.Items.GLASS)
                .key('Y', Blocks.PACKED_ICE)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_REFINERY))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_INVAR)
                .key('P', rfCoil)
                .key('X', Tags.Items.GLASS)
                .key('Y', ItemTagsCoFH.INGOTS_COPPER)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_PYROLYZER))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_CONSTANTAN)
                .key('P', rfCoil)
                .key('X', Items.BLAZE_ROD)
                .key('Y', Items.NETHER_BRICKS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_BREWER))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_CONSTANTAN)
                .key('P', rfCoil)
                .key('X', Blocks.BREWING_STAND)
                .key('Y', Tags.Items.GLASS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_BOTTLER))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_COPPER)
                .key('P', rfCoil)
                .key('X', Items.BUCKET)
                .key('Y', Tags.Items.GLASS)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_MACHINE_CRAFTER))
                .key('C', machineFrame)
                .key('I', ItemTagsCoFH.GEARS_COPPER)
                .key('P', rfCoil)
                .key('X', Items.CRAFTING_TABLE)
                .key('Y', ItemTagsCoFH.INGOTS_TIN)
                .patternLine(" X ")
                .patternLine("YCY")
                .patternLine("IPI")
                .addCriterion("has_machine_frame", hasItem(machineFrame))
                .build(consumer);
    }

    private void generateDynamoRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Item> reg = ITEMS;

        Item rfCoil = reg.get("rf_coil");

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_DYNAMO_STIRLING))
                .key('C', rfCoil)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('G', ItemTagsCoFH.GEARS_IRON)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', Tags.Items.STONE)
                .patternLine(" C ")
                .patternLine("IGI")
                .patternLine("YXY")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_DYNAMO_COMPRESSION))
                .key('C', rfCoil)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('G', ItemTagsCoFH.GEARS_BRONZE)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', ItemTagsCoFH.INGOTS_BRONZE)
                .patternLine(" C ")
                .patternLine("IGI")
                .patternLine("YXY")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_DYNAMO_MAGMATIC))
                .key('C', rfCoil)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('G', ItemTagsCoFH.GEARS_INVAR)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', ItemTagsCoFH.INGOTS_INVAR)
                .patternLine(" C ")
                .patternLine("IGI")
                .patternLine("YXY")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_DYNAMO_NUMISMATIC))
                .key('C', rfCoil)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('G', ItemTagsCoFH.GEARS_TIN)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', ItemTagsCoFH.INGOTS_CONSTANTAN)
                .patternLine(" C ")
                .patternLine("IGI")
                .patternLine("YXY")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get(ID_DYNAMO_LAPIDARY))
                .key('C', rfCoil)
                .key('I', Tags.Items.INGOTS_IRON)
                .key('G', ItemTagsCoFH.GEARS_GOLD)
                .key('X', Tags.Items.DUSTS_REDSTONE)
                .key('Y', Tags.Items.GEMS_LAPIS)
                .patternLine(" C ")
                .patternLine("IGI")
                .patternLine("YXY")
                .addCriterion("has_rf_coil", hasItem(rfCoil))
                .build(consumer);
    }

    private void generateCraftingRecipes(Consumer<IFinishedRecipe> consumer) {

        DeferredRegisterCoFH<Item> reg = ITEMS;

        ShapedRecipeBuilder.shapedRecipe(reg.get("press_coin_die"))
                .key('P', ItemTagsCoFH.PLATES_INVAR)
                .key('X', Tags.Items.GEMS_EMERALD)
                .patternLine(" P ")
                .patternLine("PXP")
                .patternLine(" P ")
                .addCriterion("has_invar_plate", hasItem(ItemTagsCoFH.PLATES_INVAR))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("press_gear_die"))
                .key('P', ItemTagsCoFH.PLATES_INVAR)
                .key('X', ItemTagsCoFH.GEARS_DIAMOND)
                .patternLine(" P ")
                .patternLine("PXP")
                .patternLine(" P ")
                .addCriterion("has_invar_plate", hasItem(ItemTagsCoFH.PLATES_INVAR))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("press_packing_2x2_die"))
                .key('C', ItemTagsCoFH.PLATES_CONSTANTAN)
                .key('I', ItemTagsCoFH.PLATES_INVAR)
                .key('X', ItemTags.PLANKS)
                .patternLine(" C ")
                .patternLine("IXI")
                .patternLine(" C ")
                .addCriterion("has_invar_plate", hasItem(ItemTagsCoFH.PLATES_INVAR))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("press_packing_3x3_die"))
                .key('C', ItemTagsCoFH.PLATES_CONSTANTAN)
                .key('I', ItemTagsCoFH.PLATES_INVAR)
                .key('X', ItemTags.PLANKS)
                .patternLine(" I ")
                .patternLine("CXC")
                .patternLine(" I ")
                .addCriterion("has_invar_plate", hasItem(ItemTagsCoFH.PLATES_INVAR))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("press_unpacking_die"))
                .key('C', ItemTagsCoFH.PLATES_CONSTANTAN)
                .key('I', ItemTagsCoFH.PLATES_INVAR)
                .key('X', ItemTags.PLANKS)
                .patternLine("C I")
                .patternLine(" X ")
                .patternLine("I C")
                .addCriterion("has_invar_plate", hasItem(ItemTagsCoFH.PLATES_INVAR))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("chiller_ball_cast"))
                .key('P', ItemTagsCoFH.PLATES_BRONZE)
                .key('X', Items.MAGMA_CREAM)
                .patternLine(" P ")
                .patternLine("PXP")
                .patternLine(" P ")
                .addCriterion("has_bronze_plate", hasItem(ItemTagsCoFH.PLATES_BRONZE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("chiller_ingot_cast"))
                .key('P', ItemTagsCoFH.PLATES_BRONZE)
                .key('X', Items.NETHER_BRICK)
                .patternLine(" P ")
                .patternLine("PXP")
                .patternLine(" P ")
                .addCriterion("has_bronze_plate", hasItem(ItemTagsCoFH.PLATES_BRONZE))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(reg.get("chiller_rod_cast"))
                .key('P', ItemTagsCoFH.PLATES_BRONZE)
                .key('X', Items.BLAZE_ROD)
                .patternLine(" P ")
                .patternLine("PXP")
                .patternLine(" P ")
                .addCriterion("has_bronze_plate", hasItem(ItemTagsCoFH.PLATES_BRONZE))
                .build(consumer);
    }

}
