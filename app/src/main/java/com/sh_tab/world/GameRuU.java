package com.sh_tab.world;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;

import com.sh_tab.tools.Assets;
import com.sh_tab.tools.Camera;
import com.sh_tab.tools.RenderUndUpdater;
import com.sh_tab.tools.Vec2;
import com.sh_tab.tools.Vec2i;
import com.sh_tab.world.DynamicObject.Entity;

import java.util.ArrayList;

import static com.sh_tab.tools.Assets.convertToSpriteSheet;

public class GameRuU implements RenderUndUpdater {

    protected Assets assets = new Assets();
    protected Camera camera;

    protected ArrayList<Entity> entities = new ArrayList<Entity>();

    public GameRuU(AssetManager mrg){


        
        camera = new Camera(new Vec2());
        assets.AssetsSheet.add(0, convertToSpriteSheet(20,156, 20, 26, (byte) 0, "mobs/pink_puff.png", mrg));
        entities.add(0, new Entity(255, 256, 256, 128, new Vec2i(100, 130), assets.AssetsSheet.get(0)));
        entities.get(0).teleportation(100, 200);
        entities.get(0).setSpeed(16, 4);
    }

    @Override
    public void update(float dt) {
        for (int i = 0; i < entities.size(); i++)
            entities.get(i).update(dt);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        for (int i = 0; i < entities.size(); i++)
            entities.get(i).draw(canvas, camera.getPos());
    }
}
