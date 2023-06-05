package com.example.employeemanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private AnchorPane dashboardView;

    @FXML
    private Button home_attendBtn;

    @FXML
    private Button home_dashbordBtn;

    @FXML
    private Button home_staffmgmtBtn;

    @FXML
    private Button home_usermgmtBtn;

    @FXML
    private AnchorPane staffmgmtView;

    @FXML
    private AnchorPane usermgmtView;

    @FXML
    private Button logoutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void switchView(ActionEvent event) {
        if (event.getSource() == home_dashbordBtn) {
            dashboardView.setVisible(true);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(false);


        } else if (event.getSource() == home_staffmgmtBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(true);
            usermgmtView.setVisible(false);
        } else if (event.getSource() == home_usermgmtBtn) {
            dashboardView.setVisible(false);
            staffmgmtView.setVisible(false);
            usermgmtView.setVisible(true);
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
