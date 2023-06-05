 // Employee.java
 import java.util.Date;
 import java.sql.*;
 import java.util.Scanner;
 public class Employee {
    private int employeeId;
    private String name;
    private String email;
    private String department;
    private Date dateOfJoining;
    private static DatabaseConnection dbConnection; 

    public Employee(String email) {
        this.email = email;
    }

    public Employee(int employeeId, String name, String email, String department, Date dateOfJoining, int roleId) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.dateOfJoining = new Date();
        this.dbConnection = new DatabaseConnection(); 
    }

    // Getters and setters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    // Setters
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public static Employee login() {
        try (Scanner scanner = new Scanner(System.in)) {
            String email = scanner.nextLine();
            String password = scanner.nextLine();

            // Validate the email and password
            if (validateLogin(email, password)) {
                System.out.println("Login successful.");
                return new Employee(email); // backup to the getData class - using instance to get the email
            } else {
                System.out.println("Invalid email or password.");
                return null;
            }
        }
    }
        
    private static boolean validateLogin(String email, String password) {
        try {
            Connection connection = dbConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM employee_password WHERE email = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; // Return true if at least one matching record is found
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Return false if an error occurred or no matching record found
    }

    public void changePassword(String newPassword) {
        //use the getData class to get the email
        try {
            Connection connection = dbConnection.getConnection();
            String sql = "UPDATE employee_password SET password = ? WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, email);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Failed to change password.");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred while changing the password.");
        }
    }

    public void markAttendance(Date date, String status, String remarks) {

        // Open database connection
        dbConnection.open();

        // Prepare SQL statement to insert attendance record
        String sql = "INSERT INTO attendance (employee_id, date, status, remarks) VALUES (?, ?, ?, ?)";

        try {
            // Create a PreparedStatement with the SQL statement
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);

            // Set the parameter values
            statement.setInt(1, employeeId);
            statement.setDate(2, new java.sql.Date(date.getTime()));
            statement.setString(3, status);
            statement.setString(4, remarks);

            // Execute the SQL statement
            statement.executeUpdate();

            // Close the statement
            statement.close();

            // Close database connection
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Display error message
            System.out.println("Failed to mark attendance. Please try again.");
        }
    }

    public void requestLeave(Date startDate, Date endDate, String reason) {
        // Implement logic to create a leave request for the employee
    
        // Open database connection
        dbConnection.open();
    
        // Prepare SQL statement to insert leave request
        String sql = "INSERT INTO leave_request (employee_id, start_date, end_date, reason, status) VALUES (?, ?, ?, ?, ?)";
    
        try {
            // Create a PreparedStatement with the SQL statement
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(sql);
    
            // Set the parameter values
            statement.setInt(1, employeeId);
            statement.setDate(2, new java.sql.Date(startDate.getTime()));
            statement.setDate(3, new java.sql.Date(endDate.getTime()));
            statement.setString(4, reason);
            statement.setString(5, "Pending"); // Set initial status as "Pending"
    
            // Execute the SQL statement
            statement.executeUpdate();
    
            // Close the statement
            statement.close();
    
            // Close database connection
            dbConnection.close();
    
            // Send success message to UI

                } catch (SQLException e) {
            e.printStackTrace();
            // Display error message
        }
    }
    

    // Other employee-related methods
}
