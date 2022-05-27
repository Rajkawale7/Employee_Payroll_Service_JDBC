package com.bridgelabz;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

public class EmployeePyrollService {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("*****WELCOME TO THE PROGRAM ON JAVA DATABASE CONNECTIVITY*****");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("driver loaded");
            String url = "jdbc:mysql://localhost:3306/employee_payroll_service";
            String username = "root";
            String password = "Mietgondia712@";
            Connection connection;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver Loaded");
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("cannot find the driver in classpath");
            }
            listDrivers();
            try {
                System.out.println("connecting to database " + url);
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("connection is successful " + connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
        }
    }

    private static void listDrivers() {
        Enumeration<Driver> driverList=DriverManager.getDrivers();

        while(driverList.hasMoreElements()){
            Driver driverClass=(Driver) driverList.nextElement();
            System.out.println(" "+driverClass.getClass().getName());
        }
    }
}