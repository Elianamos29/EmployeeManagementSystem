import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;


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
            // Retrieve the attendance records from the database
            Map<Integer, Integer> attendanceRecords = getAttendanceRecords();

            // Calculate payroll based on attendance
            Map<Integer, Double> payroll = calculatePayroll(attendanceRecords);

            // Print the payroll report
            System.out.println("Payroll Report:");
            System.out.println("--------------------");
            for (Map.Entry<Integer, Double> entry : payroll.entrySet()) {
                int employeeId = entry.getKey();
                double salary = entry.getValue();
                System.out.println("Employee ID: " + employeeId + ", Salary: $" + salary);
            }
            System.out.println("--------------------");
        } else {
            System.out.println("Access denied. Please log in.");
        }
    }

    private Map<Integer, Integer> getAttendanceRecords() {
        Map<Integer, Integer> attendanceRecords = new HashMap<>();

        // Open database connection
        dbConnection.open();

        // Prepare SQL query to retrieve attendance records
        String sql = "SELECT employee_id, COUNT(*) AS attendance_count FROM attendance GROUP BY employee_id";

        try {
            // Execute the query
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Process the result set
            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                int attendanceCount = resultSet.getInt("attendance_count");
                attendanceRecords.put(employeeId, attendanceCount);
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();

            // Close database connection
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceRecords;
    }

    private Map<Integer, Double> calculatePayroll(Map<Integer, Integer> attendanceRecords) {
        Map<Integer, Double> payroll = new HashMap<>();

        // Iterate over the attendance records and calculate payroll
        for (Map.Entry<Integer, Integer> entry : attendanceRecords.entrySet()) {
            int employeeId = entry.getKey();
            int attendanceCount = entry.getValue();

            // Assuming salary is fixed per day
            double salaryPerDay = 100.0;

            // Calculate salary based on attendance count
            double salary = salaryPerDay * attendanceCount;

            payroll.put(employeeId, salary);
        }

        return payroll;
    }

}