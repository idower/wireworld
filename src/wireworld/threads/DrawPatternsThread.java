package wireworld.threads;

import wireworld.Manager;

public class DrawPatternsThread implements Runnable {

    private boolean running = false;
    private Thread thread;
    private int sleepTime = 1000 / 15;

    public DrawPatternsThread() {

    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(sleepTime);

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
