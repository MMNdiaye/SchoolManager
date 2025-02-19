package sn.moustapha.schoolmanager.objects;

import java.util.ArrayList;

public class Class {
    // private int classId;
    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    public Class() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.add(course);
    }

    public ArrayList<Student> getStudents(){
        return students;
    }
}
