 // Employee.java
 import java.util.Date;
 public class Employee {
    private int employeeId;
    private String name;
    private String email;
    private String department;
    private String designation;
    private Date dateOfJoining;
    private int roleId;

    public Employee(int employeeId, String name, String email, String department, String designation, Date dateOfJoining, int roleId) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.designation = designation;
        this.dateOfJoining = dateOfJoining;
        this.roleId = roleId;
    }

    // Getters and setters

    public void markAttendance(Date date, String status, String remarks) {
        // Implement logic to mark attendance for the employee on the given date
    }

    public void requestLeave(Date startDate, Date endDate, String reason) {
        // Implement logic to create a leave request for the employee
    }

    // Other employee-related methods
}
