package sn.moustapha.schoolmanager.objects;

import java.util.ArrayList;

public class Course {
    private int courseId;
    private String subject;
    private Teacher teacher;

    public Course(int courseId, String subject, Teacher teacher){
        this.courseId = courseId;
        this.subject = subject;
        this.teacher = teacher;
    }

    public String getSubject() {
        return subject;
    }

    public int getCourseId() {
        return courseId;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String toString() {
        return subject;
    }


    /* public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj.getClass() != this.getClass())
            return false;

        Course comparedCourse = (Course) obj;
        boolean sameSubject = comparedCourse.getSubject().equals(subject);
        boolean sameId = comparedCourse.getCourseId() == courseId;

        return (sameId && sameSubject);
    } */

}
