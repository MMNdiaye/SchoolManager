package sn.moustapha.schoolmanager.objects;

public class Admin extends Person{

    public Admin(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
    }

    public Admin(int userId, String firstName, String lastName, String password) {
        super(userId, firstName, lastName, password);
    }
}
