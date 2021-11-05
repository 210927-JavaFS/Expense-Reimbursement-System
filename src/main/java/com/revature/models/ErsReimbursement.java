package com.revature.models;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ErsReimbursement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reimbId;
	private double reimbAmount;
	private Timestamp reimbSubmitted;
	private Timestamp reimbResolved;
	private String reimbDescription;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	//@JoinColumn(name="ersUserId")
	private ErsUser reimbAuthor;
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
	//@JoinColumn(name="ersUserId")
    private ErsUser reimbResolver;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    //@JoinColumn(name="reimbStatusId")
    private ErsReimbursementStatus reimbStatusId;
    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    //@JoinColumn(name="reimbTypeId")
    private ErsReimbursementType reimbTypeId;
	
    public ErsReimbursement(int reimbId, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbDescription, ErsUser reimbAuthor, ErsUser reimbResolver, ErsReimbursementStatus reimbStatusId,
			ErsReimbursementType reimbTypeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}

	public ErsReimbursement(double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved, String reimbDescription,
			ErsUser reimbAuthor, ErsUser reimbResolver, ErsReimbursementStatus reimbStatusId,
			ErsReimbursementType reimbTypeId) {
		super();
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}

	public ErsReimbursement() {
		super();
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(int reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public Timestamp getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public ErsUser getReimbAuthor() {
		return reimbAuthor;
	}

	public void setReimbAuthor(ErsUser reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public ErsUser getReimbResolver() {
		return reimbResolver;
	}

	public void setReimbResolver(ErsUser reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	public ErsReimbursementStatus getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(ErsReimbursementStatus reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public ErsReimbursementType getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(ErsReimbursementType reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + reimbAmount);
		result = prime * result + ((reimbAuthor == null) ? 0 : reimbAuthor.hashCode());
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + ((reimbResolver == null) ? 0 : reimbResolver.hashCode());
		result = prime * result + ((reimbStatusId == null) ? 0 : reimbStatusId.hashCode());
		result = prime * result + ((reimbSubmitted == null) ? 0 : reimbSubmitted.hashCode());
		result = prime * result + ((reimbTypeId == null) ? 0 : reimbTypeId.hashCode());
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
		ErsReimbursement other = (ErsReimbursement) obj;
		if (reimbAmount != other.reimbAmount)
			return false;
		if (reimbAuthor == null) {
			if (other.reimbAuthor != null)
				return false;
		} else if (!reimbAuthor.equals(other.reimbAuthor))
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbResolved == null) {
			if (other.reimbResolved != null)
				return false;
		} else if (!reimbResolved.equals(other.reimbResolved))
			return false;
		if (reimbResolver == null) {
			if (other.reimbResolver != null)
				return false;
		} else if (!reimbResolver.equals(other.reimbResolver))
			return false;
		if (reimbStatusId == null) {
			if (other.reimbStatusId != null)
				return false;
		} else if (!reimbStatusId.equals(other.reimbStatusId))
			return false;
		if (reimbSubmitted == null) {
			if (other.reimbSubmitted != null)
				return false;
		} else if (!reimbSubmitted.equals(other.reimbSubmitted))
			return false;
		if (reimbTypeId == null) {
			if (other.reimbTypeId != null)
				return false;
		} else if (!reimbTypeId.equals(other.reimbTypeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ErsReimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver + ", reimbStatusId="
				+ reimbStatusId + ", reimbTypeId=" + reimbTypeId + "]";
	}
	
    
    
    
}
