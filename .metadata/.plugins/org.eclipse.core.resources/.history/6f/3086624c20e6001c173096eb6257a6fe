package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exceptions.ApplicationException;
import pojo.ReimbursementPojo;

public class ReimbursementDaoImpl implements ReimbursementDao {

	@Override
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		try {
			
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO reimbursements(reimb_amt, reimb_status_id, requester_id) values ('"+reimbursementPojo.getReimbAmt()+"', 1, '"+reimbursementPojo.getRequesterId()+"') returning reimb_id";

			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			reimbursementPojo.setReimbId(rs.getInt(1));
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		return reimbursementPojo;
	}
	
	@Override
	public List<ReimbursementPojo> viewAllRequestsByRequester(int requesterId) throws ApplicationException {
		List<ReimbursementPojo> allRequestsByRequester = new ArrayList<ReimbursementPojo>();
		Statement stmt;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select view_all_requests.emp_id, view_all_requests.first_name, view_all_requests.last_name, view_all_requests.reimb_id, view_all_requests.reimb_amt, view_all_requests.reimb_status_id, view_all_requests.status, view_all_requests.approver_id, approver_info.first_name, approver_info.last_name from view_all_requests left join approver_info on approver_info.emp_id=view_all_requests.approver_id where view_all_requests.emp_id='"
					+ requesterId + "'";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getInt(8),
						rs.getString(9), rs.getString(10));
				allRequestsByRequester.add(reimbursementPojo);

			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		return allRequestsByRequester;
	}

	@Override
	public List<ReimbursementPojo> viewPendingRequestsByRequester(int empId) throws ApplicationException {
		List<ReimbursementPojo> allPendingRequestsByRequester = new ArrayList<ReimbursementPojo>();
		Statement stmt;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select view_all_requests.emp_id, view_all_requests.first_name, view_all_requests.last_name, view_all_requests.reimb_id, view_all_requests.reimb_amt, view_all_requests.reimb_status_id, view_all_requests.status, view_all_requests.approver_id, approver_info.first_name, approver_info.last_name from view_all_requests left join approver_info on approver_info.emp_id=view_all_requests.approver_id where view_all_requests.emp_id='"+empId+"' and view_all_requests.reimb_status_id=1";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getInt(8),
						rs.getString(9), rs.getString(10));
				allPendingRequestsByRequester.add(reimbursementPojo);

			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		return allPendingRequestsByRequester;
	}

	@Override
	public List<ReimbursementPojo> viewResolvedRequestsByRequester(int empId) throws ApplicationException {
		List<ReimbursementPojo> allResolvedRequestsByRequester = new ArrayList<ReimbursementPojo>();
		Statement stmt;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select view_all_requests.emp_id, view_all_requests.first_name, view_all_requests.last_name, view_all_requests.reimb_id, view_all_requests.reimb_amt, view_all_requests.reimb_status_id, view_all_requests.status, view_all_requests.approver_id, approver_info.first_name, approver_info.last_name from view_all_requests left join approver_info on approver_info.emp_id=view_all_requests.approver_id where view_all_requests.emp_id='"+empId+"' and view_all_requests.reimb_status_id!=1";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {

				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getInt(8),
						rs.getString(9), rs.getString(10));
				allResolvedRequestsByRequester.add(reimbursementPojo);

			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}

		return allResolvedRequestsByRequester;
	}

	@Override
	public List<ReimbursementPojo> viewAllRequests() throws ApplicationException {
		List<ReimbursementPojo> allRequests = new ArrayList<ReimbursementPojo>();
		Statement stmt;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select view_all_requests.emp_id, view_all_requests.first_name, view_all_requests.last_name, view_all_requests.reimb_id, view_all_requests.reimb_amt, view_all_requests.reimb_status_id, view_all_requests.status, view_all_requests.approver_id, approver_info.first_name, approver_info.last_name from view_all_requests left join approver_info on approver_info.emp_id=view_all_requests.approver_id";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getInt(8),
						rs.getString(9), rs.getString(10));
				allRequests.add(reimbursementPojo);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		return allRequests;
	}

	@Override
	public List<ReimbursementPojo> viewAllPendingRequests() throws ApplicationException {
		List<ReimbursementPojo> allPendingRequests = new ArrayList<ReimbursementPojo>();
		Statement stmt;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select view_all_requests.emp_id, view_all_requests.first_name, view_all_requests.last_name, view_all_requests.reimb_id, view_all_requests.reimb_amt, view_all_requests.reimb_status_id, view_all_requests.status, view_all_requests.approver_id, approver_info.first_name, approver_info.last_name from view_all_requests left join approver_info on approver_info.emp_id=view_all_requests.approver_id where view_all_requests.reimb_status_id=1";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getInt(8),
						rs.getString(9), rs.getString(10));
				allPendingRequests.add(reimbursementPojo);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		return allPendingRequests;
	}

	@Override
	public List<ReimbursementPojo> viewAllResolvedRequests() throws ApplicationException {
		List<ReimbursementPojo> allResolvedRequests = new ArrayList<ReimbursementPojo>();
		Statement stmt;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select view_all_requests.emp_id, view_all_requests.first_name, view_all_requests.last_name, view_all_requests.reimb_id, view_all_requests.reimb_amt, view_all_requests.reimb_status_id, view_all_requests.status, view_all_requests.approver_id, approver_info.first_name, approver_info.last_name from view_all_requests left join approver_info on approver_info.emp_id=view_all_requests.approver_id where view_all_requests.reimb_status_id!=1";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6), rs.getString(7), rs.getInt(8),
						rs.getString(9), rs.getString(10));
				allResolvedRequests.add(reimbursementPojo);
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		return allResolvedRequests;
	}

}