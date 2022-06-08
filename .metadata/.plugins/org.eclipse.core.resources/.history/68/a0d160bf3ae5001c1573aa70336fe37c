package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import dao.DBUtil;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import dao.ReimbursementDaoImpl;
import exception.ApplicationException;
import model.EmployeePojo;
import model.RolesPojo;

public class EmployeeServiceImpl implements EmployeeService{
	final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);
	
	//Creating an EmployeeDaoImpl to call dao layer from the service layer
	EmployeeDao employeeDao= new EmployeeDaoImpl();
	

	@Override
	public EmployeePojo login(String username, String password) throws ApplicationException {
		LOG.info("hit login() in service.");
		EmployeePojo user = null;
		try {
			user=employeeDao.login(username, password);
			LOG.info("returning login() in service.");
			return user;
		}catch(ApplicationException e) {
			throw new ApplicationException(e.getLocalizedMessage());
		}

		

	}

	@Override
	public EmployeePojo empViewInfo(int empId) throws ApplicationException{
		LOG.info("Entered empViewInfo() in service.");
		EmployeePojo returnEmployeePojo = this.employeeDao.empViewInfo(empId);
		LOG.info("Exited empViewInfo() in service.");
		return returnEmployeePojo;
	}

	@Override
	public EmployeePojo empUpdateInfo(EmployeePojo employeePojo, String currPassword) throws ApplicationException{
		LOG.info("Entered empUpdateInfo() in service.");
		Boolean checkPassword = employeeDao.checkPass(currPassword, employeePojo.getEmpPassword());
		if (checkPassword == true) {
			EmployeePojo returnEmployeePojo = this.employeeDao.empUpdateInfo(employeePojo);
			LOG.info("Exited empUpdateInfo() in service.");
			return returnEmployeePojo;
		}else {
			throw new ApplicationException("incorrect Password");
		}
		
		
		
	}

	@Override
	public List<EmployeePojo> manViewAll() throws ApplicationException{
		LOG.info("Entered manViewAll() in service.");
		List<EmployeePojo> allEmployees = employeeDao.manViewAll();
		LOG.info("Exited manViewAll() in service.");

		return allEmployees;
	}
	@Override
	public EmployeePojo logout(EmployeePojo employeePojo) {
		// TODO Auto-generated method stub
		LOG.info("Entered logout() in service.");
		LOG.info("Returning logout() in service.");
		return null;
	}
	@Override
	public EmployeePojo changePassword(int empId) throws ApplicationException {
		LOG.info("Entered changePassword() in service.");
		EmployeePojo returnEmployeePojo = this.employeeDao.changePassword(empId);
		LOG.info("Exited changePassword() in service.");
		return returnEmployeePojo;
	}
	@Override
	public RolesPojo getRole(int id) throws ApplicationException {
	LOG.info("Entered getRole() in service.");
	RolesPojo rolesPojo = employeeDao.getRole(id);
	LOG.info("Exited getRole() in service.");
	return rolesPojo;
	}
}
