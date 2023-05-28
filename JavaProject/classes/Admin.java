import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import java.sql.*;

public class Admin {
    private int adminId;
    private String username;
    private String password;
    private boolean isLoggedIn;
    private DatabaseConnection dbConnection;

    public Admin(int adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
        this.dbConnection = new DatabaseConnection();
    }

    public boolean login() {
        Scanner scanner = new Scanner(System.in);
        String inputUsername = scanner.nextLine();
        String inputPassword = scanner.nextLine();
        scanner.close();

        // Open database connection
        dbConnection.open();

        // Prepare SQL query to check admin credentials
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try {
            // Execute the query
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
            statement.setString(1, inputUsername);
            statement.setString(2, inputPassword);
            ResultSet resultSet = statement.executeQuery();

            // Check if admin credentials are valid
            boolean isValid = resultSet.next();

            // Close the result set and statement
            resultSet.close();
            statement.close();

            // Close database connection
            dbConnection.close();

            if (isValid) {
                isLoggedIn = true;
            }

            return isValid;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void logout() {
        isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    // Other admin-related methods

    public void generatePayrollReport() {
        if (isLoggedIn) {
            // Open database connection
            dbConnection.open();
            Connection connection = dbConnection.getConnection();

            // Prepare SQL query to retrieve payroll information
            String sql = "SELECT employee_name, salary FROM employees";

            try {
                // Execute the query
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                // Print payroll report to the UI created by JavaFX

                // Close the result set and statement
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Close database connection
            dbConnection.close();
        } else {
            System.out.println("Error message: Access Denied Bitch");
        }

        // Other admin-related methods
    }

}