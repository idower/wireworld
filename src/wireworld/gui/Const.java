package wireworld.gui;

import java.awt.*;
import java.io.IOException;

public class Const {

    public static Color color1 = new Color(245, 245, 245);
    public static Color color2 = new Color(250, 250, 250);
    public static Color color3 = new Color(160, 160, 160);
    public static Color colorShadow = new Color(220, 220, 220);
    public static Color colorFont = new Color(110, 110, 110);

    public Const() {
        init();
    }

    public void init() {
        try {
            Font droidsans = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getClassLoader().getResourceAsStream("DroidSans.ttf")).deriveFont(2f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(droidsans);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
}
