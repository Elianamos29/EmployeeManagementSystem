import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class Admin {
    private int adminId;
    private String username;
    private String password;
    private DatabaseConnection dbConnection;

    //Constructor
    public Admin(int adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
        this.dbConnection = new DatabaseConnection();
    }
    public boolean login(HttpServletRequest request) {
        String inputUsername = request.getParameter("username");
        String inputPassword = request.getParameter("password");

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

            // If login is successful, create a session
            if (isValid) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", inputUsername);
            }

            return isValid;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public void generatePayrollReport() {
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
    }

    // Other admin-related methods
}
