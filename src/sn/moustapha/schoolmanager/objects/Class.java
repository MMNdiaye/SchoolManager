package sn.moustapha.schoolmanager.objects;

import java.util.ArrayList;

public class Class {
    private ArrayList<Student> students;

    public Class() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}
