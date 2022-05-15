package com.mindex.challenge.data;

import com.mindex.challenge.data.Employee;
import java.util.Date;

public class Compensation {

    private Employee employee;
    private int salary;
    private String effectiveDate;

    public Compensation(){
        Date date = new Date();
        this.effectiveDate = date.toString();
    }

    public Employee getEmployee() {
        return employee;
    }

    public  void setEmployee(Employee employee){this.employee = employee;}

    public int getSalary(){return salary;}

    public void setSalary(int salary){this.salary = salary;}
    
    public String getEffectiveDate(){return effectiveDate;}

}
