package sn.moustapha.schoolmanager.logic;

import sn.moustapha.schoolmanager.objects.Class;
import sn.moustapha.schoolmanager.objects.Course;
import sn.moustapha.schoolmanager.objects.Person;
import sn.moustapha.schoolmanager.objects.Student;
import sn.moustapha.schoolmanager.objects.Teacher;

import java.util.ArrayList;
import java.util.HashMap;

public class SchoolManager {
    private ArrayList<Person> persons;

    public SchoolManager(){
        persons = new ArrayList<>();
    }

    // Admin functions

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void removePerson(Person person) {
        persons.remove(person);
    }
    public void addTeacher(Teacher teacher) {
        persons.add(teacher);
    }

    public void addCourse(Class schoolClass, Course course) {
        schoolClass.addCourse(course);
    }

    public void removeCourse(Class schoolClass, Course course) {
        schoolClass.removeCourse(course);
    }

    public void addStudent(Student student, Class schoolClass) {
        schoolClass.addStudent(student);
    }

    public void removeStudent(Student student, Class schoolClass) {
        schoolClass.removeStudent(student);
    }

    // Students functions
    public void seeGrades(Student student) {
        HashMap<Course, Integer> grades = student.getGrades();
        for (Course course: grades.keySet()) {
            String subject = course.getSubject();
            Teacher teacher = course.getTeacher();
            int grade = grades.get(course);
            System.out.println(subject + ": " + grade +" Teacher: Mr." +
                    teacher);
        }
    }

    //Teacher functions
    public void gradeStudent(Student student,Course course, int grade) {
        student.obtainsGrade(course, grade);
    }

   public void seeClass(Class schoolClass, Course course) {
        ArrayList<Student> students = schoolClass.getStudents();
        for (Student student : students) {
            System.out.println("Course: " + course);
            int grade = student.getGrade(course);
            System.out.println(student + ": " + grade);
        }
   }




}
