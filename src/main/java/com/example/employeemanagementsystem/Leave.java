package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
public class Leave {
    private int requestID;
    private int staffID;
    private String leaveType;
    private String description;
    private Date fromDate;
    private Date toDate;
    private String status;
    private String comment;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    public Leave() {

    }

    public Leave(int requestID, int staffID, String leaveType, String description, Date fromDate, Date toDate, String status, String comment) {
        this.requestID = requestID;
        this.staffID = staffID;
        this.leaveType = leaveType;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
        this.comment = comment;
    }

    public int getRequestID() {
        return requestID;
    }

    public int getStaffID() {
        return staffID;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public String getDescription() {
        return description;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public String getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public ObservableList<Leave> leaveListData() {

        ObservableList<Leave> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM leaverequest";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Leave leave;

            while (result.next()) {
                leave = new Leave(result.getInt("requestID"),
                        result.getInt("staffID"),
                        result.getString("leaveType"),
                        result.getString("description"),
                        result.getDate("fromDate"),
                        result.getDate("toDate"),
                        result.getString("status"),
                        result.getString("comment"));
                listData.add(leave);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private static ObservableList<Leave> leaveList;


    public void showLeaveListData(TableView<Leave> tblView, TableColumn<Leave, Integer> colRequestid, TableColumn<Leave, Integer> colStaffid, TableColumn<Leave, String> colLeavetype, TableColumn<Leave, Date> colFrom, TableColumn<Leave, Date> colTo) {
        leaveList = leaveListData();

        colRequestid.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        colStaffid.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        colLeavetype.setCellValueFactory(new PropertyValueFactory<>("leaveType"));
        colFrom.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        colTo.setCellValueFactory(new PropertyValueFactory<>("toDate"));

        tblView.setItems(leaveList);

    }
}
