package fr.zunf1x.mc2d.game.level.blocks.blocks;

import fr.zunf1x.mc2d.game.level.blocks.properties.BlockProperties;

public abstract class BlockContainer extends Block {

    public BlockContainer(String name, BlockProperties blockProperties, int textureIndex) {
        super(name, blockProperties, textureIndex);
    }

    public abstract void interact();
}
