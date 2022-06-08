package dao;

import exception.ApplicationException;
import model.ReimbursementPojo;

public interface ReimbursementDao {
	
	//Method for employee to submit a reimbursement request
	ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo);
	
	//Method for manager to approve/deny pending reimbursement requests
	ReimbursementPojo manUpdateRequest(ReimbursementPojo reimbursementPojo);
	
	//Method for employee to view their pending reimbursement requests
	ReimbursementPojo empViewPending(ReimbursementPojo reimbursementPojo) throws ApplicationException;
	
	//Method for employee to view their resolved reimbursement requests
	ReimbursementPojo empViewResolved(ReimbursementPojo reimbursementPojo) throws ApplicationException;
	
	//Method for manager to view all pending request of all employees
	ReimbursementPojo manViewAllPending(ReimbursementPojo reimbursementPojo) throws ApplicationException;
	
	//Method for manager to view all resolved request of all employees
	ReimbursementPojo manViewAllResolved(ReimbursementPojo reimbursementPojo) throws ApplicationException;
	
	//Method for manager to view reimbursement request of a specific employee
	ReimbursementPojo manViewRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException;

}
