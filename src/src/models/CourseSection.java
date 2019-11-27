package models;

import java.util.ArrayList;

public class CourseSection {
    private String name;
    private ArrayList<Student> students;

    public CourseSection(String name, ArrayList<Student> students) {
        this.name = name;
        this.students = students;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }
}
