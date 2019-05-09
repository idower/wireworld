package wireworld.logic;

import wireworld.Manager;

public class PlayThread implements Runnable {

    private boolean running = false;
    private Thread thread;
    private int sleepTime = 17;
    private Manager m;

    public PlayThread(Manager m) {
        this.m = m;
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(sleepTime);
                m.nextGen();
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
