package pojo;

public class ReimbursementPojo {

	private int reimbId;
	private double reimbAmt;
	private StatusPojo statusPojo;
	private EmployeePojo requester;
	private EmployeePojo approver;

	public ReimbursementPojo() {
	}

	public ReimbursementPojo(int reimbId, double reimbAmt, StatusPojo statusPojo, EmployeePojo requester,
			EmployeePojo approver) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.statusPojo = statusPojo;
		this.requester = requester;
		this.approver = approver;
	}

	public ReimbursementPojo(int reimbId, double reimbAmt, StatusPojo statusPojo, EmployeePojo requester) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.statusPojo = statusPojo;
		this.requester = requester;
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

	public StatusPojo getStatusPojo() {
		return statusPojo;
	}

	public void setStatusPojo(StatusPojo statusPojo) {
		this.statusPojo = statusPojo;
	}

	public EmployeePojo getRequester() {
		return requester;
	}

	public void setRequester(EmployeePojo requester) {
		this.requester = requester;
	}

	public EmployeePojo getApprover() {
		return approver;
	}

	public void setApprover(EmployeePojo approver) {
		this.approver = approver;
	}

	@Override
	public String toString() {
		return "ReimbursementPojo [reimbId=" + reimbId + ", reimbAmt=" + reimbAmt + ", statusPojo=" + statusPojo
				+ ", requester=" + requester + ", approver=" + approver + "]";
	}

}