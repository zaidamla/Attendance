package com.zaid.attendance;

/**
 * Created by Vaibhav on 4/25/2017.
 */

public class ReportGetSet {
    private int rollno;
    private String name;
    private String date;
    private String satus;

    public ReportGetSet(int rollno,String Name,String date,String status)
    {
        this.setRollno(rollno);
       this.setName(Name);
        this.setDate(date);
        this.setSatus(status);
    }


    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

   public String getName() {
       return name;
    }
   public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSatus() {
        return satus;
    }

    public void setSatus(String satus) {
        this.satus = satus;
    }
}
