package wireworld.gui;

import wireworld.logic.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

public class MyCanvas extends JPanel {

    Grid currentGrid;
    BufferedImage currentFrame;
    private int offsetX = 0;
    private int offsetY = 0;
    int lastx = 0, lasty = 0;
    int scale = 4;
    boolean mousePressed = false;
    private MoveGridThread mgt;

    public MyCanvas() {
        super();
        mgt = new MoveGridThread(this);
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
                scale -= mouseWheelEvent.getWheelRotation();
                if (scale < 1) scale = 1;
                update();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                mgt.start();
                /*
                mousePressed = true;
                lastx = mouseEvent.getX();
                lasty = mouseEvent.getY();
                 */
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mouseReleased(mouseEvent);
                mgt.stop();
                /*
                mousePressed = false;
                offsetX -= lastx - mouseEvent.getX();
                offsetY -= lasty - mouseEvent.getY();
                update();
                 */
            }
        });
        setDoubleBuffered(true);
    }

    public void update() {
        update(currentGrid);
    }

    public void update(Grid grid) {
        if (grid.getWidth() <= 0 | grid.getHeight() <= 0) return;
        currentGrid = grid;
        BufferedImage img = new BufferedImage(grid.getWidth(), grid.getHeight(), BufferedImage.TYPE_INT_RGB);
        int p;
        for (int x = 0; x < grid.getWidth(); x++)
            for (int y = 0; y < grid.getHeight(); y++) {
                switch (grid.getCell(x, y)) {
                    case 0:
                        img.setRGB(x, y, Color.WHITE.getRGB());
                        break;
                    case 1:
                        img.setRGB(x, y, Color.ORANGE.getRGB());
                        break;
                    case 2:
                        img.setRGB(x, y, Color.BLUE.getRGB());
                        break;
                    case 3:
                        img.setRGB(x, y, Color.BLACK.getRGB());
                        break;
                }
            }
        currentFrame = img;
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

    public void setScale(int s) {
        scale = s;
    }

    public void setOffsetX(int x ) {
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
}