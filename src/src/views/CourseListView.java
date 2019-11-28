package views;

import models.Course;

import javax.swing.*;
import java.awt.*;

public class CourseListView {


    public CourseListView() {
        JFrame frame = new JFrame("Grading System");
        frame.setSize(600, 450);

        JLabel welcomeLabel = new JLabel("Select an Existing Course or Create a New Course", SwingConstants.CENTER);

        JLabel courseLabel = new JLabel("Active Courses", SwingConstants.CENTER);
        JComboBox courseList = new JComboBox();

        JButton createButton = new JButton("Create Course");
        JButton logoutButton = new JButton("Log Out");

        Panel infoPanel = new Panel();
        Panel buttonPanel = new Panel();

        infoPanel.setLayout(new GridLayout(2, 1, 30,5));
        infoPanel.add(courseLabel);
        infoPanel.add(courseList);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(createButton);
        buttonPanel.add(logoutButton);

        frame.add(welcomeLabel);
        frame.add(infoPanel);
        frame.add(buttonPanel);


        frame.setLayout(new GridLayout(3, 1, 0, 50));
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);


    }

}
