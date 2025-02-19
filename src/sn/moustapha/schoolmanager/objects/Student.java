package sn.moustapha.schoolmanager.objects;

import java.util.HashMap;

public class Student extends Person {
    private HashMap<Course, Integer> grades;

    public Student(int userId, String firstName, String lastName){
        super(userId, firstName, lastName);
    }

    public HashMap<Course, Integer> getGrades() {
        return grades;
    }

    public int getGrade(Course course) {
        return grades.getOrDefault(course, 0);
    }

    public void obtainsGrade(Course course, int grade) {
        grades.put(course, grade);
    }

}
