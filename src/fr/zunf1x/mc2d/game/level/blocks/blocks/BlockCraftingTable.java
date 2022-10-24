package fr.zunf1x.mc2d.game.level.blocks.blocks;

import fr.zunf1x.mc2d.MC2D;
import fr.zunf1x.mc2d.game.gui.GuiCraftingTable;
import fr.zunf1x.mc2d.game.level.blocks.properties.BlockProperties;

public class BlockCraftingTable extends BlockContainer {

    public BlockCraftingTable() {
        super("Crafting Table", BlockProperties.DEFAULT_PROPERTIES, 6);
    }

    @Override
    public void interact() {
        MC2D.instance.game.player.openInventory(new GuiCraftingTable());
    }
}
