package controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.*;
import views.ButtonColumn;
import views.CourseStudentView;
import views.MainPanelView;

public class CourseStudentController   {

	private JPanel parentPanel;
	private CourseStudentView courseStudentView;
	public TableModel tableModel;
	Course data = null;
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
		courseStudentView.getSaveButton().addActionListener(l -> save());
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
		ClassHomePageController cHP = new ClassHomePageController(LoggedData.getSelectedCourse().toString());
	}


	public void fillStudentData() {
		String header = LoggedData.getSelectedCourse().getName();
		courseStudentView.setCourseLabel(header + "'s Students List");

		data = LoggedData.getSelectedCourse();

		var taskList = data.getTasks();
		int columSize = taskList.size() + 6;
		String col[] = new String[columSize];
		col[0] = "Id";
		col[1] = "Student's Name";
		col[2] = "Section";
		for (int i = 0; i < taskList.size(); i++) {
			col[i + 3] = taskList.get(i).getName() + " (" + taskList.get(i).getWeightInFinalGrade() + "%)";
		}
		col[columSize - 3] = "Final Grade";
		col[columSize - 2] = "Has Withdrawn";
		col[columSize - 1] = "Action";

		int studentCount = 0;
		AbstractTableModel tableModel = new DefaultTableModel(col, 0) {
			@Override
			public Class getColumnClass(int columnIndex) {
				//System.out.println("Inside getColumnClass("+ columnIndex +" of "+columSize+")");
				return columnIndex == (columSize -2) ? Boolean.class : super.getColumnClass(columnIndex);
			}
		};
		//DefaultTableModel model = new DefaultTableModel()

		for (var cSc : data.getCourseSections())
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
						objs[i+3] = String.valueOf(taskList.get(i).getStudentsGrade(s));
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
				Object oj = ((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0);
				int studentId = (Integer) oj;
				System.out.println("To be deleted Student ="+studentId);
				((DefaultTableModel)table.getModel()).removeRow(modelRow);
				Student tobeRemoved = null;
				for (var s: LoggedData.getSelectedCourse().getAllStudents())
				{
					if (s.getId() == studentId)
					{
						LoggedData.getDbManager().deleteEnrollment(data.getId(), studentId);
						data.deleteStudent(s);
						LoggedData.setSelectedCourse(data);
						var r = data;
						break;
					}
				}
				var data1 = data;
				//LoggedData.getSelectedCourse().getAllStudents().re
			}
		};


		ButtonColumn buttonColumn = new ButtonColumn(courseStudentView.getTable(), delete, columSize-2);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

	}

	private void save() {
		DefaultTableModel model = (DefaultTableModel) courseStudentView.getTable().getModel();
		var taskList = LoggedData.getSelectedCourse().getTasks();
		//name
		for (int i = 0; i < model.getRowCount(); i++) {

			String name = (String) model.getValueAt(i, 1);

				String[] arr = name.split(" ");
				String firstName = arr[0];
				String LastName;
				if (arr.length == 1) {
					LastName = "";
				} else {
					LastName = arr[1];
				}
				Name name1 = new Name(firstName, LastName);
				int id = (int)model.getValueAt(i, 0);
				for (var cSc : data.getCourseSections()) {
					if(cSc.getSingleStudent(id) != null){
						cSc.getSingleStudent(id).setName(name1);
					}
				}

		}

		//grade
		for (int i = 0; i < model.getRowCount(); i++) {


			int id = (int)model.getValueAt(i, 0);
			Student student = null;
			for (var cSc : data.getCourseSections()) {
				if(cSc.getSingleStudent(id) != null){
					student = cSc.getSingleStudent(id);
				}
			}
			//System.out.println(student.getName());
			for (int ii=0; ii < taskList.size(); ii++)
			{
//				System.out.println(model.getValueAt(i, ii+3));

				String grade = (String) model.getValueAt(i, ii+3);
				//System.out.println(Float.parseFloat(grade));
				taskList.get(ii).setStudentGrade(student, Float.parseFloat(grade));

			}
		}

		//with draw
		for (int i = 0; i < model.getRowCount(); i++) {

			int id = (int)model.getValueAt(i, 0);
			Student student = null;
			for (var cSc : data.getCourseSections()) {
				if(cSc.getSingleStudent(id) != null){
					student = cSc.getSingleStudent(id);
				}
			}
			//System.out.println(student.getName());

			boolean withDraw = (boolean) model.getValueAt(i, taskList.size()+4);
			student.setWithdrawn(withDraw);
			System.out.println("Student Status " + student.isWithdrawn());

		}

		//section
//		for (int i = 0; i < model.getRowCount(); i++) {
//
//			int id = (int)model.getValueAt(i, 0);
//			Student student = null;
//			CourseSection originalSection = null;
//			for (var cSc : data.getCourseSections()) {
//				if(cSc.getSingleStudent(id) != null){
//					originalSection = cSc;
//					student = cSc.getSingleStudent(id);
//				}
//			}
//			//System.out.println(student.getName());
//
//			String section = (String) model.getValueAt(i, 2);
//			for (var cSc1 : data.getCourseSections()) {
//				if (cSc1.getName().equals(section)) {
//					originalSection.deleteStudent(student);
//					cSc1.addStudent(student);
//				}
//			}
//		}


		for(var s : data.getAllStudents())
		{
			LoggedData.getDbManager().UpdateStudent(s);
		}

		LoggedData.setSelectedCourse(data);
	}

	// Save to DataBase;



}
