package com.zaid.attendance;

/**
 * Created by Najma on 24-04-2017.
 */

public class report_list {
    private int rollno;
    private String Name;
    private String status;

    public report_list(int rollno, String name, String status)
    {
        this.setRollno(rollno);
        this.setName(name);
        this.setStatus(status);
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
