package controllers;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.LoggedData;
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
	}

	private void create()
	{

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
		String header = LoggedData.getGradingSystem().getActiveCourses().get(0).getName();
		courseStudentView.setCourseLabel(header+"'s Students List");
		
		
		
		
		var taskList = LoggedData.getGradingSystem().getActiveCourses().get(0).getTasks();

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
		col[columSize-2] = "Status";
		col[columSize-1] = " ";

		int studentCount = 0;
		tableModel = new DefaultTableModel(col, 0);
		for (var cSc : LoggedData.getGradingSystem().getActiveCourses().get(0).getCourseSections())
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

					col[columSize-3] = LoggedData.getGradingSystem().getActiveCourses().get(0).getStudentsFinalLetterGrade(s);
					col[columSize-2] = "";
					col[columSize-1] = "Edit";

					((DefaultTableModel) tableModel).addRow(objs);
				}					
			}
		}

		
		courseStudentView.setTable(tableModel);
		//double mean = DoubleSummaryStatistics.
		
		//String stat = "Statistic:   Mean: "+ LoggedData.getGradingSystem().getActiveCourses().get(0). + "   Median: " + median + "   " + "Standard Deviation: " + standardDeviation
	    courseStudentView.setStatisticLabel("");
	}
}
