package controllers;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.CourseSection;
import models.LoggedData;
import models.Student;
import org.apache.poi.hpsf.Section;
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
		bindData();


	}

	private void initController()
	{
		subTaskGrade.getHomeButton().addActionListener(l -> backHome());
		subTaskGrade.getBackButton().addActionListener(l -> back());
		subTaskGrade.getSectionCombo().addItemListener(event -> {
			if (event.getStateChange() == ItemEvent.SELECTED) {
				//System.out.println(ItemEvent.SELECTED+" 123");
				String section = (String)event.getItem();
				//System.out.println(section);
				for (CourseSection c: LoggedData.getSelectedCourse().getCourseSections())
				{
				   if (c.getName().equals(section)) {


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



					   tableModel = new DefaultTableModel(col, 0);


						   ArrayList<Student> studentList =c.getStudents();
						   //System.out.println(studentList);
						   //studentCount= studentCount+ studentList.size();
						   if (studentList != null)
						   {
							   for (var s : studentList)
							   {
								   Object[] objs =new Object[columSize];
								   objs[0] = s.getId();
								   objs[1] = s.getName();
								   objs[2] = c.getName();
								   //objs[3] = subTask.getGrades().get(0).getAbsolutePointsScored();

								   ((DefaultTableModel) tableModel).addRow(objs);
							   }
						   }



					   subTaskGrade.setTable(tableModel);
					   //double mean = DoubleSummaryStatistics.

					   //String stat = "Statistic:   Mean: "+ LoggedData.getGradingSystem().getActiveCourses().get(0). + "   Median: " + median + "   " + "Standard Deviation: " + standardDeviation
					   //subTaskGrade.setStatisticLabel("");
				   }
				}

			}
		}
		);

	}

	private void bindData() {

		JComboBox j = subTaskGrade.getSectionCombo();

		for (CourseSection c: LoggedData.getCourseSectionList())
		{
			//for (CourseSection cs : LoggedData.getSelectedCourse().getCourseSections()) {
			j.addItem(c.getName());
		}
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
		String header = LoggedData.getSelectedSubTask().getName();
		//courseStudentView.setCourseLabel(header+"'s Students List");
		var subTask = LoggedData.getSelectedSubTask();
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
