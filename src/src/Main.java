import controllers.ClassHomePageController;
import controllers.CourseStudentController;
import controllers.LoginController;
import models.ImportExcel;
import models.LoggedData;
import models.Student;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import views.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

    	LoggedData.InitData();
    	MainPanelView.initialize();
    	//ClassHomePageController cHPC = new ClassHomePageController(null);
    	LoginController lg = new LoginController();
        //LoginView loginView = new LoginView();
        //CourseListView courseListView = new CourseListView();
        //CreateCourseView createCourseView = new CreateCourseView();
        //ClassHomePage classHomePage = new ClassHomePage("Math");
        //SubTaskGrade subTaskGrade = new SubTaskGrade("hw1", 80, 80, 80);
        //CourseStudentView courseStudentView = new CourseStudentView("hw1", 80, 80, 80);

    	String filePath = "";
    	//CourseStudentController csc = new CourseStudentController();
		/*ImportExcel importExcel = new ImportExcel(filePath);
		for(Student student : importExcel.importE()){
			System.out.println(student.getBuID());
		}*/


    }
}