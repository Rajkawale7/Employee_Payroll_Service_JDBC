package com.bridgelabz.test;
import com.bridgelabz.EmployeePayrollData;
import com.bridgelabz.EmployeePayrollService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
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
}

