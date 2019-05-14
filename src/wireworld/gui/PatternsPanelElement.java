package wireworld.gui;

import wireworld.Manager;
import wireworld.logic.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PatternsPanelElement extends JPanel {

    private BufferedImage img;
    private MyLabel label;
    private Grid grid;

    public PatternsPanelElement(GUI g, Grid grid) {
        setBackground(Const.color2);
        setPreferredSize(new Dimension(140, 140));
        label = new MyLabel(grid.getName());
        setLayout(null);
        label.setBounds(0, 120, 140, 20);
        add(label);
        this.grid = grid;
        this.img = grid.getImage();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                g.getCanvas().setMouseMode(2);
                g.getCanvas().setPatternToDraw(grid);
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double sw = 140 / img.getWidth();
        double sh = 120 / img.getHeight();
        double s = Math.min(sw, sh);
        int x = (int) (70 - img.getWidth() * s / 2);
        int y = (int) (60 - img.getHeight() * s / 2);
        int w = (int) (img.getWidth() * s);
        int h = (int) (img.getHeight() * s);
        g.drawImage(img, x, y, w, h, this);
    }

}
