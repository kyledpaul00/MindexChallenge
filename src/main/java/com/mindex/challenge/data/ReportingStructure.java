package com.mindex.challenge.data;


import com.mindex.challenge.data.Employee;
import java.util.List;

public class ReportingStructure {
	private Employee employee;
	private int numberOfReports;
	
	public ReportingStructure(Employee temp) {
		employee = temp;
		numberOfReports = findReports(employee);
	}
	
	public int getReports() {
		return this.numberOfReports;
	}
	
	private int recurse(List<Employee> lst) {
		if(lst==null || lst.size()==0) {
			return 0;
		}
		else if (lst.size() == 1) {
			Employee leftNode = lst.get(0);
			return 1 + recurse(leftNode.getDirectReports());
		}
		else {
			Employee leftNode = lst.get(0);
			Employee rightNode = lst.get(1);
			return 2 + recurse(leftNode.getDirectReports()) + recurse(rightNode.getDirectReports());
		}
		
	}
	private int findReports(Employee employee) {
		List<Employee> lst = employee.getDirectReports();
		return recurse(lst);
		}
	}
