package com.company.tables;

import com.company.basic.CurrentDay;
import com.company.db.DBWorker;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class CurrentTable extends AbstractTableModel {
    private List<CurrentDay> data;
    public CurrentTable () throws SQLException {
        DBWorker.initDB();
        DBWorker.deleteDays();
        data=DBWorker.getAll();
        DBWorker.closeConnection();
    }
    public void update() throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAll();
        DBWorker.closeConnection();
        this.fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
        }
        return null;
    }
    public String getColumnName(int column) {
        switch (column){
            case 0: return "id";
            case 1: return "Тренер";
            case 2: return "Клиенты";
            case 3: return "Телефоны";
        }
        return "";
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CurrentDay day=data.get(rowIndex);
        switch (columnIndex){
            case 0: return day.getId();
            case 1: return day.getName();
            case 2: return day.getClients();
            case 3: return day.getPhone();
        }
        return "";
    }
    public CurrentDay getDay (int row){
        return data.get(row);
    }

    public void add(CurrentDay day) {
        try {
            DBWorker.initDB();
            DBWorker.addDay(day);
            update();
            DBWorker.closeConnection();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void delete(){
        try {
            DBWorker.initDB();
            DBWorker.deleteDays();
            update();
            DBWorker.closeConnection();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }/*
    public void  remove(int [] id)  {
        try{ DBWorker.initDB();
            DBWorker.deleteSub(id);
            update();
            DBWorker.closeConnection();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void updateCl(int id, Subscription subscription){
        try {
            DBWorker.initDB();
            DBWorker.updateSub(id, subscription);
            update();
            DBWorker.closeConnection();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }*/
}
