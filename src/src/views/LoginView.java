package views;

import javax.swing.*;
import java.awt.*;

public class LoginView {


    public LoginView() {
        JFrame frame = new JFrame("Grading System");


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        JLabel welcomeLabel = new JLabel("Welcome to BU Grading System", SwingConstants.CENTER);
        JLabel userIDLabel = new JLabel("User ID: ", SwingConstants.CENTER);
        JLabel pwdLabel = new JLabel("Password: ", SwingConstants.CENTER);
        JTextField idText =new JTextField("User ID");
        JPasswordField pwdText =new JPasswordField();
        pwdText.setEchoChar('*');
        JButton signInButton = new JButton("Sign In");
        JButton cancelButton = new JButton("cancel");
        Panel infoPanel = new Panel();
        Panel buttonPanel = new Panel();


        infoPanel.setLayout(new GridLayout(2, 2, 30,40));
        infoPanel.add(userIDLabel);
        infoPanel.add(idText);
        infoPanel.add(pwdLabel);
        infoPanel.add(pwdText);

        buttonPanel.add(signInButton);
        buttonPanel.add(cancelButton);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        frame.add(welcomeLabel);
        frame.add(infoPanel);
        frame.add(buttonPanel);


        frame.setLayout(new GridLayout(3, 1, 20, 50));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
