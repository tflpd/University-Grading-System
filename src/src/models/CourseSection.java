package models;

import java.util.ArrayList;

public class CourseSection {
    private int id;
    private String name;
    private ArrayList<Student> students;

    public int getId() {
        return id;
    }

    public CourseSection(int id, String name, ArrayList<Student> students) {
        this.id = id;
        this.name = name;
        this.students = students;
    }

    public CourseSection(int id, String name) {
        this.id = id;
        this.name = name;
        this.students = null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }

    public boolean deleteStudent(Student studentToDelete){
        for (Student student:students) {
            if (student.getId() == studentToDelete.getId()){
                students.remove(student);
                return true;
            }
        }
        return false;
    }

    public void addStudent(Student student) {
        if (students == null)
        {
            students = new ArrayList<>();
        }
        students.add(student);
    }

    public Student getSingleStudent(int id) {
        for (Student st : students) {
            if (st.getId() == id) {
                return st;
            }
        }
        return null;
    }

    public String toString()
    {
        return getName();
    }
}
