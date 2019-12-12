package controllers;


import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.JPanel;

import models.Course;
import models.LoggedData;
import views.CourseListView;
import views.MainPanelView;

public class CourseListController {
	
	private CourseListView courseList;
	private JPanel parentPanel;
	
	public CourseListController()
	{
		courseList = new CourseListView();
		
		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(courseList, BorderLayout.CENTER);
		//LoggedData.setActiveCourseList(LoggedData.getGradingSystem().getActiveCourses());
		initController();
	}
	
	private void initController()
	{
		courseList.getCreateButton().addActionListener(l -> CreateCourse());
		courseList.getLogoutButton().addActionListener(l -> Logout());		
        courseList.setCourseList(LoggedData.getGradingSystem().getActiveCourses());
        
        courseList.getCourseList().addMouseListener(new MouseAdapter() {
    	    public void mouseClicked(MouseEvent evt) {
    	        JList list = (JList)evt.getSource();
    	        int index = list.locationToIndex(evt.getPoint());
                if (index >= 0) {
                    Object o = list.getModel().getElementAt(index);
                    
                    for(var c : LoggedData.getGradingSystem().getActiveCourses())
                    {
                    	if (c.toString().equalsIgnoreCase( o.toString()))
                    	{
                    		LoggedData.setSelectedCourse(c);
                    		
                    	}
                    }
                    
                    System.out.println("Double-clicked on: " + o.toString());
                    ClassHomePageController chP = new ClassHomePageController(LoggedData.getSelectedCourse().toString());
                }
    	    }
    	});
        
	}
	
	
	
	
	private void CreateCourse()
	{
		CreateCourseController ccc = new CreateCourseController();
	}
	
	private void Logout()
	{
		LoginController lc = new LoginController();
	}

}
