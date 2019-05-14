package wireworld.gui;

import wireworld.Manager;
import wireworld.logic.Grid;
import wireworld.logic.GridList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PatternsPanel extends JPanel {

    private JPanel panelTop;
    private JPanel panelBottom;
    private int panelTopOffsetY;
    private int panelTopOffsetYMax;
    private MyButton buttonCancel;
    private ArrayList<PatternsPanelElement> ppel;

    public PatternsPanel(JPanel panelMain) {
        setBackground(Const.color1);
        setLayout(null);
        panelTop = new JPanel();
        panelTop.setBackground(Const.color1);
        panelTop.setLayout(null);
        panelTop.setBounds(0, 0, 300, 1000);
        panelTop.addMouseWheelListener(mouseWheelEvent -> {
            panelTopOffsetY += 10 * mouseWheelEvent.getWheelRotation();
            if (panelTopOffsetY > panelTopOffsetYMax) panelTopOffsetY = panelTopOffsetYMax;
            if (panelTopOffsetY < 0) panelTopOffsetY = 0;
            scroll();
        });
        add(panelTop);
        panelBottom = new JPanel();
        panelBottom.setBackground(Const.color1);
        panelBottom.setLayout(null);
        panelBottom.setBounds(0, 0, 0, 0);
        add(panelBottom);
        buttonCancel = new MyButton("Cancel");
        buttonCancel.setBounds(195, 5, 100, 25);
        buttonCancel.addActionListener(actionEvent -> {
            setVisible(false);
            panelMain.setVisible(true);
        });
        panelBottom.add(buttonCancel);
    }

    private void scroll() {
        int x, y;
        for (int i = 0; i < ppel.size(); i++) {
            x = i % 2 * 150 + 5;
            y = i / 2 * 150 + 5 - panelTopOffsetY;
            ppel.get(i).setBounds(x, y, 140, 140);
        }
    }

    public void resizee(int w, int h) {
        setBounds(0, 0, 300, h);
        panelTop.setBounds(0, 0, 300, h - 35);
        panelBottom.setBounds(0, h - 35, 300, 35);
        panelTopOffsetYMax = (Manager.getInstance().getPatterns().size() + 1) / 2 * 145 + 25 - h + 35;
        scroll();
    }

    public void updatePatterns(GUI gui, ArrayList<Grid> patterns) {
        ppel = new ArrayList<>();
        panelTop.removeAll();
        for (Grid grid : patterns) {
            ppel.add(new PatternsPanelElement(gui, grid));
            panelTop.add(ppel.get(ppel.size() - 1));
            gui.resizeGUI();
        }
    }
}
