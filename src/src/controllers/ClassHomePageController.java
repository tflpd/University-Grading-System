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
				      var o = target.getValueAt(row, column);
				      
				      if (column == 0)
				      {
				    	  TaskDialogController tDC = new TaskDialogController(classHomePage);
				    	  
				      }else if (column == 1)
				      {
				    	  
				      }
				      
				      System.out.println(row+"  "+column+" "+id);
				      // do some action if appropriate column
				    }
				  }
				});
		
		fillTaskData();
	}
	
	public void fillTaskData()
	{
		var taskList = LoggedData.getActiveCourseList().get(0).getTasks();
		
		String col[] = {"Id","Task Name","Edit",};
		tableModel = new DefaultTableModel(col, 0);
		

		if (taskList != null)
		{
		
			System.out.println(taskList.size());
			for (int i = 0; i < taskList.size(); i++)
			{
				Object[] objs = {i, taskList.get(i).getName(),
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
	/*
	table.addMouseListener(new MouseAdapter() {
		  public void mouseClicked(MouseEvent e) {
		    if (e.getClickCount() == 2) {
		      JTable target = (JTable)e.getSource();
		      int row = target.getSelectedRow();
		      int column = target.getSelectedColumn();
		      // do some action if appropriate column
		    }
		  }
		});
 */
}
