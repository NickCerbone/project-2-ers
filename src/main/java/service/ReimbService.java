package service;

import java.util.List;

import exceptions.ApplicationException;
import pojo.ReimbursementPojo;

public interface ReimbService {

	List<ReimbursementPojo> viewAllRequests() throws ApplicationException;
	
	ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException;
	
	List<ReimbursementPojo> viewAllRequestsByRequester(int requesterId) throws ApplicationException;
	
	List<ReimbursementPojo> viewPendingRequestsByRequester(int empid) throws ApplicationException;
	
	List<ReimbursementPojo> viewResolvedRequestsByRequester(int empid) throws ApplicationException;
	
	//method for manager to view all pending
	List<ReimbursementPojo> viewAllPendingRequests() throws ApplicationException;
		
	//method for manager to view all resolved
	List<ReimbursementPojo> viewAllResolvedRequests() throws ApplicationException;
	
	ReimbursementPojo manUpdateRequest(ReimbursementPojo reimbursementPojo, int reimbId) throws ApplicationException;
	
}


