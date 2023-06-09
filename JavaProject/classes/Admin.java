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

        dbConnection.open();

        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
            statement.setString(1, inputUsername);
            statement.setString(2, inputPassword);
            ResultSet resultSet = statement.executeQuery();

            boolean isValid = resultSet.next();

            resultSet.close();
            statement.close();

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

    public void generatePayrollReport() {
        if (isLoggedIn) {
            Map<Integer, Integer> attendanceRecords = getAttendanceRecords();

            Map<Integer, Double> payroll = calculatePayroll(attendanceRecords);

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

        dbConnection.open();

        String sql = "SELECT employee_id, COUNT(*) AS attendance_count FROM attendance GROUP BY employee_id";

        try {
            Statement statement = dbConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                int attendanceCount = resultSet.getInt("attendance_count");
                attendanceRecords.put(employeeId, attendanceCount);
            }

            resultSet.close();
            statement.close();

            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return attendanceRecords;
    }

    private Map<Integer, Double> calculatePayroll(Map<Integer, Integer> attendanceRecords) {
        Map<Integer, Double> payroll = new HashMap<>();

        for (Map.Entry<Integer, Integer> entry : attendanceRecords.entrySet()) {
            int employeeId = entry.getKey();
            int attendanceCount = entry.getValue();

            double salaryPerDay = 100.0;

            double salary = salaryPerDay * attendanceCount;

            payroll.put(employeeId, salary);
        }

        return payroll;
    }

    public void updateEmployeeSalary(int employeeId, double newSalary) {
        try {
            Connection connection = dbConnection.getConnection();
            String sql = "UPDATE employee SET salary = ? WHERE employeeId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, newSalary);
            statement.setInt(2, employeeId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee salary updated successfully.");
            } else {
                System.out.println("Failed to update employee salary.");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while updating employee salary.");
        }
    }

    public void registerEmployee(int employeeId, String name, String email, String department, Date dateOfJoining,
            String password) {
        try {
            Connection connection = dbConnection.getConnection();
            String sql = "INSERT INTO employee (employeeId, name, email, department, designation, dateOfJoining, roleId) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, employeeId);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, department);
            statement.setDate(5, new java.sql.Date(dateOfJoining.getTime()));

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                insertEmployeePassword(email, password); // Insert password into employee_password table
                // registerd successfully message to the User Interface
            } else {
                // registration failed message to the User Interface
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while registering the employee.");
        }
    }

    private void insertEmployeePassword(String email, String password) {
        try {
            Connection connection = dbConnection.getConnection();
            String sql = "INSERT INTO employee_password (email, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected <= 0) {
                System.out.println("Failed to insert employee password.");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while inserting the employee password.");
        }
    }

}