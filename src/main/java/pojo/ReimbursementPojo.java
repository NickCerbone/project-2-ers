package pojo;

public class ReimbursementPojo {

	private int reimbId;
	private double reimbAmt;
	private StatusPojo status;
	private EmployeePojo requester;
	private EmployeePojo approver;

	public ReimbursementPojo() {
	}

	public ReimbursementPojo(int reimbId, double reimbAmt, StatusPojo status, EmployeePojo requester,
			EmployeePojo approver) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.status = status;
		this.requester = requester;
		this.approver = approver;
	}

	public ReimbursementPojo(int reimbId, double reimbAmt, StatusPojo status, EmployeePojo requester) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.status = status;
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

	public StatusPojo getStatus() {
		return status;
	}

	public void setStatus(StatusPojo status) {
		this.status = status;
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
		return "ReimbursementPojo [reimbId=" + reimbId + ", reimbAmt=" + reimbAmt + ", status=" + status
				+ ", requester=" + requester + ", approver=" + approver + "]";
	}

}