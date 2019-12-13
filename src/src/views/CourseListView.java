package views;

import models.Course;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CourseListView extends JPanel{

	private JButton createButton;
    private JButton logoutButton;
    private JList courseList;
    DefaultListModel<Course> dlm;
	
    public CourseListView() {
        //JFrame frame = new JFrame("Grading System");
        //frame.setSize(600, 450);

        JLabel welcomeLabel = new JLabel("Select an Existing Course or Create a New Course", SwingConstants.CENTER);
        JLabel courseLabel = new JLabel("Active Courses", SwingConstants.CENTER);
        
        dlm = new DefaultListModel<Course>();
        courseList = new JList<Course>(dlm);
        courseList.setPreferredSize(new Dimension(1000, 300));

        createButton = new JButton("Create Course");
        logoutButton = new JButton("Log Out");

        Panel infoPanel = new Panel();
        Panel courseListPanel = new Panel();
        Panel buttonPanel = new Panel();

        //infoPanel.setLayout();
        infoPanel.add(courseLabel);
        courseListPanel.add(courseList);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(createButton);
        buttonPanel.add(logoutButton);
        
        this.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        this.add(welcomeLabel, gbc);
        this.add(infoPanel, gbc);
        this.add(courseListPanel, gbc);
        this.add(buttonPanel, gbc);
    }

	public JButton getCreateButton() {
		return createButton;
	}

	public JButton getLogoutButton() {
		return logoutButton;
	}

	public JList getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<Course> cList) {
		
		if (cList != null) {
            for (var p : cList) {
                dlm.addElement(p);
            }
        }
			
	}

	
    
    

}
