package Koji;

import java.awt.*;
import java.util.Random;

public class Turn {
    private boolean turn;

    Turn(Player p1, Player p2) {
        turn = new Random().nextBoolean();
        setActive(p1, p2);
        changeTurn();
    }

    void setActive(Player p1, Player p2) {
        if (!turn) {
            p1.setDisactive();
            p2.setBackground(new Color(253, 253, 253));
        } else {
            p2.setDisactive();
            p1.setBackground(new Color(253, 253, 253));
        }
    }

    void changeTurn() {
        turn = !turn;
    }

    boolean isTurn() {
        return turn;
    }
}
