package wireworld.gui;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends  JLabel{

    public MyLabel(String text) {
        super(text);
        setForeground(Const.colorFont);
        setFont(new Font("droidsans", Font.BOLD, 14));
    }

    public MyLabel(String text, int fontSize) {
        super(text);
        setForeground(Const.colorFont);
        setFont(new Font("droidsans", Font.BOLD, fontSize));
    }

}
