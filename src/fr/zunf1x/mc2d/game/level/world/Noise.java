package fr.zunf1x.mc2d.game.level.world;

import fr.zunf1x.mc2d.utils.math.Mathf;
import fr.zunf1x.mc2d.utils.math.Vector2f;

import java.util.Random;

public class Noise {

    private long seed;
    private Random rand;
    private int octave;
    private float amplitude;

    public Noise(long seed, int octave, float amplitude) {
        this.seed = seed;
        this.octave = octave;
        this.amplitude = amplitude;

        rand = new Random();
    }

    public float getNoise(float x, float y) {
        int xMin = (int) (double) x / octave;
        int xMax = xMin + 1;
        int yMin = (int) (double) y / octave;
        int yMax = yMin + 1;

        Vector2f a = new Vector2f(xMin, yMin);
        Vector2f b = new Vector2f(xMax, yMin);
        Vector2f c = new Vector2f(xMax, yMax);
        Vector2f d = new Vector2f(xMin, yMax);

        float ra = (float) noise(a);
        float rb = (float) noise(b);
        float rc = (float) noise(c);
        float rd = (float) noise(d);

        float t1 = (x - xMin * octave) / octave;
        float t2 = (y - yMin * octave) / octave;
        float i1 = interpolate(ra, rb, t1);
        float i2 = interpolate(rd, rc, t1);

        float h = interpolate(i1, i2, t2);

        return h * amplitude;
    }

    private float interpolate(float a, float b, float t) {
        float ft = (float) (t * Math.PI);
        float f = (float) ((1F - Math.cos(ft)) * 0.5F);

        return Mathf.lerp(a, b, t);
    }

    private double noise(Vector2f coord) {
        double var = 10000 * (Math.sin(coord.getX() + Math.cos(coord.getY())) + Math.tan(seed));
        rand.setSeed((long) var);

        return rand.nextDouble();
    }
}
