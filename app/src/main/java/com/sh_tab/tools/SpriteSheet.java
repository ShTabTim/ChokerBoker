package com.sh_tab.tools;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class SpriteSheet {
    public int SubWidth;
    public int SubHeight;
    public int SubCountX;
    public int SubCountY;

    public Bitmap Texture;

    byte borderSize;     // Толщина рамки между фрагментами

    public static Bitmap getBitmapFromAsset(AssetManager mgr, String path , int width , int height) {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            is = mgr.open(path);
            bitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(is), width , height, false);
        } catch (final IOException ignored) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ignored) {
                }
            }
        }
        return bitmap;
    }

    public SpriteSheet(int width , int height , int sprite_width , int sprite_height , byte borderSize, String path , AssetManager mgr)
    {
        System.out.println(path);
        Texture = getBitmapFromAsset(mgr , path , width , height);

        this.borderSize = borderSize;

        SubCountX = (Texture.getWidth() / (sprite_width + borderSize));
        SubCountY = (Texture.getHeight() / (sprite_height + borderSize));
        SubWidth = sprite_width;
        SubHeight = sprite_height;
    }

    public void Dispose()
    {
        Texture.recycle();
        Texture = null;
    }

    // Получаем фрагмент текстуры по номеру столбца и строки
    public Bitmap GetTextureRect(int count_from_x, int count_from_y , Vec2i size) {
        return Bitmap.createScaledBitmap(Bitmap.createBitmap(Texture,
                (count_from_x * (SubWidth + borderSize)),
                (count_from_y * (SubHeight + borderSize)),
                SubWidth,
                SubHeight),
                size.i,
                size.j,
                false);
    }
}