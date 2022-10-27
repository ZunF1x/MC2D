package fr.zunf1x.mc2d.game.level.world.features;

import fr.zunf1x.mc2d.game.level.world.World;

import java.util.Random;

public abstract class WorldGenerator {

    public abstract void generate(Random rand, int chunkX, World world);
}
