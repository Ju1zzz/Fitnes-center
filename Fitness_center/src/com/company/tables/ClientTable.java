package com.company.tables;

import com.company.basic.Client;
import com.company.db.DBWorker;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class ClientTable extends AbstractTableModel {
    private List<Client> data;
    public ClientTable () throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllclients();
        DBWorker.closeConnection();
    }
    public void update() throws SQLException {
        DBWorker.initDB();
        data=DBWorker.getAllclients();
        DBWorker.closeConnection();
       this.fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
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
                return Integer.class;
            case 4:
                return Integer.class;
            case 5:
                return Double.class;
            case 6:
                return String.class;
            case 7:
                return String.class;
            case 8:
                return String.class;
            case 9:
                return String.class;
        }
        return null;
    }
    public String getColumnName(int column) {
        switch (column){
            case 0: return "id";
            case 1: return "Имя";
            case 2: return "Пол";
            case 3: return "Рост";
            case 4: return "Вес";
            case 5: return "ИМТ";
            case 6: return "Абонемент";
            case 7: return  "Тренер";
            case 8: return  "Комментарий";
            case 9: return "Телефон";
        }
        return "";
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client=data.get(rowIndex);
        switch (columnIndex){
            case 0: return client.getId();
            case 1: return client.getName();
            case 2: return client.getSex();
            case 3: return client.getHeight();
            case 4: return client.getWeight();
            case 5: return client.getСoef();
            case 6: return client.getSubscription();
            case 7: return  client.getCoach();
            case 8: return  client.getComment();
            case 9: return client.getPhone();
        }
        return "";
    }
    public Client getClient(int row){
        return data.get(row);
    }

    public void add(Client clients)  {
        try{
        DBWorker.initDB();
        DBWorker.addClient(clients);
        update();
        DBWorker.closeConnection();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public void  remove(int [] id) {
       try{ DBWorker.initDB();
        DBWorker.deleteClients(id);
        update();
        DBWorker.closeConnection();}
       catch (SQLException throwables){
           throwables.printStackTrace();
       }
    }
    public void updateCl(int id, Client clients){
        try {DBWorker.initDB();
        DBWorker.updateClient(id, clients);
        update();
        DBWorker.closeConnection();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
