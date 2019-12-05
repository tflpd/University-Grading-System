package controllers;

import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.LoggedData;
import views.ClassHomePage;
import views.TaskDialog;

public class TaskDialogController {

	private TaskDialog dialog; 
	private ClassHomePage classHomePage;

	public TaskDialogController(ClassHomePage c)
	{
		classHomePage = c;
		try {
			dialog = new TaskDialog();
			initController();
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void initController()
	{
		dialog.getSaveButton().addActionListener(l -> SaveTask());
		dialog.getCancelButton().addActionListener(l -> Close());
	}

	private void SaveTask()
	{
		var taskList = LoggedData.getActiveCourseList().get(0).getTasks();

		String col[] = {"Task Name","Edit",};
		TableModel tableModel = new DefaultTableModel(col, 0);


		if (taskList != null)
		{
			System.out.println(taskList.size());
			for (int i = 0; i < taskList.size(); i++)
			{
				Object[] objs = {taskList.get(i).getName(),
				"Edit"};
				((DefaultTableModel) tableModel).addRow(objs);

			}	
			for (int i = 0; i < taskList.size(); i++)
			{
				Object[] objs = {taskList.get(i).getName(),
				"Edit"};
				((DefaultTableModel) tableModel).addRow(objs);

			}
		}

		//JTable table = new JTable(tableModel);
		//table.setFillsViewportHeight(true);

		classHomePage.setTaskTable(tableModel);

		Close();
	}

	private void Close()
	{

		dialog.dispose();
	}

}
