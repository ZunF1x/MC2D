package fr.zunf1x.mc2d.math.vectors;

public class Vector2d {

    private double x;
    private double y;

    public Vector2d() {
        this(0, 0);
    }

    public Vector2d(Vector2d v) {
        this(v.getX(), v.getY());
    }

    public Vector2d(double v) {
        this(v, v);
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2d normalize() {
        double length = this.length();

        this.x /= length;
        this.y /= length;

        return this;
    }

    public Vector2d add(Vector2d v) {
        return this.add(v.x, v.y);
    }

    public Vector2d add(double v) {
        return this.add(v, v);
    }

    public Vector2d add(double x, double y) {
        this.addX(x);
        this.addY(y);

        return this;
    }

    public void addX(double x) {
        this.x += x;
    }

    public void addY(double y) {
        this.y += y;
    }

    public Vector2d sub(Vector2d v) {
        return this.sub(v.x, v.y);
    }

    public Vector2d sub(double v) {
        return this.sub(v, v);
    }

    public Vector2d sub(double x, double y) {
        this.subX(x);
        this.subY(y);

        return this;
    }

    public void subX(double x) {
        this.x -= x;
    }

    public void subY(double y) {
        this.y -= y;
    }

    public Vector2d mul(Vector2d v) {
        return this.sub(v.x, v.y);
    }

    public Vector2d mul(double v) {
        return this.mul(v, v);
    }

    public Vector2d mul(double x, double y) {
        this.mulX(x);
        this.mulY(y);

        return this;
    }

    public void mulX(double x) {
        this.x *= x;
    }

    public void mulY(double y) {
        this.y *= y;
    }

    public Vector2d div(Vector2d v) {
        return this.div(v.x, v.y);
    }

    public Vector2d div(double v) {
        return this.div(v, v);
    }

    public Vector2d div(double x, double y) {
        this.divX(x);
        this.divY(y);

        return this;
    }

    public void divX(double x) {
        this.x /= x;
    }

    public void divY(double y) {
        this.y /= y;
    }

    public Vector2d set(Vector2d v) {
        return this.set(v.x, v.y);
    }

    public Vector2d set(double v) {
        return this.set(v, v);
    }

    public Vector2d set(double x, double y) {
        this.setX(x);
        this.setY(y);

        return this;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2d copy() {
        return new Vector2d(this);
    }

    @Override
    public String toString() {
        return "X : " + this.getX() + "; Y : " + this.getY();
    }

    public Vector2d print() {
        System.out.println(this);

        return this;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
