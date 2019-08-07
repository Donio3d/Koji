package Koji;

import javax.swing.*;
import java.awt.*;

class AlertBox {
    public static void display(String title, String message, String buttonText) {
        JFrame frame = new JFrame();
        JPanel panel1 = new JPanel();
        frame.getContentPane().add(panel1);

        JLabel jl1 = new JLabel(message);
        JButton btn = new JButton(buttonText);

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel1.add(jl1);
        panel1.add(btn);

        frame.add(panel1); // <---------------------------------------------- Tego brakowało. Wyżej w konstruktorze działa bo bezpośrendnio odwołuje się do nadklasy stąd nie było trzeba dodawać

        frame.setSize(250, 100);
        frame.setTitle(title);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        btn.addActionListener(e -> frame.dispose());
    }
}
