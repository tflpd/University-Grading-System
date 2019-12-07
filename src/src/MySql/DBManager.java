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


    public void addCourse(Course course, String courseCode, Timestamp timestamp, int professorId, boolean isDeleted){
        String sql = "INSERT INTO grading_system.Course(id, code, name, created_at, professorId, isDeleted) VALUES (\'"+ course.getId() + "\', \'"+ courseCode +"\', \'"+ course.getName() +"\', \'" + timestamp + "\', \'" + professorId + "\', \'"+ isDeleted +"\')";
        System.out.println(sql);
        sqlExecute(sql);

    }

    public void addStudent(Student student, String comment){

        String sql = "INSERT INTO grading_system.Student (id, first_name, last_name, email, buid, comment) VALUES (\'"+ student.getId() + "\', \'"+ student.getNameObject().getFirstName() +"\', \'"+ student.getNameObject().getSurname() +"\', \'" + student.getEmail() + "\', \'" + student.getBuID() + "\', \'"+ comment +"\')";
        System.out.println(sql);
        sqlExecute(sql);
    }

    public void addTask(){

    }

    public void addSubtask(){

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

    public Course readCourseById(int id, String semester, String year){
        Course temp = null;

        try {
            Statement stmt=con.createStatement();
            String sql = "select * from Course WHERE id = \'"+id +"\'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                //public Course(int id, String name, String semester, String year, ArrayList<CourseSection> courseSections, CourseTemplate courseTemplate) {
                //temp = new Course(rs.getInt("id"), new Name(rs.getInt(""), rs.getString("first_name"), rs.getString("last_name")), rs.getString("email"), rs.getString("buid"));
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
    public void deleteCourse(){

    }

    public void deleteStudentById(int id){
        String sql = "DELETE FROM bank_atm.person WHERE id = \'"+ id +"\'";
        sqlExecute(sql);
    }

    public void deleteTask(){

    }

    public void deleteSubtask(){

    }


}
