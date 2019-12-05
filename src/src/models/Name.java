package models;

public class Name {
    private int id;
    private String firstName;
    private String middleName;
    private String surname;

    public Name(int id, String firstName, String middleName, String surname) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
    }

    public Name(int id, String firstName, String surname) {
        this(id, firstName, "", surname);
    }

    @Override
    public String toString() {
        return firstName + " " + middleName + " " + surname;
    }
}
