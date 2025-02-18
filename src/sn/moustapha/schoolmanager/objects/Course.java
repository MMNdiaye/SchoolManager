package sn.moustapha.schoolmanager.objects;

import java.util.ArrayList;

public class Course {
    private String subject;
    private ArrayList<Class> classes;

    public Course(String subject){
        this.subject = subject;
        this.classes = new ArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    public void addClass(Class schoolClass) {
        classes.add(schoolClass);
    }

    public void removeClass(Class schoolClass){
        classes.remove(schoolClass);
    }

}
