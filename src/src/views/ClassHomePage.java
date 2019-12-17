package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
	private JButton homeButton;

	public ClassHomePage(String courseName) {

		taskDialog = new TaskDialog();
		this.setBorder(new EmptyBorder(10, 20, 10, 20));

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


		BorderLayout bl = new BorderLayout();
		homeButton = new JButton("Back Home");
		JPanel topPanel = new JPanel();

		topPanel.setLayout(bl);
		topPanel.add(homeButton, bl.WEST);

		deleteButton = new JButton("Delete this Class");
		topPanel.add(deleteButton, bl.EAST);
		//frame = new JFrame("Course Home Page");
		//frame.setSize(1000, 1000);

		//taskDialog = new TaskDialog(frame);
		//subTaskDialog = new SubTaskDialog(frame);

		JLabel courseNameLabel = new JLabel(courseName, JLabel.CENTER);
		courseNameLabel.setFont(new Font("", Font.PLAIN, 25));
		topPanel.add(courseNameLabel, bl.CENTER);

		leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		studentListButton = new JButton("Student List");
		topPanel.add(studentListButton, bl.SOUTH);

		//frame.setLayout(new FlowLayout(FlowLayout.LEFT));
		//frame.setLayout(new BorderLayout());
		this.setLayout(new BorderLayout());

		//frame.add(topPanel, BorderLayout.NORTH);
		this.add(topPanel, BorderLayout.NORTH);




		JLabel taskLabel = new JLabel("Tasks");
		//JLabel tipLabel = new JLabel("Please either choose one of the existing tasks to inspect it or create a new one");
		//String text = "Please either choose one of the existing tasks to inspect it or create a new one";

/*
		JTextArea textArea = new JTextArea(5, 40);
		textArea.setText(text);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		textArea.setBackground(UIManager.getColor("Label.background"));
		textArea.setFont(UIManager.getFont("Label.font"));
		textArea.setBorder(UIManager.getBorder("Label.border"));
*/
		creatButton = new JButton("Create new Task");


		leftPanel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;


		//leftPanel.add(studentListButton, gc);
		gc.gridy = 1;
		leftPanel.add(taskLabel, gc);
		gc.gridy = 2;
		//leftPanel.add(textArea, gc);
		//gc.gridy = 3;

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

		JScrollPane tableSP = new JScrollPane(TaskTable);
		//Dimension preferredSize = new Dimension(400, 600);
		tableSP.setPreferredSize(new Dimension(400,250 ));
		tableSP.revalidate();
		leftPanel.add(tableSP, gc);

		gc.gridy = 4;
		leftPanel.add(creatButton, gc);

		//frame.add(leftPanel, BorderLayout.WEST);
		this.add(leftPanel, BorderLayout.WEST);
		// right


		JLabel subtaskLabel = new JLabel("Sub Tasks");

		/*
		String text1 = "Please either choose one of the existing subtasks of the selected task or create a new one";

		JTextArea textArea1 = new JTextArea(5, 40);
		textArea1.setText(text1);
		textArea1.setWrapStyleWord(true);
		textArea1.setLineWrap(true);
		textArea1.setOpaque(false);
		textArea1.setEditable(false);
		textArea1.setFocusable(false);
		textArea1.setBackground(UIManager.getColor("Label.background"));
		textArea1.setFont(UIManager.getFont("Label.font"));
		textArea1.setBorder(UIManager.getBorder("Label.border"));


		 */
		creatSubButton = new JButton("Create new SubTask");

		rightPanel.setLayout(new GridBagLayout());

		GridBagConstraints gcr = new GridBagConstraints();
		gcr.gridx = 0;
		gcr.gridy = 0;
		gcr.weightx = 1;
		gcr.weighty = 1;
		gcr.fill = GridBagConstraints.NONE;


		//rightPanel.add(deleteButton, gcr);
		gcr.gridy = 1;
		rightPanel.add(subtaskLabel, gcr);
		gcr.gridy = 2;
		//rightPanel.add(textArea1, gcr);
		//gcr.gridy = 3;

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

		JScrollPane tableSP1 = new JScrollPane(subtaskTable);
		//Dimension preferredSize = new Dimension(400, 600);
		tableSP1.setPreferredSize(new Dimension(400,250 ));
		tableSP1.revalidate();
		rightPanel.add(tableSP1, gcr);

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

	public JTable getTaskTable() {
		return TaskTable;
	}

	public void setTaskTable(TableModel dataModel) {

		TaskTable.setModel(dataModel);
		if (TaskTable.getColumn("Id") != null)
		{
			TaskTable.removeColumn(TaskTable.getColumn("Id"));
		}

	}

	public void setSubTaskTable(TableModel dataModel) {


		subtaskTable.setModel(dataModel);
		if (subtaskTable.getColumn("Id") != null)
		{
			subtaskTable.removeColumn(subtaskTable.getColumn("Id"));
		}

	}


	public JButton getHomeButton() {
		return homeButton;
	}

	public JTable getSubtaskTable() {
		return subtaskTable;
	}
}
