package fr.zunf1x.mc2d.game.level.world.features;

import fr.zunf1x.mc2d.game.level.blocks.Block;
import fr.zunf1x.mc2d.game.level.blocks.Blocks;
import fr.zunf1x.mc2d.game.level.world.World;
import fr.zunf1x.mc2d.math.vectors.Vector2d;

import java.util.Random;

public class WorldGeneratorOres extends WorldGenerator {

    @Override
    public void generate(Random random, int chunkX, World world) {
        generateOres(world, random, chunkX * 16);
    }

    public void generateOres(World world, Random random, int x) {
        generateOre(Blocks.ORE_COAL, Blocks.STONE, world, random, x, 136, 256, 37, 30);
        generateOre(Blocks.ORE_COAL, Blocks.STONE, world, random, x, 0, 190, 37, 20);
        generateOre(Blocks.ORE_IRON, Blocks.STONE, world, random, x, 128, 256, 13, 40);
        generateOre(Blocks.ORE_IRON, Blocks.STONE, world, random, x, 0, 54, 13, 6);
        generateOre(Blocks.ORE_IRON, Blocks.STONE, world, random, x, 0, 64, 13, 3);
        generateOre(Blocks.ORE_GOLD, Blocks.STONE, world, random, x, 0, 32, 9, 4);
        generateOre(Blocks.ORE_DIAMOND, Blocks.STONE, world, random, x, 0, 14, 4, 3);
        generateOre(Blocks.ORE_DIAMOND, Blocks.STONE, world, random, x, 0, 14, 8, 1);
        generateOre(Blocks.ORE_REDSTONE, Blocks.STONE, world, random, x, 0, 15, 10, 4);
        generateOre(Blocks.ORE_LAPIS, Blocks.STONE, world, random, x, 0, 64, 10, 4);
        generateOre(Blocks.ORE_LAPIS, Blocks.STONE, world, random, x, 0, 30, 10, 2);
    }

    public void generateOre(Block ore, Block toReplace, World world, Random random, int x, int minY, int maxY, int maxVeinSize, int chances) {
        int maY = 256 - minY;
        int miY = 256 - maxY;

        int deltaY = maY - miY;
        int veinSize = random.nextInt(maxVeinSize + 1);

        for (int i = 0; i < chances; i++) {
            Vector2d loc = new Vector2d(x + random.nextInt(16), miY + random.nextInt(deltaY));

            WorldGenMinable generator = new WorldGenMinable(ore, toReplace, veinSize);
            generator.generate(world, random, loc);
        }
    }
}
