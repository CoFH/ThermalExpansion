package cofh.thermal.expansion.compat.patchouli;

import cofh.thermal.core.util.recipes.machine.SmelterRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SmelterProcessor implements IComponentProcessor {

    private SmelterRecipe recipe;

    // aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
    @Override
    public void setup(IVariableProvider variables) {

        if (!variables.has("recipe"))
            return;
        ResourceLocation recipeId = new ResourceLocation(variables.get("recipe").asString());
        IRecipe<?> recipe = Minecraft.getInstance().world.getRecipeManager().getRecipe(recipeId).get();
        if (recipe == null)
            LogManager.getLogger().warn("Thermalpedia missing the smelter recipe: " + recipeId);
        else
            this.recipe = (SmelterRecipe) recipe;
    }

    // Did you ever hear the tragedy of Darth Lemming the wise?
    @Override
    public IVariable process(String key) {

        if (recipe == null)
            return null;
        if (key.equals("out"))
            return IVariable.from(recipe.getOutputItems().get(0));
        if (key.startsWith("in")) {
            int index = Integer.parseInt(key.substring(key.length() - 1)) - 1;
            if (recipe.getInputItems().size() <= index)
                return null;
            return IVariable.wrapList(Arrays.stream(recipe.getInputItems().get(index).getMatchingStacks()).map(IVariable::from).collect(Collectors.toList()));
        }
        return null;
    }

}
