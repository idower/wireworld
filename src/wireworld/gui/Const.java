package wireworld.gui;

import wireworld.logic.Grid;
import wireworld.logic.GridList;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Const {

    public static Color color1 = new Color(245, 245, 245);
    public static Color color2 = new Color(250, 250, 250);
    public static Color color3 = new Color(220, 220, 220);
    public static Color colorShadow = new Color(220, 220, 220);
    public static Color colorFont = new Color(90, 90, 90);
    public static ArrayList<Grid> wireworldPatterns;
    public static ArrayList<Grid> gameOfLifePatterns;

    public Const() {
        init();
    }

    public void init() {
        try {
            Font droidsans = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("DroidSans.ttf"))).deriveFont(2f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(droidsans);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        wireworldPatterns = new ArrayList<>();
        gameOfLifePatterns = new ArrayList<>();
        Grid g = new Grid("/wireGateAND", "AND gate");
        if (g.isGood())
            wireworldPatterns.add(g);
        g = new Grid("/wireGateOR", "OR gate");
        if (g.isGood())
            wireworldPatterns.add(g);
        g = new Grid("/wireGateXOR", "XOR gate");
        if (g.isGood())
            wireworldPatterns.add(g);
        g = new Grid("/gameOfLifeGlider", "Glider");
        if (g.isGood())
            gameOfLifePatterns.add(g);
    }
}
