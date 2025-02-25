package sn.moustapha.schoolmanager.objects;

import java.util.ArrayList;

public class Teacher extends Person{
    private ArrayList<Course> courses;

    public Teacher(String firstName, String lastName, String password) {
        super(firstName, lastName, password);
        this.courses = new ArrayList<>();
    }

    public Teacher(int userId, String firstName, String lastName, String password) {
        super(userId, firstName, lastName, password);
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course){
        courses.remove(course);
    }


}
