package fr.zunf1x.mc2d.utils.math;

public class Mathf {

    public static float lerp(float s, float e, float t) {
        return s + (e - s) * t;
    }
}
