package sn.moustapha.schoolmanager.objects;

import java.util.HashMap;

public class Student extends Person {

    public Student(String firstName, String lastName, String password){
        super(firstName, lastName, password);
    }

    public Student(int userId, String firstName, String lastName, String password){
        super(userId, firstName, lastName, password);
    }

}
