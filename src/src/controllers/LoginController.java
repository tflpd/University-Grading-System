package controllers;

import models.Professor;
import models.Name;
import views.LoginView;

public class LoginController {
	
	private LoginView loginView ;
	
	public LoginController()
	{
		loginView = new LoginView();
		initController();
	}
	
	public void initController()
	{
		loginView.getSignInButton().addActionListener(l -> Login());
		loginView.getCancelButton().addActionListener(l -> CancelLogin());
	}
	
	private void Login()
	{
		
		Name name = new Name("Christine"," ", "Papapdakis");
		Professor prf = new Professor(1, name, "c@bu.edu", "1234");
		
		
		String id = loginView.getIdText().getText();
		String pwd = String.valueOf(loginView.getPwdText().getPassword());
		
		System.out.println("User id "+id +" "+pwd);
		
		if (prf.getEmail().equalsIgnoreCase(id) && pwd.equalsIgnoreCase(prf.getPassword()))
		{
			GradingController gC = new GradingController();
			
		}else
		{
			loginView.setMsgLabel("Wrong email or password");
		}
		// Get the user
		
		
	}
	
	private void CancelLogin()
	{
		
	}
	
}
