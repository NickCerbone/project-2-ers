package pojo;

public class EmployeePojo {

	int empId;
	String empFirstName;
	String empLastName;
	String empUserName;
	String empHashedPassword;
	int empRoleId;
	String empRole;
	
	public EmployeePojo() {
		}

	public EmployeePojo(int empId, String empFirstName, String empLastName, String empUserName,
			String empHashedPassword, int empRoleId, String empRole) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empUserName = empUserName;
		this.empHashedPassword = empHashedPassword;
		this.empRoleId = empRoleId;
		this.empRole = empRole;
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

	public int getEmpRoleId() {
		return empRoleId;
	}

	public void setEmpRoleId(int empRoleId) {
		this.empRoleId = empRoleId;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empFirstName == null) ? 0 : empFirstName.hashCode());
		result = prime * result + ((empHashedPassword == null) ? 0 : empHashedPassword.hashCode());
		result = prime * result + empId;
		result = prime * result + ((empLastName == null) ? 0 : empLastName.hashCode());
		result = prime * result + ((empRole == null) ? 0 : empRole.hashCode());
		result = prime * result + empRoleId;
		result = prime * result + ((empUserName == null) ? 0 : empUserName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeePojo other = (EmployeePojo) obj;
		if (empFirstName == null) {
			if (other.empFirstName != null)
				return false;
		} else if (!empFirstName.equals(other.empFirstName))
			return false;
		if (empHashedPassword == null) {
			if (other.empHashedPassword != null)
				return false;
		} else if (!empHashedPassword.equals(other.empHashedPassword))
			return false;
		if (empId != other.empId)
			return false;
		if (empLastName == null) {
			if (other.empLastName != null)
				return false;
		} else if (!empLastName.equals(other.empLastName))
			return false;
		if (empRole == null) {
			if (other.empRole != null)
				return false;
		} else if (!empRole.equals(other.empRole))
			return false;
		if (empRoleId != other.empRoleId)
			return false;
		if (empUserName == null) {
			if (other.empUserName != null)
				return false;
		} else if (!empUserName.equals(other.empUserName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmployeePojo [empId=" + empId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", empUserName=" + empUserName + ", empHashedPassword=" + empHashedPassword + ", empRoleId="
				+ empRoleId + ", empRole=" + empRole + "]";
	}
	
}