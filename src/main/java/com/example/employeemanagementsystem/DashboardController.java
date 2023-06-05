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
    private TableView<?> emptableView;

    @FXML
    private TableColumn<?, ?> colDOJ;

    @FXML
    private TableColumn<?, ?> colDept;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFname;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colLname;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private ComboBox<?> combGender;

    @FXML
    private AnchorPane dashboardView;

    @FXML
    private Button deleteBtn;

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
    private TextField txtSalary;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button updateBtn;

    @FXML
    private AnchorPane usermgmtView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboardView.setVisible(true);
        staffmgmtView.setVisible(false);
        usermgmtView.setVisible(false);
        leavemgmtView.setVisible(false);
        attendanceView.setVisible(false);
        setUserName();
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

    public void setUserName() {
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
