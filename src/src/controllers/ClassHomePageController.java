package controllers;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import views.ClassHomePage;
import views.CreateCourseView;
import views.MainPanelView;

public class ClassHomePageController {
	
	private ClassHomePage classHomePage;
	private JPanel parentPanel;
	
	public ClassHomePageController(String courseName)
	{
		classHomePage = new ClassHomePage(courseName);
		
		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(classHomePage, BorderLayout.CENTER);
		
		initController();
	}
	
	private void initController()
	{
		classHomePage.getStudentListButton().addActionListener(l -> openStudentList());
	}
	
	private void openStudentList()
	{
		CourseStudentController csc = new CourseStudentController();
	}

}
