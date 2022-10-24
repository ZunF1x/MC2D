package fr.zunf1x.mc2d.game.level;

import fr.zunf1x.mc2d.math.Mathf;
import fr.zunf1x.mc2d.math.vectors.Vector2f;

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

    public float getNoise(float x, float y) {
        int xMin = (int) (double) x / this.octave;
        int xMax = xMin + 1;
        int yMin = (int) (double) y / this.octave;
        int yMax = yMin + 1;

        Vector2f a = new Vector2f(xMin, yMin);
        Vector2f b = new Vector2f(xMax, yMin);
        Vector2f c = new Vector2f(xMax, yMax);
        Vector2f d = new Vector2f(xMin, yMax);

        float ra = (float) this.noise(a);
        float rb = (float) this.noise(b);
        float rc = (float) this.noise(c);
        float rd = (float) this.noise(d);

        float t1 = (x - xMin * octave) / octave;
        float t2 = (y - yMin * octave) / octave;
        float i1 = Mathf.interpolate(ra, rb, t1);
        float i2 = Mathf.interpolate(rd, rc, t1);

        float h = Mathf.interpolate(i1, i2, t2);

        return h * this.amplitude;
    }

    private double noise(Vector2f vec) {
        double var = 10000 * (Math.sin(vec.getX() + Math.cos(vec.getY()) + Math.tan(seed)));
        this.rand.setSeed((long) var);

        return this.rand.nextDouble();
    }
}
