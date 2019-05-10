package wireworld.logic;

import wireworld.Manager;

public class PlayThread implements Runnable {

    private boolean running = false;
    private Thread thread;
    private int sleepTime = 17;

    public PlayThread() {

    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(sleepTime);
                Manager.getInstance().nextGen();
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

    public boolean isRunning() {
        return running;
    }

}
