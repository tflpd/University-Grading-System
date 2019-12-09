package models;

import java.util.ArrayList;

public class CourseTemplate {
    private int id;
    private String name;
    private String semester;
    private String year;
    private ArrayList<Task> tasks;

    // Constructor to be used when creating a new course/template from scratch
    public CourseTemplate(int id, String name, String semester, String year, ArrayList<Task> tasks) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.year = year;
        this.tasks = tasks;
    }

    // Constructor to be used when creating a new course/template from an old template courseTemplate
    public CourseTemplate(int id, String name, String semester, String year, CourseTemplate courseTemplate) {
        this(id, name, semester, year, courseTemplate.getDeepCopyOfTasks());
    }

    public CourseTemplate(int id, String name, String semester, String year) {
        this(id, name, semester, year, new ArrayList<Task>());
    }

    public String toString(){
        return name +" "+year+" "+semester;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addNewTask(String name, Float weightInFinalGrade){
        tasks.add(new Task(0, name, weightInFinalGrade));
    }

    private ArrayList<Task> getDeepCopyOfTasks(){
        ArrayList<Task> newTasks = new ArrayList<Task>();
        for (Task task: tasks) {
            Task newTmpTask = task.getDeepCopyOfTask();
            for (SubTask subTask:task.getSubTasks()){
                newTmpTask.addNewSubTask(subTask.getDeepCopyOfSubTask());
            }
            newTasks.add(newTmpTask);
        }
        return newTasks;
    }
}
