package fr.zunf1x.mc2d.game.level.items.items;

import fr.zunf1x.mc2d.game.level.blocks.blocks.Block;
import fr.zunf1x.mc2d.game.level.items.properties.ItemProperties;

public class ItemBlock extends Item {

    private final Block block;

    public ItemBlock(Block block) {
        super(block.getName(), ItemProperties.DEFAULT_PROPERTIES, block.getTextureIndex());

        this.block = block;
    }

    public Block getBlock() {
        return block;
    }
}
