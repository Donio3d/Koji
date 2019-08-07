package Koji;

import javax.swing.*;
import java.awt.*;


class Config extends JFrame {

    private JTextField p1 = new JTextField();
    private JTextField p2 = new JTextField();
    private JTextField height = new JTextField();
    private JTextField width = new JTextField();

    private int rows, cols;

    Config() {

        JPanel panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(new GridLayout(9, 1));

/*
        Define JPanel
 */
        JLabel jl = new JLabel("Gracz 1 ");
        JLabel jl2 = new JLabel("Gracz 2 ");

        JLabel jl3 = new JLabel("Map height 5-10");
        JLabel jl4 = new JLabel("Map width 5-10");

        JButton start = new JButton("Start!");
/*
        Define objects
 */
        p1.setPreferredSize(new Dimension(200, 24));
        p2.setPreferredSize(new Dimension(200, 24));

        height.setPreferredSize(new Dimension(200, 24));
        width.setPreferredSize(new Dimension(200, 24));
/*
        Set objects size
 */
        panel.add(jl);
        panel.add(p1);

        panel.add(jl2);
        panel.add(p2);

        panel.add(jl3);
        panel.add(height);

        panel.add(jl4);
        panel.add(width);

        panel.add(start);
        start.setMargin(new Insets(5, 10, 5, 10));
        /*
        Add objects to panel
 */
        setPreferredSize(new Dimension(250, 270));
        setTitle("Config Window!");
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setResizable(false);
/*
        Configure JFrame

 */
        start.addActionListener(e -> { // <--------------------------------------------- Ultra Important! More on end <-----------------------------------------------------------------------
            if (check() == 2) {
                new GameBoard(p1.getText(), p2.getText(), rows, cols);
                Config.super.dispose(); // static declare becase in constructor
            } else if (check() == 1) {
                AlertBox.display("Empty Field", "Please enter values to all fields!", "Okay");
            } else if (check() == 0) {
                AlertBox.display("Wrong Value", "Please enter values from range!", "Okay");
            } else if (check() == 4) {
                AlertBox.display("Wrong Type Values", "Please enter correct value for height field", "Okay");
            } else
                AlertBox.display("Wrong Type Values", "Please enter correct value for width field", "Okay");
        });
/*
        Start ActionListener on object
 */
    }

    private byte check() {
        if (p1.getText().length() == 0)
            return 1;
        if (p2.getText().length() == 0)
            return 1;
        if (width.getText().length() == 0)
            return 1;
        if (height.getText().length() == 0)
            return 1;
        //Width field isInteger TEST:
        try {
            rows = Integer.parseInt(width.getText());
        } catch (NumberFormatException e) {
            return 3;
        }
        if (rows < 5 || rows > 10) {
            return 0;
        }
        //Height field isInteger TEST:
        try {
            cols = Integer.parseInt(height.getText());
        } catch (NumberFormatException e) {
            return 4;
        }
        if (cols < 5 || cols > 10)
            return 0;
        //If All OK return 3
        return 2;
    }
}




/*
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
}

----------------------------------------------------------------

    btn.addActionListener(e -> frame.dispose());

 */


