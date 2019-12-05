package controllers;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.LoggedData;
import views.ClassHomePage;
import views.CreateCourseView;
import views.MainPanelView;
import views.TaskDialog;

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
		classHomePage.getCreatSubButton().addActionListener(l -> OpenSubTaskDialog());
		classHomePage.getCreatButton().addActionListener(l -> OpenTaskDialog());
		
		fillTaskData();
	}
	
	public void fillTaskData()
	{
		var taskList = LoggedData.getActiveCourseList().get(0).getTasks();
		
		String col[] = {"Task Name","Edit",};
		TableModel tableModel = new DefaultTableModel(col, 0);
		

		if (taskList != null)
		{
			System.out.println(taskList.size());
			for (int i = 0; i < taskList.size(); i++)
			{
				Object[] objs = {taskList.get(i).getName(),
						"Edit"};
				((DefaultTableModel) tableModel).addRow(objs);
			}			
		}
		
		//JTable table = new JTable(tableModel);
		//table.setFillsViewportHeight(true);
		
		classHomePage.setTaskTable(tableModel);
	}
	
	private void openStudentList()
	{
		CourseStudentController csc = new CourseStudentController();
	}
	
	private void OpenSubTaskDialog()
	{
		SubTaskDialogController tDC = new SubTaskDialogController();
	}
	
	private void OpenTaskDialog()
	{
		TaskDialogController tDC = new TaskDialogController(classHomePage);
	}

}
