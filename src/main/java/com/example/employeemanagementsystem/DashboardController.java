package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DashboardController implements Initializable {
    @FXML
    private Button addBtn;

    @FXML
    private AnchorPane attendanceView;

    @FXML
    private Button clearBtn;

    @FXML
    private TableColumn<Employee, Date> colDOJ;

    @FXML
    private TableColumn<Employee, String> colDept;

    @FXML
    private TableColumn<Employee, String> colEmail;

    @FXML
    private TableColumn<Employee, String> colFname;

    @FXML
    private TableColumn<Employee, String> colGender;

    @FXML
    private TableColumn<Employee, Integer> colID;

    @FXML
    private TableColumn<Employee, String> colLname;

    @FXML
    private TableColumn<Employee, String> colPos;

    @FXML
    private TableColumn<Employee, Double> colSalary;

    @FXML
    private ComboBox<?> combGender;

    @FXML
    private AnchorPane dashboardView;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<Employee> emptableView;

    @FXML
    private Button home_attendBtn;

    @FXML
    private Button home_dashbordBtn;

    @FXML
    private Button home_leavemgmtBtn;

    @FXML
    private Button home_staffmgmtBtn;

    @FXML
    private Button home_usermgmtBtn;

    @FXML
    private Label lblAdmin;

    @FXML
    private AnchorPane leavemgmtView;

    @FXML
    private Button logoutBtn;

    @FXML
    private Button searchBtn;

    @FXML
    private AnchorPane staffmgmtView;

    @FXML
    private TextField txtDept;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtFname;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtLname;

    @FXML
    private TextField txtPos;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button updateBtn;

    @FXML
    private AnchorPane usermgmtView;
    @FXML
    private Button adduserBtn;
    @FXML
    private Button clearuserBtn;
    @FXML
    private TableColumn<User, String> colUseremail;

    @FXML
    private TableColumn<User, String> colUsername;

    @FXML
    private TableColumn<User, String> colPassword;
    @FXML
    private TableColumn<User, String> colUsertype;
    @FXML
    private Button deleteuserBtn;
    @FXML
    private TableView<User> tblPassword;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtUserid;
    @FXML
    private TextField txtUseremail;

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtUsertype;

    @FXML
    private TextField txtsearchUser;
    @FXML
    private Button updateuserBtn;
    @FXML
    private AnchorPane viewApproved;

    @FXML
    private AnchorPane viewNotApproved;

    @FXML
    private AnchorPane viewPending;
    @FXML
    private ToggleGroup status;
    @FXML
    private RadioButton pendingradBtn;
    @FXML
    private RadioButton approvedradBtn;
    @FXML
    private RadioButton notapprovedradBtn;

    @FXML
    private TableColumn<Leave, Date> colAprFrom;

    @FXML
    private TableColumn<Leave, String> colAprLeavetype;

    @FXML
    private TableColumn<Leave, Integer> colAprRequestid;

    @FXML
    private TableColumn<Leave, Integer> colAprStaffid;

    @FXML
    private TableColumn<Leave, Date> colAprTo;
    @FXML
    private TableColumn<Leave, Date> colNaprFrom;

    @FXML
    private TableColumn<Leave, String> colNaprLeavetype;

    @FXML
    private TableColumn<Leave, Integer> colNaprRequestid;

    @FXML
    private TableColumn<Leave, Integer> colNaprStaffid;

    @FXML
    private TableColumn<Leave, Date> colNaprTo;

    @FXML
    private TableColumn<Leave, Date> colPenFrom;

    @FXML
    private TableColumn<Leave, String> colPenLeavetype;

    @FXML
    private TableColumn<Leave, Integer> colPenRequestid;

    @FXML
    private TableColumn<Leave, Integer> colPenStaffid;
    @FXML
    private TableColumn<Leave, Date> colPenTo;
    @FXML
    private TableView<Leave> tblApproved;
    @FXML
    private TableView<Leave> tblNotapproved;
    @FXML
    private TableView<Leave> tblPending;

    @FXML
    private Button approveLeaveBtn;

    @FXML
    private Button NaproveLeaveBtn;

    @FXML
    private Label lblLeaveFrom;

    @FXML
    private Label lblLeaveRequestid;

    @FXML
    private Label lblLeaveStaffid;

    @FXML
    private Label lblLeaveStaffname;

    @FXML
    private Label lblLeaveTo;

    @FXML
    private Label lblLeaveType;

    @FXML
    private TextArea txtLeaveComment;

    @FXML
    private TextArea txtLeaveDescription;
    @FXML
    private TableColumn<Attendance, String> colAttendanceName;

    @FXML
    private TableColumn<Attendance, Integer> colAttendanceStaffid;

    @FXML
    private TableColumn<Attendance, String> colAttendanceStatus;

    @FXML
    private TableColumn<Attendance, Date> colAttendanceTimein;

    @FXML
    private TableColumn<Attendance, Date> colAttendanceTimeout;

    @FXML
    private TableView<Attendance> tblAttendance;

    @FXML
    private ComboBox<?> combChooseDate;

    @FXML
    private Label lblAtDatejoin;

    @FXML
    private Label lblAtDepartment;

    @FXML
    private Label lblAtEmail;

    @FXML
    private Label lblAtGender;

    @FXML
    private Label lblAtID;

    @FXML
    private Label lblAtName;

    @FXML
    private Label lblAtPosition;

    @FXML
    private Label lblAtSalary;

    @FXML
    private Label lblAtStatus;

    @FXML
    private Label lblTotalEmployees;
    @FXML
    private Label lblTotalPrsent;
    @FXML
    private Label lblTotalInactive;
    @FXML
    private Label lblNewRequests;

    @FXML
    private AnchorPane navDashboard;

    @FXML
    private AnchorPane navLeaveManagement;

    @FXML
    private AnchorPane navStaffAttendance;

    @FXML
    private AnchorPane navStaffManagement;

    @FXML
    private AnchorPane navUserManagement;
    @FXML
    private Button btnMinimize;





    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardView.setVisible(true);
        staffmgmtView.setVisible(false);
        usermgmtView.setVisible(false);
        leavemgmtView.setVisible(false);
        attendanceView.setVisible(false);

        navDashboard.setVisible(true);
        navLeaveManagement.setVisible(false);
        navStaffAttendance.setVisible(false);
        navStaffManagement.setVisible(false);
        navUserManagement.setVisible(false);

        home_dashbordBtn.setStyle("-fx-background-color: #aaa;");
        home_staffmgmtBtn.setStyle("-fx-background-color: transparent;");
        home_leavemgmtBtn.setStyle("-fx-background-color: transparent;");
        home_usermgmtBtn.setStyle("-fx-background-color: transparent;");
        home_attendBtn.setStyle("-fx-background-color: transparent;");

        lblAdmin.setText(getData.username);
        totalEmployeesShow();
        totalPresentShow();
        pendingLeaveRequestsShow();
        updateStaffStatus();

    }


    public void switchView(ActionEvent event) throws ParseException {
        if (event.getSource() == home_dashbordBtn) {
            dashboardView.setVisible(true);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(false);
            leavemgmtView.setVisible(false);
            attendanceView.setVisible(false);

            navDashboard.setVisible(true);
            navLeaveManagement.setVisible(false);
            navStaffAttendance.setVisible(false);
            navStaffManagement.setVisible(false);
            navUserManagement.setVisible(false);

            home_dashbordBtn.setStyle("-fx-background-color: #aaa;");
            home_staffmgmtBtn.setStyle("-fx-background-color: transparent;");
            home_leavemgmtBtn.setStyle("-fx-background-color: transparent;");
            home_usermgmtBtn.setStyle("-fx-background-color: transparent;");
            home_attendBtn.setStyle("-fx-background-color: transparent;");

            totalEmployeesShow();
            totalPresentShow();
            pendingLeaveRequestsShow();
        } else if (event.getSource() == home_staffmgmtBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(true);
            usermgmtView.setVisible(false);
            leavemgmtView.setVisible(false);
            attendanceView.setVisible(false);

            navDashboard.setVisible(false);
            navLeaveManagement.setVisible(false);
            navStaffAttendance.setVisible(false);
            navStaffManagement.setVisible(true);
            navUserManagement.setVisible(false);

            home_dashbordBtn.setStyle("-fx-background-color: transparent;");
            home_staffmgmtBtn.setStyle("-fx-background-color: #aaa;");
            home_leavemgmtBtn.setStyle("-fx-background-color: transparent;");
            home_usermgmtBtn.setStyle("-fx-background-color: transparent;");
            home_attendBtn.setStyle("-fx-background-color: transparent;");


            showEmployeeListData();
            addEmployeeGenderList();
        } else if (event.getSource() == home_usermgmtBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(true);
            leavemgmtView.setVisible(false);
            attendanceView.setVisible(false);

            navDashboard.setVisible(false);
            navLeaveManagement.setVisible(false);
            navStaffAttendance.setVisible(false);
            navStaffManagement.setVisible(false);
            navUserManagement.setVisible(true);

            home_dashbordBtn.setStyle("-fx-background-color: transparent;");
            home_staffmgmtBtn.setStyle("-fx-background-color: transparent;");
            home_leavemgmtBtn.setStyle("-fx-background-color: transparent;");
            home_usermgmtBtn.setStyle("-fx-background-color: #aaa;");
            home_attendBtn.setStyle("-fx-background-color: transparent;");

            showUserListData();
        } else if (event.getSource() == home_leavemgmtBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(false);
            leavemgmtView.setVisible(true);
            attendanceView.setVisible(false);

            navDashboard.setVisible(false);
            navLeaveManagement.setVisible(true);
            navStaffAttendance.setVisible(false);
            navStaffManagement.setVisible(false);
            navUserManagement.setVisible(false);

            home_dashbordBtn.setStyle("-fx-background-color: transparent;");
            home_staffmgmtBtn.setStyle("-fx-background-color: transparent;");
            home_leavemgmtBtn.setStyle("-fx-background-color: #aaa;");
            home_usermgmtBtn.setStyle("-fx-background-color: transparent;");
            home_attendBtn.setStyle("-fx-background-color: transparent;");

            viewPending.setVisible(true);
            viewApproved.setVisible(false);
            viewNotApproved.setVisible(false);
            showPendingLeaveList();
        } else if (event.getSource() == home_attendBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(false);
            leavemgmtView.setVisible(false);
            attendanceView.setVisible(true);

            navDashboard.setVisible(false);
            navLeaveManagement.setVisible(false);
            navStaffAttendance.setVisible(true);
            navStaffManagement.setVisible(false);
            navUserManagement.setVisible(false);

            home_dashbordBtn.setStyle("-fx-background-color: transparent;");
            home_staffmgmtBtn.setStyle("-fx-background-color: transparent;");
            home_leavemgmtBtn.setStyle("-fx-background-color: transparent;");
            home_usermgmtBtn.setStyle("-fx-background-color: transparent;");
            home_attendBtn.setStyle("-fx-background-color: #aaa;");

            setChooseDateList();
            showAttendanceList();
        }
    }

    public void totalEmployeesShow() {
        String sql = "SELECT COUNT(id) FROM employee";

        connect = database.connectDb();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(id)");
            }

            lblTotalEmployees.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void totalPresentShow() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "SELECT COUNT(*) FROM attendance WHERE date = '" + sqlDate + "'";

        connect = database.connectDb();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(*)");
            }

            lblTotalPrsent.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pendingLeaveRequestsShow() {
        String sql = "SELECT COUNT(requestID) FROM leaverequest WHERE status = 'pending'";

        connect = database.connectDb();
        int countData = 0;
        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                countData = result.getInt("COUNT(requestID)");
            }

            lblNewRequests.setText(String.valueOf(countData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void totalInactiveShow() {

    }



    public void addEmployee() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO employee  (id,firstName,lastName,gender,department,email,position,salary,dateJoining) VALUES(?,?,?,?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;
            if (txtID.getText().isEmpty()
                    || txtFname.getText().isEmpty()
                    || txtLname.getText().isEmpty()
                    || combGender.getSelectionModel().getSelectedItem() == null
                    || txtDept.getText().isEmpty()
                    || txtEmail.getText().isEmpty()
                    || txtPos.getText().isEmpty()
                    || txtSalary.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT id FROM employee WHERE id = "
                        + txtID.getText();

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee ID: " + txtID.getText() + " already exists.");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, txtID.getText());
                    prepare.setString(2, txtFname.getText());
                    prepare.setString(3, txtLname.getText());
                    prepare.setString(4, (String) combGender.getSelectionModel().getSelectedItem());
                    prepare.setString(5, txtDept.getText());
                    prepare.setString(6, txtEmail.getText());
                    prepare.setString(7, txtPos.getText());
                    prepare.setString(8, txtSalary.getText());

                    prepare.setString(9, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    showEmployeeListData();
                    resetText();
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void deleteEmployee() {
        String sql = "DELETE FROM employee WHERE id = "
                + txtID.getText();

        connect = database.connectDb();

        try {

            Alert alert;
            if (txtID.getText().isEmpty()
                    || txtFname.getText().isEmpty()
                    || txtLname.getText().isEmpty()
                    || txtDept.getText().isEmpty()
                    || txtEmail.getText().isEmpty()
                    || txtPos.getText().isEmpty()
                    || txtSalary.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Employee ID: " + txtID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);


                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    showEmployeeListData();
                    resetText();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE employee SET firstName = '"
                + txtFname.getText() + "', lastName = '"
                + txtLname.getText() + "', gender = '"
                + combGender.getSelectionModel().getSelectedItem() + "', department = '"
                + txtDept.getText()+ "', email = '"
                + txtEmail.getText() + "', position = '"
                + txtPos.getText()+ "', salary = "
                + txtSalary.getText() + ", dateJoining = '" + sqlDate + "' WHERE id = "
                + txtID.getText();

        connect = database.connectDb();

        try {
            Alert alert;
            if (txtID.getText().isEmpty()
                    || txtFname.getText().isEmpty()
                    || txtLname.getText().isEmpty()
                    || combGender.getSelectionModel().getSelectedItem() == null
                    || txtDept.getText().isEmpty()
                    || txtEmail.getText().isEmpty()
                    || txtPos.getText().isEmpty()
                    || txtSalary.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Employee ID: " + txtID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    showEmployeeListData();
                    resetText();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addEmployeeSelect() {
        Employee employee = emptableView.getSelectionModel().getSelectedItem();
        int num = emptableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        txtID.setText(String.valueOf(employee.getId()));
        txtFname.setText(employee.getFname());
        txtLname.setText(employee.getLname());
        txtDept.setText(employee.getDepartment());
        txtEmail.setText(employee.getEmail());
        txtPos.setText(employee.getPosition());
        txtSalary.setText(String.valueOf(employee.getSalary()));
    }

    public void employeeSearch() {

        FilteredList<Employee> filter = new FilteredList<>(employeeList, e -> true);

        txtSearch.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (String.valueOf(predicateEmployeeData.getId()).contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getFname().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getLname().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDepartment().toLowerCase().contains(searchKey)) {
                    return true;
                }else if (predicateEmployeeData.getEmail().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPosition().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateEmployeeData.getSalary()).contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateEmployeeData.getDateJoin()).contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Employee> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(emptableView.comparatorProperty());
        emptableView.setItems(sortList);
    }

    public ObservableList<Employee> employeeListData() {

        ObservableList<Employee> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM employee";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Employee employee;

            while (result.next()) {
                employee = new Employee(result.getInt("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getString("department"),
                        result.getString("email"),
                        result.getString("position"),
                        result.getDouble("salary"),
                        result.getDate("dateJoining"));
                listData.add(employee);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Employee> employeeList;

    public void showEmployeeListData() {
        employeeList = employeeListData();

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lname"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colDept.setCellValueFactory(new PropertyValueFactory<>("department"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPos.setCellValueFactory(new PropertyValueFactory<>("position"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDOJ.setCellValueFactory(new PropertyValueFactory<>("dateJoin"));

        emptableView.setItems(employeeList);

    }

    private String[] listGender = {"Male", "Female"};

    public void addEmployeeGenderList() {
        List<String> lists = new ArrayList<>();

        for (String data : listGender) {
            lists.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(lists);
        combGender.setItems(listData);
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

    public void showPendingLeaveList() {
        showLeaveListData("pending",tblPending, colPenRequestid, colPenStaffid, colPenLeavetype, colPenFrom, colPenTo);
    }

    public void showApprovedLeaveList() {
        showLeaveListData("approved",tblApproved, colAprRequestid, colAprStaffid, colAprLeavetype, colAprFrom, colAprTo);
    }

    public void showRejectedLeaveList() {
        showLeaveListData("rejected",tblNotapproved, colNaprRequestid, colNaprStaffid, colNaprLeavetype, colNaprFrom, colNaprTo);
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

    public void selectPendingLeave() {
        leaveSelect(tblPending, lblLeaveRequestid, lblLeaveStaffid, lblLeaveStaffname, lblLeaveType, lblLeaveFrom, lblLeaveTo, txtLeaveDescription);
    }

    public void selectApprovedLeave() {
        leaveSelect(tblApproved, lblLeaveRequestid, lblLeaveStaffid, lblLeaveStaffname, lblLeaveType, lblLeaveFrom, lblLeaveTo, txtLeaveDescription);
    }

    public void selectRejectedLeave() {
        leaveSelect(tblNotapproved, lblLeaveRequestid, lblLeaveStaffid, lblLeaveStaffname, lblLeaveType, lblLeaveFrom, lblLeaveTo, txtLeaveDescription);
    }

    public void updateLeave(String status) {
        String sql = "UPDATE leaverequest SET status = '" + status + "', comment = '" + txtLeaveComment.getText() + "' WHERE requestID = " + lblLeaveRequestid.getText();

        connect = database.connectDb();

        try {
            Alert alert;
            if (lblLeaveRequestid.getText().isEmpty()
                    || lblLeaveStaffid.getText().isEmpty()
                    || lblLeaveType.getText().isEmpty()
                    || lblLeaveStaffname.getText().isEmpty()
                    || lblLeaveFrom.getText().isEmpty()
                    || lblLeaveTo.getText().isEmpty()
                    || txtLeaveDescription.getText().isEmpty()) {
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
                    if (status.equals("approved")) {
                        alert.setContentText("Successfully Approved!");
                        updateStaffStatus();
                    } else
                        alert.setContentText("Successfully rejected!");
                    alert.showAndWait();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> inactiveStaffList() {
        ArrayList<Integer> inactiveList = new ArrayList<>();
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "SELECT * FROM leaverequest WHERE status = 'approved' AND fromDate <= '"
                + sqlDate + "' AND toDate >= '" + sqlDate + "'";

        connect = database.connectDb();
        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                inactiveList.add(result.getInt("staffID"));
            }
        } catch (Exception e) {e.printStackTrace();}

        return inactiveList;
    }

    public void updateStaffStatus() {
        String staffStatus = "active";
        ArrayList<Integer> list = inactiveStaffList();
        lblTotalInactive.setText(String.valueOf(list.size()));
        String sql = "SELECT * FROM employee";

        connect = database.connectDb();
        try {

            statement = connect.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                if (list.contains(result.getInt("id"))) {
                    staffStatus = "inactive";
                }

                String setStatus = "UPDATE attendance SET status = '" + staffStatus + "' WHERE staffID = " + result.getInt("id");
                Statement statement2 = connect.createStatement();
                statement2.executeUpdate(setStatus);
                staffStatus = "active";
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void approveLeaveRequest() {
        updateLeave("approved");
        showPendingLeaveList();
        resetLeaveDetails();
    }

    public void rejectLeaveRequest() {
        updateLeave("rejected");
        showPendingLeaveList();
        resetLeaveDetails();
    }

    public ObservableList<User> userListData() {

        ObservableList<User> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";

        connect = database.connectDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            User user;

            while (result.next()) {
                user = new User(result.getInt("staffID"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getString("userType"));
                listData.add(user);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private static ObservableList<User> userList;


    public void showUserListData() {
        userList = userListData();

        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colUseremail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colUsertype.setCellValueFactory(new PropertyValueFactory<>("userType"));

        tblPassword.setItems(userList);

    }

    public void addUser() {
        String sql = "INSERT INTO users (staffID, username, email, password, userType) VALUES(?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;
            if (txtUserid.getText().isEmpty()
                    || txtUsername.getText().isEmpty()
                    || txtUseremail.getText().isEmpty()
                    || txtPassword.getText().isEmpty()
                    || txtUsertype.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT username FROM users WHERE staffID = " + txtUserid.getText();

                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Userid already exists.");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, txtUserid.getText());
                    prepare.setString(2, txtUsername.getText());
                    prepare.setString(3, txtUseremail.getText());
                    prepare.setString(4, txtPassword.getText());
                    prepare.setString(5, txtUsertype.getText());
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    showUserListData();
                    resetUserText();
                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void deleteUser() {
        String sql = "DELETE FROM users where staffID = " +
                txtUserid.getText();
        connect = database.connectDb();

        try {

            Alert alert;
            if (txtUserid.getText().isEmpty()
                    ||txtUsername.getText().isEmpty()
                    || txtUseremail.getText().isEmpty()
                    || txtPassword.getText().isEmpty()
                    || txtUsertype.getText().isEmpty() ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE User: " + txtUsername.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);


                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    showUserListData();
                    resetUserText();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser() {
        String sql = "UPDATE users SET username = '"
                + txtUsername.getText() + "', email = '"
                + txtUseremail.getText() + "', password = '"
                + txtPassword.getText() + "', userType = '"
                + txtUsertype.getText() + "' WHERE staffID = "
                + txtUserid.getText();

        connect = database.connectDb();

        try {
            Alert alert;
            if (txtUserid.getText().isEmpty()
                    ||txtUsername.getText().isEmpty()
                    || txtUseremail.getText().isEmpty()
                    || txtPassword.getText().isEmpty()
                    || txtUsertype.getText().isEmpty() ) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE user: " + txtUsername.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    showUserListData();
                    resetUserText();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectUser() {
        User userSelected = tblPassword.getSelectionModel().getSelectedItem();
        int num = tblPassword.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        txtUserid.setText(String.valueOf(userSelected.getUserid()));
        txtUsername.setText(userSelected.getUsername());
        txtUseremail.setText(userSelected.getEmail());
        txtPassword.setText(userSelected.getPassword());
        txtUsertype.setText(userSelected.getUserType());
    }

    public void searchUser() {
        FilteredList<User> filter = new FilteredList<>(userList, e -> true);

        txtsearchUser.textProperty().addListener((Observable, oldValue, newValue) -> {

            filter.setPredicate(predicateUserData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (String.valueOf(predicateUserData.getUserid()).contains(searchKey)) {
                    return true;
                } else if (predicateUserData.getUsername().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUserData.getEmail().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUserData.getPassword().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUserData.getUserType().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<User> sortList = new SortedList<>(filter);

        sortList.comparatorProperty().bind(tblPassword.comparatorProperty());
        tblPassword.setItems(sortList);
    }

    public ObservableList<Attendance> attendancesListData() throws ParseException {
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

    public void showAttendanceList() throws ParseException {
        attendanceList = attendancesListData();

        colAttendanceStaffid.setCellValueFactory(new PropertyValueFactory<>("staffID"));
        colAttendanceName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAttendanceStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAttendanceTimein.setCellValueFactory(new PropertyValueFactory<>("timeIn"));
        colAttendanceTimeout.setCellValueFactory(new PropertyValueFactory<>("timeOut"));

        tblAttendance.setItems(attendanceList);
    }

    public void selectAttendance() {
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


    public void resetText() {
        txtID.setText("");
        txtFname.setText("");
        txtLname.setText("");
        combGender.getSelectionModel().clearSelection();
        txtDept.setText("");
        txtEmail.setText("");
        txtPos.setText("");
        txtSalary.setText("");
    }

    public void resetUserText() {
        txtUserid.setText("");
        txtUsername.setText("");
        txtUseremail.setText("");
        txtPassword.setText("");
        txtUsertype.setText("");
    }

    public void resetLeaveDetails() {
        lblLeaveRequestid.setText("");
        lblLeaveStaffid.setText("");
        lblLeaveStaffname.setText("");
        lblLeaveFrom.setText("");
        lblLeaveTo.setText("");
        txtLeaveDescription.setText("");
        txtLeaveComment.setText("");
    }

    public void switchStatusView() {
        if (((RadioButton)status.getSelectedToggle()).equals(pendingradBtn)) {
            viewPending.setVisible(true);
            viewApproved.setVisible(false);
            viewNotApproved.setVisible(false);

            showPendingLeaveList();
            resetLeaveDetails();
            approveLeaveBtn.setDisable(false);
            NaproveLeaveBtn.setDisable(false);
            txtLeaveComment.setEditable(true);
        } else if (((RadioButton)status.getSelectedToggle()).equals(approvedradBtn)) {
            viewPending.setVisible(false);
            viewApproved.setVisible(true);
            viewNotApproved.setVisible(false);

            showApprovedLeaveList();
            resetLeaveDetails();
            approveLeaveBtn.setDisable(true);
            NaproveLeaveBtn.setDisable(true);
            txtLeaveComment.setEditable(false);
        } else if (((RadioButton)status.getSelectedToggle()).equals(notapprovedradBtn)) {
            viewPending.setVisible(false);
            viewApproved.setVisible(false);
            viewNotApproved.setVisible(true);

            showRejectedLeaveList();
            resetLeaveDetails();
            approveLeaveBtn.setDisable(true);
            NaproveLeaveBtn.setDisable(true);
            txtLeaveComment.setEditable(false);
        }
    }

    public Date subtractDays(Date date, int days) {
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
            curDate = subtractDays(curDate, -1);
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

    public void setChooseDateList() {
        ArrayList<String> listdate = listDate();

        List<String> lists = new ArrayList<>(listdate);

        ObservableList listData = FXCollections.observableArrayList(lists);
        combChooseDate.setItems(listData);
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
                dashboardStage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(EMS.class.getResource("EMS-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage loginStage = new Stage();

                loginStage.initStyle(StageStyle.TRANSPARENT);
                loginStage.setResizable(false);
                loginStage.setScene(scene);
                loginStage.show();

            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) btnMinimize.getScene().getWindow();
        stage.setIconified(true);
    }
}
