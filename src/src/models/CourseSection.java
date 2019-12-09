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

    public ArrayList<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }
}
