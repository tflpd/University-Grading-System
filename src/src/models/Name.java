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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
