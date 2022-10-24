package fr.zunf1x.mc2d.utils.math;

import java.util.Vector;

public class Vector2f {

    private float x, y;

    public Vector2f() {
        this(0, 0);
    }

    public Vector2f(Vector2f v) {
        this(v.x, v.y);
    }

    public Vector2f(float v) {
        this(v, v);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float sqrt() {
        return x * x + y * y;
    }

    public float magnitude() {
        return (float) Math.sqrt(sqrt());
    }

    public Vector2f normalize() {
        float mag = magnitude();

        x /= mag;
        y /= mag;

        return this;
    }

    public float dot(Vector2f v) {
        return x * v.x + y * v.y;
    }

    public float min() {
        return Math.min(x, y);
    }

    public float max() {
        return Math.max(x, y);
    }

    public static Vector2f lerp(Vector2f a, Vector2f b, float t) {
        float x = Mathf.lerp(a.x, b.x, t);
        float y = Mathf.lerp(a.y, b.y, t);

        return new Vector2f(x, y);
    }

    public Vector2f negate() {
        this.x = -x;
        this.y = -y;

        return this;
    }

    public Vector2f add(float x, float y) {
        this.x += x;
        this.y += y;

        return this;
    }

    public Vector2f add(float v) {
        return add(v, v);
    }

    public Vector2f add(Vector2f v) {
        return add(v.x, v.y);
    }

    public Vector2f sub(float x, float y) {
        this.x -= x;
        this.y -= y;

        return this;
    }

    public Vector2f sub(float v) {
        return sub(v, v);
    }

    public Vector2f sub(Vector2f v) {
        return sub(v.x, v.y);
    }

    public Vector2f mul(float x, float y) {
        this.x *= x;
        this.y *= y;

        return this;
    }

    public Vector2f mul(float v) {
        return mul(v, v);
    }

    public Vector2f mul(Vector2f v) {
        return mul(v.x, v.y);
    }

    public Vector2f div(float x, float y) {
        this.x /= x;
        this.y /= y;

        return this;
    }

    public Vector2f div(float v) {
        return mul(v, v);
    }

    public Vector2f div(Vector2f v) {
        return mul(v.x, v.y);
    }

    public Vector2f set(float x, float y) {
        this.x = x;
        this.y = y;

        return this;
    }

    public Vector2f set(float v) {
        this.x = v;
        this.y = v;

        return this;
    }
    public Vector2f set(Vector2f v) {
        this.x = v.x;
        this.y = v.y;

        return this;
    }


    public Vector2f copy() {
        return new Vector2f(x, y);
    }

    public Vector2f setX(float x) {
        this.x = x;

        return this;
    }

    public Vector2f setY(float y) {
        this.y = y;

        return this;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
