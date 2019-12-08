package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class LoggedData {
	
	private static Professor prof;
	private static GradingSystem grading;
	private static ArrayList<Course> activeCourseList;
	private static Course selectedCourse;
	private static Task selectedTask;

	private static SubTask selectedSubTask; 
	private static GradingSystem GS;

	private static SubTask selectedSubTask;
	public static int subTaskID = 1;


	public  static void  InitData()
	{
		//Professor
		Name name = new Name(0,"Christine"," ", "Papapdakis");
		prof = new Professor(0, name, "c@bu.edu", "1234");	
		
		//Student
		name = new Name(0,"Jone"," ", "Doe");		
		ArrayList<Student> studentList = new ArrayList<Student>();
		Student s = new Student(1, name, "doe.bu.edu", "1234567");
		studentList.add(s);
		
		
		CourseSection cc = new CourseSection(1,"Morning Section", studentList );
		ArrayList<CourseSection> ccList = new ArrayList<CourseSection>();
		ccList.add(cc);
		
		LocalDateTime startDate = LocalDateTime.now();
		
		
		ArrayList<Task> taskList = new ArrayList<Task>();
		Task t = new Task(1, "Exam", 70.0f);
		taskList.add(t);
		t = new Task(2, "Assignments", 30.0f);
		SubTask sT = new SubTask(1, studentList, "Assingment 1", startDate, startDate.plusDays(5), 100.f, 50.f, 10.f, "First Assignment", false);
		sT.setStudentsGrade(s, 70.f);
		t.addNewSubTask(sT);
		sT = new SubTask(2, studentList, "Assingment 2", startDate, startDate.plusDays(5), 100.f, 50.f, 10.f, "Second Assignment", false);
		sT.setStudentsGrade(s, 90.f);
		t.addNewSubTask(sT);
		taskList.add(t);
		
		
		CourseTemplate cT = new CourseTemplate(0,"CS591P1", "Fall", "2019", taskList);
		ArrayList<CourseTemplate> cTList = new ArrayList<CourseTemplate>();
		cTList.add(cT);

		CourseSection courseSection = new CourseSection(0, "Morning Section", studentList);
		ArrayList<CourseSection> courseSections = new ArrayList<>();
		courseSections.add(courseSection);
		
		activeCourseList = new ArrayList<Course>();

		Course c = new Course(0,"CS591P1","Fall", "2019", ccList, cT);
		activeCourseList.add(c);
		c = new Course(0,"CS591P1","Spring", "2020", ccList, cT);

		Course c = new Course(0,"CS591P1","Fall", "2019", courseSections, cT);
		activeCourseList.add(c);
		c = new Course(0,"CS591P1","Spring", "2020", courseSections, cT);

		activeCourseList.add(c);
		
		
				
		GS = new GradingSystem(1,prof, activeCourseList, cTList);
		
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

	public static GradingSystem getGradingSystem() {
		return GS;
	}

	public static void setGradingSystem(GradingSystem gS) {
		GS = gS;
	}
	
	
	

}
