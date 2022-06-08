package dao;

import java.util.List;

import exceptions.ApplicationException;
import pojo.ReimbursementPojo;

public interface ReimbursementDao {

	ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException;
	
	List<ReimbursementPojo> viewAllRequestsByRequester(int requesterId) throws ApplicationException;
	
	List<ReimbursementPojo> viewPendingRequestsByRequester(int empid) throws ApplicationException;
	
	List<ReimbursementPojo> viewResolvedRequestsByRequester(int empId) throws ApplicationException;
	
	List<ReimbursementPojo> viewAllRequests() throws ApplicationException;
	
	List<ReimbursementPojo> viewAllPendingRequests() throws ApplicationException;
	
	List<ReimbursementPojo> viewAllResolvedRequests() throws ApplicationException;
	
}
