package MySql;


import models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    Connection con;

    public void connect(){


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
    public void close(){
        try {
            con.close();
        }
        catch(Exception e){ System.out.println(e);}
    }

    public void sqlExecute(String sql){
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        }
        catch(Exception e){ System.out.println(e);}
    }

    /*
    * CREATE
    * */

    public void addStudent(Student student){

        String sql = "INSERT INTO grading_system.Student (id, first_name, last_name, email, buid) VALUES (\'"+ student.getId() + "\', \'"+ student.getNameObject().getFirstName() +"\', \'"+ student.getNameObject().getSurname() +"\', \'" + student.getEmail() + "\', \'" + student.getBuID() + "\')";
        System.out.println(sql);
        sqlExecute(sql);
    }

    public void addTemplateCourse(CourseTemplate courseTemplate){
        String sql = "INSERT INTO grading_system.TemplateCourse (id, name, year, semester) VALUES (\'"+ courseTemplate.getId() + "\', \'"+ courseTemplate.getName() +"\', \'"+ courseTemplate.getYear() +"\', \'" + courseTemplate.getSemester() + "\')";
        System.out.println(sql);
        sqlExecute(sql);
    }

    public void addCredential(Professor professor){
        String sql = "INSERT INTO grading_system.Credential(email, password) VALUES (\'"+ professor.getEmail() + "\', \'"+ professor.getPassword() +"\')";
        System.out.println(sql);
        sqlExecute(sql);
    }
    public void addEnrollment(int studentId, boolean isWithdrawn, int courseSectionId){
        String sql = "INSERT INTO grading_system.Enrollment (studentId, courseSectionId) VALUES (\'"+ studentId + "\', \'"+ courseSectionId +"\')";
        System.out.println(sql);
        sqlExecute(sql);

    }
    public void addTask(Task task, int courseTemplateId){
        String sql = "INSERT INTO grading_system.Task(id, name, templateCourseId, weight) VALUES (\'"+ task.getId() + "\', \'"+ task.getName() +"\', \'"+ courseTemplateId +"\', \'" + task.getWeightInFinalGrade() + "\')";
        System.out.println(sql);
        sqlExecute(sql);
    }



//);
    public void addCourse(Course course, int templateCourseId, int professorId){
        String sql = "INSERT INTO grading_system.Task(id, `templateCourseId`, year, semester, name, professorId) VALUES " +
                "(\'"+ course.getId() + "\', \'"+ templateCourseId +"\', \'"+ course.getYear() +"\', \'" + course.getSemester() + "\', \'" + course.getName() + "\', \'"+ professorId +"\')";
        System.out.println(sql);
        sqlExecute(sql);
    }

    public void addProfessor(Professor professor, int credentialId){
        String sql = "INSERT INTO grading_system.Task(id, first_name, last_name, email, credentialId) VALUES (\'"+ professor.getId() + "\', \'"+ professor.getName().getFirstName() +"\', \'"+ professor.getName().getSurname()+"\', \'"+ professor.getEmail()+ "\', \'" + credentialId + "\')";
        System.out.println(sql);
        sqlExecute(sql);
    }

    public void addSubtask(SubTask subTask){
        String sql = "INSERT INTO grading_system.Task(id, `weight`, name, totalPointsAvailable, releasedDate, dueDate, groupProject, maxAvailableBonusPoints) VALUES " +
                "(\'"+ subTask.getId() + "\', \'"+ subTask.getWeightInParentTask() +"\', \'"+ subTask.getName() +"\', \'" + subTask.getTotalPointsAvailable() + "\', \'" + subTask.getReleaseDate() + "\', \'"+ subTask.getDateDue() +"\', \'"+ subTask.isGroupProject() +"\', \'"+ subTask.getMaxAvailableBonusPoints() +"\')";
        System.out.println(sql);
        sqlExecute(sql);
    }

    public void addGrade(Grade grade, int subTaskId, int studentId){

        String sql = "INSERT INTO grading_system.Grade(id, subTaskId, studentId, absolutePointsScored) VALUES (\'"+ grade.getId() + "\', \'"+ subTaskId +"\', \'"+ studentId +"\', \'" + grade.getAbsolutePointsScored() + "\')";
        System.out.println(sql);
        sqlExecute(sql);

    }

    public void addCourseSection(CourseSection courseSection, int courseId){

        String sql = "INSERT INTO grading_system.CourseSection(id, name, courseId) VALUES (\'"+ courseSection.getId() + "\', \'"+ courseSection.getName() +"\', \'"+ courseId +"\')";
        System.out.println(sql);
        sqlExecute(sql);

    }


    /*
     * READ
     * */

    public Student readStudentById(int id){
        Student temp = null;

        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Student WHERE id = \'"+id +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                temp = new Student(rs.getInt("id"), new Name(rs.getString("first_name"), rs.getString("last_name")), rs.getString("email"), rs.getString("buid"));
            }
        }
        catch(Exception e){ System.out.println(e);}
        return temp;
    }
    public List<Student> readAllStudents(){
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
                temp = new CourseTemplate(rs.getInt("id"), rs.getString("name"), rs.getString("semester"), rs.getString("year"));
                listTemplate.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return listTemplate;
    }

    public CourseTemplate readCourseTemplateById(int id){
        CourseTemplate temp = null;
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from TemplateCourse WHERE id = \'"+id +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                temp = new CourseTemplate(rs.getInt("id"), rs.getString("name"), rs.getString("semester"), rs.getString("year"));
            }
        }
        catch(Exception e){ System.out.println(e);}
        return temp;
    }



    public Course readCourseById(int id, ArrayList<CourseSection> courseSections, CourseTemplate courseTemplate){
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

    public Course readCourseByCourseTemplateId(int courseTemplateId){

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
    public ArrayList<Course> readAllCourses(){

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

    public String readCredentialById(int id){
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



    public List<Professor> readAllProfessors(){

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

    public Professor readProfessorById(int id){
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


    public ArrayList<Student> readEnrollmentsByCourseSectionId(int courseSectionId){
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

    public ArrayList<CourseSection> readCourseSectionsByCourseId(int courseId){
        ArrayList<CourseSection> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from CourseSection";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            CourseSection temp = null;
            while(rs.next()) {

                ArrayList<Student> studentList = readEnrollmentsByCourseSectionId(rs.getInt("id"));
                temp = new CourseSection(rs.getInt("id"), rs.getString("name"), studentList);
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

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

    public GradingSystem readGradingSystem(){
        ArrayList<CourseTemplate> templatesList = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<Course>();
        Professor professor = readAllProfessors().get(0);
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Task TemplateCourse";
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

    public ArrayList<Task> readTasksByTemplateCourseId(int TemplateCourseId, ArrayList<Student> students){
        ArrayList<Task> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Task WHERE templateCourseId = \'"+ TemplateCourseId +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            Task temp = null;
            while(rs.next()) {
                ArrayList<SubTask> subTasks = readSubTasksByTaskId(rs.getInt("id"), students); //
                temp = new Task(rs.getInt("id"), rs.getString("name"), (float)rs.getDouble("weight"), subTasks);
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }


    public ArrayList<SubTask> readSubTasksByTaskId(int TaskId, ArrayList<Student> students){
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



    public Grade readGradeById(int id){
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


    /*
     * DELETE
     * */
    public void deleteCourseById(int id){
        String sql = "DELETE FROM bank_atm.Course WHERE id = \'"+ id +"\'";
        sqlExecute(sql);
    }
    public void deleteEnrollmentByStudentId(int studentId){
        String sql = "DELETE FROM bank_atm.Enrollment WHERE studentId = \'"+ studentId +"\'";
        sqlExecute(sql);
    }
    public void deleteEnrollmentByCourseSectionId(int courseSectionId){
        String sql = "DELETE FROM bank_atm.Enrollment WHERE courseSectionId = \'"+ courseSectionId +"\'";
        sqlExecute(sql);
    }
    public void deleteCourses(){
        String sql = "DELETE FROM grading_system.Course;";
        sqlExecute(sql);
    }

    public void deleteStudentById(int id){
        String sql = "DELETE FROM grading_system.Student WHERE id = \'"+ id +"\'";
        sqlExecute(sql);
    }
    public void deleteStudents(){
        String sql = "DELETE FROM grading_system.Student;";
        sqlExecute(sql);
    }


    public void deleteTask(){

    }

    public void deleteSubtask(){

    }


}
