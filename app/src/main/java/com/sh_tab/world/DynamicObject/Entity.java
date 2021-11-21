package com.sh_tab.world.DynamicObject;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.sh_tab.tools.SpriteSheet;
import com.sh_tab.tools.Vec2;
import com.sh_tab.tools.Vec2i;

public class Entity {

    protected Vec2 posit = new Vec2();
    protected Vec2 speed = new Vec2();
    protected Vec2 accel = new Vec2();
    protected Paint paint = new Paint();
    protected int mass = 1;

    private Vec2i size;

    private SpriteSheet texture;

    public Entity(int a, int r, int g, int b, Vec2i size, SpriteSheet texture){
        paint.setARGB(a, r, g, b);
        this.size = size;
        this.texture = texture;
    }

    public Entity(byte a, byte r, byte g, byte b, SpriteSheet texture){
        paint.setARGB(a, r, g, b);
        this.size = new Vec2i(texture.SubWidth, texture.SubHeight);
        this.texture = texture;
    }

    public void teleportation(Vec2 pos){
        this.posit = pos;
    }

    public void teleportation(float x, float y){
        this.posit.set(x, y);
    }
    public void setSpeed(float x, float y){this.speed.set(x, y);}
    public void setAccel(float x, float y){this.accel.set(x, y);}

    public void update(float deltaT){
        ozk(deltaT);
    }

    public void impact(Vec2 power){
        accel = power.mul(1f/mass);
    }
    public void impact(float iF, float jF){accel.set(iF/mass, jF/mass);}

    public void draw(Canvas canvas, Vec2 Cam){
        canvas.drawBitmap(texture.GetTextureRect(0, 0 , size), (posit.x-(size.i/2f))-Cam.x, (posit.y-(size.j/2f))-Cam.y, paint);
    }

    protected void ozk(float deltaT){
        posit.addLocal(speed.add(accel.mulLocal(deltaT/2f)).mul(deltaT));
        speed.addLocal(accel.mul(2));
        accel.set(0,0);
    }

}
