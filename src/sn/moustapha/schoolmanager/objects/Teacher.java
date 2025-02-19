package sn.moustapha.schoolmanager.objects;

import java.util.ArrayList;

public class Teacher extends Person{
    private ArrayList<Course> courses;


    public Teacher(int userId, String firstName, String lastName) {
        super(userId, firstName, lastName);
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course){
        courses.remove(course);
    }


}
