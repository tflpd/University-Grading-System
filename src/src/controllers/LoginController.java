package controllers;

import models.Professor;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import models.LoggedData;
import models.Name;
import views.LoginView;
import views.MainPanelView;

public class LoginController {
	
	private LoginView loginView ;
	private JPanel parentPanel;
	
	public LoginController()
	{
		loginView = new LoginView();
		
		parentPanel = MainPanelView.getParentPanel();
		parentPanel.removeAll();
		parentPanel.revalidate();
		parentPanel.repaint();
		parentPanel.add(loginView, BorderLayout.CENTER);
		
		
		initController();
	}
	
	public void initController()
	{
		loginView.getSignInButton().addActionListener(l -> Login());
		loginView.getCancelButton().addActionListener(l -> CancelLogin());
	}
	
	private void Login()
	{
		
		Name name = new Name(1, "Christine"," ", "Papapdakis");
	    Professor prf = LoggedData.getProf();
		String id = loginView.getIdText().getText();
		String pwd = String.valueOf(loginView.getPwdText().getPassword());
		System.out.println("User id "+id +" "+pwd);
		
		if (prf.getEmail().equalsIgnoreCase(id) && pwd.equalsIgnoreCase(prf.getPassword()))
		{
			CourseListController clC = new CourseListController();
			//loginView.
			
		}else
		{
			loginView.setMsgLabel("Wrong email or password");
		}	
	}
	
	private void CancelLogin()
	{
		
	}
	
}
