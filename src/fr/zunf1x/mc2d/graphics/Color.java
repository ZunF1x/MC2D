package fr.zunf1x.mc2d.graphics;

public class Color {

    private final float red, green, blue, alpha;
    public final boolean high;

    public static final Color RED = new Color(255f, 0, 0, 255f, true);

    public Color(float red, float green, float blue, float alpha, boolean high) {
        this.high = high;

        if (high) {
            this.red = red / 255f;
            this.green = green / 255f;
            this.blue = blue / 255f;
            this.alpha = alpha / 255f;
        } else {
            this.red = red;
            this.green = green;
            this.blue = blue;
            this.alpha = alpha;
        }
    }

    public Color(float red, float green, float blue, boolean high) {
        this.high = high;

        if (high) {
            this.red = red / 255f;
            this.green = green / 255f;
            this.blue = blue / 255f;
        } else {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        this.alpha = 1.0f;
    }

    public float getRed() {
        return red;
    }

    public float getGreen() {
        return green;
    }

    public float getBlue() {
        return blue;
    }

    public float getAlpha() {
        return alpha;
    }
}
