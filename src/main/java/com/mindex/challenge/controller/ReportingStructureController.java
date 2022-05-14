package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportingStructureController {
	
	 @Autowired
	 private EmployeeService employeeService;

	 @GetMapping("/employee/{id}/reportingStructure")
	 public ReportingStructure getReports(@PathVariable String id) {
	 	Employee employee = employeeService.read(id);
	 	ReportingStructure temp = new ReportingStructure(employee);
	 	return temp;
	    }

}
