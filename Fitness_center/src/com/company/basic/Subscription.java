package com.company.basic;

public class Subscription {
    private  int id;
    private String title;
    private String type;
    private int duration;
    private boolean freeze;

    public Subscription(int id, String title, String type, int duration, boolean freeze) {
        this.id=id;
        this.title = title;
        this.type = type;
        this.duration = duration;
        this.freeze = freeze;
    }

    public Subscription(String title, String type, int duration, boolean freeze) {
        this.title = title;
        this.type = type;
        this.duration = duration;
        this.freeze = freeze;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isFreeze() {
         return freeze;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
