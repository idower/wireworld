package wireworld.gui;

import wireworld.Manager;
import wireworld.logic.Grid;
import wireworld.threads.MoveGridThread;
import wireworld.threads.PaintThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MyCanvas extends JPanel {

    private Grid currentGrid;
    private BufferedImage currentFrame;
    private int offsetX = 0;
    private int offsetY = 0;
    private int scale = 4;
    private MoveGridThread mgt;
    private PaintThread pt;
    private int mouseMode = 0; // 0 nothing // 1 draw cells // 2 draw patterns
    private int whatToDraw = 0;
    private Grid patternToDraw;

    MyCanvas() {
        super();
        setLayout(null);
        mgt = new MoveGridThread(this);
        pt = new PaintThread(this);
        addMouseWheelListener(mouseWheelEvent -> {
            scale -= mouseWheelEvent.getWheelRotation();
            if (scale < 1) scale = 1;
            update();
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                if (mouseMode == 0) {
                    mgt.start();
                } else if (mouseMode == 1) {
                    pt.start();
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mouseReleased(mouseEvent);
                if (mouseMode == 0) {
                    mgt.stop();
                } else if (mouseMode == 1) {
                    pt.stop();
                }
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (mouseMode != 2) {
                    return;
                }
                if (mouseEvent.getButton() == MouseEvent.BUTTON3) {
                    setMouseMode(0);
                    return;
                }
                mouseMode = 0;
                int x = mouseEvent.getX() - (getWidth() / 2 - currentFrame.getWidth() * scale / 2 + offsetX);
                if (x < 0) return;
                x /= scale;
                if (x >= currentGrid.getWidth()) return;
                int y = mouseEvent.getY() - (getHeight() / 2 - currentFrame.getHeight() * scale / 2 + offsetY);
                if (y < 0) return;
                y /= scale;
                if (y >= currentGrid.getHeight()) return;
                boolean fail = false;
                for (int xx = 0; xx < patternToDraw.getWidth(); xx++) {
                    for (int yy = 0; yy < patternToDraw.getHeight(); yy++) {
                        try {
                            currentGrid.changeCell(x + xx, y + yy, patternToDraw.getCell(xx, yy));
                        } catch (ArrayIndexOutOfBoundsException e) {
                            fail = true;
                        }

                    }
                }
                if (fail)
                    Manager.getInstance().notify("Couldn't draw entire pattern.", 8);
                update();
            }

        });
        setDoubleBuffered(true);
    }

    public void update() {
        update(currentGrid);
    }

    void update(Grid grid) {
        if (grid == null || grid.getWidth() <= 0 || grid.getHeight() <= 0) return;
        currentGrid = grid;
        currentFrame = currentGrid.getImage();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        setDoubleBuffered(true);
        super.paintComponent(g);
        if (currentFrame != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            int x = getWidth() / 2 - currentFrame.getWidth() * scale / 2;
            int y = getHeight() / 2 - currentFrame.getHeight() * scale / 2;
            g2d.drawImage(currentFrame, x + offsetX, y + offsetY, currentFrame.getWidth() * scale, currentFrame.getHeight() * scale, this);
            g2d.dispose();
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public void setOffsetX(int x) {
        offsetX = x;
    }

    public void setOffsetY(int y) {
        offsetY = y;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    void setMouseMode(int i) {
        mouseMode = i;
    }

    void setWhatToDraw(int i) {
        whatToDraw = i;
    }

    int getMouseMode() {
        return mouseMode;
    }

    public int getWhatToDraw() {
        return whatToDraw;
    }

    public BufferedImage getCurrentFrame() {
        return currentFrame;
    }

    public int getScale() {
        return scale;
    }

    public Grid getCurrentGrid() {
        return currentGrid;
    }

    public void setPatternToDraw(Grid patternToDraw) {
        this.patternToDraw = patternToDraw;
    }
}