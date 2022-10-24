package fr.zunf1x.mc2d.math;

public class Mathf {

    public static float interpolate(float a, float b, float t) {
        float ft = (float) (t * Math.PI);
        float f = (float) ((1F - Math.cos(ft)) * 0.5F);

        return a * (1F - f) + b * f;
    }
}
