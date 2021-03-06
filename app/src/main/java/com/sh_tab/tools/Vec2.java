package com.sh_tab.tools;


public class Vec2 {
    final static public boolean watchCreations = true;

    static public int creationCount = 0;

    public float x, y;

    public Vec2() {
        this(0, 0);
    }

    public Vec2(float x, float y) {
        if (watchCreations)
            creationCount++;
        this.x = x;
        this.y = y;
    }

    public void setZero() {
        x = 0.0f;
        y = 0.0f;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vec2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vec2 add(Vec2 v) {
        return new Vec2(x + v.x, y + v.y);
    }

    public Vec2 sub(Vec2 v) {
        return new Vec2(x - v.x, y - v.y);
    }

    public Vec2 mul(float a) {
        return new Vec2(x * a, y * a);
    }

    public Vec2 negate() {
        return new Vec2(-x, -y);
    }

    public Vec2 negateLocal() {
        x = -x;
        y = -y;
        return this;
    }

    public Vec2 addLocal(Vec2 v) {
        x += v.x;
        y += v.y;
        return this;
    }

    public Vec2 subLocal(Vec2 v) {
        x -= v.x;
        y -= v.y;
        return this;
    }

    public Vec2 mulLocal(float a) {
        x *= a;
        y *= a;
        return this;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float lengthSquared() {
        return (x*x + y*y);
    }

    public float normalize() {
        float l = length();
        if (l == 0) {
            return 0f;
        }

        x /= l;
        y /= l;
        return l;
    }

    public boolean isValid() {
        return x != Float.NaN && x != Float.NEGATIVE_INFINITY
                && x != Float.POSITIVE_INFINITY && y != Float.NaN
                && y != Float.NEGATIVE_INFINITY && y != Float.POSITIVE_INFINITY;
    }

    public Vec2 abs() {
        return new Vec2(Math.abs(x), Math.abs(y));
    }

    @Override
    public Vec2 clone() {
        return new Vec2(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public static Vec2 abs(Vec2 a) {
        return new Vec2(Math.abs(a.x), Math.abs(a.y));
    }

    public static float dot(Vec2 a, Vec2 b) {
        return a.x * b.x + a.y * b.y;
    }

    public static float cross(Vec2 a, Vec2 b) {
        return a.x * b.y - a.y * b.x;
    }

    public static Vec2 cross(Vec2 a, float s) {
        return new Vec2(s * a.y, -s * a.x);
    }

    public static Vec2 cross(float s, Vec2 a) {
        return new Vec2(-s * a.y, s * a.x);
    }

    public static Vec2 min(Vec2 a, Vec2 b) {
        return new Vec2(a.x < b.x ? a.x : b.x, a.y < b.y ? a.y : b.y);
    }

    public static Vec2 max(Vec2 a, Vec2 b) {
        return new Vec2(a.x > b.x ? a.x : b.x, a.y > b.y ? a.y : b.y);
    }
}