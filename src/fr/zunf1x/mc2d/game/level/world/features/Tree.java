package fr.zunf1x.mc2d.game.level.world.features;

import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.world.Chunk;

public class Tree {

    public static void addTree(Chunk chunk, int xp, int yp) {
        for (int y = 1; y < 3; y++) {
            chunk.setBlock(xp, yp - y, Blocks.LOG);
        }

        for (int x = 0; x < 5; x++) {
            for (int y = 3; y < 5; y++) {
                chunk.setBlock(xp + x - 2, yp - y, Blocks.LEAVES);
            }
        }

        for (int x = 0; x < 3; x++) {
            chunk.setBlock(xp + x - 1, yp - 5, Blocks.LEAVES);
        }
    }
}
