package fr.zunf1x.mc2d.math.vectors;

public class Vector2f {

    private float x;
    private float y;

    public Vector2f() {
        this(0, 0);
    }

    public Vector2f(Vector2f v) {
        this(v.getX(), v.getY());
    }

    public Vector2f(float v) {
        this(v, v);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float length() {
        return (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2f normalize() {
        float length = this.length();

        this.x /= length;
        this.y /= length;

        return this;
    }

    public Vector2f add(Vector2f v) {
        return this.add(v.x, v.y);
    }

    public Vector2f add(float v) {
        return this.add(v, v);
    }

    public Vector2f add(float x, float y) {
        this.addX(x);
        this.addY(y);

        return this;
    }

    public void addX(float x) {
        this.x += x;
    }

    public void addY(float y) {
        this.y += y;
    }

    public Vector2f sub(Vector2f v) {
        return this.sub(v.x, v.y);
    }

    public Vector2f sub(float v) {
        return this.sub(v, v);
    }

    public Vector2f sub(float x, float y) {
        this.subX(x);
        this.subY(y);

        return this;
    }

    public void subX(float x) {
        this.x -= x;
    }

    public void subY(float y) {
        this.y -= y;
    }

    public Vector2f mul(Vector2f v) {
        return this.sub(v.x, v.y);
    }

    public Vector2f mul(float v) {
        return this.mul(v, v);
    }

    public Vector2f mul(float x, float y) {
        this.mulX(x);
        this.mulY(y);

        return this;
    }

    public void mulX(float x) {
        this.x *= x;
    }

    public void mulY(float y) {
        this.y *= y;
    }

    public Vector2f div(Vector2f v) {
        return this.div(v.x, v.y);
    }

    public Vector2f div(float v) {
        return this.div(v, v);
    }

    public Vector2f div(float x, float y) {
        this.divX(x);
        this.divY(y);

        return this;
    }

    public void divX(float x) {
        this.x /= x;
    }

    public void divY(float y) {
        this.y /= y;
    }

    public Vector2f set(Vector2f v) {
        return this.set(v.x, v.y);
    }

    public Vector2f set(float v) {
        return this.set(v, v);
    }

    public Vector2f set(float x, float y) {
        this.setX(x);
        this.setY(y);

        return this;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2f copy() {
        return new Vector2f(this);
    }

    @Override
    public String toString() {
        return "X : " + this.getX() + "; Y : " + this.getY();
    }

    public Vector2f print() {
        System.out.println(this);

        return this;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
