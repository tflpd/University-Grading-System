package controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.LoggedData;
import views.ButtonColumn;
import views.CourseStudentView;
import views.MainPanelView;

public class CourseStudentController {

	private JPanel parentPanel;
	private CourseStudentView courseStudentView;
	TableModel tableModel;
	public CourseStudentController()
	{
		courseStudentView = new CourseStudentView();
		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(courseStudentView, BorderLayout.CENTER);
		fillStudentData();
		initController();

	}

	private void initController()
	{
		courseStudentView.getHomeButton().addActionListener(l -> backHome());
		courseStudentView.getBackButton().addActionListener(l -> back());
		courseStudentView.getAddButton().addActionListener(l -> create());
	}

	private void create()
	{

		AddSingleStudentController ass = new AddSingleStudentController();
	}
	private void backHome()
	{
		CourseListController cLc = new CourseListController();
	}
	private void back()
	{
		ClassHomePageController cHP = new ClassHomePageController("");
	}


	public void fillStudentData()
	{
		String header = LoggedData.getSelectedCourse().getName();
		courseStudentView.setCourseLabel(header+"'s Students List");
	
		var taskList = LoggedData.getSelectedCourse().getTasks();

		int columSize = taskList.size()+6;

		String col[] = new String[columSize];
		col[0] = "Id";
		col[1] = "Student's Name";
		col[2] = "Section";
		for (int i=0; i < taskList.size(); i++)
		{
			col[i+3] = taskList.get(i).getName() +" ("+taskList.get(i).getWeightInFinalGrade()+"%)";
		}
		col[columSize-3] = "Final Grade";
		col[columSize-2] = "Has Withdrawn";
		col[columSize-1] = "Action";

		int studentCount = 0;
		tableModel = new DefaultTableModel(col, 0);
		for (var cSc : LoggedData.getSelectedCourse().getCourseSections())
		{
			var studentList = cSc.getStudents();
			studentCount= studentCount+ studentList.size();
			if (studentList != null)
			{
				for (var s : studentList)
				{
					Object[] objs =new Object[columSize];
					objs[0] = s.getId();
					objs[1] = s.getName();
					objs[2] = cSc.getName();
					for (int i=0; i < taskList.size(); i++)
					{
						objs[i+3] = taskList.get(i).getStudentsGrade(s);
					}

					objs[columSize-3] = LoggedData.getSelectedCourse().getStudentsFinalLetterGrade(s);
					objs[columSize-2] = s.isWithdrawn();
					objs[columSize-1] = "Delete";

					((DefaultTableModel) tableModel).addRow(objs);
				}					
			}
		}

		
		courseStudentView.setTable(tableModel);
		//double mean = DoubleSummaryStatistics.
		
		//String stat = "Statistic:   Mean: "+ LoggedData.getSelectedCourse(). + "   Median: " + median + "   " + "Standard Deviation: " + standardDeviation
	    courseStudentView.setStatisticLabel("");

		Action delete = new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				JTable table = (JTable)e.getSource();
				int modelRow = Integer.valueOf( e.getActionCommand() );
				((DefaultTableModel)table.getModel()).removeRow(modelRow);
			}
		};


		ButtonColumn buttonColumn = new ButtonColumn(courseStudentView.getTable(), delete, columSize-2);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}


}
