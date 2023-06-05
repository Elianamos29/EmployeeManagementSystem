package com.example.employeemanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Optional;

public class StaffDashboardController {
    @FXML
    private AnchorPane RequestleaveView;

    @FXML
    private Button attendanceBtn;

    @FXML
    private AnchorPane attendanceView;

    @FXML
    private Button requestleaveBtn;

    @FXML
    private Button staffdashboardBtn;

    @FXML
    private AnchorPane staffdashboardView;

    @FXML
    private Button logoutBtn;

    public void switchView(ActionEvent event) {
        if (event.getSource() == staffdashboardBtn) {
            staffdashboardView.setVisible(true);
            attendanceView.setVisible(false);
            RequestleaveView.setVisible(false);


        } else if (event.getSource() == attendanceBtn) {
            staffdashboardView.setVisible(false);
            attendanceView.setVisible(true);
            RequestleaveView.setVisible(false);

        } else if (event.getSource() == requestleaveBtn) {
            staffdashboardView.setVisible(false);
            attendanceView.setVisible(false);
            RequestleaveView.setVisible(true);

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
