package com.sh_tab.tools;

import static java.lang.Math.sqrt;

public class Vec_math {

    public static Vec2i sum(Vec2i a, Vec2i b){return new Vec2i(a.i+b.i, a.j+b.j);}
    public static Vec2i sum(Vec2i a, int   i, int   j){return new Vec2i(a.i+i, a.j+j);}

    public static int   scW(Vec2i a, Vec2i b){return a.i*b.i+a.j*b.j;}
    public static Vec2i w(Vec2i a, float b){return new Vec2i((int) (a.i*b), (int) (a.j*b));}

    public static float get_long(Vec2i a){return (float) sqrt(a.i*a.i+a.j*a.j);}
}
