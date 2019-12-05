package models;

public class Grade {
    private int id;
    private Student student;
    private Float absolutePointsScored;
    private String comment;

    public Grade(int id, Student student, Float absolutePointsScored, String comment) {
        this.student = student;
        this.absolutePointsScored = absolutePointsScored;
        this.comment = comment;
    }

    public Grade(int id, Student student) {
        this(id, student, 0f, "");
    }

    public void setAbsolutePointsScored(Float absolutePointsScored) {
        this.absolutePointsScored = absolutePointsScored;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Student getStudent() {
        return student;
    }

    public Float getAbsolutePointsScored() {
        return absolutePointsScored;
    }

    public String getComment() {
        return comment;
    }
}
