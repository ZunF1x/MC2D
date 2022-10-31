package fr.zunf1x.mc2d.game.level.blocks;

import fr.zunf1x.mc2d.game.Game;

public class BlockCraftingTable extends Block {

    public BlockCraftingTable() {
        setTexture(7);
    }

    @Override
    public void onBlockInteract(Game game, int x, int y) {
        game.openInventory(1);
    }
}
