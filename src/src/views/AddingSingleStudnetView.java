package views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by qqq58 on 2019/12/8  20:57 .
 */
public class AddingSingleStudnetView extends JPanel {
    private JButton addButton;
    private JButton backButton;
    private JButton homeButton;
    private JTextField nameText;
    private JTextField emailText;
    private JTextField BUIDText;
    private JComboBox sectionCombo;
    private JButton importStudent;

    public AddingSingleStudnetView() {
        JLabel welcomeLabel = new JLabel("Add a Student", SwingConstants.CENTER);
        addButton = new JButton("Add Student");
        importStudent = new JButton("Import Students from Excel");
        backButton = new JButton("Back");
        homeButton = new JButton("Home");
        nameText =new JTextField("Student Name");
        emailText =new JTextField("Student Email");
        BUIDText =new JTextField("Student BU ID");
        sectionCombo = new JComboBox();

        Panel infoPanel = new Panel();
        Panel buttonPanel = new Panel();

        infoPanel.setLayout(new GridLayout(5, 1, 30,5));
        infoPanel.add(welcomeLabel);
        infoPanel.add(sectionCombo);
        infoPanel.add(nameText);
        infoPanel.add(emailText);
        infoPanel.add(BUIDText);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        buttonPanel.add(homeButton);
        buttonPanel.add(importStudent);


        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;


        this.add(infoPanel, gbc);
        this.add(buttonPanel, gbc);

    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getHomeButton() {
        return homeButton;
    }

    public JTextField getBUIDText() {
        return BUIDText;
    }

    public JTextField getEmailText() {
        return emailText;
    }

    public JTextField getNameText() {
        return nameText;
    }

    public JComboBox getSectionCombo() {
        return sectionCombo;
    }

    public JButton getImportStudent() {
        return importStudent;
    }
}
