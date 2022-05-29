package com.bridgelabz.test;
import com.bridgelabz.EmployeePayrollData;
import com.bridgelabz.EmployeePayrollService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static com.bridgelabz.EmployeePayrollService.IOService.DB_IO;

public class EmployeePayrollServiceTest {

    //JDBC UC-2
    @Test
    public void givenEmployeePayrollInDB_whenRetrieved_shouldMatchEmployeeCount(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollDataDB(DB_IO);
        System.out.println(employeePayrollData);
        Assertions.assertEquals(3,employeePayrollData.size());
    }

    //JDBC UC-3
    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_shouldSyncDB(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollDataDB(DB_IO);
        employeePayrollService.updateEmployeeSalary("Terissa",500000);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terissa");
        System.out.println(result);
        Assertions.assertTrue(result);
    }

    //JDBC UC-4
    @Test
    public void givenNewSalaryForEmployee_WhenUpdatedUsingPreparedStatement_shouldSyncDB(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollDataDB(DB_IO);
        employeePayrollService.updateEmployeeSalary("Terisa",3000000);
        boolean result = employeePayrollService.checkEmployeePayrollInSyncWithDB("Terisa");
        Assertions.assertTrue(result);
    }

    //JDBC UC-5
    @Test
    public void givenDateRange_whenRetrieved_shouldMatchEmployeeCount(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollDataDB(DB_IO);
        LocalDate startDate = LocalDate.of(2018,01,01);
        LocalDate endDate = LocalDate.now();
        List<EmployeePayrollData> employeePayrollData = employeePayrollService.readEmployeePayrollDataForDateRange(DB_IO,startDate,endDate);
        Assertions.assertEquals(3,employeePayrollData.size());
    }

    //JDBC UC-6
    @Test
    public void givenPayrollData_whenAverageSalaryRetrievedByGender_shouldReturnProperValue(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        employeePayrollService.readEmployeePayrollDataDB(DB_IO);

        Map<String,Double> salarySumByGender = employeePayrollService.readSalarySumByGender(DB_IO);
        Map<String,Double> averageSalaryByGender = employeePayrollService.readAverageSalaryByGender(DB_IO);
        Map<String,Double> minSalaryByGender = employeePayrollService.readMinSalaryByGender(DB_IO);
        Map<String,Double> maxSalaryByGender = employeePayrollService.readMaxSalaryByGender(DB_IO);
        Map<String,Integer> countSalaryByGender = employeePayrollService.readCountSalaryByGender(DB_IO);

        Assertions.assertTrue(salarySumByGender.get("M").equals(4000000.00) && salarySumByGender.get("F").equals(3000000.00));
        Assertions.assertTrue(averageSalaryByGender.get("M").equals(2000000.00) && averageSalaryByGender.get("F").equals(3000000.00));
        Assertions.assertTrue(minSalaryByGender.get("M").equals(1000000.00) && minSalaryByGender.get("F").equals(3000000.00));
        Assertions.assertTrue(maxSalaryByGender.get("M").equals(3000000.00) && maxSalaryByGender.get("F").equals(3000000.00));
        Assertions.assertTrue(countSalaryByGender.get("M").equals(2) && countSalaryByGender.get("F").equals(1));
    }
}

