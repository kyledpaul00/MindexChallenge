package com.mindex.challenge.reportingStructure;	

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import com.mindex.challenge.DataBootstrap;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class reportingStructureTest {
	
	private String employeeUrl;
    private String employeeIdUrl;
    
    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
    }

    @Test
    public void testReportingStructure() {
    	
    	List<Employee> johnReports = new ArrayList<Employee>();
    	List<Employee> ringoReports = new ArrayList<Employee>();
    	Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Lennon");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");
        
        Employee subEmployee = new Employee();
        subEmployee.setFirstName("Paul");
        subEmployee.setLastName("McCartney");
        subEmployee.setDepartment("Engineering");
        subEmployee.setPosition("Developer");
        
        Employee subEmployee2 = new Employee();
        subEmployee2.setFirstName("Ringo");
        subEmployee2.setLastName("Starr");
        subEmployee2.setDepartment("Engineering");
        subEmployee2.setPosition("Developer");
        
        assertTrue(johnReports.add(subEmployee));
        assertTrue(johnReports.add(subEmployee2));
        
        testEmployee.setDirectReports(johnReports);
        
        Employee subEmployee3 = new Employee();
        subEmployee3.setFirstName("Pete");
        subEmployee3.setLastName("Best");
        subEmployee3.setDepartment("Engineering");
        subEmployee3.setPosition("Developer");
        
        Employee subEmployee4 = new Employee();
        subEmployee4.setFirstName("George");
        subEmployee4.setLastName("Harrison");
        subEmployee4.setDepartment("Engineering");
        subEmployee4.setPosition("Developer");
        
        assertTrue(ringoReports.add(subEmployee3));
        assertTrue(ringoReports.add(subEmployee4));
        
        subEmployee2.setDirectReports(ringoReports);
        
        
        restTemplate.postForEntity(employeeUrl, subEmployee4, Employee.class);
        
        restTemplate.postForEntity(employeeUrl, subEmployee3, Employee.class);
        
        Employee createdSubEmployee = restTemplate.postForEntity(employeeUrl, subEmployee2, Employee.class).getBody();
        
        Employee readSubEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, createdSubEmployee.getEmployeeId()).getBody();
        
        restTemplate.postForEntity(employeeUrl, subEmployee, Employee.class);
        
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();
        
        
        Employee readEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, createdEmployee.getEmployeeId()).getBody();


        
        ReportingStructure forJohn = new ReportingStructure(readEmployee);
        
        ReportingStructure forRingo = new ReportingStructure(readSubEmployee);
        
        assertEquals(4, forJohn.getReports());
        
        assertEquals(2, forRingo.getReports());
        
        
    }
}
