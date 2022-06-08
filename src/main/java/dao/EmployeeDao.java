package dao;

import java.util.List;

import exceptions.ApplicationException;
import pojo.EmployeePojo;

public interface EmployeeDao {

	//Method for employee to view their information
	EmployeePojo empViewInfo(int empId) throws ApplicationException;
	
	//Method for employee to update their information
	EmployeePojo empUpdateInfo(EmployeePojo employeePojo, int empId) throws ApplicationException;
	
	//Method for manager to view all employees
	List<EmployeePojo> manViewAll() throws ApplicationException;
	
	//Method for getting employee information
	EmployeePojo getEmployee (String empUserName, String empHashedPassword) throws ApplicationException;
	
}
