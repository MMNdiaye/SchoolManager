package sn.moustapha.schoolmanager.objects;

import sn.moustapha.schoolmanager.objects.Class;
import java.util.ArrayList;

public class Course {
    private int courseId;
    private String subject;
    private Teacher teacher;
    private Class classroom;

    public Course(String subject, Teacher teacher, Class classroom){
        this.subject = subject;
        this.teacher = teacher;
        this.classroom = classroom;
    }

    public Course(int courseId, String subject, Teacher teacher, Class classroom){
        this.courseId = courseId;
        this.subject = subject;
        this.teacher = teacher;
        this.classroom = classroom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Class getClassroom() {
        return classroom;
    }

    public void setClassroom(Class classroom) {
        this.classroom = classroom;
    }

    public String toString() {
        return subject + " on class " + classroom + " presented by " + teacher ;
    }


}
