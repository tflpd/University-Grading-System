package views;

import javax.swing.*;
import java.awt.*;

public class TaskDialog extends JDialog {
    String name;
    double weight;
    JLabel nameLabel;
    JLabel weightLabel;
    public JButton saveButton;
    public JButton deleteButton;
    public JButton cancelButton;
	JTextField nameTf;
    JTextField weightTf;

    public TaskDialog() {
        //super(parent, "Details", false);
        setSize(400, 300);

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        name = "Homework";
        nameLabel = new JLabel("Task's name: ");
        nameTf = new JTextField(name);

        weight = 0.3;
        weightLabel = new JLabel("Weight of this task in final grade: " );
        weightTf = new JTextField(Double.toString(weight));

        saveButton = new JButton("Save changes");
        deleteButton = new JButton("Delete this Task");
        cancelButton = new JButton("Cancel");

        add(nameLabel, gc);
        gc.gridx = 1;
        add(nameTf, gc);
        gc.gridy = 1;
        gc.gridx = 0;
        add(weightLabel, gc);
        gc.gridx = 1;
        add(weightTf, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        add(saveButton, gc);
        gc.gridy = 3;
        add(deleteButton, gc);
        gc.gridy = 4;
        add(cancelButton, gc);

    }
    
    public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(JButton deleteButton) {
		this.deleteButton = deleteButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

    public void setNameLabel(String name) {
        this.nameLabel = new JLabel(name);
    }

    public void setWeightLabel(String weight) {
        this.weightLabel = new JLabel("Weight of this task in final grade: " + weight);
    }
}
