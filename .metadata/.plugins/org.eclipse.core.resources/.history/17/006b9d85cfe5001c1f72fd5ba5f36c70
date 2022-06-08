package service;

import java.util.List;

import exception.ApplicationException;
import model.EmployeePojo;
import model.ReimbursementPojo;

public interface ReimbursementService {
		
	//Method for employee to submit a reimbursement request
	ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException;
	
	//Method for manager to approve/deny pending reimbursement requests
	ReimbursementPojo manUpdateRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException;
	
	//Method for employee to view their pending reimbursement requests
	ReimbursementPojo empViewPending(int empId) throws ApplicationException;
	
	//Method for employee to view their resolved reimbursement requests
	ReimbursementPojo empViewResolved(int empId) throws ApplicationException;
	
	//Method for manager to view all pending request of all employees
	List<ReimbursementPojo> manViewAllPending() throws ApplicationException;
	
	//Method for manager to view all resolved request of all employees
	List<ReimbursementPojo> manViewAllResolved() throws ApplicationException;
	
	//Method for manager to view reimbursement request of a specific employee
	ReimbursementPojo manViewRequest(int requesterId) throws ApplicationException;
	

}
