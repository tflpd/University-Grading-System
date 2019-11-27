package models;

public class Name {
    private String firstName;
    private String middleName;
    private String surname;

    public Name(String firstName, String middleName, String surname) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
    }

    public Name(String firstName, String surname) {
        this(firstName, "", surname);
    }

    @Override
    public String toString() {
        return firstName + " " + middleName + " " + surname;
    }
}
