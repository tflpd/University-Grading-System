package MySql;


import models.*;

import java.nio.charset.CharsetEncoder;
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
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from person");
            while(rs.next())
                System.out.println(rs.getString(2));
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


//    CREATE TABLE `Course` (
//            `id` int PRIMARY KEY AUTO_INCREMENT,
//                          `templateCourseId` int,
//            `year` varchar(255),
//                          `semester` varchar(255),
//                          `name` varchar(255),
//                          `professorId` int
//);
    public void addCourse(Course course, int templateCourseId, int professorId){
        String sql = "INSERT INTO grading_system.Task(id, `templateCourseId`, year, semester, name, professorId) VALUES " +
                "(\'"+ course.getId() + "\', \'"+ templateCourseId +"\', \'"+ course.getYear() +"\', \'" + course.getSemester() + "\', \'" + course.getName() + "\', \'"+ professorId +"\')";
        System.out.println(sql);
        sqlExecute(sql);
    }

    public void addProfessor(Professor professor, int credentialId){
        String sql = "INSERT INTO grading_system.Task(id, first_name, last_name, credentialId) VALUES (\'"+ professor.getId() + "\', \'"+ professor.getName().getFirstName() +"\', \'"+ professor.getName().getSurname()+"\', \'" + credentialId + "\')";
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

    public Course readCourseById(int id, String semester, String year, ArrayList<CourseSection> courseSections, CourseTemplate courseTemplate){
        Course temp = null;

        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Course WHERE id = \'"+id +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                temp = new Course(rs.getInt("id"), rs.getString("name"), semester, year, courseSections, courseTemplate);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return temp;
    }

    public List<Course> readAllCourses(){
        // can't be done because lack of info in DB
        return new ArrayList<>();
    }

    public List<Integer> readEnrollmentsByCourseSectionId(int courseSectionId){
        List<Integer> list = new ArrayList<>();
        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Enrollment WHERE courseSectionId = " + courseSectionId;
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next()) {
                list.add(rs.getInt("studentId"));
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
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
