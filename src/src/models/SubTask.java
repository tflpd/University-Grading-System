package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SubTask {
    private int id;
    private String name;
    private LocalDateTime creationDate;
    //private LocalDateTime dateDue;
    private String dateDue;
    private Float totalPointsAvailable;
    private Float weightInParentTask;
    private Float bonusPoints;
    private ArrayList<Grade> grades;
    private String otherComments;
    private boolean groupProject;


    public SubTask(int id, ArrayList<Student> students, String name, LocalDateTime creationDate, String dateDue, Float totalPointsAvailable, Float weightInParentTask, Float bonusPoints, String otherComments, boolean groupProject) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.dateDue = dateDue;
        this.totalPointsAvailable = totalPointsAvailable;
        this.weightInParentTask = weightInParentTask;
        this.bonusPoints = bonusPoints;
        this.grades = new ArrayList<Grade>();
        for (Student student:students){
            grades.add(new Grade(0, student, 0f, ""));
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

    public String getDateDue() {
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

    public void setDateDue(String dateDue) {
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

    public void setStudentsGrade(Student student, Float absolutePointsScored){
        for (Grade grade:grades){
            if (grade.getStudent().getBuID().equals(student.getBuID())){
                grade.setAbsolutePointsScored(absolutePointsScored);
                return;
            }
        }
    }

    public Float getStudentsGrade(Student student){
        for (Grade grade:grades){
            if (grade.getStudent().getBuID().equals(student.getBuID())){
                return grade.getAbsolutePointsScored()/totalPointsAvailable;
            }
        }
        return -1f;
    }

    public void setStudentsComment(Student student, String comment){
        for (Grade grade:grades){
            if (grade.getStudent().getBuID().equals(student.getBuID())){
                grade.setComment(comment);
                return;
            }
        }
    }

    // When creating a copy of this sub task for future use we copy everything but the dates; this has to be set again from the user for the
    // new sub task as they will definitely not be the same
    // MAKE SURE TO BE READY TO HANDLE NULL VALUES ON THOSE TWO FIELDS
    public SubTask getDeepCopyOfSubTask(){
        return new SubTask(0, null, name, null, null, totalPointsAvailable, weightInParentTask, bonusPoints, otherComments, groupProject);
    }

    public Float getMeanGrade(){
        int activeStudentsN = 0;
        Float aggregatePointsScored = 0f;
        for (Grade grade:grades){
            if (!grade.getStudent().isWithdrawn()){
                activeStudentsN++;
                aggregatePointsScored += grade.getAbsolutePointsScored();
            }
        }
        return aggregatePointsScored/activeStudentsN;
    }

    public Float getMeanGradePercentage(){
        return getMeanGrade()/totalPointsAvailable;
    }


    public float getStandardDeviation(){
        float standardDeviation = 0f;
        Float mean =  getMeanGrade();
        int activeStudentsN = 0;
        for(Grade grade: grades) {
            if (!grade.getStudent().isWithdrawn()){
                activeStudentsN++;
                standardDeviation += (float)Math.pow(grade.getAbsolutePointsScored() - mean, 2);
            }
        }
        if (activeStudentsN == 0){
            return 0;
        }
        return (float)Math.sqrt(standardDeviation/activeStudentsN);
    }

    public Float getMedianPercentage(){
        ArrayList<Float> pointsScoredList = new ArrayList<Float>();
        for (Grade grade:grades){
            if (!grade.getStudent().isWithdrawn()){
                pointsScoredList.add(grade.getAbsolutePointsScored()/totalPointsAvailable);
            }
        }
        if (pointsScoredList.size() == 0){
            return 0f;
        }
        Collections.sort(pointsScoredList);
        int listSize = pointsScoredList.size();
        if (listSize % 2 == 0)
            return (pointsScoredList.get(listSize/2) + pointsScoredList.get(listSize/2 - 1))/2;
        else
            return pointsScoredList.get(listSize/2);
    }

    public int getId() {
        return id;
    }




}
