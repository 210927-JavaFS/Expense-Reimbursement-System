package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ErsReimbursementStatus {

	@Id
	private int reimbStatusId;
	private String status;
	
	public ErsReimbursementStatus(int reimbStatusId, String status) {
		super();
		this.reimbStatusId = reimbStatusId;
		this.status = status;
	}

	public ErsReimbursementStatus(String status) {
		super();
		this.status = status;
	}

	public ErsReimbursementStatus() {
		super();
	}

	public int getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(int reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbStatusId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ErsReimbursementStatus other = (ErsReimbursementStatus) obj;
		if (reimbStatusId != other.reimbStatusId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErsReimbursementStatus [reimbStatusId=" + reimbStatusId + ", status=" + status + "]";
	}
	
	
}
