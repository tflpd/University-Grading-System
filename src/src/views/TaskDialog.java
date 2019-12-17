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
        setSize(700, 300);
        Dimension defD = new Dimension(250, 20);

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
        nameTf.setPreferredSize(defD);

        weight = 30;
        weightLabel = new JLabel("Weight of this task in final grade (%): " );
        weightTf = new JTextField(Double.toString(weight));
        weightTf.setPreferredSize(defD);

        saveButton = new JButton("Save changes");
        deleteButton = new JButton("Delete this Task");
        cancelButton = new JButton("Cancel");

        gc.anchor = GridBagConstraints.EAST;
        add(nameLabel, gc);
        
        gc.anchor = GridBagConstraints.WEST;
        gc.gridx = 1;
        add(nameTf, gc);
        
        gc.gridy = 1;
        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        add(weightLabel, gc);
        
        gc.gridx = 1;
        gc.anchor = GridBagConstraints.WEST;
        add(weightTf, gc);
        
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.EAST;
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

	public JTextField getNameTf() {
		return nameTf;
	}

	public void setNameTf(String nameTf) {
		this.nameTf.setText(nameTf);
	}

	public JTextField getWeightTf() {
		return weightTf;
	}

	public void setWeightTf(String weightTf) {
		this.weightTf.setText(weightTf);
	}

	public void hideDeleteButton()
    {
        this.deleteButton.setVisible(false);
    }
    
    
}
