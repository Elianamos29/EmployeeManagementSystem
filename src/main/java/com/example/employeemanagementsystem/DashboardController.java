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

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
        User.showUserName(lblAdmin);

    }


    public void switchView(ActionEvent event) {
        if (event.getSource() == home_dashbordBtn) {
            dashboardView.setVisible(true);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(false);
            leavemgmtView.setVisible(false);
            attendanceView.setVisible(false);
        } else if (event.getSource() == home_staffmgmtBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(true);
            usermgmtView.setVisible(false);
            leavemgmtView.setVisible(false);
            attendanceView.setVisible(false);

            showEmployeeListData();
            addEmployeeGenderList();
        } else if (event.getSource() == home_usermgmtBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(true);
            leavemgmtView.setVisible(false);
            attendanceView.setVisible(false);

            showUserListData();
        } else if (event.getSource() == home_leavemgmtBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(false);
            leavemgmtView.setVisible(true);
            attendanceView.setVisible(false);

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
        }
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

    public void showUserListData() {
        User user = new User();

        user.showUserListData(tblPassword, colUsername, colUseremail, colPassword, colUsertype);
    }

    public void showPendingLeaveList() {
        Leave leave = new Leave();

        leave.showLeaveListData("pending",tblPending, colPenRequestid, colPenStaffid, colPenLeavetype, colPenFrom, colPenTo);
    }

    public void showApprovedLeaveList() {
        Leave leave = new Leave();

        leave.showLeaveListData("approved",tblApproved, colAprRequestid, colAprStaffid, colAprLeavetype, colAprFrom, colAprTo);
    }

    public void showRejectedLeaveList() {
        Leave leave = new Leave();

        leave.showLeaveListData("rejected",tblNotapproved, colNaprRequestid, colNaprStaffid, colNaprLeavetype, colNaprFrom, colNaprTo);
    }

    public void addUser() {
        User user = new User();

        user.addUser(txtUserid, txtUsername, txtUseremail, txtPassword, txtUsertype);
        showUserListData();
        resetUserText();
    }

    public void deleteUser() {
        User user = new User();

        user.deleteUser(txtUserid, txtUsername, txtUseremail, txtPassword, txtUsertype);
        showUserListData();
        resetUserText();
    }

    public void updateUser() {
        User user = new User();

        user.updateUser(txtUserid, txtUsername, txtUseremail, txtPassword, txtUsertype);
        showUserListData();
        resetUserText();
    }

    public void selectUser() {
        User user = new User();

        user.userSelect(tblPassword, txtUserid, txtUsername, txtUseremail, txtPassword, txtUsertype);
    }

    public void selectPendingLeave() {
        Leave leave = new Leave();

        leave.leaveSelect(tblPending, lblLeaveRequestid, lblLeaveStaffid, lblLeaveStaffname, lblLeaveType, lblLeaveFrom, lblLeaveTo, txtLeaveDescription);
    }

    public void selectApprovedLeave() {
        Leave leave = new Leave();

        leave.leaveSelect(tblApproved, lblLeaveRequestid, lblLeaveStaffid, lblLeaveStaffname, lblLeaveType, lblLeaveFrom, lblLeaveTo, txtLeaveDescription);
    }

    public void selectRejectedLeave() {
        Leave leave = new Leave();

        leave.leaveSelect(tblNotapproved, lblLeaveRequestid, lblLeaveStaffid, lblLeaveStaffname, lblLeaveType, lblLeaveFrom, lblLeaveTo, txtLeaveDescription);
    }

    public void approveLeaveRequest() {
        Leave leave = new Leave();

        leave.updateLeave("approved", lblLeaveRequestid, lblLeaveStaffid, lblLeaveStaffname, lblLeaveType, lblLeaveFrom, lblLeaveTo, txtLeaveDescription, txtLeaveComment);
        showPendingLeaveList();
        resetLeaveDetails();
    }

    public void rejectLeaveRequest() {
        Leave leave = new Leave();

        leave.updateLeave("rejected", lblLeaveRequestid, lblLeaveStaffid, lblLeaveStaffname, lblLeaveType, lblLeaveFrom, lblLeaveTo, txtLeaveDescription, txtLeaveComment);
        showPendingLeaveList();
        resetLeaveDetails();
    }

    public void searchUser() {
        User user = new User();

        user.searchUser(txtsearchUser, tblPassword);
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
                loginStage.setTitle("Employee Management System");

                //stage.initStyle(StageStyle.TRANSPARENT);
                loginStage.setResizable(false);
                loginStage.setScene(scene);
                loginStage.show();

            }
        } catch (Exception e) {e.printStackTrace();}
    }
}
