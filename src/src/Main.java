import controllers.ClassHomePageController;
import controllers.CourseStudentController;
import controllers.LoginController;
import models.ImportExcel;
import models.LoggedData;
import models.Student;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import views.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException {

    	LoggedData.InitData();
    	MainPanelView.initialize();
    	//ClassHomePageController cHPC = new ClassHomePageController(null);
    	//LoginController lg = new LoginController();
        //LoginView loginView = new LoginView();
        //CourseListView courseListView = new CourseListView();
        //CreateCourseView createCourseView = new CreateCourseView();
        //ClassHomePage classHomePage = new ClassHomePage("Math");
        //SubTaskGrade subTaskGrade = new SubTaskGrade("hw1", 80, 80, 80);
        //CourseStudentView courseStudentView = new CourseStudentView("hw1", 80, 80, 80);

    	CourseStudentController csc = new CourseStudentController();


		String filePath = "C:\\Users\\qqq58\\Desktop\\BU Study\\CS 591 P1 JAVA OO\\Final Project\\University-Grading-System\\StudentList.xlsx";
		ImportExcel importExcel = new ImportExcel(filePath);
		for(Student student : importExcel.importE()){
			System.out.println(student.getBuID());
		}


    }
}