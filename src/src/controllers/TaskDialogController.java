package controllers;

import java.awt.Dialog.ModalityType;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.LoggedData;
import models.Task;
import views.ClassHomePage;
import views.TaskDialog;

public class TaskDialogController {

	private TaskDialog dialog; 
	private ClassHomePage classHomePage;
	private ClassHomePageController cHController;
	private int TaskId; // 0 means creating new task


	public TaskDialogController(ClassHomePage c, ClassHomePageController cr)
	{

		this(c,0, cr);

	}

	public TaskDialogController(ClassHomePage c, int id, ClassHomePageController cr)
	{
		cHController = cr;
		TaskId = id;
		classHomePage = c;
		try {
			dialog = new TaskDialog();
			if (TaskId == 0)
			{
				dialog.getDeleteButton().setVisible(false);
			}
			initController();
			dialog.setModalityType(ModalityType.APPLICATION_MODAL);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(classHomePage);
			dialog.setVisible(true);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	private void initController()
	{
		BindData();
		dialog.getSaveButton().addActionListener(l -> SaveTask());
		dialog.getCancelButton().addActionListener(l -> Close());
		dialog.getDeleteButton().addActionListener(l -> DeleteTask());

	}

	private void BindData()
	{
		System.out.println("Bind Data" + TaskId);
		if (TaskId != 0)
		{
			dialog.setNameTf(LoggedData.getSelectedTask().getName());
			dialog.setWeightTf(Float. toString(LoggedData.getSelectedTask().getWeightInFinalGrade()));
		}
	}

	private void SaveTask()
	{
		var taskList = LoggedData.getSelectedCourse().getTasks();
		Float totalWeight = 0.f;
		for (var t : taskList)
		{
			totalWeight = totalWeight+ t.getWeightInFinalGrade();
		}
		boolean isAllowed = true;

		if (TaskId != 0)
		{
			for (var t : taskList)
			{
				if (t.getId() == TaskId)
				{
					t.setName(dialog.getNameTf().getText());
					if ((totalWeight - t.getWeightInFinalGrade() + Float.parseFloat(dialog.getWeightTf().getText())) > 100)
					{
						isAllowed = false;
					}
					else {
						t.setWeightInFinalGrade(Float.parseFloat(dialog.getWeightTf().getText()));
					}
					if (isAllowed)
					{
						LoggedData.getDbManager().UpdateTask(t);
					}
					break;
				}
			}
		}
		else
		{
			if ((totalWeight + Float.parseFloat(dialog.getWeightTf().getText())) <= 100) {
				Task task = new Task(0, dialog.getNameTf().getText(), Float.parseFloat(dialog.getWeightTf().getText()));
				int id = LoggedData.getDbManager().addTask(task, LoggedData.getSelectedCourse().getCourseTemplate().getId());
				task.setId(id);
				LoggedData.setSelectedTask(task);
				LoggedData.getSelectedCourse().getTasks().add(task);

			}else
			{
				isAllowed = false;
			}
		}
		if (isAllowed) {
			//LoggedData.getSelectedTask().setId(LoggedData.getDbManager().addTask(LoggedData.getSelectedTask(), LoggedData.getSelectedCourse().getCourseTemplate().getId()));
		    UpdateTaskTable(LoggedData.getSelectedCourse().getTasks());
			Close();
		}else
		{
			String message = "\" Error\"\n"
				+ "The total weight is more than 100";

			JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void Close()
	{
		dialog.dispose();
	}
	private void DeleteTask()
	{
		if (TaskId != 0)
		{
			var taskList = LoggedData.getSelectedCourse().getTasks();

			for (var t : taskList)
			{
				if (t.getId() == TaskId)
				{
					taskList.remove(t);
					LoggedData.getDbManager().deleteTask(t);
					break;
				}
			}
			UpdateTaskTable(taskList);
			LoggedData.setSelectedTask(null);
			LoggedData.setSelectedSubTask(null);
		}
		Close();
	}

	private void UpdateTaskTable(ArrayList<Task> taskList)
	{

		cHController.fillTaskData();

	}


}
