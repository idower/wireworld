package wireworld.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyButton extends JButton {

    public MyButton(String text) {
        super(text);
        setFont(new Font("droidsans", Font.BOLD, 12));
        //setBorderPainted(false);
        setFocusPainted(false);
        setBackground(Const.color2);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Const.colorShadow));
        setForeground(Const.colorFont);
        //setBorder(BorderFactory.createLineBorder(Const.color2));
        //setContentAreaFilled(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
            }
        });
    }

}

