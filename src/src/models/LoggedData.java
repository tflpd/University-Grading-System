package models;

import java.util.ArrayList;

public class LoggedData {
	
	private static Professor prof;
	private static GradingSystem grading;
	private static ArrayList<Course> activeCourseList;
	private static Course selectedCourse;
	private static Task selectedTask;
	private static SubTask selectedSubTask;
	public static int subTaskID = 1;

	public  static void  InitData()
	{
		Name name = new Name(0,"Christine"," ", "Papapdakis");
		prof = new Professor(0, name, "c@bu.edu", "1234");		
		name = new Name(0,"Jone"," ", "Doe");		
		ArrayList<Student> studentList = new ArrayList<Student>();
		Student s = new Student(1, name, "doe.bu.edu", "1234567");
		studentList.add(s);
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		Task t = new Task(1, "Exam", 70.0f);
		taskList.add(t);
		t = new Task(2, "Assignments", 30.0f);
		taskList.add(t);
		
		
		CourseTemplate cT = new CourseTemplate(0,"CS591P1", "Fall", "2019", taskList);
		ArrayList<CourseTemplate> cTList = new ArrayList<CourseTemplate>();
		cTList.add(cT);

		CourseSection courseSection = new CourseSection(0, "Morning Section", studentList);
		ArrayList<CourseSection> courseSections = new ArrayList<>();
		courseSections.add(courseSection);
		
		activeCourseList = new ArrayList<Course>();
		Course c = new Course(0,"CS591P1","Fall", "2019", courseSections, cT);
		activeCourseList.add(c);
		c = new Course(0,"CS591P1","Spring", "2020", courseSections, cT);
		activeCourseList.add(c);
				
		GradingSystem GS = new GradingSystem(0,prof, activeCourseList, cTList);
		
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

	public static Course getSelectedCourse() {
		return selectedCourse;
	}

	public static void setSelectedCourse(Course selectedCourse) {
		LoggedData.selectedCourse = selectedCourse;
	}

	public static Task getSelectedTask() {
		return selectedTask;
	}

	public static void setSelectedTask(Task selectedTask) {
		LoggedData.selectedTask = selectedTask;
	}

	public static SubTask getSelectedSubTask() {
		return selectedSubTask;
	}

	public static void setSelectedSubTask(SubTask selectedSubTask) {
		LoggedData.selectedSubTask = selectedSubTask;
	}
	
	

}
