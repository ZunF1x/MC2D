package fr.zunf1x.mc2d.game.level.inventory.inventories.crafting;

import fr.zunf1x.mc2d.game.level.inventory.ItemStack;

public interface IRecipe {

    boolean matches(CraftMatrix matrix);

    ItemStack getResult();
}
