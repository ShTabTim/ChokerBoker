package com.sh_tab.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.HashMap;

public class AnimationBitmap {
    // Спрайт с анимацией
    public static class AnimSprite  {
        public float Speed = 0.05f;

        Vec2i scale;

        Bitmap rectShape;
        public SpriteSheet ss; // Набор спрайтов
        HashMap<String , Animation> animations = new HashMap<String, Animation>(); // Список анимаций
        Animation currAnim; // Текущая анимация
        String currAnimName;// Имя текущей анимации

        public String getCurrAnimName() {
            return currAnimName;
        }

        // Цвет спрайта
        public Paint my_color = null;

        // Конструктор
        public AnimSprite(SpriteSheet ss , Vec2i scale)
        {
            this.scale = scale;
            this.ss = ss;
            rectShape = ss.GetTextureRect(0 , 0 , scale);
        }

        // Добавить анимацию
        public void AddAnimation(String name, Animation animation)
        {
            animations.put(name , animation);
            currAnim = animation;
            currAnimName = name;
        }

        // Проигрывает указанную анимацию
        public void Play(String name)
        {
            if (currAnimName == name) return;

            currAnim = animations.get(name);
            currAnimName = name;
            currAnim.Reset();
        }

        public Bitmap GetTextureRect()
        {
            AnimationFrame currFrame = currAnim.GetFrame(Speed);
            return ss.GetTextureRect(currFrame.i, currFrame.j , scale);
        }

        public void Draw(Canvas canvas, int x , int y)
        {
            rectShape = GetTextureRect();
            canvas.drawBitmap(rectShape , x , y , my_color);
        }
    }

    // Кадр
    public static class AnimationFrame
    {
        public int i, j;
        public float time;

        public AnimationFrame(int i, int j, float time)
        {
            this.i = i;
            this.j = j;
            this.time = time;
        }
    }

    // Анимация
    public static class Animation
    {
        // Кадры
        public AnimationFrame[] frames;

        float timer;
        AnimationFrame currFrame;
        int currFrameIndex;

        public Animation(AnimationFrame[] frames)
        {
            this.frames = frames;
            Reset();
        }

        // Начать проигрывание анимации сначала
        public void Reset()
        {
            timer = 0f;
            currFrameIndex = 0;
            currFrame = frames[currFrameIndex];
        }

        // Следующий кадр
        public void NextFrame()
        {
            timer = 0f;
            currFrameIndex++;

            if (currFrameIndex == frames.length)
                currFrameIndex = 0;

            currFrame = frames[currFrameIndex];
        }

        // Получить текущий кадр
        public AnimationFrame GetFrame(float speed)
        {
            timer += speed;

            if (timer >= currFrame.time)
                NextFrame();

            return currFrame;
        }
    }
}
