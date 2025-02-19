package sn.moustapha.schoolmanager.objects;

public abstract class Person  {
    private int userId;
    private String firstName;
    private String lastName;

    public Person(int userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getUserId() {
        return userId;
    }

    public String toString() {
        return firstName + " " + lastName;
    }
}
