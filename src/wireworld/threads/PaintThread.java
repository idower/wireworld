package wireworld.threads;

import wireworld.gui.MyCanvas;

public class PaintThread implements Runnable {

    private boolean running = false;
    private Thread thread;
    private int sleepTime = 15;
    private MyCanvas c;

    public PaintThread(MyCanvas c) {
        this.c = c;
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(sleepTime);
                int x = c.getMousePosition().x - (c.getWidth() / 2 - c.getCurrentFrame().getWidth() * c.getScale() / 2 + c.getOffsetX());
                if (x < 0) return;
                x /= c.getScale();
                if (x >= c.getCurrentGrid().getWidth()) return;
                int y = c.getMousePosition().y - (c.getHeight() / 2 - c.getCurrentFrame().getHeight() * c.getScale() / 2 + c.getOffsetY());
                if (y < 0) return;
                y /= c.getScale();
                if (y >= c.getCurrentGrid().getHeight()) return;
                c.getCurrentGrid().changeCell(x, y, c.getWhatToDraw());
                c.update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
