package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.ApplicationException;
import model.ReimbursementPojo;
import model.StatusPojo;

public class ReimbursementDaoImpl implements ReimbursementDao {
	final Logger LOG = LogManager.getLogger(ReimbursementDaoImpl.class);
	
	StatusPojo statusPojo = new StatusPojo();
	ReimbursementPojo reimbursementPojo = new ReimbursementPojo();

	@Override
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		LOG.info("hit submitRequest");
		try {
			Connection conn = DBUtil.dbConnection();
			Statement stmt = conn.createStatement();
			String query = "insert into reimbursements(reimb_amt, requester_id)" 
					+ "values('"+reimbursementPojo.getReimbAmt()+"','"+reimbursementPojo.getRequesterId()+"') returning reimb_id";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			reimbursementPojo.setReimbId(rs.getInt(1));
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		LOG.info("returning submitRequest");
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo manUpdateRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		LOG.info("hit manUpdateRequest");
		try {
			Connection conn = DBUtil.dbConnection();
			Statement stmt = conn.createStatement();
			String query = "update reimbursement set reimb_status_id='"+reimbursementPojo.getReimbStatusId()+"' where reimbId='"+reimbursementPojo.getReimbId()+"'";
			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		LOG.info("returning manUpdateRequest");
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo empViewPending(int empId) throws ApplicationException{
		LOG.info("hit empViewPending");
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
		LOG.info("returning empViewPending");
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo empViewResolved(int empId) throws ApplicationException{
		LOG.info("hit empViewResolved");
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
		LOG.info("returning empViewResolved");
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo manViewAllPending() throws ApplicationException{
		LOG.info("hit manViewAllPending");
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
		LOG.info("returning manViewAllPending");
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo manViewAllResolved() throws ApplicationException{
		
		LOG.info("hit manViewAllResolved");
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
		LOG.info("returning manViewAllResolved");
		return reimbursementPojo;
	}

	@Override
	public ReimbursementPojo manViewRequest(int requesterId) throws ApplicationException{
		LOG.info("hit manViewRequest");
		//LOG.info("Entered manViewAll() in Dao...");
		Connection connect = null;
		ReimbursementPojo reimbursementPojo = null;
		try {
			connect = DBUtil.dbConnection();
			Statement stmt = connect.createStatement();
			String query = "SELECT e.emp_id,  e.first_name, e.last_name, r.reimb_id , s.status FROM employees e JOIN reimbursements r ON e.emp_id = r.requester_id JOIN status s ON r.reimb_status_id = s.status_id WHERE e. emp_id ="+requesterId;
			ResultSet resultSet = stmt.executeQuery(query);
			if(resultSet.next()) {
				//reimbursementPojo = new ReimbursementPojo(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4));
				reimbursementPojo = new ReimbursementPojo(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getInt(5));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		LOG.info("returning manViewRequest");
			return reimbursementPojo;
	}

}
