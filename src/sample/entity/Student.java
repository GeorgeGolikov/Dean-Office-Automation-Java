package sample.entity;

public final class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String groupName;

    public Student() {

    }

    public Student(String firstName, String lastName, String fatherName, String groupName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.groupName = groupName;
    }

    public Student(Integer id, String firstName, String lastName, String fatherName, String groupName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.groupName = groupName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
