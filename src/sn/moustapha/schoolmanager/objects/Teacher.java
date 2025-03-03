package sn.moustapha.schoolmanager.objects;

import java.util.ArrayList;

public class Teacher extends Person{

    public Teacher(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
    }

    public Teacher(int userId, String firstName, String lastName, String password) {
        super(userId, firstName, lastName, password);
    }



}
