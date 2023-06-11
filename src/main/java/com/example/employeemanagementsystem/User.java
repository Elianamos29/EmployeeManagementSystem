package com.example.employeemanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class User {
    private int userid;
    private String username;
    private String email;
    private String password;
    private String userType;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    public User() {}

    public User(int userid, String username, String email, String password, String userType) {
        this.userid = userid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public int getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public static void showUserName(Label username) {
        username.setText(getData.username);
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


    public void showUserListData(TableView<User> tblPassword, TableColumn<User, String> colUsername, TableColumn<User, String> colUseremail, TableColumn<User, String> colPassword, TableColumn<User, String> colUsertype) {
        userList = userListData();

        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colUseremail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colUsertype.setCellValueFactory(new PropertyValueFactory<>("userType"));

        tblPassword.setItems(userList);

    }

    public void addUser(TextField txtUserid, TextField txtUsername, TextField txtUseremail, TextField txtPassword, TextField txtUsertype) {
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

                }
            }
        } catch (Exception e) {e.printStackTrace();}
    }

    public void deleteUser(TextField txtUserid, TextField txtUsername, TextField txtUseremail, TextField txtPassword, TextField txtUsertype) {
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

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateUser(TextField txtUserid, TextField txtUsername, TextField txtUseremail, TextField txtPassword, TextField txtUsertype) {
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
                    }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePassword(TextField txtNewUsername, TextField txtNewPassword, TextField txtConfirmPassword) {
        String sql = "UPDATE users SET username = '"
                + txtNewUsername.getText() + "', password = '"
                + txtNewPassword.getText() + "' WHERE staffID = "
                + getData.id;

        connect = database.connectDb();

        try {
            Alert alert;
            if (txtNewUsername.getText().isEmpty()
                    ||txtNewPassword.getText().isEmpty()
                    || txtConfirmPassword.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else if (!(txtNewPassword.getText()).equals(txtConfirmPassword.getText())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("password mismatch!");
                alert.showAndWait();
            } else {

                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to change your password and username?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        statement = connect.createStatement();
                        statement.executeUpdate(sql);

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully changed!");
                        alert.showAndWait();
                    }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void userSelect(TableView<User> tblpassword, TextField txtUserid, TextField txtUsername, TextField txtUseremail, TextField txtPassword, TextField txtUsertype) {
        User userSelected = tblpassword.getSelectionModel().getSelectedItem();
        int num = tblpassword.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        txtUserid.setText(String.valueOf(userSelected.getUserid()));
        txtUsername.setText(userSelected.getUsername());
        txtUseremail.setText(userSelected.getEmail());
        txtPassword.setText(userSelected.getPassword());
        txtUsertype.setText(userSelected.getUserType());
    }

    public void searchUser(TextField txtSearch, TableView<User> tblpassword) {
        FilteredList<User> filter = new FilteredList<>(userList, e -> true);

        txtSearch.textProperty().addListener((Observable, oldValue, newValue) -> {

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

        sortList.comparatorProperty().bind(tblpassword.comparatorProperty());
        tblpassword.setItems(sortList);
    }
}
