package views;

import models.LoggedData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;

public class SubTaskGrade extends JPanel{

    //private JFrame frame;
    private DefaultTableModel tableModel;
    private JTable table;
    
    private JButton backButton ;
    private JButton homeButton ;
    private JComboBox sectionCombo;
    private JButton saveButton;

    public SubTaskGrade(String subtaskName, double mean, double median, double standardDeviation) {
        //frame = new JFrame("Course Home Page");
        //frame.setSize(1000, 1000);
        //frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        backButton = new JButton("Back");
        homeButton = new JButton("Home");
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        topPanel.add(homeButton);
        this.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        JLabel subtaskLabel = new JLabel(subtaskName + "'s Grades");
        JLabel sectionLabel = new JLabel("Sections: ");
        sectionCombo = new JComboBox();
        sectionLabel.setLabelFor(sectionCombo);
        JLabel statisticLabel = new JLabel("Statistic:   Mean: "+ String.format("%.2f", mean) +
                "   Median: " + String.format("%.2f", median)+ "   " + "Standard Deviation: "
                + String.format("%.2f", standardDeviation));
        String col[] = {"Students", "Points deducted", "Points scored", "Student status", "Comments", "Group ID", "%Score", "Bonus Points"};
        
        
        table = new JTable();	
		JScrollPane scrollPane = new JScrollPane(table);
		Dimension d = table.getPreferredSize();
		scrollPane.setPreferredSize(
				new Dimension(800,300));

        saveButton = new JButton("Save Changes");

        centerPanel.add(subtaskLabel, gc);
        gc.gridy = 1;
        JPanel combo = new JPanel();
        combo.setLayout(new FlowLayout(FlowLayout.LEFT));
        combo.add(sectionLabel);
        combo.add(sectionCombo);

        centerPanel.add(combo, gc);

        gc.gridy = 2;

        centerPanel.add(statisticLabel, gc);
        gc.gridy = 3;

        centerPanel.add(scrollPane, gc);
        gc.gridy = 4;
        centerPanel.add(saveButton, gc);

        this.add(centerPanel, BorderLayout.CENTER);

    }
    
    public void setTable(TableModel dataModel) {
		//this.table = table;

		//System.out.println("masuk ke table");
		
		table.setModel(dataModel);
		if (table.getColumn("Id") != null)
		{
			table.removeColumn(table.getColumn("Id"));
		}
	}

    public JComboBox getSectionCombo() {
        return sectionCombo;
    }

    public JButton getBackButton() {
		return backButton;
	}
	public JButton getHomeButton() {
		return homeButton;
	}

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTable getTable() {
        return table;
    }
}
