package views;

import javax.swing.*;
import java.awt.*;

public class CreateCourseView {

    public CreateCourseView() {
        JFrame frame = new JFrame("Grading System");
        frame.setSize(600, 550);

        JLabel welcomeLabel = new JLabel("Course Creation", SwingConstants.CENTER);

        JLabel templateLabel = new JLabel("Course Templates", SwingConstants.CENTER);
        JComboBox templateList = new JComboBox();

        JButton createButton = new JButton("Create Course");
        JButton importButton = new JButton("Import Student");

        JTextField nameText =new JTextField("Course Name");
        JTextField semesterText =new JTextField("Semester");

        Panel infoPanel = new Panel();
        Panel buttonPanel = new Panel();

        infoPanel.setLayout(new GridLayout(4, 1, 30,5));
        infoPanel.add(templateLabel);
        infoPanel.add(templateList);
        infoPanel.add(nameText);
        infoPanel.add(semesterText);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(createButton);
        buttonPanel.add(importButton);

        frame.add(welcomeLabel);
        frame.add(infoPanel);
        frame.add(buttonPanel);


        frame.setLayout(new GridLayout(3, 1, 0, 50));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


    }
}
