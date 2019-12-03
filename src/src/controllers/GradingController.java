package controllers;

import views.ClassHomePage;

public class GradingController {
	
	private ClassHomePage classHomePage;
	
	public GradingController()
	{
		initController();
	}
	
	private void initController()
	{
		classHomePage = new ClassHomePage("Math");
		
		
		
	}
}
