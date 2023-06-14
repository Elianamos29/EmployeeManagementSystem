package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;

import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

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

        lblStaff.setText(getData.username);
        showEmployeeDetails();

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
        String sql = "UPDATE users SET username = '"
                + txtNewUsername.getText() + "', password = '"
                + txtNewPassword.getText() + "' WHERE staffID = "
                + getData.id;

        connect = database.connectDb();

        try {
            Alert alert;
            if (txtNewUsername.getText().isEmpty()
                    ||txtNewPassword.getText().isEmpty()
                    || txtConfirmPassword.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else if (!(txtNewPassword.getText()).equals(txtConfirmPassword.getText())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("password mismatch!");
                alert.showAndWait();
            } else {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to change your password and username?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully changed!");
                    alert.showAndWait();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Leave> requestListData() {
        ObservableList<Leave> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM leaverequest WHERE staffID = " + getData.id;

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

                if (result.getString("status").equals("pending"))
                    getData.hasPendingRequest = true;
            }
        } catch (Exception e) {e.printStackTrace();}
        return listData;
    }
    private static ObservableList<Leave> requestList;

    public void showRequestHistory() {
        requestList = requestListData();

        colHistoryRequestid.setCellValueFactory(new PropertyValueFactory<>("requestID"));
        colHistoryLeavetype.setCellValueFactory(new PropertyValueFactory<>("leaveType"));
        colHistoryFrom.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        colHistoryTo.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        colHistoryDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colHistoryStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colHistoryComment.setCellValueFactory(new PropertyValueFactory<>("comment"));

        tblRequestHistory.setItems(requestList);
    }

    public void switchView(ActionEvent event) {
        if (event.getSource() == staffdashboardBtn) {
            staffdashboardView.setVisible(true);
            RequestleaveView.setVisible(false);
            myDetailsView.setVisible(true);
            passwordChangeView.setVisible(false);

            showEmployeeDetails();

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

                Employee emp = (new Employee()).getEmployeeDetails(getData.id);
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

    public void markTimeOut() {
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
