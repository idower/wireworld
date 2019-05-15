package wireworld.threads;

import wireworld.Manager;

public class PlayThread implements Runnable {

    private boolean running = false;
    private Thread thread;
    private int sleepTime = 1000 / 15;

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
        thread.setName(".PlayThread");
        thread.start();
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

}
