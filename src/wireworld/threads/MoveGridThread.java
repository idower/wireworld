package wireworld.threads;

import wireworld.Manager;
import wireworld.gui.MyCanvas;

import java.awt.*;

public class MoveGridThread implements Runnable {

    private boolean running = false;
    private Thread thread;
    private int sleepTime = 17;
    private MyCanvas c;

    public MoveGridThread(MyCanvas c) {
        this.c = c;
    }

    public void run() {
        Point mp = c.getMousePosition();
        Point mp2;
        while (running) {
            try {
                Thread.sleep(sleepTime);
                mp2 = c.getMousePosition();
                if (mp.x != mp2.x || mp.y != mp2.y) {
                    c.setOffsetX(c.getOffsetX() - mp.x + mp2.x);
                    c.setOffsetY(c.getOffsetY() - mp.y + mp2.y);
                    c.update();
                }
                mp = mp2;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.setName(".MoveGridThread");
        thread.start();
    }

    public void stop() {
        running = false;
    }


}
