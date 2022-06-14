package pojo;

public class EmployeePojo {

	private int empId;
	private String empFirstName;
	private String empLastName;
	private String empUserName;
	private String empHashedPassword;
	private RolesPojo role;

	public EmployeePojo() {
	}

	public EmployeePojo(int empId, String empFirstName, String empLastName, String empUserName,
			String empHashedPassword, RolesPojo role) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empUserName = empUserName;
		this.empHashedPassword = empHashedPassword;
		this.role = role;
	}

	public EmployeePojo(int empId, String empFirstName, String empLastName, String empUserName, RolesPojo role) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empUserName = empUserName;
		this.role = role;
	}

	public EmployeePojo(int empId, String empFirstName, String empLastName, String empUserName,
			String empHashedPassword) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empUserName = empUserName;
		this.empHashedPassword = empHashedPassword;
	}

	public RolesPojo getRole() {
		return role;
	}

	public void setRole(RolesPojo role) {
		this.role = role;
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

	@Override
	public String toString() {
		return "EmployeePojo [empId=" + empId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", empUserName=" + empUserName + ", empHashedPassword=" + empHashedPassword + ", role="
				+ role + "]";
	}

}