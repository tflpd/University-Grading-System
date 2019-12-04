package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClassHomePage extends JPanel {

    private JTable TaskTable;
   
    private JPanel leftPanel;
    private DefaultTableModel taskTableModel;
    private DefaultTableModel subtaskTableModel;
    private JTable subtaskTable;
    private JPopupMenu jPopupMenu;
    private TaskDialog taskDialog;
    private SubTaskDialog subTaskDialog;
    private JPopupMenu rightJPopupMenu;
    private JButton studentListButton;
    private JButton creatButton;
    private JButton deleteButton;
    private JButton creatSubButton;

    public ClassHomePage(String courseName) {



        jPopupMenu = new JPopupMenu();
        JMenuItem inspectItem = new JMenuItem("Inspect");
        jPopupMenu.add(inspectItem);
        inspectItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = TaskTable.getSelectedRow();
                taskDialog.setVisible(true);
            }
        });

        rightJPopupMenu = new JPopupMenu();
        JMenuItem chooseItem = new JMenuItem("Choose");
        rightJPopupMenu.add(chooseItem);
        chooseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = subtaskTable.getSelectedRow();
                subTaskDialog.setVisible(true);
            }
        });



        JButton homeButton = new JButton("Back Home");
        JPanel topPanel = new JPanel();

        topPanel.setLayout(new BorderLayout());
        topPanel.add(homeButton, BorderLayout.WEST);


        //frame = new JFrame("Course Home Page");
        //frame.setSize(1000, 1000);

        //taskDialog = new TaskDialog(frame);
        //subTaskDialog = new SubTaskDialog(frame);

        JLabel courseNameLabel = new JLabel(courseName, JLabel.CENTER);
        topPanel.add(courseNameLabel, BorderLayout.CENTER);

        leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        //frame.setLayout(new FlowLayout(FlowLayout.LEFT));
        //frame.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout());

        //frame.add(topPanel, BorderLayout.NORTH);
        this.add(topPanel, BorderLayout.NORTH);



        studentListButton = new JButton("Student List");
        JLabel taskLabel = new JLabel("Tasks");
        JLabel tipLabel = new JLabel("Please either choose one of the existing tasks to inspect it or create a new one");
        creatButton = new JButton("Create new Task");


        leftPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;


        leftPanel.add(studentListButton, gc);
        gc.gridy = 1;
        leftPanel.add(taskLabel, gc);
        gc.gridy = 2;
        leftPanel.add(tipLabel, gc);
        gc.gridy = 3;

        String col[] = {"Task"};
        taskTableModel = new DefaultTableModel(col, 2);
        TaskTable = new JTable(taskTableModel);
        TaskTable.addMouseListener(new MouseAdapter() {
            @Override

            public void mousePressed(MouseEvent e) {
                int row = TaskTable.rowAtPoint(e.getPoint());

                TaskTable.getSelectionModel().setSelectionInterval(row, row);

                if (e.getButton() == MouseEvent.BUTTON3) {
                    jPopupMenu.show(TaskTable, e.getX(), e.getY());
                }
            }
        });
        TaskTable.setFillsViewportHeight(true);
        leftPanel.add(TaskTable, gc);

        gc.gridy = 4;
        leftPanel.add(creatButton, gc);

        //frame.add(leftPanel, BorderLayout.WEST);
        this.add(leftPanel, BorderLayout.WEST);
    // right

        deleteButton = new JButton("Delete this Class");
        JLabel subtaskLabel = new JLabel("Sub Tasks");
        JLabel tipRLabel = new JLabel("Please either choose one of the existing subtasks of the selected task or create a new one");
        creatSubButton = new JButton("Create new SubTask");

        rightPanel.setLayout(new GridBagLayout());

        GridBagConstraints gcr = new GridBagConstraints();
        gcr.gridx = 0;
        gcr.gridy = 0;
        gcr.weightx = 1;
        gcr.weighty = 1;
        gcr.fill = GridBagConstraints.NONE;


        rightPanel.add(deleteButton, gcr);
        gcr.gridy = 1;
        rightPanel.add(subtaskLabel, gcr);
        gcr.gridy = 2;
        rightPanel.add(tipRLabel, gcr);
        gcr.gridy = 3;

        String colr[] = {"Subtask"};
        subtaskTableModel = new DefaultTableModel(colr, 2);
        subtaskTable = new JTable(subtaskTableModel);
        subtaskTable.addMouseListener(new MouseAdapter() {
            @Override

            public void mousePressed(MouseEvent e) {
                int row = subtaskTable.rowAtPoint(e.getPoint());

                subtaskTable.getSelectionModel().setSelectionInterval(row, row);

                if (e.getButton() == MouseEvent.BUTTON3) {
                    rightJPopupMenu.show(subtaskTable, e.getX(), e.getY());
                }
            }
        });
        subtaskTable.setFillsViewportHeight(true);
        rightPanel.add(subtaskTable, gcr);

        gcr.gridy = 4;
        rightPanel.add(creatSubButton, gcr);

        //frame.add(rightPanel, BorderLayout.EAST);
        this.add(rightPanel, BorderLayout.EAST);

        //frame.setLocationRelativeTo(null);
        //frame.setResizable(false);
        //frame.setVisible(true);




    }

    public DefaultTableModel getTaskTableModel() {
        return taskTableModel;
    }

	public JButton getStudentListButton() {
		return studentListButton;
	}

	public JButton getCreatButton() {
		return creatButton;
	}

	public JButton getDeleteButton() {
		return deleteButton;
	}

	public JButton getCreatSubButton() {
		return creatSubButton;
	}

	
    
    
    
    
}
