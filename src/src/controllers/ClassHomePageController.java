package controllers;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import models.LoggedData;
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
							
					}else if (column == 1)
					{
						TaskDialogController tDC = new TaskDialogController(classHomePage, id);
					}

				}
			}
		});
		classHomePage.getHomeButton().addActionListener(l -> backHome());

		fillTaskData();
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
		SubTaskDialogController tDC = new SubTaskDialogController();
	}

	private void OpenTaskDialog()
	{
		TaskDialogController tDC = new TaskDialogController(classHomePage);
	}
	private void backHome()
	{
		CourseListController cLc = new CourseListController();
	}
}
