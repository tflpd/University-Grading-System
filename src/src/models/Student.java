package models;

public class Student {
    private int id;
    private Name name;
    private String email;
    private String buID;
    private boolean withdrawn;

    public Student(int id, Name name, String email, String buID) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.buID = buID;
        this.withdrawn = false;
    }

    public Student(int id, Name name, String email, String buID, boolean withdrawn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.buID = buID;
        this.withdrawn = withdrawn;
    }

    public Student(Name name, String email, String buID) {
        this(0, name, email, buID);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name.toString();
    }

    public Name getNameObject() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBuID() {
        return buID;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
