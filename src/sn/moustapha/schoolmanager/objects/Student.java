package sn.moustapha.schoolmanager.objects;

import java.util.HashMap;

public class Student extends Person {
    private HashMap<String, Integer> grades;

    public Student(int userId, String firstName, String lastName){
        super(userId, firstName, lastName);
    }

    public int getGrade(String subject) {
        return grades.getOrDefault(subject, 0);
    }
    
}
