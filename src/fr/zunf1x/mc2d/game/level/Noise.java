package fr.zunf1x.mc2d.game.level;

import java.util.Random;

public class Noise {

    private final long seed;
    private final Random rand;
    private final int octave;
    private final float amplitude;

    public Noise(long seed, int octave, float amplitude) {
        this.seed = seed;
        this.octave = octave;
        this.amplitude = amplitude;

        this.rand = new Random();
    }

    public double interpolate(float a, float b, float t) {
        float ft = (float) (t * Math.PI);
        float f = (float) ((1F - Math.cos(ft)) * 0.5F);

        return a * (1F - f) + b * f;
    }
}
