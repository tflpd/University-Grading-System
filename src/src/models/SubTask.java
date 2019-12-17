package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class SubTask {
    private int id;
    private String name;
    private LocalDateTime releaseDate;
    //private LocalDateTime dateDue;
    private String dateDue;
    private Float totalPointsAvailable;
    private Float weightInParentTask;
    private Float maxAvailableBonusPoints;
    private ArrayList<Grade> grades;
    private String otherComments;
    private boolean groupProject;

    public void setId(int id) {
        this.id = id;
    }

    public SubTask(int id, ArrayList<Student> students, String name, LocalDateTime releaseDate, String dateDue, Float totalPointsAvailable, Float weightInParentTask, Float bonusPoints, String otherComments, boolean groupProject) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.dateDue = dateDue;
        this.totalPointsAvailable = totalPointsAvailable;
        this.weightInParentTask = weightInParentTask;
        this.maxAvailableBonusPoints = bonusPoints;
        this.grades = new ArrayList<Grade>();
        if (students != null){
            for (Student student:students){
                grades.add(new Grade(0, student, 0f, ""));
                LoggedData.getDbManager().addGrade(0, this.getId(), student.getId());
            }
        }
        this.otherComments = otherComments;
        this.groupProject = groupProject;
    }


    public String getName() {
        return name;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
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

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
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

    public void setOtherComments(String otherComments) {
        this.otherComments = otherComments;
    }

    public void setGroupProject(boolean groupProject) {
        this.groupProject = groupProject;
    }

    public void setStudentsGrade(Student student, Float absolutePointsScored){
        for (Grade grade:grades){
            if (grade.getStudent().getId() == student.getId()){
                grade.setAbsolutePointsScored(absolutePointsScored);
                return;
            }
        }
    }

    public Float getStudentsGrade(Student student){
        for (Grade grade:grades){
            if (grade.getStudent().getId() == student.getId()){

                return grade.getAbsolutePointsScored()/totalPointsAvailable;
            }
        }
        return -1f;
    }

    public Grade getGrade(Student student){
        for (Grade grade:grades){
            if (grade.getStudent().getId() == student.getId()){
                return grade;
            }
        }
        return null;
    }

    public void setStudentsComment(Student student, String comment){
        for (Grade grade:grades){
            if (grade.getStudent().getId() == student.getId()){
                grade.setComment(comment);
                return;
            }
        }
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    public Float getStudentsBonusGrade(Student student){
        for (Grade grade:grades){
            if (grade.getStudent().getId() == student.getId()){
                return grade.getBonusPoints();
            }
        }
        return -1f;
    }

    public void setStudentsBonusGrade(Student student, Float bonus){
        for (Grade grade:grades){
            if (grade.getStudent().getId() == student.getId()){
                grade.setBonusPoints(bonus);
                return;
            }
        }
    }

    // When creating a copy of this sub task for future use we copy everything but the dates; this has to be set again from the user for the
    // new sub task as they will definitely not be the same
    // MAKE SURE TO BE READY TO HANDLE NULL VALUES ON THOSE TWO FIELDS
    public SubTask getDeepCopyOfSubTask(){
        return new SubTask(0, null, name, null, null, totalPointsAvailable, weightInParentTask, maxAvailableBonusPoints, otherComments, groupProject);
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


    public Float getMaxAvailableBonusPoints() {
        return maxAvailableBonusPoints;
    }

    public void setMaxAvailableBonusPoints(Float maxAvailableBonusPoints) {
        this.maxAvailableBonusPoints = maxAvailableBonusPoints;
    }

    public void deleteStudentFromSubTask(Student student){
        for (Grade grade:grades) {
            if (grade.getStudent().getId() == student.getId()){
                grades.remove(grade);
            }
        }
    }

    public void addNewGrade(Student student){
        grades.add(new Grade(0, student, 0f, ""));
        LoggedData.getDbManager().addGrade(0, this.getId(), student.getId());
    }
}
