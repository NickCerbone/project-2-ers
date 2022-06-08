package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import exception.ApplicationException;
import model.EmployeePojo;
import model.RolesPojo;

public class EmployeeDaoImpl implements EmployeeDao {
	final Logger LOG = LogManager.getLogger(EmployeeDaoImpl.class);
	@Override
	public String hashPassword(String password) {
		LOG.info("hit hashPassword() in EmployeeDaoImpl");
		// takes your password and returns an encrypted version of it
		String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(10));
		LOG.info("returning hashPassword() in EmployeeDaoImpl");
		return hashedPass;
	}
	@Override
	
	public boolean checkPass(String password, String hashedPass) {
		LOG.info("hit checkPass() in EmployeeDaoImpl");
		// takes your password and an encrypted password and compares it to see if its
		// the same values
		// as the password, if so it returns true
		LOG.info("returning checkPass() in EmployeeDaoImpl");
		if (BCrypt.checkpw(password, hashedPass)) {
			
			return true;
		} else {
			
			return false;
		}
	}
	@Override
	public EmployeePojo login(String username, String password) throws ApplicationException {
		LOG.info("hit login() in EmployeeDaoImpl");
		Connection conn;
		ResultSet result = null;
		EmployeePojo employeePojo = null;
		try {
			conn = DBUtil.dbConnection();
			String query = "SELECT * FROM employees WHERE user_name = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, username);
			result = stmt.executeQuery();
			if(result.next()) {
				boolean checkPassword = checkPass(password, result.getString("hashed_password"));
				if (checkPassword == true) {
					// if correct password returns the user and their information
					employeePojo = new EmployeePojo(result.getInt(1), result.getString(2), result.getString(3),
							result.getString(4), result.getString(5), result.getInt(6));
					
					
				} else {
					throw new ApplicationException("invalid username or password");
				}
			}
			LOG.info("returning login() in EmployeeDaoImpl");
			return employeePojo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException(e.getLocalizedMessage());
		}
		catch(ApplicationException e){
			LOG.info("returning login ApplicationException");
			throw new ApplicationException(e.getLocalizedMessage());
		}
		
		
		
		
		
	}

	@Override
	public EmployeePojo logout(EmployeePojo employeePojo) {
		LOG.info("hit logout() in EmployeeDaoImpl");
		LOG.info("returning logout() in EmployeeDaoImpl");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeePojo empViewInfo(int empId) throws ApplicationException{
		//LOG.info("Entered empViewInfo() in Dao...");
		LOG.info("hit empViewInfo() in EmployeeDaoImpl");		
		Connection connect = null;
				EmployeePojo employeePojo = null;
				try {
					connect = DBUtil.dbConnection();
					Statement stmt = connect.createStatement();
					String query = "SELECT * FROM employees WHERE emp_id="+empId;
					ResultSet result = stmt.executeQuery(query);
					if(result.next()) {
						employeePojo = new EmployeePojo(result.getInt(1), result.getString(2), result.getString(3),
								result.getString(4), result.getString(5), result.getInt(6));					}
				} catch (SQLException e) {
					throw new ApplicationException(e.getMessage());
				}
				LOG.info("returning empViewInfo() in EmployeeDaoImpl");
				//LOG.info("Exited empViewInfo() in Dao...");
				return employeePojo;
	}

	@Override
	public EmployeePojo empUpdateInfo(EmployeePojo employeePojo) throws ApplicationException{
		//LOG.info("Entered empUpdateInfo() in Dao...");
		LOG.info("hit empUpdateInfo() in EmployeeDaoImpl");
				Connection connect = null;
				try {
					connect = DBUtil.dbConnection();
					Statement stmt = connect.createStatement();
					String query1 = "UPDATE employees SET first_name = '"+employeePojo.getEmpFirstName()+ 
							"' WHERE emp_id = " +employeePojo.getEmpId();
					int rowsAffected1 = stmt.executeUpdate(query1);
					String query2 = "UPDATE employees SET last_name = '"+employeePojo.getEmpLastName()+
							"' WHERE emp_id = " +employeePojo.getEmpId();
					int rowsAffected2 = stmt.executeUpdate(query2);
					String query3 = "UPDATE employees SET user_name = '"+employeePojo.getEmpUserName()+ 
							"' WHERE emp_id = " +employeePojo.getEmpId();
					int rowsAffected3 = stmt.executeUpdate(query3);
					String query4 = "UPDATE employees SET hashed_password = '"+employeePojo.getEmpPassword()+ 
							"' WHERE emp_id = " +employeePojo.getEmpId();
					int rowsAffected4 = stmt.executeUpdate(query4);
					
					//update all employee details
					String query = "UPDATE employees SET first_name = '"+employeePojo.getEmpFirstName()+"'"+ 
							", last_name = '"+employeePojo.getEmpLastName()+"'"+
							", user_name = '"+employeePojo.getEmpUserName()+"'"+
							", hashed_password = '"+employeePojo.getEmpPassword()+"'"+
							" WHERE emp_id = " +employeePojo.getEmpId();
					int rowsAffected = stmt.executeUpdate(query);

				} catch (SQLException e) {
					e.printStackTrace();
					throw new ApplicationException(e.getMessage());
				}
				LOG.info("returning empUpdateInfo() in EmployeeDaoImpl");
				//LOG.info("Exited empUpdateInfo() in Dao...");
				return employeePojo;
	}

	@Override
	public List<EmployeePojo> manViewAll() throws ApplicationException{
		//LOG.info("Entered manViewAll() in Dao...");
		LOG.info("hit manViewAll() in EmployeeDaoImpl");
				Connection connect = null;
				List<EmployeePojo> employeePojo = new ArrayList<EmployeePojo>();
				try {
					connect = DBUtil.dbConnection();
					Statement stmt = connect.createStatement();
//					String query = "SELECT e.emp_id,  e.first_name, e.last_name, r.reimb_id ,r.reimb_amt, "
//							+ "s.status FROM employees e JOIN reimbursements r ON e.emp_id = r.requester_id JOIN status s "
//							+ "ON r.reimb_status_id = s.status_id";
					String query = "SELECT * FROM employees where emp_role_id = 2;";
					ResultSet resultSet = stmt.executeQuery(query);
					
						while(resultSet.next()) {
							employeePojo.add(new EmployeePojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), 
									resultSet.getString(4), resultSet.getString(5), resultSet.getInt(6)));
						}
						
					
				} catch (SQLException e) {
					throw new ApplicationException(e.getMessage());
				}
						//LOG.info("Exited manViewAll() in Dao...");
				LOG.info("returning manViewAll() in EmployeeDaoImpl");
				return employeePojo;
			}
	@Override
	public EmployeePojo changePassword(int empId) throws ApplicationException {
		//LOG.info("Entered changePassword() in Dao...");
		LOG.info("hit changePassword() in EmployeeDaoImpl");
		Connection connect = null;
		EmployeePojo employeePojo = null;

		try {
			connect = DBUtil.dbConnection();
			Statement stmt = connect.createStatement();
			String query = "UPDATE employees SET hashed_password = '"+employeePojo.getEmpPassword()+ 
					"' WHERE emp_id = " +employeePojo.getEmpId();
			int rowsAffected = stmt.executeUpdate(query);
		}catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		//LOG.info("Exited changePassword() in Dao...");
		LOG.info("returning changePassword() in EmployeeDaoImpl");
		return employeePojo;
	}
	@Override
	public RolesPojo getRole(int id) throws ApplicationException {
		LOG.info("hit getRole() in EmployeeDaoImpl");
		Connection connect = null;
		RolesPojo rolesPojo = null;
		try {
			connect = DBUtil.dbConnection();
			Statement stmt = connect.createStatement();
			String query = "SELECT * FROM roles where role_id = " + id + ";";
			ResultSet result = stmt.executeQuery(query);
			if(result.next()) {
				rolesPojo = new RolesPojo(result.getInt(1), result.getString(2));					}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		//LOG.info("Exited changePassword() in Dao...");
		LOG.info("returning getRole() in EmployeeDaoImpl");
		return rolesPojo;
	}
}


