package MySql;


import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    static Connection con;

    public static void connect(){


        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/grading_system","admin","admin");
            //Statement stmt=con.createStatement();
            //ResultSet rs=stmt.executeQuery("select * from person");
            //while(rs.next())
            //    System.out.println(rs.getString(2));
        }catch(Exception e){ System.out.println(e);}
    }
    public static void close(){
        try {
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }

    public static void sqlExecute(String sql){
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        }
        catch(Exception e){ System.out.println(e);}
    }

    public static int AddExecute(String sql){
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            int autoIncKeyFromApi = 0;
            var rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            } else {

                // throw an exception from here
            }
            return autoIncKeyFromApi;
        }
        catch(Exception e){ System.out.println(e);}
        return 0;
    }

    /*
    * CREATE
    * */

    public static int addStudent(Student student){

        String sql = "INSERT INTO grading_system.Student ( first_name, last_name, email, buid) VALUES ( \'"+ student.getNameObject().getFirstName() +"\', \'"+ student.getNameObject().getSurname() +"\', \'" + student.getEmail() + "\', \'" + student.getBuID() + "\')";
        System.out.println(sql);
        return AddExecute(sql);
        //return student.getId();
    }

    public static int addTemplateCourse(CourseTemplate courseTemplate){
        String sql = "INSERT INTO grading_system.TemplateCourse (id, name, year, semester) VALUES (\'"+ courseTemplate.getId() + "\', \'"+ courseTemplate.getName() +"\', \'"+ courseTemplate.getYear() +"\', \'" + courseTemplate.getSemester() + "\')";
        System.out.println(sql);
        return AddExecute(sql);
    }

    public static void addCredential(Professor professor){
        String sql = "INSERT INTO grading_system.Credential(email, password) VALUES (\'"+ professor.getEmail() + "\', \'"+ professor.getPassword() +"\')";
        System.out.println(sql);
        sqlExecute(sql);

    }
    public static int addEnrollment(int studentId, boolean isWithdrawn, int courseSectionId, int courseId){
        String sql = "INSERT INTO grading_system.Enrollment (studentId, courseSectionId, courseId) VALUES (\'"+ studentId + "\', \'"+ courseSectionId +"\', \'"+ courseId +"\')";
        System.out.println(sql);
        return AddExecute(sql);

    }
    public static int addTask(Task ta, int courseTemplateId){
        System.out.println("Task Name "+ta.getWeightInFinalGrade().toString());
        String sql = "INSERT INTO grading_system.Task(name, templateCourseId, weight) VALUES (\'"+ ta.getName() +"\', \'"+ courseTemplateId +"\', \'" + ta.getWeightInFinalGrade() + "\')";
        //System.out.println(sql);
        //sqlExecute(sql);
        return AddExecute(sql);
    }



//);
    public static int addCourse(Course course, int templateCourseId, int professorId){
        String sql = "INSERT INTO grading_system.Course(id, `templateCourseId`, year, semester, name, professorId) VALUES " +
                "(\'"+ course.getId() + "\', \'"+ templateCourseId +"\', \'"+ course.getYear() +"\', \'" + course.getSemester() + "\', \'" + course.getName() + "\', \'"+ professorId +"\')";
        System.out.println(sql);
        //sqlExecute(sql);
        return AddExecute(sql);
    }

    public static int addProfessor(Professor professor, int credentialId){
        String sql = "INSERT INTO grading_system.Professor(id, first_name, last_name, email, credentialId) VALUES (\'"+ professor.getId() + "\', \'"+ professor.getName().getFirstName() +"\', \'"+ professor.getName().getSurname()+"\', \'"+ professor.getEmail()+ "\', \'" + credentialId + "\')";
        System.out.println(sql);
        sqlExecute(sql);
        return professor.getId();
    }

    public static int addSubtask(SubTask subTask){
        String sql = "INSERT INTO grading_system.SubTask(id, `weight`, name, totalPointsAvailable, releasedDate, dueDate, groupProject, maxAvailableBonusPoints) VALUES " +
                "(\'"+ subTask.getId() + "\', \'"+ subTask.getWeightInParentTask() +"\', \'"+ subTask.getName() +"\', \'" + subTask.getTotalPointsAvailable() + "\', \'" + subTask.getReleaseDate() + "\', \'"+ subTask.getDateDue() +"\', \'"+ subTask.isGroupProject() +"\', \'"+ subTask.getMaxAvailableBonusPoints() +"\')";
        System.out.println(sql);
        sqlExecute(sql);
        return subTask.getId();
    }

    public static int addGrade(Grade grade, int subTaskId, int studentId){

        String sql = "INSERT INTO grading_system.Grade(id, subTaskId, studentId, absolutePointsScored) VALUES (\'"+ grade.getId() + "\', \'"+ subTaskId +"\', \'"+ studentId +"\', \'" + grade.getAbsolutePointsScored() + "\')";
        System.out.println(sql);
        sqlExecute(sql);
        return grade.getId();
    }

    public static int addCourseSection(CourseSection courseSection, int courseId){

        String sql = "INSERT INTO grading_system.CourseSection(id, name, courseId) VALUES (\'"+ courseSection.getId() + "\', \'"+ courseSection.getName() +"\', \'"+ courseId +"\')";
        System.out.println(sql);
        sqlExecute(sql);
        return courseSection.getId();
    }


    /*
     * READ
     * */

    public static Student readStudentById(int id){
        Student temp = null;

        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Student WHERE id = \'"+id +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                temp = new Student(rs.getInt("id"), new Name(rs.getString("first_name"), rs.getString("last_name")), rs.getString("email"), rs.getString("buid"), rs.getBoolean("isWithdrawn"));
            }
        }
        catch(Exception e){ System.out.println(e);}
        return temp;
    }
    public static List<Student> readAllStudents(){
        List<Student> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Student";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            Student temp = null;
            while(rs.next()) {
                temp = new Student(rs.getInt("id"), new Name(rs.getString("first_name"), rs.getString("last_name")), rs.getString("email"), rs.getString("buid"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    public List<CourseTemplate> readAllCourseTemplate(){
        List<CourseTemplate> listTemplate = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from TemplateCourse";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            CourseTemplate temp = null;
            while(rs.next()) {
                ArrayList<Task> tasks = readTasksByTemplateCourseId(rs.getInt("id"), null);
                temp = new CourseTemplate(rs.getInt("id"), rs.getString("name"), rs.getString("semester"), rs.getString("year"), tasks);
                listTemplate.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return listTemplate;
    }

    public static CourseTemplate readCourseTemplateById(int id){
        CourseTemplate temp = null;
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from TemplateCourse WHERE id = \'"+id +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                ArrayList<Task> tasks = readTasksByTemplateCourseId(rs.getInt("id"), null);
                temp = new CourseTemplate(rs.getInt("id"), rs.getString("name"), rs.getString("semester"), rs.getString("year"), tasks);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return temp;
    }

    public static Course readCourseById(int id, ArrayList<CourseSection> courseSections, CourseTemplate courseTemplate){
        Course temp = null;

        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Course WHERE id = \'"+id +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                temp = new Course(rs.getInt("id"), rs.getString("name"), rs.getString("semester"), rs.getString("year"), courseSections, courseTemplate);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return temp;
    }

    public static Course readCourseByCourseTemplateId(int courseTemplateId){

        Course course = null;
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Course WHERE templateCourseId ="+ courseTemplateId ;
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            Course temp = null;
            while(rs.next()) {
                ArrayList<CourseSection> courseSectionsList = readCourseSectionsByCourseId(rs.getInt("id"));
                CourseTemplate courseTemplate = readCourseTemplateById(courseTemplateId);
                temp = new Course(rs.getInt("id"), rs.getString("name"), rs.getString("semester"), rs.getString("year"), courseSectionsList, courseTemplate);
                return temp;
            }
        }
        catch(Exception e){ System.out.println(e);}
        return null;
    }

    public static List<Course> readCourseByCourseProfId(int profId){
        ArrayList<CourseSection> courseSections = new ArrayList<>();
        List<Course> courseList = new ArrayList<Course>();
        courseSections =readALLSections();

        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Course WHERE professorId ="+ profId ;
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            Course temp = null;
            while(rs.next()) {
                ArrayList<CourseSection> courseSectionsList = readCourseSectionsByCourseId(rs.getInt("id"));
                CourseTemplate courseTemplate = readCourseTemplateById(rs.getInt("templateCourseId"));
                temp = new Course(rs.getInt("id"), rs.getString("name"), rs.getString("semester"), rs.getString("year"), courseSectionsList, courseTemplate);
                courseList.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return courseList;
    }

    public static ArrayList<Course> readAllCourses(){

        ArrayList<Course> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Course";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            Course temp = null;
            while(rs.next()) {
                ArrayList<CourseSection> courseSectionsList = readCourseSectionsByCourseId(rs.getInt("id"));
                CourseTemplate courseTemplate = readCourseTemplateById(rs.getInt("templateCourseId"));
                temp = new Course(rs.getInt("id"), rs.getString("name"), rs.getString("semester"), rs.getString("year"), courseSectionsList, courseTemplate);
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    public static String readCredentialById(int id){
        String temp = null;
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Credential WHERE id = \'"+id +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                temp = rs.getString("password");
            }
        }
        catch(Exception e){ System.out.println(e);}
        return temp;
    }


    public static List<Professor> readAllProfessors(){
        List<Professor> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Professor";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            Professor temp = null;
            while(rs.next()) {

                String password = readCredentialById(rs.getInt("credentialId"));

                temp = new Professor(rs.getInt("id"), new Name(rs.getString("first_name"), rs.getString("last_name")), rs.getString("email"), password);
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    public static Professor readProfessorById(int id){
        Professor temp = null;

        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Professor WHERE id = \'"+id +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                String password = readCredentialById(rs.getInt("credentialId"));
                temp = new Professor(rs.getInt("id"), new Name(rs.getString("first_name"), rs.getString("last_name")), rs.getString("email"), password);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return temp;
    }


    public static ArrayList<Student> readEnrollmentsByCourseSectionId(int courseSectionId){
        ArrayList<Student> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Enrollment WHERE courseSectionId = " + courseSectionId;
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                int studentId = rs.getInt("studentId");
                Student student = readStudentById(studentId);
                list.add(student);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    public static ArrayList<CourseSection> readALLSections(){
        ArrayList<CourseSection> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from CourseSection";
            System.out.println(sql);
            CourseSection cC = null;
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                //int studentId = rs.getInt("studentId");
                cC = new CourseSection(rs.getInt("id"), rs.getString("name"));
                list.add(cC);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }





    // To get course section of the course and to get students



//    public ArrayList<CourseTemplate> readAllCourseTemplates(){
//        ArrayList<CourseTemplate> list = new ArrayList<>();
//        try {
//            Statement stmt=con.createStatement();
//            String sql = "select * from Task TemplateCourse";
//            System.out.println(sql);
//            ResultSet rs=stmt.executeQuery(sql);
//            CourseTemplate temp = null;
//            while(rs.next()) {
//                Course course = readCourseByCourseTemplateId(rs.getInt("id"));
//                ArrayList<Student> students = course.getAllStudents();
//                ArrayList<Task> tasks = readTasksByTemplateCourseId(rs.getInt("id"), students);
//                temp = new CourseTemplate(rs.getInt("id"), rs.getString("name"), rs.getString("semester"), rs.getString("year"), tasks);
//                list.add(temp);
//            }
//        }
//        catch(Exception e){ System.out.println(e);}
//        return list;
//    }

    public static boolean readGradingSystem(String email, String pswd){
        Professor professor = null;
        boolean isLoggedIn = false;
        var profList = readAllProfessors();
        for (var pr : profList)
        {
            if (pr.getEmail().equalsIgnoreCase(email) && pswd.equalsIgnoreCase(pr.getPassword()))
            {
                professor = pr;
                isLoggedIn = true;
                break;
            }
        }
        if (isLoggedIn) {
            LoadGradingSystem(professor);
        }
        return isLoggedIn;
    }

    public static void LoadGradingSystem(Professor professor)
    {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<CourseTemplate> templatesList = new ArrayList<>();
        ArrayList<CourseSection> courseSections = new ArrayList<>();

        courses = new ArrayList<Course>(readCourseByCourseProfId(professor.getId()));
        try {
            Statement stmt = con.createStatement();
            String sql = "select * from TemplateCourse";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            CourseTemplate tmpCourseTemplate = null;
            while (rs.next()) {
                ArrayList<Task> tasks = readTasksByTemplateCourseId(rs.getInt("id"), students);
                tmpCourseTemplate = new CourseTemplate(rs.getInt("id"), rs.getString("name"),
                        rs.getString("semester"), rs.getString("year"), tasks);
                templatesList.add(tmpCourseTemplate);

            }
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        }
        LoggedData.setGradingSystem(new GradingSystem(0, professor, courses, templatesList));
        LoggedData.setProf(professor);
    }

    public static ArrayList<CourseSection> readCourseSectionsByCourseId(int courseId){
        ArrayList<CourseSection> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from CourseSection";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            CourseSection temp = null;
            while(rs.next()) {

                //ArrayList<Student> studentList = readEnrollmentsByCourseSectionId(rs.getInt("id"));
                ArrayList<Student> studentList = readEnrollmentsByCourseId(rs.getInt("id"), courseId);
                if (studentList.size() > 0) {
                    temp = new CourseSection(rs.getInt("id"), rs.getString("name"), studentList);
                    list.add(temp);
                }
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    public static ArrayList<Student> readEnrollmentsByCourseId(int courseSectionId, int courseId){
        ArrayList<Student> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Enrollment WHERE courseSectionId = " + courseSectionId +" and courseId = "+courseId;
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                int studentId = rs.getInt("studentId");
                Student student = readStudentById(studentId);
                list.add(student);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    public static GradingSystem readGradingSystem(){
        ArrayList<CourseTemplate> templatesList = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<Course>();
        Professor professor = readAllProfessors().get(0);
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from TemplateCourse";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            CourseTemplate tmpCourseTemplate = null;
            while(rs.next()) {
                Course tmpCourse = readCourseByCourseTemplateId(rs.getInt("id"));
                ArrayList<Student> students = tmpCourse.getAllStudents();
                ArrayList<Task> tasks = readTasksByTemplateCourseId(rs.getInt("id"), students);
                tmpCourseTemplate = new CourseTemplate(rs.getInt("id"), rs.getString("name"), rs.getString("semester"), rs.getString("year"), tasks);
                tmpCourse.setCourseTemplate(tmpCourseTemplate);
                courses.add(tmpCourse);
                templatesList.add(tmpCourseTemplate);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return new GradingSystem(0, professor, courses, templatesList);
    }

    public static ArrayList<Task> readTasksByTemplateCourseId(int TemplateCourseId, ArrayList<Student> students){
        ArrayList<Task> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Task WHERE templateCourseId = \'"+ TemplateCourseId +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            Task temp = null;
            while(rs.next()) {
                ArrayList<SubTask> subTasks = readSubTasksByTaskId(rs.getInt("id"), students); //
                System.out.println("Task Id ="+rs.getInt("id"));
                temp = new Task(rs.getInt("id"), rs.getString("name"), (float)rs.getDouble("weight"), subTasks);
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }


    public static ArrayList<SubTask> readSubTasksByTaskId(int TaskId, ArrayList<Student> students){
        ArrayList<SubTask> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from SubTask WHERE taskId = \'"+ TaskId +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            SubTask temp = null;
            while(rs.next()) {
                temp = new SubTask(rs.getInt("id"), students, rs.getString("name"), rs.getTimestamp("releasedDate").toLocalDateTime(), rs.getDate("dueDate").toString(), rs.getFloat("totalPointsAvailable"), (float)rs.getDouble("weight"), rs.getFloat("maxAvailableBonusPoints"), rs.getString("comment"), rs.getBoolean("groupProject"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }



    public static Grade readGradeById(int id){
        Grade temp = null;
        //public Grade(int id, Student student, Float absolutePointsScored, String comment) {
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Grade WHERE id = \'"+id +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                Student student = readStudentById(rs.getInt("studentId"));
                temp = new Grade(rs.getInt("id"), student, rs.getFloat("absolutePointsScored"), rs.getString("comment"));
            }
        }
        catch(Exception e){ System.out.println(e);}
        return temp;
    }

    /*
     * UPDATE
     * */
    public static void UpdateTask(Task task)
    {
      String sql =   "UPDATE Task SET name = "+task.getName()+", weight = "+task.getWeightInFinalGrade()+" WHERE id = "+task.getId();
      sqlExecute(sql);
    }

    public static void UpdateStudent(Student student)
    {
        String sql =   "UPDATE Student SET isWithdrawn = "+student.isWithdrawn()+" WHERE id = "+student.getId();
        sqlExecute(sql);
    }

    /*
     * DELETE
     * */
    public static void deleteCourseById(int id){
        String sql = "DELETE FROM bank_atm.Course WHERE id = \'"+ id +"\'";
        sqlExecute(sql);
    }
    public static void deleteEnrollmentByStudentId(int studentId){
        String sql = "DELETE FROM bank_atm.Enrollment WHERE studentId = \'"+ studentId +"\'";
        sqlExecute(sql);
    }
    public static void deleteEnrollmentByCourseSectionId(int courseSectionId){
        String sql = "DELETE FROM bank_atm.Enrollment WHERE courseSectionId = \'"+ courseSectionId +"\'";
        sqlExecute(sql);
    }
    public static void deleteCourses(){
        String sql = "DELETE FROM grading_system.Course;";
        sqlExecute(sql);
    }

    public static void deleteStudentById(int id){
        String sql = "DELETE FROM grading_system.Student WHERE id = \'"+ id +"\'";
        sqlExecute(sql);
    }
    public static void deleteStudents(){
        String sql = "DELETE FROM grading_system.Student";
        sqlExecute(sql);
    }

    public void deleteTasks(List<Task> tasks){
        for (var t : tasks)
        {
            deleteTask(t);
        }


    }

    public void deleteTask(Task task){
        if (task.getSubTasks() != null)
        {
            deleteSubtasks(task.getSubTasks());
        }
        String sql = "DELETE FROM grading_system.Task where id = \'"+task.getId()+"\'";
        sqlExecute(sql);

    }

    public void deleteSubtasks(List<SubTask> subTaskList){
        if (subTaskList != null)
        {
            for(var sT : subTaskList)
            {
                deleteSubtask(sT);
            }
        }
    }

    public void deleteSubtask(SubTask subTask){
        String sql = "DELETE FROM grading_system.SubTask where id = \'"+subTask.getId()+"\'";
        sqlExecute(sql);
    }

    public void deleteGrade(int gradeId){
        String sql = "DELETE FROM grading_system.Grade where id = \'"+gradeId+"\'";
        sqlExecute(sql);
    }

    public void deleteEnrollment(int courseId, int studentId){
        String sql = "DELETE FROM grading_system.Enrollment where courseId = \'"+courseId+"\' and studentId = \'"+studentId+"\'";
        System.out.println(sql);
        sqlExecute(sql);
    }

    public void deleteCourse(Course course)
    {
        if (course.getTasks() != null)
        {
            deleteTasks(course.getTasks());
        }
        if (course.getAllStudents() != null)
        {
            for (var s : course.getAllStudents())
            {
                deleteEnrollment(course.getId(), s.getId());
            }
        }
        //if (course.getCourseSections().get(i).getStudents().gra)

        String sql = "DELETE FROM grading_system.Course where id = \'"+course.getId()+"\'";
        sqlExecute(sql);
    }

    public void deleteGradeS(List<Grade> grades)
    {
      for (var g : grades)
      {
          deleteGrade(g);
      }
    }

    public void deleteGrade(Grade grade)
    {
        String sql = "DELETE FROM grading_system.Grade where id = \'"+grade.getId()+"\'";
        sqlExecute(sql);
    }




}
