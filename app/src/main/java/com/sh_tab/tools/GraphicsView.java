package com.sh_tab.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GraphicsView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThread drawThread;
    public static float Delta;

    private RenderUndUpdater RuU = new RenderUndUpdater() {
        @Override
        public void update(float dt) {}

        @Override
        public void draw(Canvas canvas) {canvas.drawColor(Color.BLUE);}
    };

    public GraphicsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public void Pause(){
        drawThread.Pause();
    }

    public void update(Canvas canvas, float dt){
        RuU.update(dt);
        RuU.draw(canvas);
    }

    public void ReRuU(RenderUndUpdater RuU){
        this.RuU = RuU;
    }

    @Override
    protected void onDraw(Canvas canvas){update(canvas, 0.001f);}

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        drawThread = new DrawThread(this);
        drawThread.Start();
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {boolean retry = true;
        drawThread.Stop();
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException ignored) {
            }
        }
    }

    static class DrawThread extends Thread {

        private boolean running = false;
        private boolean pausing = false;
        private GraphicsView parent;

        public DrawThread(GraphicsView parent) {
            this.parent = parent;
        }

        public void Start() {
            running = true;
        }

        public void Stop() {running = false;}

        public void Pause() {
            pausing = !pausing;
        }

        @Override
        public void run() {
            Canvas canvas;

            long time = System.currentTimeMillis();

            while (running) {
                if (!pausing) {
                    Delta = (System.currentTimeMillis() - time) / 1000f;
                    time = System.currentTimeMillis();
                    canvas = null;
                    try {
                        canvas = parent.getHolder().lockCanvas(null);
                        if (canvas == null)
                            continue;
                        parent.update(canvas, Delta);
                    } finally {
                        if (canvas != null) {
                            parent.getHolder().unlockCanvasAndPost(canvas);
                        }
                    }
                }else time = System.currentTimeMillis();
            }
        }
    }
}
