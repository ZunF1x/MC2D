package fr.zunf1x.mc2d.game.level.inventory.inventories.crafting;

import fr.zunf1x.mc2d.game.level.inventory.ItemStack;
import fr.zunf1x.mc2d.game.level.inventory.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShapedRecipe implements IRecipe {

    Item[] ingredients;
    private ItemStack itemOut;
    private int xpOut;
    private int width, height;

    public ShapedRecipe(Item[] ingredients, ItemStack itemOut, int xpOut) {
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

    public boolean matches(CraftMatrix matrix) {
        return Arrays.deepEquals(this.ingredients, matrix.ingredients);
    }

    @Override
    public ItemStack getResult() {
        return this.itemOut;
    }
}