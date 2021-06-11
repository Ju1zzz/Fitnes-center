package com.company;

import com.company.basic.Client;
import com.company.basic.CurrentDay;
import com.company.db.DBWorker;
import com.company.gui.MainWindow;
import com.company.tables.CoachTable;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args)  {
        new MainWindow();
    }
}
