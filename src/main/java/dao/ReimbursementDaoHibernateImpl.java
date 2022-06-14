package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import entity.EmployeeEntity;
import entity.ReimbursementEntity;
import entity.StatusEntity;
import exceptions.ApplicationException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;
import pojo.RolesPojo;
import pojo.StatusPojo;

public class ReimbursementDaoHibernateImpl implements ReimbursementDao {

	@Override
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {

		StatusEntity statusEnt = new StatusEntity();
		statusEnt.setStatusId(reimbursementPojo.getStatus().getStatusId());

		EmployeeEntity requesterEnt = new EmployeeEntity();
		requesterEnt.setEmpId(reimbursementPojo.getRequester().getEmpId());

		ReimbursementEntity submitReimbEntity = new ReimbursementEntity(reimbursementPojo.getReimbAmt(), statusEnt,
				requesterEnt);

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		session.save(submitReimbEntity);
		session.flush();

		session.getTransaction().commit();
		session.close();

		reimbursementPojo.setReimbId(submitReimbEntity.getReimbId());
		return reimbursementPojo;
	}

	@Override
	public List<ReimbursementPojo> viewAllRequestsByRequester(int requesterId) throws ApplicationException {
		List<ReimbursementPojo> allRequestsByRequesterPojo = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from ReimbursementEntity where requester_id='" + requesterId + "'";

			List<ReimbursementEntity> allRequestsByRequesterEntity = session.createQuery(hql, ReimbursementEntity.class)
					.getResultList();

			allRequestsByRequesterPojo = new ArrayList<ReimbursementPojo>();

			for (ReimbursementEntity fetchedRequestsByRequesterEntity : allRequestsByRequesterEntity) {

				StatusPojo statusPojo = new StatusPojo();
				statusPojo.setStatusId(fetchedRequestsByRequesterEntity.getStatus().getStatusId());
				statusPojo.setStatus(fetchedRequestsByRequesterEntity.getStatus().getStatus());

				EmployeePojo employeePojoRequester = new EmployeePojo();
				employeePojoRequester.setEmpId(fetchedRequestsByRequesterEntity.getRequester().getEmpId());
				employeePojoRequester
						.setEmpFirstName(fetchedRequestsByRequesterEntity.getRequester().getEmpFirstName());
				employeePojoRequester.setEmpLastName(fetchedRequestsByRequesterEntity.getRequester().getEmpLastName());

				RolesPojo requesterRole = new RolesPojo();
				requesterRole.setRoleId(fetchedRequestsByRequesterEntity.getRequester().getRole().getRoleId());
				requesterRole.setRole(fetchedRequestsByRequesterEntity.getRequester().getRole().getRole());

				employeePojoRequester.setRole(requesterRole);

				EmployeePojo employeePojoApprover = new EmployeePojo();
				if (fetchedRequestsByRequesterEntity.getApprover() != null) {
					employeePojoApprover.setEmpId(fetchedRequestsByRequesterEntity.getApprover().getEmpId());
					employeePojoApprover
							.setEmpFirstName(fetchedRequestsByRequesterEntity.getApprover().getEmpFirstName());
					employeePojoApprover
							.setEmpLastName(fetchedRequestsByRequesterEntity.getApprover().getEmpLastName());

					RolesPojo approverRole = new RolesPojo();
					approverRole.setRoleId(fetchedRequestsByRequesterEntity.getApprover().getRole().getRoleId());
					approverRole.setRole(fetchedRequestsByRequesterEntity.getApprover().getRole().getRole());

					employeePojoApprover.setRole(approverRole);
				}

				ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
						fetchedRequestsByRequesterEntity.getReimbId(), fetchedRequestsByRequesterEntity.getReimbAmt(),
						statusPojo, employeePojoRequester, employeePojoApprover);
				allRequestsByRequesterPojo.add(returnReimbursementPojo);
			}
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return allRequestsByRequesterPojo;
	}

	@Override
	public List<ReimbursementPojo> viewPendingRequestsByRequester(int empid) throws ApplicationException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		String hql = "from ReimbursementEntity where requester_id='" + empid + "' and reimb_status_id=1";

		List<ReimbursementEntity> allPendingRequestsByRequesterEntity = session
				.createQuery(hql, ReimbursementEntity.class).getResultList();

		List<ReimbursementPojo> allPendingRequestsByRequesterPojo = new ArrayList<ReimbursementPojo>();

		for (ReimbursementEntity fetchedPendingRequestsByRequesterEntity : allPendingRequestsByRequesterEntity) {

			StatusPojo statusPojo = new StatusPojo();
			statusPojo.setStatusId(fetchedPendingRequestsByRequesterEntity.getStatus().getStatusId());
			statusPojo.setStatus(fetchedPendingRequestsByRequesterEntity.getStatus().getStatus());

			EmployeePojo employeePojoRequester = new EmployeePojo();
			employeePojoRequester.setEmpId(fetchedPendingRequestsByRequesterEntity.getRequester().getEmpId());
			employeePojoRequester
					.setEmpFirstName(fetchedPendingRequestsByRequesterEntity.getRequester().getEmpFirstName());
			employeePojoRequester
					.setEmpLastName(fetchedPendingRequestsByRequesterEntity.getRequester().getEmpLastName());

			RolesPojo requesterRole = new RolesPojo();
			requesterRole
					.setRoleId(fetchedPendingRequestsByRequesterEntity.getRequester().getRole().getRoleId());
			requesterRole.setRole(fetchedPendingRequestsByRequesterEntity.getRequester().getRole().getRole());

			employeePojoRequester.setRole(requesterRole);

			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedPendingRequestsByRequesterEntity.getReimbId(),
					fetchedPendingRequestsByRequesterEntity.getReimbAmt(), statusPojo, employeePojoRequester);
			allPendingRequestsByRequesterPojo.add(returnReimbursementPojo);
		}

		session.close();

		return allPendingRequestsByRequesterPojo;
	}

	@Override
	public List<ReimbursementPojo> viewResolvedRequestsByRequester(int empId) throws ApplicationException {
		List<ReimbursementPojo> allResolvedRequestsByRequesterPojo = null;
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from ReimbursementEntity where requester_id='" + empId + "' and reimb_status_id!=1";

			List<ReimbursementEntity> allResolvedRequestsByRequesterEntity = session
					.createQuery(hql, ReimbursementEntity.class).getResultList();

			allResolvedRequestsByRequesterPojo = new ArrayList<ReimbursementPojo>();

			for (ReimbursementEntity fetchedResolvedRequestsByRequesterEntity : allResolvedRequestsByRequesterEntity) {

				StatusPojo statusPojo = new StatusPojo();
				statusPojo.setStatusId(fetchedResolvedRequestsByRequesterEntity.getStatus().getStatusId());
				statusPojo.setStatus(fetchedResolvedRequestsByRequesterEntity.getStatus().getStatus());

				EmployeePojo employeePojoRequester = new EmployeePojo();
				employeePojoRequester.setEmpId(fetchedResolvedRequestsByRequesterEntity.getRequester().getEmpId());
				employeePojoRequester
						.setEmpFirstName(fetchedResolvedRequestsByRequesterEntity.getRequester().getEmpFirstName());
				employeePojoRequester
						.setEmpLastName(fetchedResolvedRequestsByRequesterEntity.getRequester().getEmpLastName());

				RolesPojo requesterRole = new RolesPojo();
				requesterRole.setRoleId(
						fetchedResolvedRequestsByRequesterEntity.getRequester().getRole().getRoleId());
				requesterRole
						.setRole(fetchedResolvedRequestsByRequesterEntity.getRequester().getRole().getRole());

				employeePojoRequester.setRole(requesterRole);

				EmployeePojo employeePojoApprover = new EmployeePojo();
				employeePojoApprover.setEmpId(fetchedResolvedRequestsByRequesterEntity.getApprover().getEmpId());
				employeePojoApprover
						.setEmpFirstName(fetchedResolvedRequestsByRequesterEntity.getApprover().getEmpFirstName());
				employeePojoApprover
						.setEmpLastName(fetchedResolvedRequestsByRequesterEntity.getApprover().getEmpLastName());

				RolesPojo approverRole = new RolesPojo();
				approverRole
						.setRoleId(fetchedResolvedRequestsByRequesterEntity.getApprover().getRole().getRoleId());
				approverRole.setRole(fetchedResolvedRequestsByRequesterEntity.getApprover().getRole().getRole());

				employeePojoApprover.setRole(approverRole);

				ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
						fetchedResolvedRequestsByRequesterEntity.getReimbId(),
						fetchedResolvedRequestsByRequesterEntity.getReimbAmt(), statusPojo, employeePojoRequester,
						employeePojoApprover);
				allResolvedRequestsByRequesterPojo.add(returnReimbursementPojo);
			}

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allResolvedRequestsByRequesterPojo;
	}

	@Override
	public List<ReimbursementPojo> viewAllRequests() throws ApplicationException {
		List<ReimbursementPojo> allRequestsPojo = null;

		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from ReimbursementEntity";
			List<ReimbursementEntity> allRequestsEnt = session.createQuery(hql, ReimbursementEntity.class)
					.getResultList();

			allRequestsPojo = new ArrayList<ReimbursementPojo>();
			for (ReimbursementEntity fetchedReimbEnt : allRequestsEnt) {
				StatusPojo status = new StatusPojo();
				status.setStatusId(fetchedReimbEnt.getStatus().getStatusId());
				status.setStatus(fetchedReimbEnt.getStatus().getStatus());
				EmployeePojo requester = new EmployeePojo();
				requester.setEmpId(fetchedReimbEnt.getRequester().getEmpId());
				requester.setEmpFirstName(fetchedReimbEnt.getRequester().getEmpFirstName());
				requester.setEmpLastName(fetchedReimbEnt.getRequester().getEmpLastName());
				EmployeePojo approver = new EmployeePojo();
				if (fetchedReimbEnt.getApprover() != null) {
					approver.setEmpId(fetchedReimbEnt.getApprover().getEmpId());
					approver.setEmpFirstName(fetchedReimbEnt.getApprover().getEmpFirstName());
					approver.setEmpLastName(fetchedReimbEnt.getApprover().getEmpLastName());
				}
				ReimbursementPojo returnReimbPojo = new ReimbursementPojo(fetchedReimbEnt.getReimbId(),
						fetchedReimbEnt.getReimbAmt(), status, requester, approver);
				allRequestsPojo.add(returnReimbPojo);

			}

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allRequestsPojo;
	}

	@Override
	public List<ReimbursementPojo> viewAllPendingRequests() throws ApplicationException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		String hql = "from ReimbursementEntity where reimb_status_id=1";

		List<ReimbursementEntity> allPendingRequestsEntity = session.createQuery(hql, ReimbursementEntity.class)
				.getResultList();

		List<ReimbursementPojo> allPendingRequestsPojo = new ArrayList<ReimbursementPojo>();

		for (ReimbursementEntity fetchedAllPendingRequestsEntity : allPendingRequestsEntity) {

			StatusPojo statusPojo = new StatusPojo();
			statusPojo.setStatusId(fetchedAllPendingRequestsEntity.getStatus().getStatusId());
			statusPojo.setStatus(fetchedAllPendingRequestsEntity.getStatus().getStatus());

			EmployeePojo employeePojoRequester = new EmployeePojo();
			employeePojoRequester.setEmpId(fetchedAllPendingRequestsEntity.getRequester().getEmpId());
			employeePojoRequester.setEmpFirstName(fetchedAllPendingRequestsEntity.getRequester().getEmpFirstName());
			employeePojoRequester.setEmpLastName(fetchedAllPendingRequestsEntity.getRequester().getEmpLastName());

			RolesPojo requesterRole = new RolesPojo();
			requesterRole.setRoleId(fetchedAllPendingRequestsEntity.getRequester().getRole().getRoleId());
			requesterRole.setRole(fetchedAllPendingRequestsEntity.getRequester().getRole().getRole());

			employeePojoRequester.setRole(requesterRole);

			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedAllPendingRequestsEntity.getReimbId(), fetchedAllPendingRequestsEntity.getReimbAmt(),
					statusPojo, employeePojoRequester);
			allPendingRequestsPojo.add(returnReimbursementPojo);
		}

		session.close();

		return allPendingRequestsPojo;
	}

	@Override
	public List<ReimbursementPojo> viewAllResolvedRequests() throws ApplicationException {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		String hql = "from ReimbursementEntity where reimb_status_id!=1";

		List<ReimbursementEntity> allResolvedRequestsEntity = session.createQuery(hql, ReimbursementEntity.class)
				.getResultList();

		List<ReimbursementPojo> allResolvedRequestsPojo = new ArrayList<ReimbursementPojo>();

		for (ReimbursementEntity fetchedAllResolvedRequestsEntity : allResolvedRequestsEntity) {

			StatusPojo statusPojo = new StatusPojo();
			statusPojo.setStatusId(fetchedAllResolvedRequestsEntity.getStatus().getStatusId());
			statusPojo.setStatus(fetchedAllResolvedRequestsEntity.getStatus().getStatus());

			EmployeePojo employeePojoRequester = new EmployeePojo();
			employeePojoRequester.setEmpId(fetchedAllResolvedRequestsEntity.getRequester().getEmpId());
			employeePojoRequester.setEmpFirstName(fetchedAllResolvedRequestsEntity.getRequester().getEmpFirstName());
			employeePojoRequester.setEmpLastName(fetchedAllResolvedRequestsEntity.getRequester().getEmpLastName());

			RolesPojo requesterRole = new RolesPojo();
			requesterRole.setRoleId(fetchedAllResolvedRequestsEntity.getRequester().getRole().getRoleId());
			requesterRole.setRole(fetchedAllResolvedRequestsEntity.getRequester().getRole().getRole());

			employeePojoRequester.setRole(requesterRole);

			EmployeePojo employeePojoApprover = new EmployeePojo();
			employeePojoApprover.setEmpId(fetchedAllResolvedRequestsEntity.getApprover().getEmpId());
			employeePojoApprover.setEmpFirstName(fetchedAllResolvedRequestsEntity.getApprover().getEmpFirstName());
			employeePojoApprover.setEmpLastName(fetchedAllResolvedRequestsEntity.getApprover().getEmpLastName());

			RolesPojo approverRole = new RolesPojo();
			approverRole.setRoleId(fetchedAllResolvedRequestsEntity.getApprover().getRole().getRoleId());
			approverRole.setRole(fetchedAllResolvedRequestsEntity.getApprover().getRole().getRole());

			employeePojoApprover.setRole(approverRole);

			ReimbursementPojo returnReimbursementPojo = new ReimbursementPojo(
					fetchedAllResolvedRequestsEntity.getReimbId(), fetchedAllResolvedRequestsEntity.getReimbAmt(),
					statusPojo, employeePojoRequester, employeePojoApprover);
			allResolvedRequestsPojo.add(returnReimbursementPojo);
		}

		session.close();

		return allResolvedRequestsPojo;
	}

	@Override
	public ReimbursementPojo manUpdateRequest(ReimbursementPojo reimbursementPojo) throws ApplicationException {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query hql = session.createQuery(
				"update ReimbursementEntity set reimb_status_id=: decision, approver_id=: approver where reimb_id=: r");
		hql.setParameter("decision", reimbursementPojo.getStatus().getStatusId());
		hql.setParameter("r", reimbursementPojo.getReimbId());
		hql.setParameter("approver", reimbursementPojo.getApprover().getEmpId());
		hql.executeUpdate();

		session.getTransaction().commit();
		session.close();

		return reimbursementPojo;
	}

}
