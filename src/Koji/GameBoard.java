package Koji;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


class GameBoard {
    private JFrame window = new JFrame("Koji Game");
    //Action Listeners:
    private ClickListener cl = new ClickListener();

    //Icons:
    private Icon used;
    private Icon notused;
    //    private Icon wall;

    //Turn Controll
    private Turn turn;

    //Players:
    private Player p1; //false
    private Player p2; //true

    //MAP:
    private Field[][] map;

    @SuppressWarnings("SuspiciousNameCombination")
    GameBoard(String p1, String p2, int height, int width) { // Tu pracuje donio
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GameBoard.this.p1 = new Player("X  - ", p1, "");
        GameBoard.this.p2 = new Player("", p2, "  - O");
        turn = new Turn(this.p1, this.p2);
        window.setLocationByPlatform(true);
        window.add(initializationGui(height, width));
        GameBoard.this.p1.start(height, width, map, this.p1);
        GameBoard.this.p2.start(height, width, map, this.p1);
        window.pack();
        window.setResizable(false);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
        System.out.println(window.isDisplayable());
    }

    //
    private JPanel initializationGui(int height, int width) {
        JPanel Window = new JPanel(new FlowLayout());
        Window.setBorder(new EmptyBorder(5, 5, 5, 5));
        Window.setPreferredSize(new Dimension(64 * width + 50, 64 * height + 60));
        JPanel bar = new JPanel(new GridLayout(1, 2));
        bar.add(p1);
        bar.add(p2);
        bar.setPreferredSize(new Dimension(64 * width, 30));
        Window.add(bar);
        bar.setVisible(true);
        //Adding MapArea
        Window.add(mapConfig(height, width));
        Window.setBackground(new Color(195, 195, 195));
        return Window;

    }

    //Function which return configured MapArea
    private Container mapConfig(int height, int width) {
        Container cont = new Container();
        cont.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        cont.setPreferredSize(new Dimension(64 * width, 64 * height));
        initializationIcons();
        map = new Field[height][width];
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                map[x][y] = new Field(x, y, notused, cl);
                map[x][y].setMargin(new Insets(0, 0, 0, 0));
                cont.add(map[x][y]);
            }
        }
        cont.setVisible(true);
        return cont;
    }

    //Getting images to variables:
    private void initializationIcons() {
        BufferedImage img;
        try {
            img = ImageIO.read(Objects.requireNonNull(MapOperators.getResourceAsFile("used.jpg")));
            used = new ImageIcon(img);
        } catch (IOException e) {
            System.out.println("Brak pliku used.jpg!");
        }
        //
        try {
            img = ImageIO.read(Objects.requireNonNull(MapOperators.getResourceAsFile("notused.jpg")));
            notused = new ImageIcon(img);
        } catch (IOException e) {
            System.out.println("Brak pliku notused.jpg!");
        }
        //WALL - Random Fields witch were blocked at creating!
        /*
        try {
            try {
                img = ImageIO.read(new File(getClass().getResource("/wall.jpg").toURI()));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            wall = new ImageIcon(img);
        } catch (IOException e) {
            System.out.println("Brak pliku wall.jpg!");
        }*/
    }
    //MapField class:
        /*
        x i y potrzebne są w pozyskaniu pozycji klikniętego obiektu przy wykonywaniu ruchu!
        */

    class ClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Field x = (Field) e.getSource();
            if (!turn.isTurn()) {
                if (MapOperators.checkMove(x, p1)) {
                    p1.moveTo(x, map, used, p1, turn, p2);
                    p2.isLose(map, window, p1, p2);

                }
            } else {
                if (MapOperators.checkMove(x, p2)) {
                    p2.moveTo(x, map, used, p1, turn, p2);
                    p1.isLose(map, window, p1, p2);
                }
            }
        }
    }
}