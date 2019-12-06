package controllers;

import java.awt.Dialog.ModalityType;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.LoggedData;
import models.Task;
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
		dialog.getDeleteButton().addActionListener(l -> DeleteTask());
		
	}

	private void SaveTask()
	{
		var taskList = LoggedData.getActiveCourseList().get(0).getTasks();

		Task task = new Task(dialog.getNameTf().getText(), Float.parseFloat(dialog.getWeightTf().getText()));
		taskList.add(task);
		
		UpdateTaskTable(taskList);
		Close();
	}

	private void Close()
	{
		dialog.dispose();
	}
	private void DeleteTask()
	{
		var taskList = LoggedData.getActiveCourseList().get(0).getTasks();
		Task task = new Task(dialog.getNameTf().getText(), Float.parseFloat(dialog.getWeightTf().getText()));
		taskList.remove(task);
		UpdateTaskTable(taskList);
		Close();
	}
	
	private void UpdateTaskTable(ArrayList<Task> taskList)
	{
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
			
		}
		classHomePage.setTaskTable(tableModel);
	}
}
