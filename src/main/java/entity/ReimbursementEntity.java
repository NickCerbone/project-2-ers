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
@Table(name = "reimbursements")
public class ReimbursementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimb_id")
	private int reimbId;

	@Column(name = "reimb_amt")
	private double reimbAmt;

	@ManyToOne
	@JoinColumn(name = "reimb_status_id")
	private StatusEntity statusEntity;

	@ManyToOne
	@JoinColumn(name = "requester_id")
	private EmployeeEntity requester;

	@ManyToOne
	@JoinColumn(name = "approver_id")
	private EmployeeEntity approver;

	public ReimbursementEntity() {
	}

	public ReimbursementEntity(int reimbId, double reimbAmt, StatusEntity statusEntity, EmployeeEntity requester,
			EmployeeEntity approver) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.statusEntity = statusEntity;
		this.requester = requester;
		this.approver = approver;
	}

	public ReimbursementEntity(int reimbId, StatusEntity statusEntity, EmployeeEntity approver) {
		super();
		this.reimbId = reimbId;
		this.statusEntity = statusEntity;
		this.approver = approver;
	}

	public ReimbursementEntity(double reimbAmt, StatusEntity statusEntity, EmployeeEntity requester) {
		super();
		this.reimbAmt = reimbAmt;
		this.statusEntity = statusEntity;
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

	public StatusEntity getStatusEntity() {
		return statusEntity;
	}

	public void setStatusEntity(StatusEntity statusEntity) {
		this.statusEntity = statusEntity;
	}

	public EmployeeEntity getRequester() {
		return requester;
	}

	public void setRequester(EmployeeEntity requester) {
		this.requester = requester;
	}

	public EmployeeEntity getApprover() {
		return approver;
	}

	public void setApprover(EmployeeEntity approver) {
		this.approver = approver;
	}

	@Override
	public String toString() {
		return "ReimbursementEntity [reimbId=" + reimbId + ", reimbAmt=" + reimbAmt + ", statusEntity=" + statusEntity
				+ ", requester=" + requester + ", approver=" + approver + "]";
	}

}
