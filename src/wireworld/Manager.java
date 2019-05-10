package wireworld;

import wireworld.gui.GUI;
import wireworld.gui.MyLabel;
import wireworld.gui.MyNotification;
import wireworld.gui.MyNotificationThread;
import wireworld.logic.Grid;
import wireworld.logic.GridList;
import wireworld.logic.PlayThread;

public class Manager {

    private static Manager instance;
    private GUI gui;
    private GridList grids;
    private PlayThread pt;
    private MyNotificationThread mnt;

    public static void init() {
        instance = new Manager();
        instance.createManager();
    }

    public static Manager getInstance() {
        return instance;
    }

    private Manager() {

    }

    private void createManager() {
        gui = new GUI();
        grids = new GridList();
        pt = new PlayThread();
        mnt = new MyNotificationThread(gui);
        /*
        grids.add(new Grid("/home/marcin/Downloads/test.txt"));
        updateCanvas();
        */
    }

    public void prevGen() {
        grids.prev();
        updateCanvas();
    }

    public void togglePlay() {
        if (pt.isRunning()) pt.stop();
        else pt.start();
    }

    public void nextGen() {
        grids.next();
        updateCanvas();
    }

    public void updateCanvas() {
        gui.updateCanvas(grids.getCurrent());
    }

    public void loadGrid(String path) {
        Grid g = new Grid(path);
        if (!g.isGood()) return;
        grids = new GridList();
        grids.add(g);
        updateCanvas();
    }

    public void notify(String text, int time) {
        mnt.add(new MyNotification(text, time));
    }

    public void saveGrid(String path) {
        grids.getCurrent().save(path);
    }

    public void newGrid(String ww, String hh) {
        int w, h;
        try {
            w = Integer.parseInt(ww);
            h = Integer.parseInt(hh);
            if (w > 0 && h > 0) {
                Grid g = new Grid(w, h);
                grids = new GridList();
                grids.add(g);
                updateCanvas();
            } else {
                notify("Can't create grid with these dimensions.", 5);
            }
        } catch (NumberFormatException e) {
            notify("Grid dimensions must be integers.", 5);
        }
    }

    public void clear() {
        newGrid(grids.getCurrent().getWidth() + "", grids.getCurrent().getHeight() + "");
    }
}
