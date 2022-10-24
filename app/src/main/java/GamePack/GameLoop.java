package GamePack;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{

    private static final double MAX_UPS = 144;
    private static final double UPS_PERIOD = 1000/MAX_UPS;
    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Game game;
    private double averageUPS;
    private double averageFPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public void startLoop() {
        isRunning = true;
        start();
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void run(){
        super.run();

        int updateCont = 0;
        int frameCont = 0;

        long startTime = System.currentTimeMillis(), elapsedTime, sleepTime;

        Canvas canvas = null;
        while(isRunning){
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    game.update();
                    updateCont++;

                    game.draw(canvas);
                }
            }catch(IllegalArgumentException e){
                e.printStackTrace();
            }finally {
                if(canvas != null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCont++;
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long)(updateCont * UPS_PERIOD - elapsedTime);
            if(sleepTime > 0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (sleepTime < 0 && updateCont < MAX_UPS-1){
                game.update();
                updateCont++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long)(updateCont * UPS_PERIOD - elapsedTime);
            }

            elapsedTime = System.currentTimeMillis() -startTime;
            if (elapsedTime >= 1000){
                averageUPS = updateCont / (0.001 * elapsedTime);
                averageFPS = frameCont / (0.001 * elapsedTime);

                updateCont = 0;
                frameCont = 0;

                startTime = System.currentTimeMillis();
            }
        }
    }
}
