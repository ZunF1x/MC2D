package fr.zunf1x.mc2d.game.level.world;

import java.util.Random;

public class WorldProvider {

    private final Noise noise;
    private final Random random;

    public WorldProvider(long seed, int octave, float amplitude) {
        this.noise = new Noise(seed, octave, amplitude);
        this.random = new Random();
        this.random.setSeed(seed);

        System.out.println(seed);
    }

    public Random getWorldSeededRandom() {
        return this.random;
    }

    public Noise getNoise() {
        return this.noise;
    }
}
