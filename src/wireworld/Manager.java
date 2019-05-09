package wireworld;

import wireworld.gui.GUI;
import wireworld.logic.Grid;
import wireworld.logic.GridList;
import wireworld.logic.PlayThread;

public class Manager {

    private GUI gui;
    private GridList grids;
    private PlayThread pt;

    public Manager() {
        gui = new GUI(this);
        grids = new GridList();
        pt = new PlayThread(this);

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

    public void saveGrid(String path) {
        grids.getCurrent().save(path);
    }
}
