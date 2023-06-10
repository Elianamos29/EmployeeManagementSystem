package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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

    public void markTimeIn() {
        //todo
    }

    public void markTimeOut() {
        //todo
    }

    public ObservableList<Attendance> attendancesListData(ComboBox<?> combChooseDate) throws ParseException {
        ObservableList<Attendance> listData = FXCollections.observableArrayList();
        String date1 = (String) combChooseDate.getSelectionModel().getSelectedItem();
        Date today = new SimpleDateFormat("yy/MM/dd").parse(date1.replace("-", "/"));
        java.sql.Date sqlDate = new java.sql.Date(today.getTime());

        String sql = "SELECT * FROM attendance WHERE date = '" + sqlDate + "'";
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Attendance attendance;

            while (result.next()) {
                attendance = new Attendance(result.getDate("date"),
                        result.getInt("staffID"),
                        result.getString("name"),
                        result.getString("status"),
                        result.getTime("timeIN"),
                        result.getTime("timeOut"));
                listData.add(attendance);
            }
        } catch (Exception e) {e.printStackTrace();}

        return listData;
    }

    private static ObservableList<Attendance> attendanceList;

    public void showAttendanceList() {
        //todo
    }

    public void attendanceSelect(TableView<Attendance> tblAttendance) {
        Attendance attendanceSelected = tblAttendance.getSelectionModel().getSelectedItem();
        int num = tblAttendance.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        Employee emp = getDetails(attendanceSelected.getStaffID());
        //todo
    }

    private Employee getDetails(int staffID) {
        //todo

        return null;
    }
}
