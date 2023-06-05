package com.example.employeemanagementsystem;

import java.sql.Date;

public class Employee {
    private int id;
    private String fname;
    private String lname;
    private String gender;
    private String department;
    private String email;
    private String position;
    private double salary;
    private java.util.Date dateJoin;

    public Employee(int id, String fname, String lname, String gender, String department, String email, String position, double salary, java.util.Date dateJoin) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.department = department;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.dateJoin = dateJoin;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public java.util.Date getDateJoin() {
        return dateJoin;
    }
}
