package fr.zunf1x.mc2d.game.level.world.biomes;

import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;

public class BiomeDesert extends Biome {

    protected BiomeDesert() {
        super("Desert", 12);
        this.climat = BiomeClimat.HOT;
        this.topBlock = Blocks.SAND;
        this.subBlocks = new Block[] {Blocks.SAND, Blocks.SANDSTONE, Blocks.SANDSTONE};
        this.cacty = true;
    }
}
