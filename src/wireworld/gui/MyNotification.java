package wireworld.gui;

import javax.swing.*;
import java.awt.*;

public class MyNotification extends JPanel {

    private MyLabel label;
    private int time;

    public MyNotification(String text, int time) {
        label = new MyLabel(text, 12);
        label.setHorizontalAlignment(JLabel.CENTER);
        this.time = time * 1000;
        setLayout(null);
        resize();
        add(label);
    }

    public void setTime(int t) {
        time = t;
    }

    public int getTime() {
        return time;
    }

    public void resize() {
        label.setBounds(0, 0, getWidth(), getHeight());
    }

}
