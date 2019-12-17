package controllers;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import models.LoggedData;
import models.SubTask;
import models.Task;
import views.ClassHomePage;
import views.MainPanelView;

public class ClassHomePageController {

	private ClassHomePage classHomePage;
	private JPanel parentPanel;
	TableModel tableModel;

	public ClassHomePageController(String courseName)
	{
		classHomePage = new ClassHomePage(courseName);
		tableModel = new DefaultTableModel(){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(classHomePage, BorderLayout.CENTER);

		initController();
		if (LoggedData.getSelectedCourse().getTasks().size() > 0) {
			LoggedData.setSelectedTask(LoggedData.getSelectedCourse().getTasks().get(0));
			UpdateSubTaskTable(LoggedData.getSelectedTask());
		}


	}

	private void initController()
	{
		classHomePage.getStudentListButton().addActionListener(l -> openStudentList());
		classHomePage.getCreatSubButton().addActionListener(l -> OpenSubTaskDialog());
		classHomePage.getCreatButton().addActionListener(l -> OpenTaskDialog());
		classHomePage.getTaskTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();				      
					int id = (int)target.getModel().getValueAt(row,0);				      
					
					for (var t : LoggedData.getSelectedCourse().getTasks())
					{
						if (t.getId() == id)
						{
							LoggedData.setSelectedTask(t);
							System.out.println("Task ID" + t.getId());
							System.out.println("SubTask Size" + t.getSubTasks().size());
							UpdateSubTaskTable(t);
							break;
						}
					}

					if (column == 0)
					{
						UpdateSubTaskTable(LoggedData.getSelectedTask());
					}else if (column == 2)
					{
						//TaskDialogController tDC = new TaskDialogController(classHomePage, id, this.);
						OpenTaskDialog(id);
					}

				}
			}
		});
		classHomePage.getSubtaskTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					int id = (int)target.getModel().getValueAt(row,0);

					for (var t : LoggedData.getSelectedTask().getSubTasks())
					{
						if (t.getId() == id)
						{
							LoggedData.setSelectedSubTask(t);
							break;
						}
					}

					if (column == 0)
					{

					}else if (column == 2)
					{

						SubTaskDialogController subTaskDialogController = new SubTaskDialogController(classHomePage, id);
					}

				}
			}
		});
		classHomePage.getHomeButton().addActionListener(l -> backHome());
		classHomePage.getDeleteButton().addActionListener(l -> DeleteCourse());

		fillTaskData();
	}

	private void DeleteCourse(){

		LoggedData.getDbManager().deleteCourse(LoggedData.getSelectedCourse());
		LoggedData.getGradingSystem().deleteCourse(LoggedData.getSelectedCourse());
		backHome();
	}


	public void fillTaskData()
	{
		var taskList = LoggedData.getSelectedCourse().getTasks();

		String col[] = {"Id","Task Name", "Weight (%)"," ",};
		tableModel = new DefaultTableModel(col, 0){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		if (taskList != null && taskList.size() > 0)
		{
			for (int i = 0; i < taskList.size(); i++)
			{
				Object[] objs = {taskList.get(i).getId(), taskList.get(i).getName(), taskList.get(i).getWeightInFinalGrade(),
				"edit"};
				((DefaultTableModel) tableModel).addRow(objs);

			}
			UpdateSubTaskTable(taskList.get(0));

		}
		
		classHomePage.setTaskTable(tableModel);
	}

	private void openStudentList()
	{
		CourseStudentController csc = new CourseStudentController();
	}

	private void OpenSubTaskDialog()
	{
		if (LoggedData.getSelectedTask() != null) {
			SubTaskDialogController tDC = new SubTaskDialogController(classHomePage);
		}else
		{
			String message = "\" Error\"\n"
					+ "Please add a task first before adding subtask";

			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void OpenTaskDialog()
	{
		//System.out.println("create new Task");
		TaskDialogController tDC = new TaskDialogController(classHomePage, this);
	}
	private void OpenTaskDialog(int id)
	{
		//System.out.println("create new Task");
		TaskDialogController tDC = new TaskDialogController(classHomePage, id,this);
	}
	private void backHome()
	{
		LoggedData.RefreshGradingSystem();
		CourseListController cLc = new CourseListController();
	}

	public void UpdateSubTaskTable(Task task)
	{
		String col[] = {"Id", task.getName()+" sub task(s)","Weight (%)"," ",};
		TableModel tableModel = new DefaultTableModel(col, 0){
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		List<SubTask> subTask = task.getSubTasks();

		if (subTask != null)
		{
			System.out.println(subTask.size());
			for (int i = 0; i < subTask.size(); i++)
			{
				Object[] objs = {subTask.get(i).getId(), subTask.get(i).getName(),
						subTask.get(i).getWeightInParentTask(),
						"Edit"};
				((DefaultTableModel) tableModel).addRow(objs);
			}
		}
		classHomePage.setSubTaskTable(tableModel);
	}
}
