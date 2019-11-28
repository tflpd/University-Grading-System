package views;

import javax.swing.*;
import java.awt.*;

public class LoginView {


    public LoginView() {
        JFrame frame = new JFrame("Grading System");


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        frame.setLayout(new GridLayout(4, 1, 0, 20));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
