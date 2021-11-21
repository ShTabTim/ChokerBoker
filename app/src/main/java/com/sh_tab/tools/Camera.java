package com.sh_tab.tools;

public class Camera {

    protected Vec2 position;

    public Camera() {
        position = new Vec2();
    }

    public Camera(Vec2 position) {
        this.position = position;
    }

    public Vec2 getPos(){return position;}
    public void  setPos(Vec2 position){this.position = position;}

}
