package com.company.basic;

public class Client {
    private int id;
    private String name;
    private String sex;
    private  double height;
    private  double weight;
    private double сoef;
    private String subscription;
    private String coach;
    private String comment;
    private String phone;


    public Client(int id, String name, String sex, double height, double weight, double сoef, String subscription,String coach, String comment, String phone) {
        this.id=id;
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.сoef = сoef;
        this.subscription = subscription;
        this.coach=coach;
        this.comment = comment;
        this.phone=phone;
    }

    public Client(String name, String sex, double height, double weight, double сoef, String subscription, String coach, String comment, String phone) {
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
        this.сoef = сoef;
        this.subscription = subscription;
        this.coach = coach;
        this.comment = comment;
        this.phone=phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getSex() {
         return sex;
    }


    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getСoef() {
        return сoef*10000;
    }

    public void setСoef(double сoef) {
        this.сoef = сoef;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", height=" + height +
                ", weight=" + weight +
                ", qoef=" + сoef +
                ", subscription='" + subscription + '\'' +
                ", coach='" + coach + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
