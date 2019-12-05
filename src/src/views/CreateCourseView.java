package views;

import javax.swing.*;
import java.awt.*;

public class CreateCourseView extends JPanel {

	private JButton createButton;
	private JButton importButton;
	
    public CreateCourseView() {
        //JFrame frame = new JFrame("Grading System");
        //frame.setSize(600, 550);

        JLabel welcomeLabel = new JLabel("Course Creation", SwingConstants.CENTER);

        JLabel templateLabel = new JLabel("Course Templates", SwingConstants.CENTER);
        JComboBox templateList = new JComboBox();

        createButton = new JButton("Create Course");
        importButton = new JButton("Import Student");

        JTextField nameText =new JTextField("Course Name");
        JTextField semesterText =new JTextField("Semester");

        Panel infoPanel = new Panel();
        Panel buttonPanel = new Panel();

        
        infoPanel.setLayout(new GridLayout(4, 1, 30,5));
        infoPanel.add(templateLabel);
        infoPanel.add(templateList);
        infoPanel.add(nameText);
        infoPanel.add(semesterText);

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(createButton);
        buttonPanel.add(importButton);
        
        this.setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        this.add(welcomeLabel, gbc);
        this.add(infoPanel, gbc);
        this.add(buttonPanel, gbc);
    }

	public JButton getCreateButton() {
		return createButton;
	}

	

	public JButton getImportButton() {
		return importButton;
	}

	
    
    
}
