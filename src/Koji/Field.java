package Koji;

import javax.swing.*;
import java.awt.*;

class Field extends JButton {
    private int x, y;

    Field(int x, int y, Icon notused, GameBoard.ClickListener cl) {
        super();
        setIcon(notused);
        this.x = x;
        this.y = y;
        setPreferredSize(new Dimension(64, 64));
        addActionListener(cl);
    }

    public int getYy() {
        return y;
    }

    public int getXx() {
        return x;
    }

}

