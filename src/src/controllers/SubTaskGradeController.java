package controllers;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.LoggedData;
import views.MainPanelView;
import views.SubTaskGrade;

public class SubTaskGradeController {
	
	private SubTaskGrade subTaskGrade;
	TableModel tableModel;
	private JPanel parentPanel;
	public SubTaskGradeController()
	{
		subTaskGrade = new SubTaskGrade("", 0,0,0);
		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(subTaskGrade, BorderLayout.CENTER);
		fillStudentData();
		initController();

	}

	private void initController()
	{
		subTaskGrade.getHomeButton().addActionListener(l -> backHome());
		subTaskGrade.getBackButton().addActionListener(l -> back());
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
		//courseStudentView.setCourseLabel(header+"'s Students List");
		
		
		
		
		var subTask = LoggedData.getGradingSystem().getActiveCourses().get(0).getTasks().get(1).getSubTasks().get(0);

	
        int columSize = 9;
		String col[] = new String[9];
		col[0] = "Id";
		col[1] = "Student's Name";
		col[2] = "Point Deducted";
		col[3] = "Point Scored";
		col[4] = "Student Status";
		col[5] = "Comment";
		col[6] = "Group ID";
		col[7] = "% Score";
		col[8] = "Bonus Point";
		

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
					//objs[3] = subTask.getGrades().get(0).getAbsolutePointsScored();

					((DefaultTableModel) tableModel).addRow(objs);
				}					
			}
		}

		
		subTaskGrade.setTable(tableModel);
		//double mean = DoubleSummaryStatistics.
		
		//String stat = "Statistic:   Mean: "+ LoggedData.getGradingSystem().getActiveCourses().get(0). + "   Median: " + median + "   " + "Standard Deviation: " + standardDeviation
		//subTaskGrade.setStatisticLabel("");
	}

}
