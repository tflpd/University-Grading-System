package controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import models.*;
import views.ButtonColumn;
import views.CourseStudentView;
import views.MainPanelView;

public class CourseStudentController {

	private JPanel parentPanel;
	private CourseStudentView courseStudentView;
	public TableModel tableModel;
	Course data = null;
	HashMap<String, Integer> columnDictionary;
	int columSize = 0;

	public CourseStudentController() {
		courseStudentView = new CourseStudentView();
		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(courseStudentView, BorderLayout.CENTER);
		//fillStudentData();
		fillStudentSubTaskData();
		initController();

	}

	private void initController() {
		courseStudentView.getHomeButton().addActionListener(l -> backHome());
		courseStudentView.getBackButton().addActionListener(l -> back());
		courseStudentView.getAddButton().addActionListener(l -> create());
		courseStudentView.getSaveButton().addActionListener(l -> save());
		courseStudentView.getTable().getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent tme) {
				if (tme.getType() == TableModelEvent.UPDATE) {
					DefaultTableModel model = (DefaultTableModel) courseStudentView.getTable().getModel();
					int row = tme.getFirstRow();
					int column = tme.getColumn();
					//if (column != )
					System.out.println("row " + row + " col " + column);
					if (column < columSize - 3 && column > 2) {
						int studentId = Integer.valueOf(model.getValueAt(row, 0).toString());
						Float up = Float.valueOf(model.getValueAt(row, column).toString());


						String colName = model.getColumnName(column);
						int subtaskId = columnDictionary.get(colName);
						float maxPoint = 0;
						for (var ta : data.getTasks()) {
							if (ta.getSubTasks() != null) {
								for (var st : ta.getSubTasks()) {
									if (st.getId() == subtaskId) {
										maxPoint = st.getTotalPointsAvailable();
										break;
									}
								}
							}
						}

						if (up > maxPoint || up < 0) {
							float down = 0;
							System.out.println("down " + down + " " + colName);
							if (colName.contains("(Point Scored -)")) {
								down = Float.valueOf(model.getValueAt(row, column + 1).toString());
								up = maxPoint - down;
								model.setValueAt(up, row, column);

							} else if (colName.contains("(Point Deducted)")) {
								down = Float.valueOf(model.getValueAt(row, column - 1).toString());
								System.out.println("down " + down + " new score ");
								up = maxPoint - down;
								model.setValueAt(up, row, column);
							}
							showErrorMessage("Please put in a corret number");
						} else {

							float down = maxPoint - up;
							System.out.println("down " + down + " " + colName);
							if (colName.contains("(Point Scored -)")) {
								model.setValueAt(down, row, column + 1);
							} else if (colName.contains("(Point Deducted)")) {
								model.setValueAt(down, row, column - 1);
							}
						}

					}
				}
			}
		});
	}

	private void showErrorMessage(String m)
	{
		String message = "\" Error\"\n"
				+ m;

		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}

	private void create() {

		AddSingleStudentController ass = new AddSingleStudentController();
	}

	private void backHome() {
		CourseListController cLc = new CourseListController();
	}

	private void back() {
		ClassHomePageController cHP = new ClassHomePageController(LoggedData.getSelectedCourse().toString());
	}

	public void fillStudentData() {

		columnDictionary = new HashMap<String, Integer>();
		String header = LoggedData.getSelectedCourse().getName();
		courseStudentView.setCourseLabel(header + "'s Students List");

		data = LoggedData.getSelectedCourse();

		for(var ta: data.getTasks())
		{
			if (ta.getSubTasks() != null)
			{
				for (var st: ta.getSubTasks())
				{
					st.setGrades(LoggedData.getDbManager().readGradeBySubTaskId(st.getId()));
				}
			}
		}

		JComboBox sectionCombox = new JComboBox();
		for (var c : LoggedData.getCourseSectionList()) {
			sectionCombox.addItem(c);
		}

		int columSize = data.getTasks().size();
		//for (var t: data.getTasks())
		//{
		//	columSize = columSize+ t.getSubTasks().size();
		//}
		//columSize = columSize*2;

		var taskList = data.getTasks();
		columSize = columSize + 6;
		String col[] = new String[columSize];
		col[0] = "Id";
		col[1] = "Student's Name";
		col[2] = "Section";
		int extra = 3;
		/*
		for (int i = 0; i < taskList.size(); i++) {
			for (int j =0; j < taskList.get(i).getSubTasks().size(); j++) {
				col[i + extra] = taskList.get(i).getSubTasks().get(i).getName() + " (" + taskList.get(i).getWeightInFinalGrade() + "%)";
				extra = extra+1;
			}
		}
		*/
		for (int i = 0; i < taskList.size(); i++) {
			col[i + extra] = taskList.get(i).getName() + " (" + taskList.get(i).getWeightInFinalGrade() + "%)";
			extra = extra+1;
		}
		col[columSize - 3] = "Final Grade";
		col[columSize - 2] = "Has Withdrawn";
		col[columSize - 1] = "Action";

		for (int i = 0; i < columSize; i++)
		{
			columnDictionary.put(col[i],i);
		}

		int studentCount = 0;
		int finalColumSize = columSize;
		AbstractTableModel tableModel = new DefaultTableModel(col, 0) {
			@Override
			public Class getColumnClass(int columnIndex) {
				//System.out.println("Inside getColumnClass("+ columnIndex +" of "+columSize+")");
				return columnIndex == (finalColumSize - 2) ? Boolean.class : super.getColumnClass(columnIndex);
			}
		};
		//DefaultTableModel model = new DefaultTableModel()

		for (var cSc : data.getCourseSections()) {
			var studentList = cSc.getStudents();
			studentCount = studentCount + studentList.size();
			if (studentList != null) {
				for (var s : studentList) {
					//var g = LoggedData.getSelectedCourse().getTasks().get(0).getSubTasks().get(0).getStudentsGrade(s);
					Object[] objs = new Object[columSize];
					objs[0] = s.getId();
					objs[1] = s.getName();
					objs[2] = cSc.getName();
					//for (int i = 0; i < taskList.size(); i++) {
					//	objs[i + 3] = String.valueOf(taskList.get(i).getStudentsGrade(s));
					//}
					extra = 3;
					for (int i = 0; i < taskList.size(); i++) {
						objs[i + extra] = taskList.get(i).getStudentsGrade(s);
						extra = extra+1;
					}

					objs[columSize - 3] = LoggedData.getSelectedCourse().getStudentsFinalLetterGrade(s);
					objs[columSize - 2] = s.isWithdrawn();
					objs[columSize - 1] = "Delete";

					((DefaultTableModel) tableModel).addRow(objs);
				}
			}
		}


		courseStudentView.setTable(tableModel);

		courseStudentView.getTable().getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(sectionCombox));

		String stat = "Statistic:   Mean: " + data.getMeanGrade() + "   Median: "
				+ data.getMedianPercentage() + "   " + "Standard Deviation: " + data.getStandardDeviation();
		courseStudentView.setStatisticLabel(stat);

		Action delete = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				Object oj = ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 0);
				int studentId = (Integer) oj;
				System.out.println("To be deleted Student =" + studentId);
				((DefaultTableModel) table.getModel()).removeRow(modelRow);
				Student tobeRemoved = null;
				for (var s : LoggedData.getSelectedCourse().getAllStudents()) {
					if (s.getId() == studentId) {
						LoggedData.getDbManager().deleteEnrollment(data.getId(), studentId);
						data.deleteStudent(s);
						LoggedData.setSelectedCourse(data);
						break;
					}
				}
			}
		};


		ButtonColumn buttonColumn = new ButtonColumn(courseStudentView.getTable(), delete, columSize - 2);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

	}


	public void fillStudentSubTaskData() {

		System.out.println("Update Table");
		columnDictionary = new HashMap<String, Integer>();
		String header = LoggedData.getSelectedCourse().getName();
		courseStudentView.setCourseLabel(header + "'s Students List");

		data = LoggedData.getSelectedCourse();

		for(var ta: data.getTasks())
		{
			if (ta.getSubTasks() != null)
			{
				for (var st: ta.getSubTasks())
				{
					st.setGrades(LoggedData.getDbManager().readGradeBySubTaskId(st.getId()));
				}
			}
		}


		JComboBox sectionCombox = new JComboBox();
		for (var c : LoggedData.getCourseSectionList()) {
			sectionCombox.addItem(c);
		}

		columSize = 0;
		for (var t: data.getTasks())
		{
			columSize = columSize+ t.getSubTasks().size();
		}
		columSize = columSize*3;

		var taskList = data.getTasks();
		columSize = columSize + 6;
		String col[] = new String[columSize];
		col[0] = "Id";
		columnDictionary.put(col[0],0);
		col[1] = "Student's Name";
		columnDictionary.put(col[1],0);
		col[2] = "Section";
		columnDictionary.put(col[2],0);
		int extra = 3;

		if (taskList != null)
		{
			for (var t : taskList)
			{
				if (t.getSubTasks() != null)
				{
					for (var st : t.getSubTasks())
					{
						col[extra] = st.getName()+"(Point Scored)" ;
						columnDictionary.put(col[extra],st.getId());
						col[extra+1] = st.getName() + "(Point Deducted)";
						columnDictionary.put(col[extra+1],st.getId());
						col[extra+2] = st.getName() + "(Bonus)";
						columnDictionary.put(col[extra+2],st.getId());
						extra = extra+3;
					}
				}
			}
		}



		col[columSize - 3] = "Final Grade";
		columnDictionary.put(col[columSize - 3] ,columSize - 3 );
		col[columSize - 2] = "Has Withdrawn";
		columnDictionary.put(col[columSize - 2] ,columSize - 2 );
		col[columSize - 1] = "Action";
		columnDictionary.put(col[columSize - 1] ,columSize - 2 );

		//for (int i = 0; i < columSize; i++)
		//{
		//	columnDictionary.put(col[i],i);
		//}

		int studentCount = 0;
		int finalColumSize = columSize;
		AbstractTableModel tableModel = new DefaultTableModel(col, 0) {
			@Override
			public Class getColumnClass(int columnIndex) {

				return columnIndex == (finalColumSize - 2) ? Boolean.class : super.getColumnClass(columnIndex);
			}



		};
		//DefaultTableModel model = new DefaultTableModel()

		for (var cSc : data.getCourseSections()) {
			var studentList = cSc.getStudents();
			studentCount = studentCount + studentList.size();
			if (studentList != null) {

				for (var s : studentList) {

					Object[] objs = new Object[columSize];
					objs[0] = s.getId();
					objs[1] = s.getName();
					objs[2] = cSc.getName();
					extra = 3;

					if (taskList != null)
					{
						for (var t : taskList)
						{
							if (t.getSubTasks() != null)
							{
								for (var st : t.getSubTasks())
								{
									var g = st.getGrade(s);
									if (g != null ) {
										System.out.println(st.getId()+" "+g.getStudent().getId() +" "+ st.getGrade(s).getAbsolutePointsScored()+
														" "+ s.getId());
										objs[extra] = st.getGrade(s).getAbsolutePointsScored();
										objs[extra + 1] = st.getTotalPointsAvailable() -
												st.getGrade(s).getAbsolutePointsScored();
										objs[extra + 2] = st.getGrade(s).getBonusPoints();
										extra = extra + 3;
									}
								}
							}
						}
					}


					objs[columSize - 3] = LoggedData.getSelectedCourse().getStudentsFinalLetterGrade(s);
					objs[columSize - 2] = s.isWithdrawn();
					objs[columSize - 1] = "Delete";

					((DefaultTableModel) tableModel).addRow(objs);
				}
			}
		}


		courseStudentView.setTable(tableModel);
		courseStudentView.getTable().getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(sectionCombox));
		String stat = "Statistic:   Mean: " + data.getMeanGrade() + "   Median: "
				+ data.getMedianPercentage() + "   " + "Standard Deviation: " + data.getStandardDeviation();
		courseStudentView.setStatisticLabel(stat);

		Action delete = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JTable table = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				Object oj = ((DefaultTableModel) table.getModel()).getValueAt(modelRow, 0);
				int studentId = (Integer) oj;
				System.out.println("To be deleted Student =" + studentId);
				((DefaultTableModel) table.getModel()).removeRow(modelRow);
				Student tobeRemoved = null;
				for (var s : LoggedData.getSelectedCourse().getAllStudents()) {
					if (s.getId() == studentId) {
						LoggedData.getDbManager().deleteEnrollment(data.getId(), studentId);
						data.deleteStudent(s);
						LoggedData.setSelectedCourse(data);
						break;
					}
				}
			}
		};

		ButtonColumn buttonColumn = new ButtonColumn(courseStudentView.getTable(), delete, columSize - 2);
		buttonColumn.setMnemonic(KeyEvent.VK_D);

//		courseStudentView.getTable().setTableHeader(new JTableHeader(courseStudentView.getTable().getColumnModel()) {
//			@Override public Dimension getPreferredSize() {
//				Dimension d = super.getPreferredSize();
//				d.height = 50;
//				d.width = 50;
//				return d;
//			}
//		});

	}

	private void save() {
		DefaultTableModel model = (DefaultTableModel) courseStudentView.getTable().getModel();

		//String n = data.getTasks().get(0).getName() + " (" + data.getTasks().get(0).getWeightInFinalGrade() + "%)";
		//int aa = columnDictionary.get(n);
		//String res = model.getValueAt(0,aa).toString();
		//System.out.println("get Model pakei nama "+res);

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
			int id = (int) model.getValueAt(i, 0);
			for (var cSc : data.getCourseSections()) {
				if (cSc.getSingleStudent(id) != null) {
					cSc.getSingleStudent(id).setName(name1);
				}
			}

		}





		for (int i = 0; i < model.getRowCount(); i++) {

			int id = (int) model.getValueAt(i, 0);
			Student student = null;
			CourseSection originalSection = null;
			for (var cSc : data.getCourseSections()) {
				if (cSc.getSingleStudent(id) != null) {
					originalSection = cSc;
					student = cSc.getSingleStudent(id);
					break;
				}
			}

			boolean withDraw = (boolean) model.getValueAt(i, columSize - 2);
			student.setWithdrawn(withDraw);
			System.out.println("Student Status " + student.isWithdrawn());

			// Section
			String section = courseStudentView.getTable().getModel().getValueAt(i, 2).toString();

			for (var sect : LoggedData.getCourseSectionList()) {
				boolean isNew = true;
				if (sect.getName().equals(section)) {
					for (var cSc1 : data.getCourseSections()) {
						if (cSc1.getName().equals(section)) {
							originalSection.deleteStudent(student);
							cSc1.addStudent(student);
							isNew = false;
							break;
						}
					}
					if (isNew) {
						originalSection.deleteStudent(student);
						CourseSection cc = new CourseSection(sect.getId(), sect.getName());
						cc.addStudent(student);
						data.addSection(cc);
					}
					break;
				}
			}

			// Save Grade

			for (int k = 3; k < columSize - 3; k++) {
				String colName = model.getColumnName(k);
				int subtaskId = columnDictionary.get(colName);

				if (colName.contains("(Point Scored)")) {
					if (model.getValueAt(i, k) != null) {

						float score = Float.valueOf(model.getValueAt(i, k).toString());
						Grade DBGrade = LoggedData.getDbManager().readGradeByStudentAndSubtaskId(student.getId(), subtaskId);
						if (DBGrade == null) {
							LoggedData.getDbManager().addGrade(score, subtaskId, student.getId());
						}
						LoggedData.getDbManager().UpdateGradeByStudentId(subtaskId, score, student.getId());

					}
				} else if (colName.contains("(Bonus)")) {
					if (model.getValueAt(i, k) != null) {
						float score = Float.valueOf(model.getValueAt(i, k).toString());
						Grade DBGrade = LoggedData.getDbManager().readGradeByStudentAndSubtaskId(student.getId(), subtaskId);
						if (DBGrade == null) {
							LoggedData.getDbManager().addGrade(0, subtaskId, student.getId());
						}
						LoggedData.getDbManager().UpdateGradeBonusByStudentAndSubtaskId(subtaskId, score, student.getId());

					}
				}
			}
		}

		// saving to database
		for (var c : data.getCourseSections()) {
			for (var s : c.getStudents()) {
				LoggedData.getDbManager().UpdateStudent(s);
				LoggedData.getDbManager().UpdateEnrollment(s.getId(), c.getId(), data.getId());
			}
		}


		LoggedData.setSelectedCourse(data);
	}




}
