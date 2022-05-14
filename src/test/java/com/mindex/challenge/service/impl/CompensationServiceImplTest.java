package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private String employeeUrl;
    private String employeeIdUrl;
    private String employeeCompUrl;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
        employeeCompUrl = "http://localhost:" + port + "/employee/{id}/compensation";
    }

    @Test
    public void testCreateAndRead(){
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        Compensation testCompensation = new Compensation();
        testCompensation.setSalary(10000);

        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();

        String employeeId = createdEmployee.getEmployeeId();

        testCompensation.setEmployee(createdEmployee);
        //check if created
        Compensation createdComp = restTemplate.postForEntity(employeeCompUrl, testCompensation, Compensation.class, employeeId).getBody();
        assertNotNull(createdComp);

        assertEquals(testCompensation.getEmployee().getFirstName(), createdComp.getEmployee().getFirstName());
        assertEquals(testCompensation.getEmployee().getLastName(), createdComp.getEmployee().getLastName());



        //read check
        Compensation readComp = restTemplate.getForEntity(employeeCompUrl, Compensation.class, employeeId).getBody();
        assertEquals(createdComp.getEmployee().getEmployeeId(), employeeId);
        assertEquals(testCompensation.getSalary(), readComp.getSalary());

    }
}
