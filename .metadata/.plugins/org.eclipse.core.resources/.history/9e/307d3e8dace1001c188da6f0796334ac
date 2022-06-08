package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.ApplicationException;
import model.EmployeePojo;
import model.ReimbursementPojo;
import model.StatusPojo;

public class ReimbursementDaoImpl implements ReimbursementDao {
	StatusPojo statusPojo = new StatusPojo();

	@Override
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReimbursementPojo manUpdateRequest(ReimbursementPojo reimbursementPojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReimbursementPojo empViewPending(ReimbursementPojo reimbursementPojo) throws ApplicationException{
		Connection connect;
		try {
			connect = DBUtil.dbConnection();
			Statement stmt = connect.createStatement();
			String query = "SELECT status, status_id FROM status JOIN reimbursements ON status.status_id = reimbursements.reimb_status_id WHERE status ='pending', requester_id="+reimbursementPojo.getRequesterId();                                                    
			ResultSet resultSet = stmt.executeQuery(query);
			resultSet.next();
			statusPojo.setStatus(resultSet.getString(0));
			statusPojo.setStatus_Id(resultSet.getInt(1));
			reimbursementPojo.setReimbStatusId(resultSet.getInt(1));
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo empViewResolved(ReimbursementPojo reimbursementPojo) throws ApplicationException{
		Connection connect;
		try {
			connect = DBUtil.dbConnection();
			Statement stmt = connect.createStatement();
			String query = "SELECT status, status_id FROM status JOIN reimbursements ON status.status_id = reimbursements.reimb_status_id WHERE status='approved', status='denied', requester_id="+reimbursementPojo.getRequesterId();                                                     
			ResultSet resultSet = stmt.executeQuery(query);
			resultSet.next();
			statusPojo.setStatus(resultSet.getString(0));
			statusPojo.setStatus_Id(resultSet.getInt(1));
			reimbursementPojo.setReimbStatusId(resultSet.getInt(1));
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo manViewAllPending(ReimbursementPojo reimbursementPojo) throws ApplicationException{
		Connection connect;
		try {
			connect = DBUtil.dbConnection();
			Statement stmt = connect.createStatement();
			String query = "SELECT * FROM status JOIN reimbursements ON status.status_id = reimbursements.reimb_status_id WHERE status='pending";                                                     
			ResultSet resultSet = stmt.executeQuery(query);
			if(resultSet.next()) {
				reimbursementPojo =  new ReimbursementPojo(resultSet.getInt(2), resultSet.getDouble(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));
				statusPojo.setStatus(resultSet.getString(0));
				statusPojo.setStatus_Id(resultSet.getInt(1));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo manViewAllResolved(ReimbursementPojo reimbursementPojo) throws ApplicationException{
		Connection connect;
		try {
			connect = DBUtil.dbConnection();
			Statement stmt = connect.createStatement();
			String query = "SELECT * FROM status JOIN reimbursements ON status.status_id = reimbursements.reimb_status_id WHERE status='approved', status='denied'";                                                  
			ResultSet resultSet = stmt.executeQuery(query);
			if(resultSet.next()) {
				reimbursementPojo = new ReimbursementPojo(resultSet.getInt(2), resultSet.getDouble(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6));statusPojo.setStatus(resultSet.getString(0));
				statusPojo.setStatus_Id(resultSet.getInt(1));
				
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo manViewRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException{
		//LOG.info("Entered manViewAll() in Dao...");
		Connection connect = null;
		//ReimbursementPojo reimbursementPojo = null;
		try {
			connect = DBUtil.dbConnection();
			Statement stmt = connect.createStatement();
			String query = "SELECT e.emp_id,  e.first_name, e.last_name, r.reimb_id , s.status FROM employees e JOIN reimbursements r ON e.emp_id = r.requester_id JOIN status s ON r.reimb_status_id = s.status_id WHERE e. emp_id ="+reimbursementPojo.getRequesterId();
			ResultSet resultSet = stmt.executeQuery(query);
			if(resultSet.next()) {
				//reimbursementPojo = new ReimbursementPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4));
				reimbursementPojo = new ReimbursementPojo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		//LOG.info("Exited manViewAll() in Dao...");
			return reimbursementPojo;
	}

}
