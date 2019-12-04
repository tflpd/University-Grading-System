package controllers;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import views.CourseStudentView;
import views.MainPanelView;

public class CourseStudentController {
	
	private JPanel parentPanel;
	private CourseStudentView courseStudentView;
	public CourseStudentController()
	{
		courseStudentView = new CourseStudentView(null, 0, 0, 0);
		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(courseStudentView, BorderLayout.CENTER);
		
		initController();
	}
	
	private void initController()
	{
		
	}

}
