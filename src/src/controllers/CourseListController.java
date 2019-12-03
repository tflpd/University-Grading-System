package controllers;


import java.awt.BorderLayout;

import javax.swing.JPanel;

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
		
		initController();
	}
	
	private void initController()
	{
		courseList.getCreateButton().addActionListener(l -> CreateCourse());
		courseList.getLogoutButton().addActionListener(l -> Logout());		
        courseList.setCourseList(LoggedData.getActiveCourseList());
        
	}
	
	private void CreateCourse()
	{
		
	}
	
	private void Logout()
	{
		LoginController lc = new LoginController();
	}

}
