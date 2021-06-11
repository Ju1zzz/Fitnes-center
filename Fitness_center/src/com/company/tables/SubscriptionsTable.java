package com.company.tables;

import com.company.basic.Subscription;
import com.company.db.DBWorker;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class SubscriptionsTable extends AbstractTableModel {
    private List<Subscription> data;
    public SubscriptionsTable () throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllsubs();
        DBWorker.closeConnection();
    }
    public void update() throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllsubs();
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
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            case 3:
                return Integer.class;
            case 4:
                return String.class;
        }
        return null;
    }
    public String getColumnName(int column) {
        switch (column){
            case 0: return "id";
            case 1: return "Название";
            case 2: return "Время";
            case 3: return "Срок";
            case 4: return "Заморозка";
        }
        return "";
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Subscription subscription =data.get(rowIndex);
        switch (columnIndex){
            case 0: return subscription.getId();
            case 1: return subscription.getTitle();
            case 2: return subscription.getType();
            case 3: return subscription.getDuration();
            case 4: if (subscription.isFreeze()){return "Да";}
            else {return "Нет";}
        }
        return "";
    }
    public Subscription getSub(int row){
        return data.get(row);
    }

    public void add(Subscription subscription) {
        try {
        DBWorker.initDB();
        DBWorker.addSub(subscription);
        update();
        DBWorker.closeConnection();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
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
    }

}
