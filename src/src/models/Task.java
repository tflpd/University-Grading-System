package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
    private String name;
    private Float weightInFinalGrade;
    private ArrayList<SubTask> subTasks;

    public Task(String name, Float weightInFinalGrade) {
        this.name = name;
        this.weightInFinalGrade = weightInFinalGrade;
        this.subTasks = new ArrayList<SubTask>();
    }

    public String getName() {
        return name;
    }

    public Float getWeightInFinalGrade() {
        return weightInFinalGrade;
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeightInFinalGrade(Float weightInFinalGrade) {
        this.weightInFinalGrade = weightInFinalGrade;
    }

    public void addNewSubTask(ArrayList<Student> students, String name, LocalDateTime creationDate, LocalDateTime dateDue, Float totalPointsAvailable, Float weightInParentTask, Float bonusPoints, String otherComments, boolean groupProject){
        subTasks.add(new SubTask(students, name, creationDate, dateDue, totalPointsAvailable, weightInParentTask, bonusPoints, otherComments, groupProject));
    }

    public void addNewSubTask(SubTask subTask){
        subTasks.add(subTask);
    }

    public void deleteSubTask(SubTask subTask){
        subTasks.remove(subTask);
    }

    public Task getDeepCopyOfTask(){
        return new Task(name, weightInFinalGrade);
    }
}
