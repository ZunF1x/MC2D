package fr.zunf1x.mc2d.game.gui;

import fr.zunf1x.mc2d.game.gui.inventory.Inventory;
import fr.zunf1x.mc2d.game.gui.inventory.InventoryCraftingTable;
import fr.zunf1x.mc2d.graphics.Texture;

public class GuiCraftingTable extends GuiContainer {

    public GuiCraftingTable() {
        super(new InventoryCraftingTable(), Texture.loadTexture("/crafting_table.png"));
    }

    @Override
    public void render() {
        super.render();
    }
}
