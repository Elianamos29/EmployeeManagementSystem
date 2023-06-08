package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.*;

public class StaffDashboardController implements Initializable {

    @FXML
    private AnchorPane RequestleaveView;

    @FXML
    private Button attendanceBtn;

    @FXML
    private AnchorPane attendanceView;

    @FXML
    private ComboBox<?> combFromdate;

    @FXML
    private ComboBox<?> combLeavetype;

    @FXML
    private ComboBox<?> combTodate;

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
    private TextArea txtDescription;


    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staffdashboardView.setVisible(true);
        attendanceView.setVisible(false);
        RequestleaveView.setVisible(false);
        User.showUserName(lblStaff);

    }

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

            setLeaveTypeList();
            setFromDateList();
            setToDateList();
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
                prepare = connect.prepareStatement(sql);
                prepare.setInt(1,getData.id);
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

            }
        } catch (Exception e) {e.printStackTrace();}
    }

    private String[] listLeaveType = {"Sick", "vacation", "other"};

    public void setLeaveTypeList() {
        List<String> lists = new ArrayList<>();

        for (String data : listLeaveType) {
            lists.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(lists);
        combLeavetype.setItems(listData);
    }

    Date date = new Date();
    java.sql.Date sqlDate= new java.sql.Date(date.getTime());
    private String[] listFromDate = {String.valueOf(sqlDate), String.valueOf(sqlDate)};

    public void setFromDateList() {
        List<String> lists = new ArrayList<>();

        for (String data : listFromDate) {
            lists.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(lists);
        combFromdate.setItems(listData);
    }

    private String[] listToDate = {String.valueOf(sqlDate), String.valueOf(sqlDate)};

    public void setToDateList() {
        List<String> lists = new ArrayList<>();

        for (String data : listToDate) {
            lists.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(lists);
        combTodate.setItems(listData);
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
