 // Attendance.java

 import java.util.Date;
 public class Attendance {
    private int attendanceId;
    private int employeeId;
    private Date date;
    private String status;
    private String remarks;

    public Attendance(int attendanceId, int employeeId, Date date, String status, String remarks) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.date = date;
        this.status = status;
        this.remarks = remarks;
    }

    // Getters and setters

    // Other attendance-related methods
}