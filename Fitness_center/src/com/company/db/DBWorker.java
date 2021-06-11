package com.company.db;

import com.company.basic.Client;
import com.company.basic.Coach;
import com.company.basic.CurrentDay;
import com.company.basic.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBWorker {
    public static final String PATH_TO_DB_FILE = "Gym.db";
    public static final String URL = "jdbc:sqlite:" + PATH_TO_DB_FILE;
    public static Connection connection;

    public static void initDB() {
        try {
            connection = DriverManager.getConnection(URL);
            if (connection != null) {
               //createDB();
            }
        } catch (SQLException ex) {
            System.out.println("Ошибка подключения: " + ex);
        }
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public static void createDB()  {
       try{ Statement stat = connection.createStatement();
        stat.execute("CREATE TABLE if not exists 'subscriptions' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'title' text, 'type' text, 'duration' integer, 'freeze' integer);");
        stat.execute("CREATE TABLE if not exists 'coaches' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'year_exp' integer, 'qualification' text, 'timetable' text);");
        stat.execute("CREATE TABLE if not exists 'client' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'sex' text, 'height' real, 'weight' real, 'coef' real, 'subscription' TEXT NOT NULL, 'coach' TEXT, 'comment' text, 'phone' text,  FOREIGN KEY (subscription) REFERENCES subscriptions (title)ON DELETE CASCADE FOREIGN KEY (coach) REFERENCES coaches (name)ON DELETE CASCADE);");
        stat.execute("CREATE TABLE if not exists 'now' ('id_coach' integer, 'coach_name' text, 'klients' text, 'phoneCl' text);");
        System.out.println("Таблицы созданы");}
       catch (SQLException ex){
           ex.printStackTrace();
       }
    }
    public static void addClient(Client client)  {
       try{ PreparedStatement statement=connection.prepareStatement("INSERT INTO client('name','sex', 'height', 'weight', 'coef', 'subscription', 'coach', 'comment', 'phone') " +
                "VALUES(?,?,?,?,?,?,?,?,?);");
        statement.setObject(1, client.getName());
        statement.setObject(2, client.getSex());
        statement.setObject(3,client.getHeight());
        statement.setObject(4,client.getWeight());
        statement.setObject(5, client.getСoef());
        statement.setObject(6,client.getSubscription());
        statement.setObject(7,client.getCoach());
        statement.setObject(8, client.getComment());
        statement.setObject(9, client.getPhone());
        statement.executeUpdate();
        statement.close();}
       catch (SQLException ex) {
           System.out.println("client prob");
       }
    }
    public static List<Client> getAllclients() {
        try{
        Statement statement = connection.createStatement();
        List<Client> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM client");
        while (resultSet.next()) {
            list.add(new Client(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getString("sex"),
                    resultSet.getDouble("height"), resultSet.getDouble("weight"),resultSet.getDouble("coef"),
                    resultSet.getString("subscription"), resultSet.getString("coach"), resultSet.getString("comment"), resultSet.getString("phone")));
        }
        resultSet.close();
        statement.close();
        return list;}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
    public static void deleteClients(int [] id)
    {  try {
        for (int i = 0; i < id.length; i++) {
            PreparedStatement Stat = connection.prepareStatement("DELETE FROM client WHERE client.id =?");
            Stat.setObject(1,id[i]);
            Stat.executeUpdate();
            Stat.close();}
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }

    public static void updateClient(int id, Client client)  {
        try{
        PreparedStatement statement = connection.prepareStatement("UPDATE client SET name=?, height=?, weight=?, coef = ?, subscription=?, coach=?, comment=?, phone=? WHERE id=?");
        statement.setObject(1, client.getName());
        statement.setObject(2,client.getHeight());
        statement.setObject(3,client.getWeight());
        statement.setObject(4,client.getСoef());
        statement.setObject(5,client.getSubscription());
        statement.setObject(6,client.getCoach());
        statement.setObject(7, client.getComment());
        statement.setObject(8, client.getPhone());
        statement.setObject(9, id);
        statement.execute();
        statement.close();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public static void addSub(Subscription sub)  {
        try{ PreparedStatement statement=connection.prepareStatement("INSERT INTO subscriptions('title', 'type', 'duration', 'freeze') " +
                "VALUES(?,?,?,?);");
            statement.setObject(1, sub.getTitle());
            statement.setObject(2, sub.getType());
            statement.setObject(3,sub.getDuration());
            statement.setObject(4,sub.isFreeze());
            statement.executeUpdate();
            statement.close();}
        catch (SQLException ex) {
            System.out.println("sub prob");
        }
    }
    public static List<Subscription> getAllsubs() {
        try{
        Statement statement = connection.createStatement();
        List<Subscription> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM subscriptions");
        while (resultSet.next()) {
            list.add(new Subscription(resultSet.getInt("id"),resultSet.getString("title"), resultSet.getString("type"),
                    resultSet.getInt("duration"), resultSet.getBoolean("freeze")));
        }
        resultSet.close();
        statement.close();
        return list;}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
    public static void deleteSub(int [] id)
    {   try {
        for (int i = 0; i < id.length; i++) {
            PreparedStatement Stat = connection.prepareStatement("DELETE FROM subscriptions WHERE subscriptions.id =?");
            Stat.setObject(1,id[i]);
            Stat.executeUpdate();
            Stat.close();}
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }
    public static void updateSub(int id, Subscription sub)  {
        try {
        PreparedStatement statement = connection.prepareStatement("UPDATE subscriptions SET title=?, type=?, duration=? WHERE id=?");
        statement.setObject(1, sub.getTitle());
        statement.setObject(2,sub.getType());
        statement.setObject(3, sub.getDuration());
        statement.setObject(4,id);
        statement.execute();
        statement.close();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public static void addCoach(Coach coach)  {
        try{ PreparedStatement statement=connection.prepareStatement("INSERT INTO coaches('name', 'year_exp', 'qualification', 'timetable') " +
                "VALUES(?,?,?,?);");
            statement.setObject(1, coach.getName());
            statement.setObject(2, coach.getYears_exp());
            statement.setObject(3, coach.getQualification());
            statement.setObject(4, coach.getTimetable());
            statement.executeUpdate();
            statement.close();}
        catch (SQLException ex) {
            System.out.println("coach prob");
        }
    }
    public static List<Coach> getAllcoaches()  {
        try{
        Statement statement = connection.createStatement();
        List<Coach> list = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM coaches");
        while (resultSet.next()) {
            list.add(new Coach(resultSet.getInt("id"),resultSet.getString("name"), resultSet.getInt("year_exp"),
                    resultSet.getString("qualification"), resultSet.getString("timetable")));
        }
        resultSet.close();
        statement.close();
        return list;}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return  null;
    }
    public static void deleteCoach(int [] id)
    {     try {
                for (int i = 0; i < id.length; i++) {
                PreparedStatement Stat = connection.prepareStatement("DELETE FROM coaches WHERE coaches.id =?");
                Stat.setObject(1,id[i]);
                Stat.executeUpdate();
                Stat.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }
    public static void updateCoach(int id, Coach coach)  {
        try{
        PreparedStatement statement = connection.prepareStatement("UPDATE coaches SET name=?, year_exp=?, qualification=?, timetable=? WHERE id=?");
        statement.setObject(1, coach.getName());
        statement.setObject(2, coach.getYears_exp());
        statement.setObject(3, coach.getQualification());
        statement.setObject(4, coach.getTimetable());
        statement.setObject(5, id);
        statement.execute();
        statement.close();}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    public static String [] getSub(){
        try{
        initDB();
        Statement statement = connection.createStatement();
        List <String> subs = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT title FROM subscriptions");
        while (resultSet.next()) {
            subs.add(resultSet.getString("title"));
        }
        String [] sub = new String[subs.size()];
        subs.toArray(sub);
        resultSet.close();
        statement.close();
        return sub;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        closeConnection();
        return null;
    }
    public static String [] getCoach(){
        try{
            initDB();
            Statement statement = connection.createStatement();
            List <String> coaches = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM coaches");
            while (resultSet.next()) {
                coaches.add(resultSet.getString("name"));
            }
            String [] coach = new String[coaches.size()];
            coaches.toArray(coach);
            resultSet.close();
            statement.close();
            return coach;
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        closeConnection();
        return null;
    }
    public static List<CurrentDay> getAll()  {
        try{
            Statement statement = connection.createStatement();
            List<CurrentDay> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM now");
            while (resultSet.next()) {
                list.add(new CurrentDay(resultSet.getInt("id_coach"),resultSet.getString("coach_name"),
                        resultSet.getString("klients"), resultSet.getString("phoneCl")));
            }
            resultSet.close();
            statement.close();
            return list;}
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return  null;
    }

    public static void addDay(CurrentDay day)  {
        try{ PreparedStatement statement=connection.prepareStatement("INSERT INTO now('id_coach', 'coach_name', 'klients', 'phoneCl')" +
                "VALUES(?,?,?,?);");
            statement.setObject(1, day.getId());
            statement.setObject(2, day.getName());
            statement.setObject(3, day.getClients());
            statement.setObject(4, day.getPhone());
            statement.executeUpdate();
            statement.close();}
        catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("days prob");
        }
    }
    public static void deleteDays()
    {     try {
            PreparedStatement Stat = connection.prepareStatement("DELETE FROM now");
            Stat.executeUpdate();
            Stat.close();
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    }
}
