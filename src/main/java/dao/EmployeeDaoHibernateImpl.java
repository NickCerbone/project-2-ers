package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import entity.EmployeeEntity;
import exceptions.ApplicationException;
import pojo.EmployeePojo;
import pojo.RolesPojo;

public class EmployeeDaoHibernateImpl implements EmployeeDao {
	// hashes the users password
	@Override
	public String hashPassword(String password) {
		// takes your password and returns an encrypted version of it
		String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(10));

		return hashedPass;
	}

	// checks the users password
	@Override
	public boolean checkPass(String password, String hashedPass) {
		// takes your password and an encrypted password and compares it to see if its
		// the same values
		// as the password, if so it returns true
		if (BCrypt.checkpw(password, hashedPass)) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public EmployeePojo empViewInfo(int empId) throws ApplicationException {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		EmployeeEntity fetchedEmployeeEntity = session.get(EmployeeEntity.class, empId);

		RolesPojo rolePojo = new RolesPojo();
		rolePojo.setRoleId(fetchedEmployeeEntity.getRolesEntity().getRoleId());
		rolePojo.setRole(fetchedEmployeeEntity.getRolesEntity().getRole());

		EmployeePojo returnEmployeePojo = new EmployeePojo(fetchedEmployeeEntity.getEmpId(),
				fetchedEmployeeEntity.getEmpFirstName(), fetchedEmployeeEntity.getEmpLastName(),
				fetchedEmployeeEntity.getEmpUserName(), fetchedEmployeeEntity.getEmpHashedPassword(), rolePojo);

		session.close();

		return returnEmployeePojo;
	}

	@Override
	public List<EmployeePojo> manViewAll() throws ApplicationException {
		List<EmployeePojo> allEmployeesPojo = null;

		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from EmployeeEntity";
			List<EmployeeEntity> allEmployeesEnt = session.createQuery(hql, EmployeeEntity.class).getResultList();

			allEmployeesPojo = new ArrayList<EmployeePojo>();
			for (EmployeeEntity fetchedEmpEnt : allEmployeesEnt) {
				RolesPojo roles = new RolesPojo();
				roles.setRoleId(fetchedEmpEnt.getRolesEntity().getRoleId());
				roles.setRole(fetchedEmpEnt.getRolesEntity().getRole());

				EmployeePojo returnEmpPojo = new EmployeePojo(fetchedEmpEnt.getEmpId(), fetchedEmpEnt.getEmpFirstName(),
						fetchedEmpEnt.getEmpLastName(), fetchedEmpEnt.getEmpUserName(), roles);
				allEmployeesPojo.add(returnEmpPojo);

			}

			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return allEmployeesPojo;
	}

	@Override
	public EmployeePojo getEmployee(EmployeePojo employeePojo) throws ApplicationException {
		EmployeePojo returnEmployeePojo = null;

		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();

			String hql = "from EmployeeEntity where user_name='" + employeePojo.getEmpUserName() +"'";

			EmployeeEntity fetchedEmpEnt = session.createQuery(hql, EmployeeEntity.class).getSingleResult();

			RolesPojo rolePojo = new RolesPojo();
			rolePojo.setRoleId(fetchedEmpEnt.getRolesEntity().getRoleId());
			rolePojo.setRole(fetchedEmpEnt.getRolesEntity().getRole());

			returnEmployeePojo = new EmployeePojo(fetchedEmpEnt.getEmpId(), fetchedEmpEnt.getEmpFirstName(),
					fetchedEmpEnt.getEmpLastName(), employeePojo.getEmpUserName(), fetchedEmpEnt.getEmpHashedPassword(),
					rolePojo);
			System.out.println(returnEmployeePojo);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnEmployeePojo;
	}

	@Override
	public EmployeePojo empUpdateInfo(EmployeePojo employeePojo, int empId) throws ApplicationException {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			Query hql = session.createQuery(
					"update EmployeeEntity set first_name=: firstname, last_name=: lastname, user_name=: username, hashed_password=: hashedpassword where emp_id=: empid");
			hql.setParameter("firstname", employeePojo.getEmpFirstName());
			hql.setParameter("lastname", employeePojo.getEmpLastName());
			hql.setParameter("username", employeePojo.getEmpUserName());
			hql.setParameter("hashedpassword", employeePojo.getEmpHashedPassword());
			hql.setParameter("empid", empId);
			hql.executeUpdate();

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeePojo;
	}

}
