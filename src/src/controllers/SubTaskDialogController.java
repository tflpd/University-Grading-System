package controllers;

import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;

import views.SubTaskDialog;
import views.TaskDialog;

public class SubTaskDialogController {
	
	private SubTaskDialog dialog;
	
	public SubTaskDialogController()
	{
		try {
	        dialog = new SubTaskDialog();
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
		
		Close();
	}
	
	private void Close()
	{
		
		dialog.dispose();
	}

}
