package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

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
    private int id;
    private String name;
    private String semester;
    private String year;
    private ArrayList<CourseSection> courseSections;
    private ArrayList<Task> tasks;
    private CourseTemplate courseTemplate;

    public Course(int id, String name, String semester, String year, ArrayList<CourseSection> courseSections, CourseTemplate courseTemplate) {
        this.id = id;
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
    
    public String toString()
    {
    	return name +" "+semester+" "+year;
    }

    public ArrayList<CourseSection> getCourseSections() {
        return courseSections;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addSection(CourseSection c){
        if (courseSections == null)
        {
            courseSections = new ArrayList<>();
        }
        courseSections.add(c);
    }

    public void addNewTask(String name, Float weightInFinalGrade){
        courseTemplate.addNewTask(name, weightInFinalGrade);
    }

    public void addNewSubTask(Task targetTask, String name, LocalDateTime creationDate, LocalDateTime dateDue, Float totalPointsAvailable, Float weightInParentTask, Float bonusPoints, String otherComments, boolean groupProject){
        targetTask.addNewSubTask(getAllStudents(), name, creationDate, dateDue, totalPointsAvailable, weightInParentTask, bonusPoints, otherComments, groupProject);
    }

    public ArrayList<Student> getAllStudents(){
        ArrayList<Student> allStudents = new ArrayList<Student>();
        if (courseSections != null) {
            for (CourseSection courseSection : courseSections) {
                allStudents.addAll(courseSection.getStudents());
            }
        }
        return allStudents;
    }

    public Float getStudentsFinalGrade(Student student){
        Float finalGrade = 0f;
        for (Task task:tasks){
            finalGrade += task.getStudentsGrade(student)*task.getWeightInFinalGrade()/100;
        }
        return finalGrade;
    }

    public Float getCourseSectionMeanGrade(CourseSection courseSection){
        ArrayList<Float> grades = new ArrayList<Float>();
        for (Student student:courseSection.getStudents()) {
            grades.add(getStudentsFinalGrade(student));
        }
        Float aggregatePointsScored = 0f;
        for (Float grade:grades){
            aggregatePointsScored += grade;
        }
        return aggregatePointsScored/grades.size();
    }

    public Float getMeanGrade(){
        ArrayList<Float> grades = new ArrayList<Float>();
        for (CourseSection courseSection:courseSections) {
            for (Student student:courseSection.getStudents()) {
                grades.add(getStudentsFinalGrade(student));
            }
        }
        Float aggregatePointsScored = 0f;
        for (Float grade:grades){
            aggregatePointsScored += grade;
        }
        return aggregatePointsScored/grades.size();
    }


    public float getCourseSectionStandardDeviation(CourseSection courseSection){
        ArrayList<Float> grades = new ArrayList<Float>();
        for (Student student:courseSection.getStudents()) {
            grades.add(getStudentsFinalGrade(student));
        }
        float standardDeviation = 0f;
        Float mean =  getCourseSectionMeanGrade(courseSection);

        for(Float grade: grades) {
            standardDeviation += (float)Math.pow(grade - mean, 2);
        }
        if (grades.size() == 0){
            return 0;
        }
        return (float)Math.sqrt(standardDeviation/grades.size());
    }

    public float getStandardDeviation(){
        ArrayList<Float> grades = new ArrayList<Float>();
        for (CourseSection courseSection:courseSections) {
            for (Student student:courseSection.getStudents()) {
                grades.add(getStudentsFinalGrade(student));
            }
        }
        float standardDeviation = 0f;
        Float mean =  getMeanGrade();

        for(Float grade: grades) {
            standardDeviation += (float)Math.pow(grade - mean, 2);
        }
        if (grades.size() == 0){
            return 0;
        }
        return (float)Math.sqrt(standardDeviation/grades.size());
    }

    public Float getCourseSectionMedianPercentage(CourseSection courseSection){
        ArrayList<Float> grades = new ArrayList<Float>();
        for (Student student:courseSection.getStudents()) {
            grades.add(getStudentsFinalGrade(student));
        }

        if (grades.size() == 0){
            return 0f;
        }
        Collections.sort(grades);
        int listSize = grades.size();
        if (listSize % 2 == 0)
            return (grades.get(listSize/2) + grades.get(listSize/2 - 1))/2;
        else
            return grades.get(listSize/2);
    }

    public Float getMedianPercentage(){
        ArrayList<Float> grades = new ArrayList<Float>();
        for (CourseSection courseSection:courseSections) {
            for (Student student:courseSection.getStudents()) {
                grades.add(getStudentsFinalGrade(student));
            }
        }

        if (grades.size() == 0){
            return 0f;
        }
        Collections.sort(grades);
        int listSize = grades.size();
        if (listSize % 2 == 0)
            return (grades.get(listSize/2) + grades.get(listSize/2 - 1))/2;
        else
            return grades.get(listSize/2);
    }

    public String getStudentsFinalLetterGrade(Student student){
        return Grade.translateGradeToLetter(getStudentsFinalGrade(student), 100f);
    }

    public void deleteStudentFromSections(Student student){
        for (CourseSection courseSection:courseSections) {
            if (courseSection.deleteStudent(student)){
                return;
            }
        }
    }

    public void deleteStudentFromCourse(Student student){
        for (Task task:tasks) {
            //task.deleteStudentFromTask(student);
        }
    }

    public void deleteStudent(Student student){
        deleteStudentFromSections(student);
        deleteStudentFromCourse(student);
    }

    public void setCourseTemplate(CourseTemplate courseTemplate) {
        this.courseTemplate = courseTemplate;
    }

    public CourseTemplate getCourseTemplate() {
        return courseTemplate;
    }

    public void addNewGrade(Student student){
        for (Task task:tasks){
            task.addNewGrade(student);
        }
    }
}
