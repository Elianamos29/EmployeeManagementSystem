import java.util.Date;

public class Leave {
    private int leaveId;
    private int employeeId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;
    private String remarks;

    public Leave(int leaveId, int employeeId, Date startDate, Date endDate, String reason, String status, String remarks) {
        this.leaveId = leaveId;
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
        this.remarks = remarks;
    }

    // Getters and setters

    // Other leave-related methods
}