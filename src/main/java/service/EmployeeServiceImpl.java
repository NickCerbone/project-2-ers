package service;

import java.util.List;

import dao.EmployeeDao;
import dao.EmployeeDaoHibernateImpl;
import exceptions.ApplicationException;
import pojo.EmployeePojo;

public class EmployeeServiceImpl implements EmployeeService {

	EmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		// this.employeeDao = new EmployeeDaoImpl();
		this.employeeDao = new EmployeeDaoHibernateImpl();
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
	public EmployeePojo getEmployee(EmployeePojo employeePojo) throws ApplicationException {
		EmployeePojo returnEmployeePojo = this.employeeDao.getEmployee(employeePojo);
		Boolean checkedPass = employeeDao.checkPass(employeePojo.getEmpHashedPassword(),returnEmployeePojo.getEmpHashedPassword());
		if (checkedPass ==true) {
			return returnEmployeePojo;
		}else {
			throw new ApplicationException("Invalid password");
		}
		
	}

}
