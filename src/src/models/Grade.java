package models;

public class Grade {
    private int id;
    private Student student;
    private Float absolutePointsScored;
    private String comment;
    private Float bonusPoints;

    public int getId() {
        return id;
    }

    public Grade(int id, Student student, Float absolutePointsScored, String comment) {
        this.student = student;
        this.absolutePointsScored = absolutePointsScored;
        this.comment = comment;
        this.bonusPoints = 0f;
    }

    public void setId(int id) {
        this.id = id;
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

    public static String translateGradeToLetter(Float absolutePointsScored, Float totalPointsAvailable){
        Float gradePercentage = absolutePointsScored/totalPointsAvailable;
        if (gradePercentage < 0.67){
            return "F";
        }else if (gradePercentage < 0.71){
            return "D";
        }else if (gradePercentage < 0.74){
            return "C-";
        }else if (gradePercentage < 0.78){
            return "C";
        }else if (gradePercentage < 0.81){
            return "C+";
        }else if (gradePercentage < 0.84){
            return "B-";
        }else if (gradePercentage < 0.88){
            return "B";
        }else if (gradePercentage < 0.91){
            return "B+";
        }else if (gradePercentage < 0.95){
            return "A-";
        }else{
            return "A";
        }
    }

    public Float getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Float bonusPoints) {
        this.bonusPoints = bonusPoints;
    }
}
