package fr.zunf1x.mc2d.game.level.inventory.inventories.crafting;

import fr.zunf1x.mc2d.game.level.inventory.ItemStack;
import fr.zunf1x.mc2d.game.level.inventory.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapelessRecipe implements IRecipe {

    Item[] ingredients;
    private ItemStack itemOut;
    private int xpOut;

    public ShapelessRecipe(Item[] ingredients, ItemStack itemOut, int xpOut) {
        this.ingredients = new Item[9];

        for (int i = 0; i < 9; i++) {
            if (ingredients.length > i) {
                this.ingredients[i] = ingredients[i];
            } else {
                this.ingredients[i] = null;
            }
        }

        this.itemOut = itemOut;
        this.xpOut = xpOut;
    }

    @Override
    public boolean matches(CraftMatrix matrix) {
        Item[] recipeIngredients = this.ingredients;
        Item[] matrixIngredients = matrix.ingredients;

        List<Item> rIngredients = new ArrayList<>();
        List<Item> mIngredients = new ArrayList<>();

        boolean flag = true;

        for (int i = 0; i < 9; i++) {
            if (recipeIngredients[i] != null) {
                rIngredients.add(recipeIngredients[i]);
            }
            if (matrixIngredients[i] != null) {
                mIngredients.add(matrixIngredients[i]);
            }
        }

        for (Item ingredient : rIngredients) {
            if (!mIngredients.contains(ingredient)) {
                flag = false;
                break;
            }
        }

        return rIngredients.size() == mIngredients.size() && flag;
    }

    @Override
    public ItemStack getResult() {
        return this.itemOut;
    }
}
