package wireworld.threads;

import wireworld.gui.GUI;
import wireworld.gui.MyNotification;

import java.awt.*;
import java.util.ArrayList;

public class MyNotificationThread implements Runnable {

    private boolean running = false;
    private Thread thread;
    private GUI g;
    private ArrayList<MyNotification> notifications;
    private ArrayList<MyNotification> removeNotifications;

    public MyNotificationThread(GUI g) {
        notifications = new ArrayList<MyNotification>();
        removeNotifications = new ArrayList<MyNotification>();
        this.g = g;
        start();
    }

    public void run() {
        while (running) {
            try {
                g.getCanvas().removeAll();
                int y = g.getCanvas().getHeight() - 35;
                for (MyNotification n : notifications) {
                    if (n.getTime() < 601) {
                        if (n.getBackground().getAlpha() >= 20)
                            n.setBackground(new Color(n.getBackground().getRed(), n.getBackground().getGreen(), n.getBackground().getBlue(), n.getBackground().getAlpha() - 20));
                    } else if (n.getBackground().getAlpha() < 235) {
                        n.setBackground(new Color(n.getBackground().getRed(), n.getBackground().getGreen(), n.getBackground().getBlue(), n.getBackground().getAlpha() + 20));
                    }
                    n.resize();
                    n.setBounds(10, y, g.getCanvas().getWidth() - 20, 25);
                    y -= 35;
                    g.getCanvas().add(n);
                    n.setTime(n.getTime() - 50);
                    if (n.getTime() < 1) removeNotifications.add(n);
                }
                g.repaint();
                notifications.removeAll(removeNotifications);
                Thread.sleep(50);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
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

    public void add(MyNotification n) {
        notifications.add(n);
    }


}
