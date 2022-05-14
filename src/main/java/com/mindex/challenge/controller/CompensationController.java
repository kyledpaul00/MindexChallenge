package com.mindex.challenge.controller;


import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompensationController {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);
    @Autowired
    private CompensationService compensationService;


    @PostMapping("/employee/{id}/compensation")
    public Compensation create(@RequestBody Compensation employeeComp, @PathVariable String id) {
        LOG.debug("Received compensation create request for [{}]", employeeComp);

        return compensationService.create(employeeComp);
    }
    @GetMapping("/employee/{id}/compensation")
    public Compensation read(@PathVariable String id){
        LOG.debug("Received compensation read request for employeeId [{}]", id);
        return compensationService.read(id);
    }
}
