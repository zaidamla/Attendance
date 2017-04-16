package com.zaid.attendance;

/**
 * Created by Najma on 11-04-2017.
 */

public class student_list {
    private int rollno;
    private String Name;
    private boolean present;

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
