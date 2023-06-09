package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;

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

    public ObservableList<Leave> leaveListData(String status) {

        ObservableList<Leave> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM leaverequest WHERE status = '" + status + "'";

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


    public void showLeaveListData(String status, TableView<Leave> tblView, TableColumn<Leave, Integer> colRequestid, TableColumn<Leave, Integer> colStaffid, TableColumn<Leave, String> colLeavetype, TableColumn<Leave, Date> colFrom, TableColumn<Leave, Date> colTo) {
        leaveList = leaveListData(status);

        colRequestid.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        colStaffid.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        colLeavetype.setCellValueFactory(new PropertyValueFactory<>("leaveType"));
        colFrom.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        colTo.setCellValueFactory(new PropertyValueFactory<>("toDate"));

        tblView.setItems(leaveList);

    }

    public void leaveSelect(TableView<Leave> tableView, Label lblRequestid, Label lblStaffid, Label lblStaffname, Label lblLeavetype, Label lblFrom, Label lblTo, TextArea txtDescription) {
        Leave leaveSelected = tableView.getSelectionModel().getSelectedItem();
        int num = tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        connect = database.connectDb();
        String staffName = "";

        String sql = "SELECT * FROM employee WHERE id = " + leaveSelected.getStaffID();

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            if (result.next()) {
                staffName = result.getString("firstName") + " " + result.getString("lastName");
            }

        } catch (Exception e) {e.printStackTrace();}

        lblRequestid.setText(String.valueOf(leaveSelected.getRequestID()));
        lblStaffid.setText(String.valueOf(leaveSelected.getStaffID()));
        lblStaffname.setText(staffName);
        lblLeavetype.setText(leaveSelected.getLeaveType());
        lblFrom.setText(String.valueOf(leaveSelected.getFromDate()));
        lblTo.setText(String.valueOf(leaveSelected.getToDate()));
        txtDescription.setText(leaveSelected.getDescription());
    }

    public void updateLeave(String status, Label lblRequestid, Label lblStaffid, Label lblStaffname, Label lblLeavetype, Label lblFrom, Label lblTo, TextArea txtDescription, TextArea txtComment) {
        String sql = "UPDATE leaverequest SET status = '" + status +"', comment = '" + txtComment.getText() + "' WHERE requestID = " + lblRequestid.getText();

        connect = database.connectDb();

        try {
            Alert alert;
            if (lblRequestid.getText().isEmpty()
                    || lblStaffid.getText().isEmpty()
                    || lblLeavetype.getText().isEmpty()
                    || lblStaffname.getText().isEmpty()
                    || lblFrom.getText().isEmpty()
                    || lblTo.getText().isEmpty()
                    || txtDescription.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a request");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                if (status.equals("approved"))
                    alert.setContentText("Are you sure you want to approve this request?");
                else
                    alert.setContentText("Are you sure you want to reject this request?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    if (status.equals("approved"))
                        alert.setContentText("Successfully Approved!");
                    else
                        alert.setContentText("Successfully rejected!");
                    alert.showAndWait();

                }
            }
        }catch (Exception e) {e.printStackTrace();}
    }
}
