package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class StatusEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private int statusId;

	@Column(name = "status")
	private String status;

	public StatusEntity() {
	}

	public StatusEntity(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "StatusEntity [statusId=" + statusId + ", status=" + status + "]";
	}

}
