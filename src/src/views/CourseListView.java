package views;

import models.Course;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CourseListView extends JPanel{

	private JButton createButton;
    private JButton logoutButton;
    private JList courseList;
    DefaultListModel<String> dlm;
	
    public CourseListView() {
        //JFrame frame = new JFrame("Grading System");
        //frame.setSize(600, 450);

        JLabel welcomeLabel = new JLabel("Select an Existing Course or Create a New Course", SwingConstants.CENTER);
        JLabel courseLabel = new JLabel("Active Courses", SwingConstants.CENTER);
        
        dlm = new DefaultListModel<String>();
        courseList = new JList<String>(dlm);
        courseList.setPreferredSize(new Dimension(450, 200));

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
        //this.add(msgPanel, gbc);

        //frame.add(welcomeLabel);
        //frame.add(infoPanel);
        //frame.add(buttonPanel);


        //frame.setLayout(new GridLayout(3, 1, 0, 50));
        //frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        //frame.setVisible(true);


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
		
		
		for(var p : cList ){
		     dlm.addElement(p.toString());
		}    
		
			
	}

	
    
    

}
