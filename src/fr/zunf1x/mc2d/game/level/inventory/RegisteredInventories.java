package fr.zunf1x.mc2d.game.level.inventory;

import fr.zunf1x.mc2d.game.Game;
import fr.zunf1x.mc2d.game.level.entities.EntityPlayer;
import fr.zunf1x.mc2d.game.level.inventory.inventories.crafting.InventoryCraftingTable;
import fr.zunf1x.mc2d.game.level.inventory.inventories.crafting.InventoryCraftingTableGui;

public class RegisteredInventories {

    public static Gui gui(int id, Game game, EntityPlayer e) {
        if (id == 0) return e.g;
        if (id == 1) return new InventoryCraftingTableGui(new InventoryCraftingTable(game));

        return null;
    }
}
