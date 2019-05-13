package wireworld.logic;

import wireworld.Manager;

import java.io.*;
import java.util.Scanner;

public class Grid {

    private int width, height;
    private int[][] cells;
    private boolean isGood = false;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        cells = new int[width][height];
    }

    public Grid(String path) {
        load(path);
    }

    public void load(String path) {
        try {
            int mode = Manager.getInstance().getMode();
            Scanner in = new Scanner(new File(path));
            width = in.nextInt();
            height = in.nextInt();
            cells = new int[width][height];
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++) {
                    cells[x][y] = in.nextInt();

                    if( mode == 1 && !(cells[x][y] == 0 || cells[x][y] == 1)) {
                        Manager.getInstance().notify("Wrong file format.", 6);
                        return;
                    } else if( mode == 0 && !(cells[x][y] == 0 || cells[x][y] == 1 || cells[x][y] == 2 || cells[x][y] == 3)) {
                        Manager.getInstance().notify("Wrong file format.", 6);
                        return;
                    }

                }
            isGood = true;
            Manager.getInstance().notify("Succesfuly loaded file " + path, 5);
        } catch (FileNotFoundException e) {
            Manager.getInstance().notify("Couldn't find file " + path, 5);
        }
    }

    public void save(String path) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write(width + " " + height);
            for (int y = 0; y < height; y++) {
                writer.append('\n');
                for (int x = 0; x < width; x++) {
                    writer.append(String.valueOf(cells[x][y])).append(" ");
                }
            }
            writer.close();
            Manager.getInstance().notify("Succesfuly saved file " + path, 5);
        } catch (IOException e) {
            Manager.getInstance().notify("Couldn't save file " + path, 5);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCell(int x, int y) {
        return cells[x][y];
    }

    public Grid nextGen() {
        Grid nextGen = new Grid(width, height);
        int mode = Manager.getInstance().getMode();
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                if (mode == 0) {
                    if (cells[x][y] == 0) {
                        nextGen.cells[x][y] = 0;
                    } else if (cells[x][y] == 1) {
                        nextGen.cells[x][y] = 2;
                    } else if (cells[x][y] == 2) {
                        nextGen.cells[x][y] = 3;
                    } else if ((cells[x][y] == 3) && (neighboursCount(x, y, 1) == 1 || neighboursCount(x, y, 1) == 2)) {
                        nextGen.cells[x][y] = 1;
                    } else {
                        nextGen.cells[x][y] = 3;
                    }
                } else if (mode == 1) {
                    if (cells[x][y] == 0 && neighboursCount(x, y, 1) == 3) {
                        nextGen.cells[x][y] = 1;
                    } else if (cells[x][y] == 1) {
                        if (neighboursCount(x, y, 1) != 2 && neighboursCount(x, y, 1) != 3) {
                            nextGen.cells[x][y] = 0;
                        } else {
                            nextGen.cells[x][y] = 1;
                        }
                    }
                }
            }
        return nextGen;
    }

    public int neighboursCount(int x, int y, int type) {
        int c = 0;
        boolean topEdge = y == 0;
        boolean leftEdge = x == 0;
        boolean bottomEdge = y + 1 == height;
        boolean rightEdge = x + 1 == width;
        if (!topEdge && !leftEdge && cells[x - 1][y - 1] == type) c++;
        if (!topEdge && cells[x][y - 1] == type) c++;
        if (!topEdge && !rightEdge && cells[x + 1][y - 1] == type) c++;
        if (!rightEdge && cells[x + 1][y] == type) c++;
        if (!rightEdge && !bottomEdge && cells[x + 1][y + 1] == type) c++;
        if (!bottomEdge && cells[x][y + 1] == type) c++;
        if (!bottomEdge && !leftEdge && cells[x - 1][y + 1] == type) c++;
        if (!leftEdge && cells[x - 1][y] == type) c++;
        return c;
    }

    public boolean isGood() {
        return isGood;
    }

    public void changeCell(int x, int y, int state) {
        cells[x][y] = state;
    }

    public void resize(int n, int e, int s, int w) {
        int nWidth = width + e + w;
        int nHeight = height + n + s;
        int[][] nCells = new int[nWidth][nHeight];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x + w >= 0 && y + n >= 0 && x + w < nWidth && y + n < nHeight)
                    nCells[x + w][y + n] = cells[x][y];
            }
        }
        width = nWidth;
        height = nHeight;
        cells = nCells;
    }

}
