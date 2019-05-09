package wireworld.logic;

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
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                if (y == 0 || y == 2) cells[x][y] = 0;
                else if (x == 1) cells[x][y] = 2;
                else if (x == 2) cells[x][y] = 1;
                else cells[x][y] = 3;
            }


    }

    public Grid(String path) {
        load(path);
    }

    public void load(String path) {
        try {
            Scanner in = new Scanner(new File(path));
            width = in.nextInt();
            height = in.nextInt();
            cells = new int[width][height];
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++)
                    cells[x][y] = in.nextInt();
            isGood = true;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file " + path);
            //e.printStackTrace();
        }
    }

    public void save(String path) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(path));
            writer.write(width + " " + height);
            for(int y = 0; y < height; y++) {
                writer.append('\n');
                for( int x = 0; x < width; x++) {
                    writer.append(String.valueOf(cells[x][y])).append(" ");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
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

}
