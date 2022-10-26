package fr.zunf1x.mc2d.math;

import java.awt.event.ActionListener;

public class Mathf {

    public static float interpolate(float a, float b, float t) {
        float ft = (float) (t * Math.PI);
        float f = (float) ((1F - Math.cos(ft)) * 0.5F);

        return a * (1F - f) + b * f;
    }

    public static double clamp(double value, double min, double max, ActionListener al) {
        if (value <= min) {
            al.actionPerformed(null);
            return min;
        }
        else if (value >= max) {
            al.actionPerformed(null);
            return max;
        }
        else return value;
    }

    public static double clamp(double value, double min, double max) {
        if (value <= min) return min;
        else return Math.min(value, max);
    }
}
