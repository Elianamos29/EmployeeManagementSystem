package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StaffDashboardController implements Initializable {

    @FXML
    private AnchorPane RequestleaveView;

    @FXML
    private Button attendanceBtn;

    @FXML
    private AnchorPane attendanceView;

    @FXML
    private TableColumn<Leave, String> colHistoryComment;

    @FXML
    private TableColumn<Leave, String> colHistoryDescription;

    @FXML
    private TableColumn<Leave, Date> colHistoryFrom;

    @FXML
    private TableColumn<Leave, String> colHistoryLeavetype;

    @FXML
    private TableColumn<Leave, Integer> colHistoryRequestid;

    @FXML
    private TableColumn<Leave, String> colHistoryStatus;

    @FXML
    private TableColumn<Leave, Date> colHistoryTo;

    @FXML
    private ComboBox<?> combFromdate;

    @FXML
    private ComboBox<?> combLeavetype;

    @FXML
    private ComboBox<?> combTodate;

    @FXML
    private Label lblRequestStatus;

    @FXML
    private Label lblStaff;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button requestBtn;

    @FXML
    private Button requestleaveBtn;

    @FXML
    private Button staffdashboardBtn;

    @FXML
    private AnchorPane staffdashboardView;

    @FXML
    private TableView<Leave> tblRequestHistory;

    @FXML
    private TextArea txtDescription;

    @FXML
    private CheckBox checkTimein;

    @FXML
    private CheckBox checkTimeout;

    @FXML
    private Button changePasswordBtn;

    @FXML
    private Label lblDetailsDept;

    @FXML
    private Label lblDetailsDoJ;

    @FXML
    private Label lblDetailsEmail;

    @FXML
    private Label lblDetailsFname;

    @FXML
    private Label lblDetailsGender;

    @FXML
    private Label lblDetailsLname;

    @FXML
    private Label lblDetailsPos;

    @FXML
    private Label lblDetailsSalary;

    @FXML
    private Label lblStaffName;

    @FXML
    private Label lblStaffPosition;

    @FXML
    private Label lblStaffid;

    @FXML
    private AnchorPane myDetailsView;

    @FXML
    private AnchorPane passwordChangeView;

    @FXML
    private Label lblDetailsID;

    @FXML
    private Button changeBtn;
    @FXML
    private TextField txtConfirmPassword;
    @FXML
    private TextField txtNewPassword;
    @FXML
    private TextField txtNewUsername;
    @FXML
    private Button detailsBtn;



    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staffdashboardView.setVisible(true);
        RequestleaveView.setVisible(false);
        myDetailsView.setVisible(true);
        passwordChangeView.setVisible(false);

        User.showUserName(lblStaff);
        showEmployeeDetails();
        setEmployeeStatus();

    }

    public void showEmployeeDetails() {
        Employee emp = new Employee();

        Employee employee = emp.getEmployeeDetails(getData.id);

        lblStaffid.setText(String.valueOf(employee.getId()));
        lblStaffName.setText(employee.getFname() + " " + employee.getLname());
        lblStaffPosition.setText(employee.getPosition());

        lblDetailsID.setText(String.valueOf(employee.getId()));
        lblDetailsFname.setText(employee.getFname());
        lblDetailsLname.setText(employee.getLname());
        lblDetailsGender.setText(employee.getGender());
        lblDetailsEmail.setText(employee.getEmail());
        lblDetailsDept.setText(employee.getDepartment());
        lblDetailsPos.setText(employee.getPosition());
        lblDetailsSalary.setText(String.valueOf(employee.getSalary()));
        lblDetailsDoJ.setText(String.valueOf(employee.getDateJoin()));

    }

    public void changeView(ActionEvent event) {
        if (event.getSource() == detailsBtn) {
            myDetailsView.setVisible(true);
            passwordChangeView.setVisible(false);
        } else if (event.getSource() == changePasswordBtn) {
            myDetailsView.setVisible(false);
            passwordChangeView.setVisible(true);
        }
    }

    public void changeUserPassword() {
        User user = new User();

        user.changePassword(txtNewUsername, txtNewPassword, txtConfirmPassword);
    }

    public void showRequestHistory() {
        Leave leave = new Leave();

        leave.showRequestHistory(tblRequestHistory, colHistoryRequestid, colHistoryLeavetype, colHistoryFrom, colHistoryTo, colHistoryDescription, colHistoryStatus, colHistoryComment);
    }

    public void switchView(ActionEvent event) {
        if (event.getSource() == staffdashboardBtn) {
            staffdashboardView.setVisible(true);
            RequestleaveView.setVisible(false);
            myDetailsView.setVisible(true);
            passwordChangeView.setVisible(false);

            showEmployeeDetails();
            setEmployeeStatus();

        } else if (event.getSource() == requestleaveBtn) {
            staffdashboardView.setVisible(false);
            RequestleaveView.setVisible(true);

            setLeaveTypeList();
            setFromDateList();
            setToDateList();
            showRequestHistory();
            resetRequestForm();
        }
    }

    public void requestLeave() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO leaverequest (staffID,leaveType,description,fromDate,toDate) VALUES(?,?,?,?,?)";
        connect = database.connectDb();

        try {
            Alert alert;
            if (combLeavetype.getSelectionModel().getSelectedItem() == null
                    || combFromdate.getSelectionModel().getSelectedItem() == null
                    || combTodate.getSelectionModel().getSelectedItem() == null
                    || txtDescription.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                if (!checkDate((String) combFromdate.getSelectionModel().getSelectedItem(), (String) combTodate.getSelectionModel().getSelectedItem())) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please check your date range.");
                    alert.showAndWait();
                } else if (getData.hasPendingRequest) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("You can not request leave while you have a pending request.");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, getData.id);
                    prepare.setString(2, (String) combLeavetype.getSelectionModel().getSelectedItem());
                    prepare.setString(3, txtDescription.getText());
                    prepare.setString(4, (String) combFromdate.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String) combTodate.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Request Successfully Sent!");
                    alert.showAndWait();

                    resetRequestForm();
                    showRequestHistory();
                }

            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public boolean checkDate(String from, String to) throws ParseException {
        boolean isValid = true;

        Date fromdate = new SimpleDateFormat("yy/MM/dd").parse(from.replace("-", "/"));
        Date todate = new SimpleDateFormat("yy/MM/dd").parse(to.replace("-", "/"));

        java.sql.Date sqlfdate = new java.sql.Date(fromdate.getTime());
        java.sql.Date sqltdate = new java.sql.Date(todate.getTime());


        if (sqltdate.compareTo(sqlfdate) <= 0)
            isValid = false;


        return isValid;
    }


    private String[] listLeaveType = {"Sick", "vacation", "other"};

    public void setLeaveTypeList() {

        List<String> lists = new ArrayList<>(Arrays.asList(listLeaveType));

        ObservableList listData = FXCollections.observableArrayList(lists);
        combLeavetype.setItems(listData);
    }

    public Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    Date curDate = new Date();

    public ArrayList<Date> dateList() {
        ArrayList<Date> dates = new ArrayList<>();

        for (int i = 1; i <= 15; i++) {
            dates.add(curDate);
            curDate = addDays(curDate, 1);
        }
        curDate = new Date();

        return dates;
    }

    java.sql.Date sqlDate;

    public ArrayList<String> listDate() {
        ArrayList<Date> datelist = dateList();
        ArrayList<String> listDate = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            sqlDate = new java.sql.Date(datelist.get(i).getTime());
            listDate.add(String.valueOf(sqlDate));
        }

        return listDate;
    }

    public void setFromDateList() {
        ArrayList<String> listdate = listDate();

        List<String> lists = new ArrayList<>(listdate);

        ObservableList listData = FXCollections.observableArrayList(lists);
        combFromdate.setItems(listData);
    }

    public void setToDateList() {
        ArrayList<String> listdate = listDate();

        List<String> lists = new ArrayList<>(listdate);

        ObservableList listData = FXCollections.observableArrayList(lists);
        combTodate.setItems(listData);
    }

    public void markTimeIn() {
        Attendance attendance = new Attendance();

        attendance.timeIn(checkTimein);
    }

    public void markTimeOut() {
        Attendance attendance = new Attendance();

        attendance.timeOut(checkTimeout);
    }

    public void setEmployeeStatus() {
        Leave leave = new Leave();

        leave.setEmployeeStatus();
    }

    public void logout() {

        Stage dashboardStage = (Stage) logoutBtn.getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();

        try{
            if (option.get().equals(ButtonType.OK)) {
                getData.hasPendingRequest = false;
                getData.isActive = true;

                dashboardStage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(EMS.class.getResource("EMS-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage loginStage = new Stage();
                loginStage.setTitle("Employee Management System");

                //stage.initStyle(StageStyle.TRANSPARENT);
                loginStage.setResizable(false);
                loginStage.setScene(scene);
                loginStage.show();

            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void resetRequestForm() {
        combLeavetype.getSelectionModel().clearSelection();
        combFromdate.getSelectionModel().clearSelection();
        combTodate.getSelectionModel().clearSelection();
        txtDescription.setText("");
    }
}
