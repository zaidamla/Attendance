package com.zaid.attendance;

/**
 * Created by Najma on 11-04-2017.
 */

public class student_list {
    private int rollno;
    private String Name;

    public student_list(int rollno,String name)
    {
        this.setRollno(rollno);
        this.setName(name);
    }

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

}
