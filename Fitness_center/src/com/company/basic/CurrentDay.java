package com.company.basic;

import java.util.ArrayList;

public class CurrentDay {
    private int id;
    private String name;
    private String clients;
    private  String phone;

    public CurrentDay(int id, String name, String clients, String phone) {
        this.id = id;
        this.name = name;
        this.clients = clients;
        this.phone=phone;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getClients() {
        return clients;
    }

    public void setClients(String clients) {
        this.clients = clients;
    }
}
