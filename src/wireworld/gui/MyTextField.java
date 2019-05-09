package wireworld.gui;

import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField {

    public MyTextField() {
        super();
        setFont(new Font("droidsans", Font.BOLD, 12));
        //setBorderPainted(false);
        setBackground(Const.color2);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Const.colorShadow));
        setForeground(Const.colorFont);
        //setBorder(BorderFactory.createLineBorder(Const.color2));
        //setContentAreaFilled(false);
    }

}
