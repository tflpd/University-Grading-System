package models;

public abstract class ProfessorsTool {
    private Professor ownerProfessor;

    public ProfessorsTool(Professor ownerProfessor) {
        this.ownerProfessor = ownerProfessor;
    }
}
