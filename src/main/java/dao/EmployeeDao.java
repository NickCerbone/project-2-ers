package dao;

import java.util.List;

import exceptions.ApplicationException;
import pojo.EmployeePojo;

public interface EmployeeDao {
	//hashes password
	public String hashPassword(String password);
	//checks password
	public boolean checkPass(String password, String hashedPass);
	//Method for employee to view their information
	EmployeePojo empViewInfo(int empId) throws ApplicationException;
	
	//Method for employee to update their information
	EmployeePojo empUpdateInfo(EmployeePojo employeePojo, int empId) throws ApplicationException;
	
	//Method for manager to view all employees
	List<EmployeePojo> manViewAll() throws ApplicationException;
	
	//Method for getting employee information
	EmployeePojo getEmployee (EmployeePojo employeePojo) throws ApplicationException;
	
}
