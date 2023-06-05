package com.example.employeemanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

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
        showUserName();
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
        } else if (event.getSource() == home_usermgmtBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(true);
            leavemgmtView.setVisible(false);
            attendanceView.setVisible(false);
        } else if (event.getSource() == home_leavemgmtBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(false);
            leavemgmtView.setVisible(true);
            attendanceView.setVisible(false);
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

        String sql = "INSERT INTO employee "
                + "(id,first-name,last-name,gender,department,email,position,salary,date-joining) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";

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

                String check = "SELECT id FROM employee WHERE id = '"
                        + txtID.getText() + "'";

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

                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void showUserName() {
        lblAdmin.setText(getData.username);
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
