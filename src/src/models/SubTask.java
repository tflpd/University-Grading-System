package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SubTask {
    private String name;
    private LocalDateTime creationDate;
    private LocalDateTime dateDue;
    private Float totalPointsAvailable;
    private Float weightInParentTask;
    private Float bonusPoints;
    private ArrayList<Grade> grades;
    private String otherComments;
    private boolean groupProject;


    public SubTask(ArrayList<Student> students, String name, LocalDateTime creationDate, LocalDateTime dateDue, Float totalPointsAvailable, Float weightInParentTask, Float bonusPoints, String otherComments, boolean groupProject) {
        this.name = name;
        this.creationDate = creationDate;
        this.dateDue = dateDue;
        this.totalPointsAvailable = totalPointsAvailable;
        this.weightInParentTask = weightInParentTask;
        this.bonusPoints = bonusPoints;
        this.grades = new ArrayList<Grade>();
        for (Student student:students){
            grades.add(new Grade(student, 0f, ""));
        }
        this.otherComments = otherComments;
        this.groupProject = groupProject;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getDateDue() {
        return dateDue;
    }

    public Float getTotalPointsAvailable() {
        return totalPointsAvailable;
    }

    public Float getWeightInParentTask() {
        return weightInParentTask;
    }

    public Float getBonusPoints() {
        return bonusPoints;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public String getOtherComments() {
        return otherComments;
    }

    public boolean isGroupProject() {
        return groupProject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setDateDue(LocalDateTime dateDue) {
        this.dateDue = dateDue;
    }

    public void setTotalPointsAvailable(Float totalPointsAvailable) {
        this.totalPointsAvailable = totalPointsAvailable;
    }

    public void setWeightInParentTask(Float weightInParentTask) {
        this.weightInParentTask = weightInParentTask;
    }

    public void setBonusPoints(Float bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public void setOtherComments(String otherComments) {
        this.otherComments = otherComments;
    }

    public void setGroupProject(boolean groupProject) {
        this.groupProject = groupProject;
    }

    // When creating a copy of this sub task for future use we copy everything but the dates; this has to be set again from the user for the
    // new sub task as they will definitely not be the same
    // MAKE SURE TO BE READY TO HANDLE NULL VALUES ON THOSE TWO FIELDS
    public SubTask getDeepCopyOfSubTask(){
        return new SubTask(null, name, null, null, totalPointsAvailable, weightInParentTask, bonusPoints, otherComments, groupProject);
    }
}
