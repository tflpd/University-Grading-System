package views;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanelView {
	
	private static  JPanel parentPanel;
	private static  JPanel headerPanel;
	private static JFrame frame;
	private static void InitiatePanelData(JFrame frame)
	{
		frame.setBounds(100, 100, 1050, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		parentPanel = new JPanel(new GridLayout());
		headerPanel = new JPanel();	
		
		headerPanel.setBackground(Color.GREEN);
		frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Welcome to Fancy Grading System ");
		headerPanel.add(lblNewLabel);
		FlowLayout flowLayouthead = (FlowLayout) headerPanel.getLayout();
		flowLayouthead.setVgap(50);
		flowLayouthead.setHgap(50);		
		frame.getContentPane().add(parentPanel, BorderLayout.CENTER);		
				
	}
	public static JPanel getParentPanel() {
		return parentPanel;
	}
	
	public static void initialize() {
		
		
        frame = new JFrame();
        frame.setVisible(true);
        MainPanelView.InitiatePanelData(frame);		
        //LoginController loginController = new LoginController();
        
	}
	
	
	public static void setParentPanel(JPanel panel)
	{		
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(panel, BorderLayout.CENTER);
	}
	
	

}
