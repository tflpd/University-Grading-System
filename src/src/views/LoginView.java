import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GUIWelcomePage {

    public GUIWelcomePage(){
        JFrame frame = new JFrame("My Fancy Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);
        JLabel welcomeLabel = new JLabel("Welcome to the Fancy Bank", SwingConstants.CENTER);
        JButton sign_inButton = new JButton("Sign In");
        JButton new_customerButton = new JButton("New Customer");
        JButton manager_modeButton = new JButton("Manager Mode");

        sign_inButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUISignInPage(frame, bankRunner);
            }
        });
        new_customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIRegisterPage(frame, bankRunner);
            }
        });
        manager_modeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new GUIManagerMainPage(frame, bankRunner);
            }
        });
        frame.add(welcomeLabel);
        frame.add(sign_inButton);
        frame.add(new_customerButton);
        frame.add(manager_modeButton);
        frame.setLayout(new GridLayout(4, 1,0,20));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
