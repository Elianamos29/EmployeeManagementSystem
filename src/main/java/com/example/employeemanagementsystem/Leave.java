package com.example.employeemanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Leave {
    private int requestID;
    private int staffID;
    private String leaveType;
    private String description;
    private Date fromDate;
    private Date toDate;
    private String status;
    private String comment;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    public Leave() {

    }

    public Leave(int requestID, int staffID, String leaveType, String description, Date fromDate, Date toDate, String status, String comment) {
        this.requestID = requestID;
        this.staffID = staffID;
        this.leaveType = leaveType;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
        this.comment = comment;
    }

    public int getRequestID() {
        return requestID;
    }

    public int getStaffID() {
        return staffID;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public String getDescription() {
        return description;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public String getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

}
