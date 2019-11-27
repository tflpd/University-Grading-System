package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

/*
* Variables semester and year are the ones that will be used to make the template's
* name more specific. For example if the course is CS591 then we would want the template
* to be named CS591 - Fall 2019
*
*
* A correct use case of the following class would be this:
* First create a new template. In the case that the new template is based in a previous one
* then call the template constructor that takes as an argument the base template.
* After creating the new template, create a new Course class. That would be done
* by sending in the Course constructor the newly created template as an argument.
* Below is a simple code snippet representing this example:
*
* CourseTemplate newCourseTemplate = new CourseTemplate(name, semester, year, baseCourseTemplate);
* Course newCourse = new Course(name, semester, year, courseSections, newCourseTemplate);
*
* */

public class Course {
    private String name;
    private String semester;
    private String year;
    private ArrayList<CourseSection> courseSections;
    private ArrayList<Task> tasks;
    private CourseTemplate courseTemplate;

    public Course(String name, String semester, String year, ArrayList<CourseSection> courseSections, CourseTemplate courseTemplate) {
        this.name = name;
        this.semester = semester;
        this.year = year;
        this.courseSections = courseSections;
        this.tasks = courseTemplate.getTasks();
        this.courseTemplate = courseTemplate;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public String getYear() {
        return year;
    }

    public ArrayList<CourseSection> getCourseSections() {
        return courseSections;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addNewTask(String name, Float weightInFinalGrade){
        courseTemplate.addNewTask(name, weightInFinalGrade);
    }

    public void addNewSubTask(Task targetTask, String name, LocalDateTime creationDate, LocalDateTime dateDue, Float totalPointsAvailable, Float weightInParentTask, Float bonusPoints, String otherComments, boolean groupProject){
        targetTask.addNewSubTask(getAllStudents(), name, creationDate, dateDue, totalPointsAvailable, weightInParentTask, bonusPoints, otherComments, groupProject);
    }

    private ArrayList<Student> getAllStudents(){
        ArrayList<Student> allStudents = new ArrayList<Student>();
        for (CourseSection courseSection:courseSections){
            allStudents.addAll(courseSection.getStudents());
        }
        return allStudents;
    }
}
