package service;

import java.util.List;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import exceptions.ApplicationException;
import pojo.ReimbursementPojo;

public class ReimbServiceImpl implements ReimbService {
	
	ReimbursementDao reimbursementDao;
	
	public ReimbServiceImpl() {
		this.reimbursementDao = new ReimbursementDaoImpl();
	}

	@Override
	public List<ReimbursementPojo> viewAllRequests() throws ApplicationException {
		List<ReimbursementPojo> allRequests = this.reimbursementDao.viewAllRequests();
		return allRequests;
	}

	@Override
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {
		ReimbursementPojo returnReimbursementPojo = this.reimbursementDao.submitRequest(reimbursementPojo);
		return returnReimbursementPojo;
	}
	
	@Override
	public List<ReimbursementPojo> viewAllRequestsByRequester(int requesterId) throws ApplicationException {
		List<ReimbursementPojo> allRequestsByRequester = this.reimbursementDao.viewAllRequestsByRequester(requesterId);
		return allRequestsByRequester;
	}
	
	@Override
	public List<ReimbursementPojo> viewPendingRequestsByRequester(int empid) throws ApplicationException {
		List<ReimbursementPojo> allPendingRequestsByRequester = this.reimbursementDao.viewPendingRequestsByRequester(empid);
		return allPendingRequestsByRequester;
	}
	
	@Override
	public List<ReimbursementPojo> viewResolvedRequestsByRequester(int empid) throws ApplicationException {
		List<ReimbursementPojo> allResolvedRequestsByRequester = this.reimbursementDao.viewResolvedRequestsByRequester(empid);
		return allResolvedRequestsByRequester;
	}

	@Override
	public List<ReimbursementPojo> viewAllPendingRequests() throws ApplicationException {
		List<ReimbursementPojo> allPendingRequests = this.reimbursementDao.viewAllPendingRequests();
		return allPendingRequests;
	}

	@Override
	public List<ReimbursementPojo> viewAllResolvedRequests() throws ApplicationException {
		List<ReimbursementPojo> allResolvedRequests = this.reimbursementDao.viewAllResolvedRequests();
		return allResolvedRequests;
	}
}
