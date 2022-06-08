package pojo;

public class ReimbursementPojo {
	
	private int requesterId;
	private String requesterFirstName;
	private String requesterLastName;
	private int reimbId;
	private double reimbAmt;
	private int reimbStatusId;
	private String reimbStatus;
	private int approverId;
	private String approverFirstName;
	private String approverLastName;
	

	public ReimbursementPojo() {
	}

	public ReimbursementPojo(int requesterId, String requesterFirstName, String requesterLastName, int reimbId,
			double reimbAmt, int reimbStatusId, String reimbStatus, int approverId, String approverFirstName,
			String approverLastName) {
		super();
		this.requesterId = requesterId;
		this.requesterFirstName = requesterFirstName;
		this.requesterLastName = requesterLastName;
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.reimbStatusId = reimbStatusId;
		this.reimbStatus = reimbStatus;
		this.approverId = approverId;
		this.approverFirstName = approverFirstName;
		this.approverLastName = approverLastName;
	}
	
	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public int getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(int requesterId) {
		this.requesterId = requesterId;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmt() {
		return reimbAmt;
	}

	public void setReimbAmt(double reimbAmt) {
		this.reimbAmt = reimbAmt;
	}

	public String getReimbStatus() {
		return reimbStatus;
	}

	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}

	public String getRequesterFirstName() {
		return requesterFirstName;
	}

	public void setRequesterFirstName(String requesterFirstName) {
		this.requesterFirstName = requesterFirstName;
	}

	public String getRequesterLastName() {
		return requesterLastName;
	}

	public void setRequesterLastName(String requesterLastName) {
		this.requesterLastName = requesterLastName;
	}

	public String getApproverFirstName() {
		return approverFirstName;
	}

	public void setApproverFirstName(String approverFirstName) {
		this.approverFirstName = approverFirstName;
	}

	public String getApproverLastName() {
		return approverLastName;
	}

	public void setApproverLastName(String approverLastName) {
		this.approverLastName = approverLastName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approverFirstName == null) ? 0 : approverFirstName.hashCode());
		result = prime * result + approverId;
		result = prime * result + ((approverLastName == null) ? 0 : approverLastName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reimbAmt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + reimbId;
		result = prime * result + ((reimbStatus == null) ? 0 : reimbStatus.hashCode());
		result = prime * result + reimbStatusId;
		result = prime * result + ((requesterFirstName == null) ? 0 : requesterFirstName.hashCode());
		result = prime * result + requesterId;
		result = prime * result + ((requesterLastName == null) ? 0 : requesterLastName.hashCode());
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
		ReimbursementPojo other = (ReimbursementPojo) obj;
		if (approverFirstName == null) {
			if (other.approverFirstName != null)
				return false;
		} else if (!approverFirstName.equals(other.approverFirstName))
			return false;
		if (approverId != other.approverId)
			return false;
		if (approverLastName == null) {
			if (other.approverLastName != null)
				return false;
		} else if (!approverLastName.equals(other.approverLastName))
			return false;
		if (Double.doubleToLongBits(reimbAmt) != Double.doubleToLongBits(other.reimbAmt))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbStatus == null) {
			if (other.reimbStatus != null)
				return false;
		} else if (!reimbStatus.equals(other.reimbStatus))
			return false;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		if (requesterFirstName == null) {
			if (other.requesterFirstName != null)
				return false;
		} else if (!requesterFirstName.equals(other.requesterFirstName))
			return false;
		if (requesterId != other.requesterId)
			return false;
		if (requesterLastName == null) {
			if (other.requesterLastName != null)
				return false;
		} else if (!requesterLastName.equals(other.requesterLastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementPojo [requesterFirstName=" + requesterFirstName + ", requesterLastName="
				+ requesterLastName + ", reimbId=" + reimbId + ", reimbAmt=" + reimbAmt + ", reimbStatus=" + reimbStatus
				+ ", reimbStatusId=" + reimbStatusId + ", approverFirstName=" + approverFirstName
				+ ", approverLastName=" + approverLastName + ", requesterId=" + requesterId + ", approverId="
				+ approverId + "]";
	}

}