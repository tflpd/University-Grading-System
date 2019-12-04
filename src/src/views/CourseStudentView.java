package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CourseStudentView extends JPanel{

        private JFrame frame;
        private DefaultTableModel tableModel;
        private JTable table;
        private JPanel centerPanel;

        public CourseStudentView(String courseName, double mean, double median, double standardDeviation) {
            //frame = new JFrame("Course Student");
            //frame.setSize(1000, 1000);
            this.setLayout(new BorderLayout());

            JPanel topPanel = new JPanel();
            JButton backButton = new JButton("Back");
            JButton homeButton = new JButton("Home");
            topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            topPanel.add(backButton);
            topPanel.add(homeButton);
            this.add(topPanel, BorderLayout.NORTH);

            centerPanel = new JPanel();
            centerPanel.setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            gc.gridx = 0;
            gc.gridy = 0;
            gc.weightx = 1;
            gc.weighty = 1;
            gc.fill = GridBagConstraints.NONE;

            JLabel courseLabel = new JLabel(courseName + "'s Students");
            JLabel statisticLabel = new JLabel("Statistic:   Mean: "+ mean + "   Median: " + median + "   " + "Standard Deviation: " + standardDeviation);
            table = new JTable();

            JButton addButton = new JButton("Add Students");

            centerPanel.add(courseLabel, gc);
            gc.gridy = 1;
            centerPanel.add(statisticLabel, gc);

            gc.gridy = 3;

            centerPanel.add(addButton, gc);

            this.add(centerPanel, BorderLayout.CENTER);

            //frame.setLocationRelativeTo(null);
            //frame.setResizable(false);
            //frame.setVisible(true);


        }

    public void setTable(JTable table) {
        this.table = table;

        JScrollPane scrollPane = new JScrollPane(table);
        Dimension d = table.getPreferredSize();
        scrollPane.setPreferredSize(
                new Dimension(d.width,table.getRowHeight()*6+1));
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;
        centerPanel.add(scrollPane, gc);
        frame.getContentPane().repaint();
    }

}


