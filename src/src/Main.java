import controllers.ClassHomePageController;
import controllers.LoginController;
import models.LoggedData;
import views.*;

public class Main {
    public static void main(String[] args) {

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
    }
}