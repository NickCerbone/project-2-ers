package service;

import java.util.List;
import java.util.logging.LogManager;

import org.slf4j.Logger;

import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import exceptions.ApplicationException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		this.employeeDao = new EmployeeDaoImpl();
	}

	@Override
	public EmployeePojo empViewInfo(int empId) throws ApplicationException {
		EmployeePojo returnEmployeePojo = this.employeeDao.empViewInfo(empId);
		return returnEmployeePojo;
	}

	@Override
	public EmployeePojo empUpdateInfo(EmployeePojo employeePojo, int empId) throws ApplicationException {
		EmployeePojo returnEmployeePojo = this.employeeDao.empUpdateInfo(employeePojo, empId);
		return returnEmployeePojo;
	}

	@Override
	public List<EmployeePojo> manViewAll() throws ApplicationException {
		List<EmployeePojo> allEmployees = employeeDao.manViewAll();
		return allEmployees;
	}

	@Override
	public EmployeePojo getEmployee(String empUsername, String empHashedPassword) throws ApplicationException {
		EmployeePojo returnEmployeePojo = this.employeeDao.getEmployee(empUsername, empHashedPassword);
		return returnEmployeePojo;
	}
	
	
}
