package controllers;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import views.CourseListView;
import views.CreateCourseView;
import views.MainPanelView;

public class CreateCourseController {
	
	private CreateCourseView createCourse;
	private JPanel parentPanel;
	
	public CreateCourseController()
	{
		createCourse = new CreateCourseView();
		
		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(createCourse, BorderLayout.CENTER);
		
		initController();
	}
	
	private void initController()
	{
		
	}

}
