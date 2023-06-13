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

    public void timeIn(CheckBox checkTimein) {
        Date date = new Date();
        java.sql.Time sqlTime = new Time(date.getTime());
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "INSERT INTO attendance (date,staffID,name,status,timeIn) VALUES(?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;
            String check = "SELECT staffID FROM attendance WHERE staffID = "
                    + getData.id + " and date = '" + sqlDate + "'";

            statement = connect.createStatement();
            result = statement.executeQuery(check);

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("You have already marked your time in.");
                alert.showAndWait();
            } else {

                Employee emp = getDetails(getData.id);
                String status = "active";
                if (!getData.isActive)
                    status = "inactive";

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, String.valueOf(sqlDate));
                prepare.setInt(2, getData.id);
                prepare.setString(3,emp.getFname() + " " + emp.getLname());
                prepare.setString(4, status);
                prepare.setString(5, String.valueOf(sqlTime));

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Marked In!");
                alert.showAndWait();

                checkTimein.setDisable(true);
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void timeOut(CheckBox checkTimeout) {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        java.sql.Time sqlTime = new java.sql.Time(date.getTime());
        String sql = "UPDATE attendance SET timeOut = '" + sqlTime + "' WHERE date = '" + sqlDate + "' and staffID = " + getData.id;

        connect = database.connectDb();
        try {
            Alert alert;
            String check = "SELECT * FROM attendance WHERE date = '" + sqlDate + "' and staffID = " + getData.id;
            statement = connect.createStatement();
            result = statement.executeQuery(check);

            if (result.next()) {
                if (result.getTime("timeOut") == null) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Marked Out!");
                    alert.showAndWait();

                    checkTimeout.setDisable(true);
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("You have already marked time out.");
                    alert.showAndWait();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("You haven't marked time in.");
                alert.showAndWait();
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public ObservableList<Attendance> attendancesListData(ComboBox<?> combChooseDate) throws ParseException {
        ObservableList<Attendance> listData = FXCollections.observableArrayList();
        String date1 = (String) combChooseDate.getSelectionModel().getSelectedItem();
        Date dateSet;

        if (date1 == null) {
            dateSet = new Date();
        } else {
            dateSet = new SimpleDateFormat("yy/MM/dd").parse(date1.replace("-", "/"));
        }

        java.sql.Date sqlDate = new java.sql.Date(dateSet.getTime());

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
                        result.getTime("timeIn"),
                        result.getTime("timeOut"));
                listData.add(attendance);
            }
        } catch (Exception e) {e.printStackTrace();}

        return listData;
    }

    private static ObservableList<Attendance> attendanceList;

    public void showAttendanceList(ComboBox<?> combChooseDate, TableView<Attendance> tblAttendance, TableColumn<Attendance, Integer> colAttendanceStaffid, TableColumn<Attendance, String> colAttendanceName,
                                   TableColumn<Attendance, String> colAttendanceStatus, TableColumn<Attendance, Date> colAttendanceTimein, TableColumn<Attendance, Date> colAttendanceTimeout) throws ParseException {
        attendanceList = attendancesListData(combChooseDate);

        colAttendanceStaffid.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        colAttendanceName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAttendanceStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAttendanceTimein.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
        colAttendanceTimeout.setCellValueFactory(new PropertyValueFactory<>("timeOut"));

        tblAttendance.setItems(attendanceList);
    }

    public void attendanceSelect(TableView<Attendance> tblAttendance, Label lblAtID, Label lblAtName, Label lblAtGender, Label lblAtEmail,
                                 Label lblAtDepartment, Label lblAtPosition, Label lblAtSalary, Label lblAtStatus, Label lblAtDatejoin) {
        Attendance attendanceSelected = tblAttendance.getSelectionModel().getSelectedItem();
        int num = tblAttendance.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        Employee emp = getDetails(attendanceSelected.getStaffID());

        if (emp != null) {
            lblAtID.setText(String.valueOf(emp.getId()));
            lblAtName.setText(emp.getFname() + " " + emp.getLname());
            lblAtGender.setText(emp.getGender());
            lblAtEmail.setText(emp.getEmail());
            lblAtDepartment.setText(emp.getDepartment());
            lblAtPosition.setText(emp.getPosition());
            lblAtSalary.setText(String.valueOf(emp.getSalary()));
            lblAtStatus.setText(attendanceSelected.getStatus());
            lblAtDatejoin.setText(String.valueOf(emp.getDateJoin()));
        }
    }

    private Employee getDetails(int staffID) {
        String sql = "SELECT * FROM employee WHERE id = " + staffID;
        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Employee employee;
            if (result.next()) {
                return new Employee(result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getString("department"),
                        result.getString("email"),
                        result.getString("position"),
                        result.getDouble("salary"),
                        result.getDate("dateJoining"));
            }

        } catch (Exception e) {e.printStackTrace();}

        return null;
    }
}
