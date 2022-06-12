package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id")
	private int empId;

	@Column(name = "first_name")
	private String empFirstName;

	@Column(name = "last_name")
	private String empLastName;

	@Column(name = "user_name")
	private String empUserName;

	@Column(name = "hashed_password")
	private String empHashedPassword;

	@ManyToOne
	@JoinColumn(name = "emp_role_id")
	private RolesEntity rolesEntity;

	public EmployeeEntity() {
	}

	public EmployeeEntity(int empId, String empFirstName, String empLastName, String empUserName,
			String empHashedPassword, RolesEntity rolesEntity) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empUserName = empUserName;
		this.empHashedPassword = empHashedPassword;
		this.rolesEntity = rolesEntity;
	}

	public EmployeeEntity(int empId, String empFirstName, String empLastName, String empUserName,
			String empHashedPassword) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empUserName = empUserName;
		this.empHashedPassword = empHashedPassword;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getEmpUserName() {
		return empUserName;
	}

	public void setEmpUserName(String empUserName) {
		this.empUserName = empUserName;
	}

	public String getEmpHashedPassword() {
		return empHashedPassword;
	}

	public void setEmpHashedPassword(String empHashedPassword) {
		this.empHashedPassword = empHashedPassword;
	}

	public RolesEntity getRolesEntity() {
		return rolesEntity;
	}

	public void setRolesEntity(RolesEntity rolesEntity) {
		this.rolesEntity = rolesEntity;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [empId=" + empId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", empUserName=" + empUserName + ", empHashedPassword=" + empHashedPassword + ", rolesEntity="
				+ rolesEntity + "]";
	}

}
