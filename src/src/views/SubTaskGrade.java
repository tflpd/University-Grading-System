package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SubTaskGrade {

    private JFrame frame;
    private DefaultTableModel tableModel;
    private JTable table;

    public SubTaskGrade(String subtaskName, double mean, double median, double standardDeviation) {
        frame = new JFrame("Course Home Page");
        frame.setSize(1000, 1000);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JButton backButton = new JButton("Back");
        JButton homeButton = new JButton("Home");
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);
        topPanel.add(homeButton);
        frame.add(topPanel, BorderLayout.NORTH);

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
        JComboBox sectionCombo = new JComboBox();
        sectionLabel.setLabelFor(sectionCombo);
        JLabel statisticLabel = new JLabel("Statistic:   Mean: "+ mean + "   Median: " + median + "   " + "Standard Deviation: " + standardDeviation);
        String col[] = {"Students", "Points deducted", "Points scored", "Student status", "Comments", "Group ID", "%Score", "Bonus Points"};
        tableModel = new DefaultTableModel(col, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        JButton saveButton = new JButton("Save Changes");

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

        centerPanel.add(table, gc);
        gc.gridy = 4;
        centerPanel.add(saveButton, gc);

        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);





    }
}
