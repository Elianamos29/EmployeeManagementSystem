package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Attendance {
    private Date date;
    private int staffID;
    private String name;
    private String status;
    private Date timeIn;
    private Date timeOut;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    public Attendance(){}
    public Attendance(Date date, int staffID, String name, String status, Date timeIn, Date timeOut) {
        this.date = date;
        this.staffID = staffID;
        this.name = name;
        this.status = status;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    public Date getDate() {
        return date;
    }

    public int getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Date getTimeIn() {
        return timeIn;
    }

    public Date getTimeOut() {
        return timeOut;
    }

    public void timeOut(CheckBox checkTimeout) {
    }
}
