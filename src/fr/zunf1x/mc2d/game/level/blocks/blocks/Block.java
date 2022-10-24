package fr.zunf1x.mc2d.game.level.blocks.blocks;

import fr.zunf1x.mc2d.game.level.blocks.properties.BlockProperties;
import fr.zunf1x.mc2d.game.level.items.Items;
import fr.zunf1x.mc2d.game.level.items.items.Item;

public class Block {

    private final BlockProperties blockProperties;
    private final int textureIndex;

    private final String name;
    private String destroyer;
    private int harvestLevel;

    public Block(String name, BlockProperties blockProperties, int textureIndex) {
        this.name = name;
        this.blockProperties = blockProperties;
        this.textureIndex = textureIndex;
    }

    public Block(String name, int textureIndex, boolean unbreakable, boolean gravity, float hardness) {
        this(name, new BlockProperties(unbreakable, gravity, hardness) {
            @Override
            public void update() {

            }
        }, textureIndex);
    }

    public String getName() {
        return name;
    }

    public BlockProperties getBlockProperties() {
        return this.blockProperties;
    }

    public int getTextureIndex() {
        return this.textureIndex;
    }

    public Item getItemDroped() {
        return Items.getItemFromBlock(this);
    }

    public boolean canBeDestroyByHand() {
        return false;
    }

    public Block setDestroyer(String destroyer) {
        this.destroyer = destroyer;

        return this;
    }

    public Block setHarvestLevel(int harvestLevel) {
        this.harvestLevel = harvestLevel;

        return this;
    }

    public String getDestroyer() {
        return destroyer;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }
}
