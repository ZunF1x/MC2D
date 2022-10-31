package fr.zunf1x.mc2d.game.level.inventory.inventories.crafting;

import fr.zunf1x.mc2d.game.level.inventory.items.Item;

import java.util.Arrays;

public class CraftMatrix {

    Item[] ingredients;

    public CraftMatrix() {
        this.ingredients = new Item[9];
    }

    public void updateIngredients(Item[] ingredients) {
        this.ingredients = ingredients;
    }

    public boolean matches(Item[] ingredients) {
        return Arrays.deepEquals(this.ingredients, ingredients);
    }
}
