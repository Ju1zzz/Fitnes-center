package com.company.gui;

import com.company.basic.Client;
import com.company.basic.Coach;
import com.company.basic.CurrentDay;
import com.company.basic.Subscription;
import com.company.db.DBWorker;
import com.company.tables.ClientTable;
import com.company.tables.CoachTable;
import com.company.tables.CurrentTable;
import com.company.tables.SubscriptionsTable;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MainWindow extends JFrame {
    private JPanel butpanelS, butpanelC, butpanelCH, main, butpanelCfind, butpanelSfind, butpanelCHfind;
    private JTable tbclients, tbsubs, tbcoaches, tborders;
    private JButton addCoach, deleteCoach, changeCoach, findCoach;
    private JButton addSub, deleteSub, changeSub, findSub;
    private JButton addClient, deleteClient, changeClient, findClient;
    private JButton klient, sub, coach;
    private JButton upd;
    private ClientTable cmodel;
    private SubscriptionsTable smodel;
    private CoachTable chmodel;
    private CurrentTable daymodel;
    private JTextField filterText1, filterText2, filterText3;
    private TableRowSorter<ClientTable> sorter1;
    private TableRowSorter<SubscriptionsTable> sorter2;
    private TableRowSorter<CoachTable> sorter3;

    public MainWindow() {
        super("Администрирование фитнес центра");
        setSize(900, 200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DBWorker.initDB();
        init();
        JPanel panel = new JPanel(new BorderLayout());
        try {
            daymodel = new CurrentTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tborders = new JTable(daymodel);
        tborders.setRowHeight(tborders.getRowHeight()+70);
        tborders.getTableHeader().setOpaque(false);
        tborders.getTableHeader().setBackground(new Color(243, 193,255));
        JScrollPane jScrollPaneClients = new JScrollPane(tborders);
        jScrollPaneClients.getViewport().setBackground(new Color(248,216,255));
        panel.add(jScrollPaneClients);
        ClockLabel dateLable = new ClockLabel("date");
        upd = new JButton("Обновить"); upd.setBackground(new Color(255,255,255));

        Date dateNow = new Date();
        SimpleDateFormat day = new SimpleDateFormat("EEEE");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        System.out.println(day.format(dateNow) + " " + time.format(dateNow));

        upd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    daymodel.delete();
                    add(day.format(dateNow), time.format(dateNow));
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
        });
        JPanel downpanel = new JPanel();
        downpanel.setBackground(new Color(248,216,255));
        downpanel.add(dateLable, BorderLayout.SOUTH);
        this.add(panel);
        this.add(downpanel, BorderLayout.SOUTH);
        this.add(upd, BorderLayout.EAST);
        this.add(main, BorderLayout.NORTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void init() {
        addClient = new JButton("Добавить Клиента"); addClient.setBackground(new Color(255,255,255));
        addClient.addActionListener(new MyListener());
        deleteClient = new JButton("Удалить данные о клиенте"); deleteClient.setBackground(new Color(255,255,255));
        deleteClient.addActionListener(new MyListener());
        changeClient = new JButton("Изменить данные о клиенте"); changeClient.setBackground(new Color(255,255,255));
        changeClient.addActionListener(new MyListener());
        findClient = new JButton("Найти клиента"); findClient.setBackground(new Color(255,255,255));
        findClient.addActionListener(new MyListener());
        filterText3 = new JTextField(10);
        addSub = new JButton("Добавить Абонемент"); addSub.setBackground(new Color(255,255,255));
        addSub.addActionListener(new MyListener());
        deleteSub = new JButton("Удалить данные об абонементе"); deleteSub.setBackground(new Color(255,255,255));
        deleteSub.addActionListener(new MyListener());
        changeSub = new JButton("Изменить данные об абонементе"); changeSub.setBackground(new Color(255,255,255));
        changeSub.addActionListener(new MyListener());
        findSub = new JButton("Найти абонемент"); findSub.setBackground(new Color(255,255,255));
        findSub.addActionListener(new MyListener());
        filterText2 = new JTextField(10);
        addCoach = new JButton("Добавить Тренера"); addCoach.setBackground(new Color(255,255,255));
        addCoach.addActionListener(new MyListener());
        deleteCoach = new JButton("Удалить данные о тренере"); deleteCoach.setBackground(new Color(255,255,255));
        deleteCoach.addActionListener(new MyListener());
        changeCoach = new JButton("Изменить данные о тренере"); changeCoach.setBackground(new Color(255,255,255));
        changeCoach.addActionListener(new MyListener());
        findCoach = new JButton("Найти тренера"); findCoach.setBackground(new Color(255,255,255));
        findCoach.addActionListener(new MyListener());
        filterText1 = new JTextField(10);
        GridLayout gl = new GridLayout(2, 3, 3, 10);
        butpanelC = new JPanel(gl);
        butpanelC.setBackground(new Color(248,216,255));
        butpanelC.add(addClient);
        butpanelC.add(deleteClient);
        butpanelC.add(changeClient);
        butpanelS = new JPanel(gl);
        butpanelS.setBackground(new Color(248,216,255));
        butpanelS.add(addSub);
        butpanelS.add(deleteSub);
        butpanelS.add(changeSub);
        butpanelCH = new JPanel(gl);
        butpanelCH.setBackground(new Color(248,216,255));
        butpanelCH.add(addCoach);
        butpanelCH.add(deleteCoach);
        butpanelCH.add(changeCoach);
        butpanelCfind = new JPanel(new GridLayout(2, 1, 10, 10));
        butpanelCfind.setBackground(new Color(248,216,255));
        butpanelCfind.add(filterText3);
        butpanelCfind.add(findClient);
        butpanelSfind = new JPanel(new GridLayout(2, 1, 10, 10));
        butpanelSfind.setBackground(new Color(248,216,255));
        butpanelSfind.add(filterText2);
        butpanelSfind.add(findSub);
        butpanelCHfind = new JPanel(new GridLayout(2, 1, 10, 10));
        butpanelCHfind.setBackground(new Color(248,216,255));
        butpanelCHfind.add(filterText1);
        butpanelCHfind.add(findCoach);
        klient = new JButton("Клиенты"); klient.setBackground(new Color(255,255,255));
        klient.addActionListener(new MyListener());
        sub = new JButton("Абонементы"); sub.setBackground(new Color(255,255,255));
        sub.addActionListener(new MyListener());
        coach = new JButton("Тренера"); coach.setBackground(new Color(255,255,255));
        coach.addActionListener(new MyListener());
        main = new JPanel(new GridLayout(1, 3, 10, 10));
        main.setBackground(new Color(248,216,255));
        main.add(klient);
        main.add(sub);
        main.add(coach);

        try {
            chmodel = new CoachTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        tbcoaches = new JTable(chmodel);
        tbcoaches.setRowHeight(tbcoaches.getRowHeight() + 90);
        tbcoaches.getTableHeader().setOpaque(false);
        tbcoaches.getTableHeader().setBackground(new Color(243, 193,255));


        sorter3 = new TableRowSorter<>(chmodel);
        tbcoaches.setRowSorter(sorter3);

        try {
            cmodel = new ClientTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tbclients = new JTable(cmodel);
        tbclients.getTableHeader().setOpaque(false);
        tbclients.getTableHeader().setBackground(new Color(243, 193,255));
        sorter1 = new TableRowSorter<>(cmodel);
        tbclients.setRowSorter(sorter1);
    }

    class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Клиенты":
                    AllClients();
                    break;
                case "Абонементы":
                    AllSubs();
                    break;
                case "Тренера":
                    AllCoaches();
                    break;
                case "Добавить Клиента":
                    AdditionClient();
                    break;
                case "Добавить Абонемент":
                    AdditionSub();
                    break;
                case "Добавить Тренера":
                    AdditionCoach();
                    break;
                case "Удалить данные о клиенте":

                        int[] count1 = tbclients.getSelectedRows();
                        int[] ids1 = new int[count1.length];
                        for (int i = 0; i < count1.length; i++) {
                            ids1[i] = (int) cmodel.getValueAt(count1[i], 0);
                        }
                        if(ids1.length==0){
                            JOptionPane.showMessageDialog(null, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                        }else {
                            cmodel.remove(ids1);
                        }

                    break;
                case "Удалить данные о тренере":
                        int[] count2 = tbcoaches.getSelectedRows();
                        int[] ids2 = new int[count2.length];
                        for (int i = 0; i < count2.length; i++) {
                            ids2[i] = (int) chmodel.getValueAt(count2[i], 0);
                        }
                        if(ids2.length==0){
                            JOptionPane.showMessageDialog(null, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                        }else {
                            chmodel.remove(ids2);
                        }
                    break;
                case "Удалить данные об абонементе":

                        int[] count = tbsubs.getSelectedRows();
                        int[] ids = new int[count.length];
                        for (int i = 0; i < count.length; i++) {
                            ids[i] = (int) smodel.getValueAt(count[i], 0);
                        }
                    if(ids.length==0){
                        JOptionPane.showMessageDialog(null, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                    }else {
                        smodel.remove(ids);
                    }
                    break;
                case "Изменить данные о клиенте":
                    try{
                    UpdateClient();
                }catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                }
                    break;
                case "Изменить данные об абонементе":
                    try{
                        UpdatingSub();
                    }catch (IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(null, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "Изменить данные о тренере":
                    try{
                    UpdatingCoach();
                }catch (IndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(null, "Выберите запись", "Error", JOptionPane.ERROR_MESSAGE);
                }
                    break;
                case "Найти клиента":
                    String ctext = filterText3.getText();
                    if (ctext.length() == 0) {
                        sorter1.setRowFilter(null);
                    } else {
                        sorter1.setRowFilter(RowFilter.regexFilter(ctext));
                    }
                    break;
                case "Найти абонемент":
                    String stext = filterText2.getText();
                    if (stext.length() == 0) {
                        sorter2.setRowFilter(null);
                    } else {
                        sorter2.setRowFilter(RowFilter.regexFilter(stext));
                    }
                    break;
                case "Найти тренера":
                    String chtext = filterText1.getText();
                    if (chtext.length() == 0) {
                        sorter3.setRowFilter(null);
                    } else {
                        sorter3.setRowFilter(RowFilter.regexFilter(chtext));
                    }
                    break;
            }
        }
    }

    public void AdditionClient() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(650, 500);
        dialog.setTitle("Добавление клиента");
        dialog.setSize(450, 300);
        GridLayout glA = new GridLayout(8, 2, 3, 3);
        JPanel add = new JPanel(glA);
        add.setBackground(new Color(248,216,255));
        JLabel name = new JLabel("  Имя клиента");
        JLabel sex = new JLabel("  Пол");
        JLabel height = new JLabel("   Рост в см");
        JLabel weight = new JLabel("   Вес");
        JLabel sub = new JLabel("   Абонемент");
        JLabel coach = new JLabel("   Тренер");
        JLabel comment = new JLabel("   Комментарий");
        JLabel phone = new JLabel("   Телефон");
        JTextField Name = new JTextField(50);
        JTextField Phone = new JTextField(50);
        String[] items = {"Мужской", "Женский",};
        JComboBox Sex = new JComboBox(items); Sex.setBackground(new Color(243, 193,255));
        JComboBox Sub = new JComboBox(DBWorker.getSub()); Sub.setBackground(new Color(243, 193,255));
        JComboBox Coach = new JComboBox(DBWorker.getCoach()); Coach.setBackground(new Color(243, 193,255));
        JTextField Height = new JTextField(10);
        JTextField Weight = new JTextField(10);
        JTextField Comment = new JTextField(50);
        JButton Add = new JButton("Добавить"); Add.setBackground(Color.white);
        add.add(name);
        add.add(Name);
        add.add(sex);
        add.add(Sex);
        add.add(height);
        add.add(Height);
        add.add(weight);
        add.add(Weight);
        add.add(sub);
        add.add(Sub);
        add.add(coach);
        add.add(Coach);
        add.add(comment);
        add.add(Comment);
        add.add(phone);
        add.add(Phone);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (Name.getText().equals("") || Height.getText().equals("") || Weight.getText().equals("") || Phone.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        double y = Double.parseDouble(Weight.getText()) / Math.pow((Double.parseDouble(Height.getText()) * 100), 2);
                        cmodel.add(new Client(Name.getText(), (String) Sex.getSelectedItem(), Double.parseDouble(Height.getText()),
                                Double.parseDouble(Weight.getText()), y, (String) Sub.getSelectedItem(), (String) Coach.getSelectedItem(), Comment.getText(), Phone.getText()));
                        Name.setText(null);
                        Height.setText(null);
                        Weight.setText(null);
                        Comment.setText(null);
                        Phone.setText(null);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Введите рост в сантиметрах и вес в килограммах", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        dialog.add(Add, BorderLayout.SOUTH);
        dialog.add(add);
        dialog.setVisible(true);
    }

    public void UpdateClient() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(650, 500);
        dialog.setTitle("Изменение данных клиента");
        dialog.setSize(450, 300);
        Client client = cmodel.getClient(tbclients.getSelectedRow());
        GridLayout glA = new GridLayout(8, 2, 3, 3);
        JPanel add = new JPanel(glA);
        add.setBackground(new Color(248,216,255));
        JLabel name = new JLabel("  Имя клиента");
        JLabel sex = new JLabel("  Пол");
        JLabel height = new JLabel("   Рост в см");
        JLabel weight = new JLabel("   Вес");
        JLabel sub = new JLabel("   Абонемент");
        JLabel coach = new JLabel("   Тренер");
        JLabel comment = new JLabel("   Комментарий");
        JLabel phone = new JLabel("   Телефон");
        JTextField Name = new JTextField(50);
        Name.setText(client.getName());
        JTextField Phone = new JTextField(50);
        Phone.setText(client.getPhone());
        String[] items = {"Мужской", "Женский",};
        JComboBox Sex = new JComboBox(items); Sex.setBackground(new Color(243, 193,255));
        Sex.setSelectedItem(client.getSex());
        JComboBox Sub = new JComboBox(DBWorker.getSub()); Sub.setBackground(new Color(243, 193,255));
        Sub.setSelectedItem(client.getSubscription());
        JComboBox Coach = new JComboBox(DBWorker.getCoach()); Coach.setBackground(new Color(243, 193,255));
        Coach.setSelectedItem(client.getCoach());
        JTextField Height = new JTextField(10);
        Height.setText(String.valueOf(client.getHeight()));
        JTextField Weight = new JTextField(10);
        Weight.setText(String.valueOf(client.getWeight()));
        JTextField Comment = new JTextField(50);
        Comment.setText(client.getComment());
        JButton Add = new JButton("Изменить"); Add.setBackground(Color.white);
        add.add(name);
        add.add(Name);
        add.add(sex);
        add.add(Sex);
        add.add(height);
        add.add(Height);
        add.add(weight);
        add.add(Weight);
        add.add(sub);
        add.add(Sub);
        add.add(coach);
        add.add(Coach);
        add.add(comment);
        add.add(Comment);
        add.add(phone);
        add.add(Phone);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Name.getText().equals("") || Height.getText().equals("") || Weight.getText().equals("") || Phone.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        double y = Double.parseDouble(Weight.getText()) / Math.pow((Double.parseDouble(Height.getText()) * 100), 2);
                        cmodel.updateCl(client.getId(), new Client(Name.getText(), (String) Sex.getSelectedItem(), Double.parseDouble(Height.getText()),
                                Double.parseDouble(Weight.getText()), y, (String) Sub.getSelectedItem(), (String) Coach.getSelectedItem(), Comment.getText(), Phone.getText()));
                        Name.setText(null);
                        Height.setText(null);
                        Weight.setText(null);
                        Comment.setText(null);
                        Phone.setText(null);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Введите рост в сантиметрах и вес в килограммах", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        dialog.add(Add, BorderLayout.SOUTH);
        dialog.add(add);
        dialog.setVisible(true);
    }

    public void AllClients() {
        JDialog clients = new JDialog();
        clients.setModal(true);
        clients.setLocation(650, 500);
        clients.setTitle("Клиенты");
        clients.setSize(900, 500);
        init();
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane jScrollPaneClients = new JScrollPane(tbclients);
        jScrollPaneClients.getViewport().setBackground(new Color(248,216,255));
        panel.add(butpanelC, BorderLayout.NORTH);
        panel.add(butpanelCfind, BorderLayout.SOUTH);
        panel.add(jScrollPaneClients);
        clients.add(panel);
        clients.setVisible(true);
    }

    public void AllSubs() {
        JDialog clients = new JDialog();
        clients.setModal(true);
        clients.setLocation(650, 500);
        clients.setTitle("Абонементы");
        clients.setSize(700, 500);
        init();
        JPanel panel = new JPanel(new BorderLayout());
        try {
            smodel = new SubscriptionsTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tbsubs = new JTable(smodel);
        tbsubs.getTableHeader().setOpaque(false);
        tbsubs.getTableHeader().setBackground(new Color(243, 193,255));
        sorter2 = new TableRowSorter<>(smodel);
        tbsubs.setRowSorter(sorter2);
        JScrollPane jScrollPaneClients = new JScrollPane(tbsubs);
        jScrollPaneClients.getViewport().setBackground(new Color(248,216,255));
        panel.add(butpanelS, BorderLayout.NORTH);
        panel.add(butpanelSfind, BorderLayout.SOUTH);
        panel.add(jScrollPaneClients);
        clients.add(panel);
        clients.setVisible(true);
    }

    public void AllCoaches() {
        JDialog c = new JDialog();
        c.setModal(true);
        c.setLocation(650, 500);
        c.setTitle("Тренера");
        c.setSize(900, 500);
        init();
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane jScrollPaneClients = new JScrollPane(tbcoaches);
        jScrollPaneClients.getViewport().setBackground(new Color(248,216,255));
        panel.add(butpanelCH, BorderLayout.NORTH);
        panel.add(butpanelCHfind, BorderLayout.SOUTH);
        panel.add(jScrollPaneClients);
        c.add(panel);
        c.setVisible(true);
    }

    public void AdditionSub() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(650, 500);
        dialog.setTitle("Добавление абонемента");
        dialog.setSize(450, 300);
        GridLayout glA = new GridLayout(7, 2, 3, 3);
        JPanel add = new JPanel(glA);
        add.setBackground(new Color(248,216,255));
        JLabel name = new JLabel("  Название абонемента");
        JLabel type = new JLabel("  Время");
        JLabel duration = new JLabel("   Продолжительность в месяцах");
        JLabel freeze = new JLabel("   Заморозка");
        JTextField Name = new JTextField(50);
        String[] items = {"1", "3", "6", "12"};
        String[] items2 = {"7:00-17:00", "7:00-23:00"};
        JCheckBox freezeBox = new JCheckBox("Заморозка"); freezeBox.setBackground(new Color(248,216,255));
        JComboBox Duration = new JComboBox(items); Duration.setBackground(new Color(243, 193,255));
        JComboBox Type = new JComboBox(items2); Type.setBackground(new Color(243, 193,255));
        JButton Add = new JButton("Добавить"); Add.setBackground(Color.white);
        add.add(name);
        add.add(Name);
        add.add(type);
        add.add(Type);
        add.add(duration);
        add.add(Duration);
        add.add(freeze);
        add.add(freezeBox);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean typ;
                if (freezeBox.isSelected()) {
                    typ = true;
                } else {
                    typ = false;
                }
                if (Name.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    smodel.add(new Subscription(Name.getText(), (String) Type.getSelectedItem(), Integer.parseInt((String) Duration.getSelectedItem()), typ));
                    Name.setText(null);
                    freezeBox.setSelected(false);
                }
            }
        });
        dialog.add(Add, BorderLayout.SOUTH);
        dialog.add(add);
        dialog.setVisible(true);
    }

    public void UpdatingSub() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(650, 500);
        dialog.setTitle("Изменение данных об абонементе");
        dialog.setSize(450, 300);
        GridLayout glA = new GridLayout(7, 2, 3, 3);
        JPanel add = new JPanel(glA);
        add.setBackground(new Color(248,216,255));
        Subscription sub = smodel.getSub(tbsubs.getSelectedRow());
        JLabel name = new JLabel("  Название абонемента");
        JLabel type = new JLabel("  Время");
        JLabel duration = new JLabel("   Продолжительность в месяцах");
        JLabel freeze = new JLabel("   Заморозка");
        JTextField Name = new JTextField(50);
        Name.setText(sub.getTitle());
        String[] items = {"1", "3", "6", "12"};
        String[] items2 = {"7:00-17:00", "7:00-23:00"};
        JCheckBox freezeBox = new JCheckBox("Заморозка");freezeBox.setBackground(new Color(248,216,255));
        freezeBox.setSelected(sub.isFreeze());
        JComboBox Duration = new JComboBox(items);  Duration.setBackground(new Color(243, 193,255));
        Duration.setSelectedItem(sub.getDuration());
        JComboBox Type = new JComboBox(items2);  Type.setBackground(new Color(243, 193,255));
        Type.setSelectedItem(sub.getType());
        JButton Add = new JButton("Изменить"); Add.setBackground(Color.white);
        add.add(name);
        add.add(Name);
        add.add(type);
        add.add(Type);
        add.add(duration);
        add.add(Duration);
        add.add(freeze);
        add.add(freezeBox);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean typ;
                if (freezeBox.isSelected()) {
                    typ = true;
                } else {
                    typ = false;
                }
                if (Name.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    smodel.updateCl(sub.getId(), new Subscription(Name.getText(), (String) Type.getSelectedItem(), Integer.parseInt((String) Duration.getSelectedItem()), typ));
                    Name.setText(null);
                    freezeBox.setSelected(false);
                }
            }
        });
        dialog.add(Add, BorderLayout.SOUTH);
        dialog.add(add);
        dialog.setVisible(true);
    }

    public void AdditionCoach() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(650, 500);
        dialog.setTitle("Добавление тренера");
        dialog.setSize(450, 500);
        GridLayout glA = new GridLayout(15, 2, 3, 3);
        JPanel add = new JPanel(glA);
        add.setBackground(new Color(248,216,255));
        JLabel name = new JLabel("  Имя");
        JLabel years = new JLabel("  Срок работы");
        JLabel qual = new JLabel("   Квалификация");
        JTextField Name = new JTextField(50);
        JTextField Years = new JTextField(50);
        JTextField Qual = new JTextField(50);
        String[] items = {"07:00-13:00", "13:00-21:00", "07:00-21:00"};

        JCheckBox mondayBox = new JCheckBox("понедельник"); mondayBox.setBackground(new Color(248,216,255));
        JComboBox Time1 = new JComboBox(items); Time1.setBackground(new Color(243, 193,255));
        JCheckBox tuesdayBox = new JCheckBox("вторник"); tuesdayBox.setBackground(new Color(248,216,255));
        JComboBox Time2 = new JComboBox(items); Time2.setBackground(new Color(243, 193,255));
        JCheckBox wednesdayBox = new JCheckBox("среда"); wednesdayBox.setBackground(new Color(248,216,255));
        JComboBox Time3 = new JComboBox(items); Time3.setBackground(new Color(243, 193,255));
        JCheckBox thursdayBox = new JCheckBox("четверг"); thursdayBox.setBackground(new Color(248,216,255));
        JComboBox Time4 = new JComboBox(items); Time4.setBackground(new Color(243, 193,255));
        JCheckBox fridayBox = new JCheckBox("пятница"); fridayBox.setBackground(new Color(248,216,255));
        JComboBox Time5 = new JComboBox(items); Time5.setBackground(new Color(243, 193,255));
        JCheckBox saturdayBox = new JCheckBox("суббота"); saturdayBox.setBackground(new Color(248,216,255));
        JComboBox Time6 = new JComboBox(items); Time6.setBackground(new Color(243, 193,255));
        JCheckBox sundayBox = new JCheckBox("воскресенье"); sundayBox.setBackground(new Color(248,216,255));
        JComboBox Time7 = new JComboBox(items); Time7.setBackground(new Color(243, 193,255));
        StringBuilder sb = new StringBuilder();
        JButton Add = new JButton("Добавить"); Add.setBackground(Color.white);
        add.add(name);
        add.add(Name);
        add.add(years);
        add.add(Years);
        add.add(qual);
        add.add(Qual);
        add.add(mondayBox);
        add.add(Time1);
        add.add(tuesdayBox);
        add.add(Time2);
        add.add(wednesdayBox);
        add.add(Time3);
        add.add(thursdayBox);
        add.add(Time4);
        add.add(fridayBox);
        add.add(Time5);
        add.add(saturdayBox);
        add.add(Time6);
        add.add(sundayBox);
        add.add(Time7);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Name.getText().equals("") || Qual.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        if (mondayBox.isSelected()) {
                            sb.append("<html> понедельник " + Time1.getSelectedItem());
                        }
                        if (tuesdayBox.isSelected()) {
                            sb.append("<html> <br>вторник " + Time2.getSelectedItem());
                        }
                        if (wednesdayBox.isSelected()) {
                            sb.append("<html> <br>среда " + Time3.getSelectedItem());
                        }
                        if (thursdayBox.isSelected()) {
                            sb.append("<html> <br>четверг " + Time4.getSelectedItem());
                        }
                        if (fridayBox.isSelected()) {
                            sb.append("<html> <br>пятница " + Time5.getSelectedItem());
                        }
                        if (saturdayBox.isSelected()) {
                            sb.append("<html> <br>суббота " + Time6.getSelectedItem());
                        }
                        if (sundayBox.isSelected()) {
                            sb.append("<html> <br>воскресенье " + Time7.getSelectedItem() + "</html>");
                        }
                        String s = sb.toString();
                        chmodel.add(new Coach(Name.getText(), Integer.parseInt(Years.getText()), Qual.getText(), s));
                        Name.setText(null);
                        Years.setText(null);
                        Qual.setText(null);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Введите срок работы в годах", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        dialog.add(Add, BorderLayout.SOUTH);
        dialog.add(add);
        dialog.setVisible(true);
    }

    public void UpdatingCoach() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setLocation(650, 500);
        dialog.setTitle("Изменение данных о тренере");
        dialog.setSize(450, 500);
        GridLayout glA = new GridLayout(15, 2, 3, 3);
        JPanel add = new JPanel(glA);
        add.setBackground(new Color(248,216,255));
        Coach coach = chmodel.getCoach(tbcoaches.getSelectedRow());
        JLabel name = new JLabel("  Имя");
        JLabel years = new JLabel("  Срок работы");
        JLabel qual = new JLabel("   Квалификация");
        JTextField Name = new JTextField(50);
        Name.setText(coach.getName());
        JTextField Years = new JTextField(50);
        Years.setText(String.valueOf(coach.getYears_exp()));
        JTextField Qual = new JTextField(50);
        Qual.setText(coach.getQualification());
        String[] items = {"07:00-13:00", "13:00-21:00", "07:00-21:00"};
        JCheckBox mondayBox = new JCheckBox("понедельник"); mondayBox.setBackground(new Color(248,216,255));
        JComboBox Time1 = new JComboBox(items); Time1.setBackground(new Color(243, 193,255));
        JCheckBox tuesdayBox = new JCheckBox("вторник"); tuesdayBox.setBackground(new Color(248,216,255));
        JComboBox Time2 = new JComboBox(items); Time2.setBackground(new Color(243, 193,255));
        JCheckBox wednesdayBox = new JCheckBox("среда"); wednesdayBox.setBackground(new Color(248,216,255));
        JComboBox Time3 = new JComboBox(items); Time3.setBackground(new Color(243, 193,255));
        JCheckBox thursdayBox = new JCheckBox("четверг"); thursdayBox.setBackground(new Color(248,216,255));
        JComboBox Time4 = new JComboBox(items); Time4.setBackground(new Color(243, 193,255));
        JCheckBox fridayBox = new JCheckBox("пятница"); fridayBox.setBackground(new Color(248,216,255));
        JComboBox Time5 = new JComboBox(items); Time5.setBackground(new Color(243, 193,255));
        JCheckBox saturdayBox = new JCheckBox("суббота"); saturdayBox.setBackground(new Color(248,216,255));
        JComboBox Time6 = new JComboBox(items); Time6.setBackground(new Color(243, 193,255));
        JCheckBox sundayBox = new JCheckBox("воскресенье"); sundayBox.setBackground(new Color(248,216,255));
        JComboBox Time7 = new JComboBox(items); Time7.setBackground(new Color(243, 193,255));
        String[] times = coach.getTimetable().split(" |\\<br>|\\<html>");
        for (int i = 0; i < times.length; i++) {
            switch (times[i]) {
                case "понедельник":
                    mondayBox.setSelected(true);
                    Time1.setSelectedItem(times[i + 1]);
                    break;
                case "вторник":
                    tuesdayBox.setSelected(true);
                    Time2.setSelectedItem(times[i + 1]);
                    break;
                case "среда":
                    wednesdayBox.setSelected(true);
                    Time3.setSelectedItem(times[i + 1]);
                    break;
                case "четверг":
                    thursdayBox.setSelected(true);
                    Time4.setSelectedItem(times[i + 1]);
                    break;
                case "пятница":
                    fridayBox.setSelected(true);
                    Time5.setSelectedItem(times[i + 1]);
                    break;
                case "суббота":
                    saturdayBox.setSelected(true);
                    Time6.setSelectedItem(times[i + 1]);
                    break;
                case "воскресенье":
                    sundayBox.setSelected(true);
                    Time7.setSelectedItem(times[i + 1]);
                    break;
            }
        }
        StringBuilder sb = new StringBuilder();
        JButton Add = new JButton("Изменить"); Add.setBackground(Color.white);
        add.add(name);
        add.add(Name);
        add.add(years);
        add.add(Years);
        add.add(qual);
        add.add(Qual);
        add.add(mondayBox);
        add.add(Time1);
        add.add(tuesdayBox);
        add.add(Time2);
        add.add(wednesdayBox);
        add.add(Time3);
        add.add(thursdayBox);
        add.add(Time4);
        add.add(fridayBox);
        add.add(Time5);
        add.add(saturdayBox);
        add.add(Time6);
        add.add(sundayBox);
        add.add(Time7);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (Name.getText().equals("") || Qual.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Введите все необходимые данные", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        if (mondayBox.isSelected()) {
                            sb.append("<html> понедельник " + Time1.getSelectedItem());
                        }
                        if (tuesdayBox.isSelected()) {
                            sb.append("<html> <br>вторник " + Time2.getSelectedItem());
                        }
                        if (wednesdayBox.isSelected()) {
                            sb.append("<html> <br>среда " + Time3.getSelectedItem());
                        }
                        if (thursdayBox.isSelected()) {
                            sb.append("<html> <br>четверг " + Time4.getSelectedItem());
                        }
                        if (fridayBox.isSelected()) {
                            sb.append("<html> <br>пятница " + Time5.getSelectedItem());
                        }
                        if (saturdayBox.isSelected()) {
                            sb.append("<html> <br>суббота " + Time6.getSelectedItem());
                        }
                        if (sundayBox.isSelected()) {
                            sb.append("<html> <br>воскресенье " + Time7.getSelectedItem() + "<html>");
                        }
                        String s = sb.toString();
                        chmodel.updateCl(coach.getId(), new Coach(Name.getText(), Integer.parseInt(Years.getText()), Qual.getText(), s));
                        Name.setText(null);
                        Years.setText(null);
                        Qual.setText(null);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Введите срок работы в годах", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        dialog.add(Add, BorderLayout.SOUTH);
        dialog.add(add);
        dialog.setVisible(true);
    }

    class ClockLabel extends JLabel implements ActionListener {

        String type;
        SimpleDateFormat sdf;

        public ClockLabel(String type) {
            this.type = type;
            setForeground(Color.darkGray);
            sdf = new SimpleDateFormat("dd.MMMM.yyyy EEEE HH:mm:ss");
            setFont(new Font("sans-serif", Font.PLAIN, 20));
            Timer t = new Timer(1000, this);
            t.start();
        }

        public void actionPerformed(ActionEvent ae) {
            Date d = new Date();
            setText(sdf.format(d));
        }
    }

    private void add( String day, String time) throws ParseException {
        StringBuilder sb = new StringBuilder();
        StringBuilder bs = new StringBuilder();
        int id =-1;
        String name = "", client ="", phone = "";
        ArrayList<String> coaches = new ArrayList<>();
        ArrayList<String> idc = new ArrayList<>();
        init();
        for (int i = 0; i < chmodel.getRowCount(); i++) {
            Coach coach= chmodel.getCoach(i);
            String[] times = coach.getTimetable().split(" |\\<br>|\\<html>");
            for (int k = 0; k < times.length; k++) {
                LocalTime now = LocalTime.parse(time);
                if(times[k].equals(day)){
                    LocalTime start = LocalTime.parse(times[k+1].split("-|\\<html>")[0]);
                    LocalTime end = LocalTime.parse(times[k+1].split("-|\\<html>")[1]);
                    if(now.isAfter(start) &&now.isBefore(end))
                    {
                        coaches.add(chmodel.getValueAt(i,1).toString());
                        idc.add(chmodel.getValueAt(i,0).toString());
                    }
                }
            }
        }
            String [] names = new String[coaches.size()];
            coaches.toArray(names);
            if (names.length==0){JOptionPane.showMessageDialog(null, "К сожалению, в данный момент никто из тренеров не работает", "Оповещение", JOptionPane.INFORMATION_MESSAGE);}
            String [] ids = new String[coaches.size()];
            idc.toArray(ids);
        for (int i = 0; i < coaches.size(); i++) {
            for (int j = 0; j < cmodel.getRowCount(); j++) {

                if (names[i].equals(cmodel.getValueAt(j, 7))) {
                        id = Integer.parseInt(ids[i]);
                        name = names[i];
                        sb.append("<html><br>" + cmodel.getValueAt(j, 1).toString());
                        bs.append("<html><br>" + cmodel.getValueAt(j, 9).toString());

                } else {
                    id = Integer.parseInt(ids[i]);
                    name = names[i];
                }
            }
            client = sb.toString();
            phone = bs.toString();
            if (!name.equals("")) {
                daymodel.add(new CurrentDay(id, name, client, phone));
            }
            sb = new StringBuilder();
            bs = new StringBuilder();
        }
    }
}
