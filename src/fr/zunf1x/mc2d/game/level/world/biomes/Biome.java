package fr.zunf1x.mc2d.game.level.world.biomes;

import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;

public abstract class Biome {

    protected BiomeClimat climat;
    protected Block topBlock;
    protected Block[] subBlocks;

    protected int length;

    protected String name;

    protected int treeProbability;

    protected boolean cacty;

    protected Biome(String name, int length) {
        this.name = name;
        this.length = length;
        this.climat = BiomeClimat.NONE;
        this.topBlock = Blocks.GRASS;
        this.cacty = false;
        this.treeProbability = 15;
        this.subBlocks = new Block[] {Blocks.DIRT};
    }

    public BiomeClimat getBiomeClimat() {
        return this.climat;
    }

    public Block getTopBlock() {
        return topBlock;
    }

    public Block[] getSubBlocks() {
        return subBlocks;
    }

    public int getTreeProbability() {
        return treeProbability;
    }

    public boolean isCacty() {
        return cacty;
    }

    public String getBiomeName() {
        return this.name;
    }

    public int getBiomeLength() {
        return length;
    }
}
