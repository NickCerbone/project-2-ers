package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.mindrot.jbcrypt.BCrypt;

import exceptions.ApplicationException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public pojo.EmployeePojo getEmployee(String empUserName, String empHashedPassword) throws exceptions.ApplicationException {
		Statement stmt;
		EmployeePojo employeePojo = null;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select * from employee_table where user_name='"+empUserName+"' and hashed_password='"+empHashedPassword+"'";
			
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getString(7));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
	
		return employeePojo;
	}

	@Override
	public EmployeePojo empViewInfo(int empId) throws ApplicationException {
		// LOG.info("Entered empViewInfo() in Dao...");
		// LOG.info("hit empViewInfo() in EmployeeDaoImpl");
		Statement stmt;
		EmployeePojo employeePojo = null;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select * from employee_table where emp_Id='"+empId+"'";

			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getInt(6), rs.getString(7));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		return employeePojo;
	}

	@Override
	public EmployeePojo empUpdateInfo(EmployeePojo employeePojo, int empId) throws ApplicationException {
		// LOG.info("Entered empUpdateInfo() in Dao...");
		// LOG.info("hit empUpdateInfo() in EmployeeDaoImpl");
		try {
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "UPDATE employees SET first_name='"+employeePojo.getEmpFirstName()+"', last_name='"+employeePojo.getEmpLastName()+"', user_name='"+employeePojo.getEmpUserName()+"', hashed_password='"+employeePojo.getEmpHashedPassword()+"' WHERE emp_id='"+empId+"' ";

			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		return employeePojo;
	}

	@Override
	public List<EmployeePojo> manViewAll() throws ApplicationException{
		List<EmployeePojo> allEmployees = new ArrayList<EmployeePojo>();
		Statement stmt;
		
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "SELECT * FROM employee_table";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				EmployeePojo employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7));
				allEmployees.add(employeePojo);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		return allEmployees;
	}	
	
}


