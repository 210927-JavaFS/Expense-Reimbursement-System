package com.revature.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ErsUserRole {

	@Id
	private int ersUserRoleId;
	private String Role;
	
	public ErsUserRole(int ersUserRoleId, String role) {
		super();
		this.ersUserRoleId = ersUserRoleId;
		Role = role;
	}

	
	
	public ErsUserRole(String role) {
		super();
		Role = role;
	}



	public ErsUserRole() {
		super();
	}



	public int getErsUserRoleId() {
		return ersUserRoleId;
	}



	public void setErsUserRoleId(int ersUserRoleId) {
		this.ersUserRoleId = ersUserRoleId;
	}



	public String getRole() {
		return Role;
	}



	public void setRole(String role) {
		Role = role;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Role == null) ? 0 : Role.hashCode());
		result = prime * result + ersUserRoleId;
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
		ErsUserRole other = (ErsUserRole) obj;
		if (Role == null) {
			if (other.Role != null)
				return false;
		} else if (!Role.equals(other.Role))
			return false;
		if (ersUserRoleId != other.ersUserRoleId)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "ErsUserRole [ersUserRoleId=" + ersUserRoleId + ", Role=" + Role + "]";
	}
	
	
	
}
