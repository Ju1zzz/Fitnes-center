package com.company.basic;

public class Coach {
    private  int id;
    private String name;
    private int years_exp;
    private String qualification;
    private String timetable;

    public Coach(int id, String name, int years_exp, String qualification, String timetable) {
        this.id=id;
        this.name = name;
        this.years_exp = years_exp;
        this.qualification = qualification;
        this.timetable = timetable;
    }

    public Coach(String name, int years_exp, String qualification, String timetable) {
        this.name = name;
        this.years_exp = years_exp;
        this.qualification = qualification;
        this.timetable = timetable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYears_exp() {
        return years_exp;
    }

    public void setYears_exp(int years_exp) {
        this.years_exp = years_exp;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }
}
