package com.example.employeemanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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
}
