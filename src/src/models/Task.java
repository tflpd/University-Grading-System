package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Task {
    private int id;
    private String name;
    private Float weightInFinalGrade;
    private ArrayList<SubTask> subTasks;

    public Task(int id, String name, Float weightInFinalGrade) {
        this.id = id;
        this.name = name;
        this.weightInFinalGrade = weightInFinalGrade;
        this.subTasks = new ArrayList<SubTask>();
    }

    public Task(int id, String name, Float weightInFinalGrade, ArrayList<SubTask> subTasks) {
        this.id = id;
        this.name = name;
        this.weightInFinalGrade = weightInFinalGrade;
        this.subTasks = subTasks;
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

    public void addNewSubTask(ArrayList<Student> students, String name, LocalDateTime creationDate, String dateDue, Float totalPointsAvailable, Float weightInParentTask, Float bonusPoints, String otherComments, boolean groupProject){
        subTasks.add(new SubTask(0, students, name, creationDate, dateDue, totalPointsAvailable, weightInParentTask, bonusPoints, otherComments, groupProject));
    }

    public void addNewSubTask(SubTask subTask){
        subTasks.add(subTask);
    }

    public void deleteSubTask(SubTask subTask){
        subTasks.remove(subTask);
    }

    public Task getDeepCopyOfTask(){
        return new Task(0, name, weightInFinalGrade);
    }


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public Float getStudentsGrade(Student student){
        Float taskAggregateGrade = 0f;
        for (SubTask subTask:subTasks){
            taskAggregateGrade += subTask.getStudentsGrade(student)*subTask.getWeightInParentTask();
        }
        return taskAggregateGrade;
    }

    public void deleteStudentFromTask(Student student){
        for (SubTask subTask:subTasks) {
            subTask.deleteStudentFromSubTask(student);
        }
    }

}
