package Koji;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

class Player extends JTextField {
    private String name;
    private int x, y;
    private Icon xIcon, oIcon;

    Player(String pefrix, String name, String sufix) {
        super(pefrix + name + sufix);
        this.name = name;
        setHorizontalAlignment(JTextField.CENTER);
        setBorder(null);
        setEditable(false);
        initializationIcons();
    }

    void isLose(Field[][] map, JFrame window, Player p1, Player p2) {
        boolean lose = true;
        for (Field[] aMap : map)
            for (int x = 0; x < aMap.length && lose; x++) {
                if (MapOperators.checkMove(aMap[x], this)) {
                    if (aMap[x].isEnabled()) {
                        lose = false;
                        break;
                    }
                }

            }
        if (lose) this.lose(window, p1, p2);
    }

    private void lose(JFrame window, Player p1, Player p2) {

        JFrame frame = new JFrame();
        JPanel panel1 = new JPanel();
        String q;
        if (this == p1) {
            q = p2.name;
        } else {
            q = p1.name;
        }


        JLabel jl1 = new JLabel("Player " + q + " won!");
        JButton btn = new JButton("Exit");
        JButton btn1 = new JButton("Restart");

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel1.add(jl1);
        panel1.add(btn);
        panel1.add(btn1);

        frame.add(panel1); // <---------------------------------------------- Tego brakowało. Wyżej w konstruktorze działa bo bezpośrendnio odwołuje się do nadklasy stąd nie było trzeba dodawać

        frame.setSize(250, 150);
        frame.setTitle("Game ended!");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setResizable(false);

        btn.addActionListener(e -> {
                    frame.dispose();
                    window.dispose();
                }
        );
        btn1.addActionListener(e -> {
                    frame.dispose();
                    window.dispose();
                    new Config();
                }
        );

    }

    private void initializationIcons() {
        BufferedImage img;
        try {
            img = ImageIO.read(Objects.requireNonNull(MapOperators.getResourceAsFile("x.jpg")));
            xIcon = new ImageIcon(img);
        } catch (IOException e) {
            System.out.println("Brak pliku x.jpg!");
        }
        try {
            img = ImageIO.read(Objects.requireNonNull(MapOperators.getResourceAsFile("o.jpg")));
            oIcon = new ImageIcon(img);
        } catch (IOException e) {
            System.out.println("Brak pliku o.jpg!");
        }
    }

    void setDisactive() {
        setBackground(new Color(195, 195, 195));
    }

    void moveTo(Field x, Field[][] map, Icon used, Player p1, Turn turn, Player p2) {
        x.setEnabled(false);
        System.out.println(name + ":\n\tX:" + this.x + " -> " + x.getXx() + "\n\tY:" + y + " -> " + x.getYy() + "\n");
        map[this.x][this.y].setDisabledIcon(used);
        this.x = x.getXx();
        this.y = x.getYy();
        turn.setActive(p1, p2);
        if (this == p1) {
            x.setDisabledIcon(xIcon);
            turn.changeTurn();
        } else {
            x.setDisabledIcon(oIcon);
            turn.changeTurn();
        }

    }

    void start(int width, int height, Field[][] map, Player p1) {
        if (this == p1) {
            map[0][0].setEnabled(false);
            map[0][0].setDisabledIcon(xIcon);
            x = 0;
            y = 0;
        } else {
            map[width - 1][height - 1].setEnabled(false);
            map[width - 1][height - 1].setDisabledIcon(oIcon);
            x = width - 1;
            y = height - 1;
        }
    }

    int getYy() {
        return y;
    }

    int getXx() {
        return x;
    }
}

