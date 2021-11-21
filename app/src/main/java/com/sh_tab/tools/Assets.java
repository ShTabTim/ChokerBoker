package com.sh_tab.tools;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import java.util.ArrayList;

public class Assets {

    public ArrayList<Bitmap> Assets = new ArrayList<Bitmap>();

    public ArrayList<SpriteSheet> AssetsSheet = new ArrayList<SpriteSheet>();

    public static SpriteSheet convertToSpriteSheet(int sizePX, int sizePY, int tileSizeX, int tileSizeY, byte d , String pass, AssetManager mgr){
        return new SpriteSheet(sizePX , sizePY , tileSizeX , tileSizeY , d, pass, mgr);
    }

    public static Bitmap getBitmapFromSpriteSheet(SpriteSheet spriteSheet, byte numX, byte numY){
        return spriteSheet.GetTextureRect(0 , 0 , new Vec2i(numX, numY));
    }

}
