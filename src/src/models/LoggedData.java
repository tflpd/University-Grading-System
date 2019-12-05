package models;

import java.util.ArrayList;

public class LoggedData {
	
	private static Professor prof;
	private static GradingSystem grading;
	private static ArrayList<Course> activeCourseList;

	public  static void  InitData()
	{
		Name name = new Name("Christine"," ", "Papapdakis");
		prof = new Professor(1, name, "c@bu.edu", "1234");
		
		name = new Name("Jone"," ", "Doe");
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		Student s = new Student(1, name, "doe.bu.edu", "1234567");
		studentList.add(s);
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		Task t = new Task("Exam", 70.0f);
		taskList.add(t);
		t = new Task("Assignments", 30.0f);
		taskList.add(t);
		
		
		CourseTemplate cT = new CourseTemplate("CS591P1", "Fall", "2019", taskList);
		ArrayList<CourseTemplate> cTList = new ArrayList<CourseTemplate>();
		cTList.add(cT);
		
		activeCourseList = new ArrayList<Course>();
		Course c = new Course("CS591P1","Fall", "2019", null, cT);
		activeCourseList.add(c);
		c = new Course("CS591P1","Spring", "2020", null, cT);
		activeCourseList.add(c);
		
		
		
		GradingSystem GS = new GradingSystem(prof, activeCourseList, cTList);
		
	}

	public static Professor getProf() {
		return prof;
	}

	public static void setProf(Professor prof) {
		LoggedData.prof = prof;
	}

	public static GradingSystem getGrading() {
		return grading;
	}

	public static void setGrading(GradingSystem grading) {
		LoggedData.grading = grading;
	}

	public static ArrayList<Course> getActiveCourseList() {
		return activeCourseList;
	}

	public static void setActiveCourseList(ArrayList<Course> activeCourseList) {
		LoggedData.activeCourseList = activeCourseList;
	}
	
	

}
