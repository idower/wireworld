package wireworld.gui;

import wireworld.logic.Grid;
import wireworld.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

@SuppressWarnings("FieldCanBeLocal")
public class GUI extends JFrame {

    private MyCanvas canvas;
    private JPanel panelRight;

    private JPanel panelMain;
    private MyButton btnPanelMainSwitchModes;
    private JPanel panelMain1;
    private MyLabel labelPanelMain1;
    private MyButton btnPanelMainNew;
    private MyButton btnPanelMainLoad;
    private MyButton btnPanelMainSave;
    private JPanel panelMain2;
    private MyLabel labelPanelMain2;
    private MyButton btnPanelMainPatterns;
    private MyButton btnPanelMainResize;
    private MyButton btnPanelMainClear;
    private JPanel panelMain21;
    private MyLabel labelPanelMain211;
    private MyLabel labelPanelMain212;
    private MyLabel labelPanelMain213;
    private MyLabel labelPanelMain214;
    private JPanel panelMain3;
    private MyLabel labelPanelMain3;
    private MyButton btnPanelMainPrevGen;
    private MyButton btnPanelMainPlayGen;
    private MyButton btnPanelMainNextGen;

    private JPanel panelLoad;
    private MyLabel labelPanelLoadPath;
    private MyTextField txtPanelLoadPath;
    private MyButton btnPanelLoadConfirm;
    private MyButton btnPanelLoadCancel;

    private JPanel panelNew;
    private MyLabel labelPanelNewWidth;
    private MyLabel labelPanelNewHeight;
    private MyTextField txtPanelNewWidth;
    private MyTextField txtPanelNewHeight;
    private MyButton btnPanelNewConfirm;
    private MyButton btnPanelNewCancel;

    private JPanel panelSave;
    private MyLabel labelPanelSavePath;
    private MyTextField txtPanelSavePath;
    private MyButton btnPanelSaveConfirm;
    private MyButton btnPanelSaveCancel;

    public GUI(Manager m) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(720, 480));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Wireworld+ by Public Static Junior India Warsaw Developers");
        setLayout(null);

        // MyCanvas(JPanel) - logic is drawn there
        canvas = new MyCanvas();
        canvas.setPreferredSize(new Dimension(300, 300));
        canvas.setBackground(Const.color2);
        add(canvas);

        // panelRight
        panelRight = new JPanel();
        panelRight.setBackground(Const.color1);
        panelRight.setLayout(null);
        add(panelRight, BorderLayout.CENTER);

        // panelMain
        panelMain = new JPanel();
        panelMain.setBackground(Const.color1);
        panelMain.setLayout(null);
        panelMain.setBounds(0, 0, 300, 1000);
        panelRight.add(panelMain);

        // siwthc modes
        btnPanelMainSwitchModes = new MyButton("Switch to Game of Life");
        btnPanelMainSwitchModes.setBounds(100, 10, 190, 25);
        panelMain.add(btnPanelMainSwitchModes);

        // new / load / save
        panelMain1 = new JPanel();
        panelMain1.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelMain1.setBounds(0, 60, 300, 60);
        panelMain1.setBackground(Const.color1);
        panelMain.add(panelMain1);
        labelPanelMain1 = new MyLabel("Board");
        labelPanelMain1.setPreferredSize(new Dimension(280, 25));
        labelPanelMain1.setHorizontalAlignment(JTextField.CENTER);
        panelMain1.add(labelPanelMain1);
        btnPanelMainNew = new MyButton("New");
        btnPanelMainNew.setPreferredSize(new Dimension(85, 25));
        panelMain1.add(btnPanelMainNew);
        btnPanelMainLoad = new MyButton("Load");
        btnPanelMainLoad.setPreferredSize(new Dimension(85, 25));
        panelMain1.add(btnPanelMainLoad);
        btnPanelMainSave = new MyButton("Save");
        btnPanelMainSave.setPreferredSize(new Dimension(85, 25));
        panelMain1.add(btnPanelMainSave);


        // patterns / resize / clear
        panelMain2 = new JPanel();
        panelMain2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelMain2.setBounds(0, 145, 300, 100);
        panelMain2.setBackground(Const.color1);
        panelMain.add(panelMain2);
        labelPanelMain2 = new MyLabel("Edit");
        labelPanelMain2.setPreferredSize(new Dimension(280, 25));
        labelPanelMain2.setHorizontalAlignment(JTextField.CENTER);
        panelMain2.add(labelPanelMain2);
        btnPanelMainPatterns = new MyButton("Patterns");
        btnPanelMainPatterns.setPreferredSize(new Dimension(85, 25));
        panelMain2.add(btnPanelMainPatterns);
        btnPanelMainResize = new MyButton("Resize");
        btnPanelMainResize.setPreferredSize(new Dimension(85, 25));
        panelMain2.add(btnPanelMainResize);
        btnPanelMainClear = new MyButton("Clear");
        btnPanelMainClear.setPreferredSize(new Dimension(85, 25));
        panelMain2.add(btnPanelMainClear);
        panelMain21 = new JPanel();
        panelMain21.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 8));
        panelMain21.setBackground(Const.color1);
        panelMain2.add(panelMain21);
        labelPanelMain211 = new MyLabel("Empty", 10);
        labelPanelMain211.setPreferredSize(new Dimension(50, 40));
        labelPanelMain211.setIcon(createImage(Color.WHITE, new Dimension(24, 24)));
        labelPanelMain211.setHorizontalTextPosition(JLabel.CENTER);
        labelPanelMain211.setVerticalTextPosition(JLabel.BOTTOM);
        panelMain21.add(labelPanelMain211);
        labelPanelMain212 = new MyLabel("Head", 10);
        labelPanelMain212.setPreferredSize(new Dimension(50, 40));
        labelPanelMain212.setIcon(createImage(Color.ORANGE, new Dimension(24, 24)));
        labelPanelMain212.setHorizontalTextPosition(JLabel.CENTER);
        labelPanelMain212.setVerticalTextPosition(JLabel.BOTTOM);
        panelMain21.add(labelPanelMain212);
        labelPanelMain213 = new MyLabel("Tail", 10);
        labelPanelMain213.setPreferredSize(new Dimension(50, 40));
        labelPanelMain213.setIcon(createImage(Color.BLUE, new Dimension(24, 24)));
        labelPanelMain213.setHorizontalTextPosition(JLabel.CENTER);
        labelPanelMain213.setVerticalTextPosition(JLabel.BOTTOM);
        panelMain21.add(labelPanelMain213);
        labelPanelMain214 = new MyLabel("Conductor", 10);
        labelPanelMain214.setPreferredSize(new Dimension(50, 40));
        labelPanelMain214.setIcon(createImage(Color.BLACK, new Dimension(24, 24)));
        labelPanelMain214.setHorizontalTextPosition(JLabel.CENTER);
        labelPanelMain214.setVerticalTextPosition(JLabel.BOTTOM);
        panelMain21.add(labelPanelMain214);


        // prev gen / play / next gen
        panelMain3 = new JPanel();
        panelMain3.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelMain3.setBounds(0, 275, 300, 60);
        panelMain3.setBackground(Const.color1);
        panelMain.add(panelMain3);
        labelPanelMain3 = new MyLabel("Generations");
        labelPanelMain3.setPreferredSize(new Dimension(280, 25));
        labelPanelMain3.setHorizontalAlignment(JTextField.CENTER);
        panelMain3.add(labelPanelMain3);
        btnPanelMainPrevGen = new MyButton("Prev");
        btnPanelMainPrevGen.setPreferredSize(new Dimension(65, 25));
        btnPanelMainPrevGen.addActionListener(actionEvent -> m.prevGen());
        panelMain3.add(btnPanelMainPrevGen);
        btnPanelMainPlayGen = new MyButton("Play/Pause");
        btnPanelMainPlayGen.setPreferredSize(new Dimension(100, 25));
        btnPanelMainPlayGen.addActionListener(actionEvent -> m.togglePlay());
        panelMain3.add(btnPanelMainPlayGen);
        btnPanelMainNextGen = new MyButton("Next");
        btnPanelMainNextGen.setPreferredSize(new Dimension(65, 25));
        btnPanelMainNextGen.addActionListener(actionEvent -> m.nextGen());
        panelMain3.add(btnPanelMainNextGen);


        // panelLoad
        panelLoad = new JPanel();
        panelLoad.setVisible(false);
        panelLoad.setBackground(Const.color1);
        panelLoad.setLayout(null);
        panelLoad.setBounds(0, 0, 300, 1000);
        panelRight.add(panelLoad);
        labelPanelLoadPath = new MyLabel("Path", 12);
        labelPanelLoadPath.setBounds(10, 60, 35, 25);
        panelLoad.add(labelPanelLoadPath);
        txtPanelLoadPath = new MyTextField();
        txtPanelLoadPath.setBounds(55, 60, 240, 25);
        panelLoad.add(txtPanelLoadPath);
        btnPanelLoadConfirm = new MyButton("Load");
        btnPanelLoadConfirm.setBounds(45, 95, 100, 25);
        btnPanelLoadConfirm.addActionListener(actionEvent -> m.loadGrid(txtPanelLoadPath.getText()));
        panelLoad.add(btnPanelLoadConfirm);
        btnPanelLoadCancel = new MyButton("Cancel");
        btnPanelLoadCancel.setBounds(155, 95, 100, 25);
        panelLoad.add(btnPanelLoadCancel);
        btnPanelLoadCancel.addActionListener(actionEvent -> {
            panelLoad.setVisible(false);
            panelMain.setVisible(true);
        });
        btnPanelMainLoad.addActionListener(actionEvent -> {
            panelMain.setVisible(false);
            panelLoad.setVisible(true);
        });


        // panelNew
        panelNew = new JPanel();
        panelNew.setVisible(false);
        panelNew.setBackground(Const.color1);
        panelNew.setLayout(null);
        panelNew.setBounds(0, 0, 300, 1000);
        panelRight.add(panelNew);
        labelPanelNewWidth = new MyLabel("Width", 12);
        labelPanelNewWidth.setBounds(10, 60, 40, 25);
        panelNew.add(labelPanelNewWidth);
        txtPanelNewWidth = new MyTextField();
        txtPanelNewWidth.setBounds(60, 60, 50, 25);
        panelNew.add(txtPanelNewWidth);
        labelPanelNewHeight = new MyLabel("Height", 12);
        labelPanelNewHeight.setBounds(140, 60, 40, 25);
        panelNew.add(labelPanelNewHeight);
        txtPanelNewHeight = new MyTextField();
        txtPanelNewHeight.setBounds(190, 60, 50, 25);
        panelNew.add(txtPanelNewHeight);
        btnPanelNewConfirm = new MyButton("Confirm");
        btnPanelNewConfirm.setBounds(45, 95, 100, 25);
        panelNew.add(btnPanelNewConfirm);
        btnPanelNewCancel = new MyButton("Cancel");
        btnPanelNewCancel.setBounds(155, 95, 100, 25);
        panelNew.add(btnPanelNewCancel);
        btnPanelNewCancel.addActionListener(actionEvent -> {
            panelNew.setVisible(false);
            panelMain.setVisible(true);
        });
        btnPanelMainNew.addActionListener(actionEvent -> {
            panelMain.setVisible(false);
            panelNew.setVisible(true);
        });

        // panelSave
        panelSave = new JPanel();
        panelSave.setVisible(false);
        panelSave.setBackground(Const.color1);
        panelSave.setLayout(null);
        panelSave.setBounds(0, 0, 300, 1000);
        panelRight.add(panelSave);
        labelPanelSavePath = new MyLabel("Path", 12);
        labelPanelSavePath.setBounds(10, 60, 35, 25);
        panelSave.add(labelPanelSavePath);
        txtPanelSavePath = new MyTextField();
        txtPanelSavePath.setBounds(55, 60, 240, 25);
        panelSave.add(txtPanelSavePath);
        btnPanelSaveConfirm = new MyButton("Save");
        btnPanelSaveConfirm.setBounds(45, 95, 100, 25);
        btnPanelSaveConfirm.addActionListener(actionEvent -> m.saveGrid(txtPanelSavePath.getText()));
        panelSave.add(btnPanelSaveConfirm);
        btnPanelSaveCancel = new MyButton("Cancel");
        btnPanelSaveCancel.setBounds(155, 95, 100, 25);
        panelSave.add(btnPanelSaveCancel);
        btnPanelSaveCancel.addActionListener(actionEvent -> {
            panelSave.setVisible(false);
            panelMain.setVisible(true);
        });
        btnPanelMainSave.addActionListener(actionEvent -> {
            panelMain.setVisible(false);
            panelSave.setVisible(true);
        });

        // resize components on window resize
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                canvas.setBounds(0, 0, getWidth() - 300, getHeight());
                panelRight.setBounds(getWidth() - 300, 0, 300, getHeight());
            }
        });


        repaint();
        setVisible(true);
    }

    public static Icon createImage(Color c, Dimension size) {
        BufferedImage img = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.createGraphics();
        g.setColor(c);
        g.fillRect(0, 0, size.width, size.height);
        return new ImageIcon(img);
    }

    public void updateCanvas(Grid g) {
        canvas.update(g);
    }
}
