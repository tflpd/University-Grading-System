package controllers;


import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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

				//JTable table = (JTable)e.getSource();
				//int modelRow = Integer.valueOf( evt.getActionCommand() );
				//((DefaultTableModel)table.getModel()).removeRow(modelRow);

    	        int index = list.locationToIndex(evt.getPoint());
                if (index >= 0) {
                    Course o = (Course)list.getModel().getElementAt(index);
					LoggedData.setSelectedCourse(o);
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
