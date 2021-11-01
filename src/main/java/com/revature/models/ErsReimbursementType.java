package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ErsReimbursementType {

	@Id
	private int reimbTypeId;
	private String type;
	
	public ErsReimbursementType(int reimbTypeId, String type) {
		super();
		this.reimbTypeId = reimbTypeId;
		this.type = type;
	}

	public ErsReimbursementType(String type) {
		super();
		this.type = type;
	}

	public ErsReimbursementType() {
		super();
	}

	public int getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(int reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbTypeId;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ErsReimbursementType other = (ErsReimbursementType) obj;
		if (reimbTypeId != other.reimbTypeId)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErsReimbursementType [reimbTypeId=" + reimbTypeId + ", type=" + type + "]";
	}
	
	
}
