package controllers;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
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
		tableModel = new DefaultTableModel();
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
							break;
						}
					}

					if (column == 0)
					{
						UpdateSubTaskTable(LoggedData.getSelectedTask());
					}else if (column == 1)
					{
						TaskDialogController tDC = new TaskDialogController(classHomePage, id);
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

					}else if (column == 1)
					{
						SubTaskDialogController subTaskDialogController = new SubTaskDialogController(classHomePage, id);
					}

				}
			}
		});
		classHomePage.getHomeButton().addActionListener(l -> backHome());

		fillTaskData();
		//fillSubTaskData();
	}

	public void fillSubTaskData()
	{
		var taskList = LoggedData.getSelectedCourse().getTasks().get(0).getSubTasks();

		String col[] = {"Id","Task Name","Edit",};
		tableModel = new DefaultTableModel(col, 0);


		if (taskList != null)
		{

			System.out.println(taskList.size());
			for (int i = 0; i < taskList.size(); i++)
			{
				Object[] objs = {taskList.get(i).getId(), taskList.get(i).getName(),
						"Edit"};
				((DefaultTableModel) tableModel).addRow(objs);
			}
		}
		classHomePage.setSubTaskTable(tableModel);
	}


	public void fillTaskData()
	{
		var taskList = LoggedData.getSelectedCourse().getTasks();

		String col[] = {"Id","Task Name","Edit",};
		tableModel = new DefaultTableModel(col, 0);


		if (taskList != null)
		{

			System.out.println(taskList.size());
			for (int i = 0; i < taskList.size(); i++)
			{
				Object[] objs = {taskList.get(i).getId(), taskList.get(i).getName(),
				"Edit"};
				((DefaultTableModel) tableModel).addRow(objs);
			}			
		}
		classHomePage.setTaskTable(tableModel);
	}

	private void openStudentList()
	{
		CourseStudentController csc = new CourseStudentController();
	}

	private void OpenSubTaskDialog()
	{
		SubTaskDialogController tDC = new SubTaskDialogController(classHomePage);
	}

	private void OpenTaskDialog()
	{
		TaskDialogController tDC = new TaskDialogController(classHomePage);
	}
	private void backHome()
	{
		CourseListController cLc = new CourseListController();
	}

	public void UpdateSubTaskTable(Task task)
	{
		String col[] = {"Id","SubTask Name","Edit",};
		TableModel tableModel = new DefaultTableModel(col, 0);
		List<SubTask> subTask = task.getSubTasks();

		if (subTask != null)
		{
			System.out.println(subTask.size());
			for (int i = 0; i < subTask.size(); i++)
			{
				Object[] objs = {subTask.get(i).getId(), subTask.get(i).getName(),
						"Edit"};
				((DefaultTableModel) tableModel).addRow(objs);
			}
		}
		classHomePage.setSubTaskTable(tableModel);
	}
}
