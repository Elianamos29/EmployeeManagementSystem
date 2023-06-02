package com.example.employeemanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EMSController implements Initializable {

    @FXML
    private Button logbtn;

    private Stage primaryStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void loginHandler(ActionEvent event) {
        try {
            primaryStage = (Stage) logbtn.getScene().getWindow();
            closePrimaryStage();
            Stage dashboardStage = createDashboardStage();
            dashboardStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closePrimaryStage() {
        primaryStage.close();
    }

    private Stage createDashboardStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EMS.class.getResource("dashboard-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage dashboardStage = new Stage();
        dashboardStage.setTitle("Employee Management System");
        dashboardStage.setResizable(false);
        //dashboardStage.initModality(Modality.APPLICATION_MODAL);
        dashboardStage.setScene(scene);
        return dashboardStage;
    }
}
