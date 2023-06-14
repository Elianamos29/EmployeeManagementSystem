package com.example.employeemanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class EMSController implements Initializable {

    @FXML
    private Button logbtn;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private Button minimizebtn;

    private Stage primaryStage;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void loginHandler(ActionEvent event) {
        try {
            primaryStage = (Stage) logbtn.getScene().getWindow();
            loginUser();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closePrimaryStage() {
        primaryStage.close();
    }

    private Stage createDashboardStage(String userType) throws IOException {
        FXMLLoader fxmlLoader;
        if (userType == "admin") {
            fxmlLoader = new FXMLLoader(EMS.class.getResource("dashboard-view.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(EMS.class.getResource("StaffDashboard-view.fxml"));
        }
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage dashboardStage = new Stage();
        dashboardStage.setResizable(false);
        dashboardStage.initStyle(StageStyle.TRANSPARENT);
        dashboardStage.setScene(scene);
        return dashboardStage;
    }

    public void loginUser(){

        String sql = "SELECT * FROM users WHERE username = ? and password = ?";

        connect = database.connectDb();

        try{
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());

            result = prepare.executeQuery();
            Alert alert;

            if(username.getText().isEmpty() || password.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                if(result.next()){
                    getData.username = username.getText();
                    getData.id = result.getInt("staffID");
                    getData.userType = result.getString("userType");
                    if (getData.userType.equals("admin")) {
                        loginAdmin();
                    } else if (getData.userType.equals("staff")){
                        loginStaff();
                    }


                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }

        }catch(Exception e){e.printStackTrace();}

    }

    public void loginAdmin () throws IOException {
        closePrimaryStage();
        Stage dashboardStage = createDashboardStage("admin");
        dashboardStage.show();
    }

    public void loginStaff() throws IOException {
        closePrimaryStage();
        Stage dashboardStage = createDashboardStage("staff");
        dashboardStage.show();
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) minimizebtn.getScene().getWindow();
        stage.setIconified(true);
    }
}
