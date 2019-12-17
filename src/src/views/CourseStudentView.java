package views;

import models.LoggedData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.*;

public class CourseStudentView extends JPanel{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel centerPanel;
	JButton backButton;
	JButton homeButton;
	private JLabel courseLabel;
	private JLabel statisticLabel;
	private JButton addButton;
	private JButton saveButton;

	public CourseStudentView() {
		//frame = new JFrame("Course Student");
		//frame.setSize(1000, 1000);
		this.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		backButton = new JButton("Back");
		homeButton = new JButton("Home");
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(backButton);
		topPanel.add(homeButton);
		JLabel courseNameLabel = new JLabel(LoggedData.getSelectedCourse().toString(), JLabel.CENTER);
		courseNameLabel.setFont(new Font("", Font.PLAIN, 25));
		BorderLayout bl = new BorderLayout();
		topPanel.add(courseNameLabel, bl.EAST);
		this.add(topPanel, BorderLayout.NORTH);

		centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;

		//JLabel courseLabel = new JLabel(courseName + "'s Students");
		//JLabel statisticLabel = new JLabel("Statistic:   Mean: "+ mean + "   Median: " + median + "   " + "Standard Deviation: " + standardDeviation);
		courseLabel = new JLabel();
		statisticLabel = new JLabel();
		
		table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane(table);
		Dimension d = table.getPreferredSize();
		scrollPane.setPreferredSize(
				new Dimension(1000,250));
		gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		centerPanel.add(scrollPane, gc);
		
		//frame.getContentPane().repaint();

		addButton = new JButton("Add Students");
		saveButton = new JButton("Save the change");

		centerPanel.add(courseLabel, gc);
		gc.gridy = 1;
		centerPanel.add(statisticLabel, gc);

		gc.gridy = 3;
		JPanel bPanel = new JPanel(new FlowLayout());
		bPanel.add(addButton);
		bPanel.add(saveButton);

		centerPanel.add(bPanel, gc);




		this.add(centerPanel, BorderLayout.CENTER);

		//frame.setLocationRelativeTo(null);
		//frame.setResizable(false);
		//frame.setVisible(true);


	}

	public void setTable(TableModel dataModel) {



		table.setModel(dataModel);

		if (table.getColumn("Id") != null)
		{
			table.removeColumn(table.getColumn("Id"));
		}

	}

	public JButton getBackButton() {
		return backButton;
	}

	public JButton getHomeButton() {
		return homeButton;
	}

	public JTable getTable() {
		return table;
	}

	public void setCourseLabel(String courseLabel) {
		this.courseLabel.setText(courseLabel);
	}

	public void setStatisticLabel(String statisticLabel) {
		this.statisticLabel.setText(statisticLabel);
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

}


