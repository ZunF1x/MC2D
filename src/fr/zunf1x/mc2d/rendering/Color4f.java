package fr.zunf1x.mc2d.rendering;

public class Color4f {

    private final float r, g, b, a;

    public Color4f(float r, float g, float b) {
        this(r, g, b, 1);
    }

    public Color4f(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public float getR() {
        return this.r;
    }

    public float getG() {
        return this.g;
    }

    public float getB() {
        return this.b;
    }

    public float getA() {
        return this.a;
    }
}
