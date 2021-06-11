package com.company.tables;

import com.company.basic.Coach;
import com.company.db.DBWorker;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class CoachTable extends AbstractTableModel {
    private List<Coach> data;
    public CoachTable () throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllcoaches();
        DBWorker.closeConnection();
    }
    public void update() throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllcoaches();
        DBWorker.closeConnection();
        this.fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0: return "id";
            case 1: return "Имя";
            case 2: return "Срок работы";
            case 3: return "Квалификация";
            case 4: return "Смена";
        }
        return "";
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Coach coach =data.get(rowIndex);
        switch (columnIndex){
            case 0: return coach.getId();
            case 1: return coach.getName();
            case 2: return coach.getYears_exp();
            case 3: return coach.getQualification();
            case 4: return coach.getTimetable();
        }
        return "";
    }
    public Coach getCoach(int row){
        return data.get(row);
    }

    public void add(Coach coach) {
        try {
        DBWorker.initDB();
        DBWorker.addCoach(coach);
        update();
        DBWorker.closeConnection();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void  remove(int [] id) {
        try{
        DBWorker.initDB();
        DBWorker.deleteCoach(id);
        update();
        DBWorker.closeConnection();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void updateCl(int id, Coach coach) {
        try {
        DBWorker.initDB();
        DBWorker.updateCoach(id, coach);
        update();
        DBWorker.closeConnection();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
