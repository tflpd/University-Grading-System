package controllers;

import java.awt.Dialog.ModalityType;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.LoggedData;
import models.SubTask;
import models.Task;
import views.ClassHomePage;
import views.SubTaskDialog;

public class SubTaskDialogController {
	
	private SubTaskDialog dialog;
	private int subTaskID; // 0 means creating new task
	private ClassHomePage classHomePage;

	public SubTaskDialogController(ClassHomePage c)
	{
		this(c, 0);
	}
	
	public SubTaskDialogController(ClassHomePage c, int subTaskID)
	{
		this.subTaskID = subTaskID;
		classHomePage = c;
		try {
	        dialog = new SubTaskDialog();
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
		dialog.getDeleteButton().addActionListener(l -> Delete());

	}

	private void BindData()
	{
		System.out.println("Bind Data" + subTaskID);
		if (subTaskID != 0)
		{
			dialog.setNameTf(LoggedData.getSelectedSubTask().getName());
			dialog.setDocTf(LoggedData.getSelectedSubTask().getReleaseDate());
			dialog.setDueTf(LoggedData.getSelectedSubTask().getDateDue());
			dialog.setMaxScoreTf(String.valueOf(LoggedData.getSelectedSubTask().getTotalPointsAvailable()));
			dialog.setWeightTf(String.valueOf(LoggedData.getSelectedSubTask().getWeightInParentTask()));
			dialog.setBonusTf(String.valueOf(LoggedData.getSelectedSubTask().getMaxAvailableBonusPoints()));
			dialog.setGroupCheck(LoggedData.getSelectedSubTask().isGroupProject());

		}
	}
	
	private void SaveTask()
	{
		if (subTaskID != 0)  // modify
		{
			var taskList = LoggedData.getSelectedTask().getSubTasks();


			for (var t : taskList)
			{
				if (t.getId() == subTaskID)
				{
					t.setName(dialog.getNameTf().getText());
					t.setDateDue(dialog.getDateDueTf().getText());
					t.setTotalPointsAvailable(Float.parseFloat(dialog.getMaxScoreTf().getText()));
					t.setWeightInParentTask(Float.parseFloat(dialog.getWeightTf().getText()));
					t.setMaxAvailableBonusPoints(Float.parseFloat(dialog.getBonusTf().getText()));
					t.setGroupProject(dialog.getGroupCheck().isSelected());

					break;
				}
			}

		}
		else
		{
			//public SubTask(int id, ArrayList<Student > students, String name, LocalDateTime creationDate, String dateDue, Float totalPointsAvailable, Float weightInParentTask, Float bonusPoints, String otherComments, boolean groupProject)
			SubTask subTask = new SubTask(LoggedData.subTaskID++, LoggedData.getSelectedCourse().getAllStudents(), dialog.getNameTf().getText(), LocalDateTime.now(), dialog.getDateDueTf().getText(), Float.parseFloat(dialog.getMaxScoreTf().getText()), Float.parseFloat(dialog.getWeightTf().getText()), Float.parseFloat(dialog.getBonusTf().getText()), null, dialog.getGroupCheck().isSelected() );
			LoggedData.getSelectedTask().addNewSubTask(subTask);
			LoggedData.setSelectedSubTask(subTask);
		}
		UpdateTaskTable(LoggedData.getSelectedTask());
		Close();
	}
	
	private void Close()
	{
		
		dialog.dispose();
	}

	public void UpdateTaskTable(Task task)
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

	private void Delete()
	{
		if (subTaskID != 0)
		{
			var taskList = LoggedData.getSelectedTask().getSubTasks();

			for (var t : taskList)
			{
				if (t.getId() == subTaskID)
				{
					taskList.remove(t);
					break;
				}
			}
			UpdateTaskTable(LoggedData.getSelectedTask());
		}
		Close();
	}

}
