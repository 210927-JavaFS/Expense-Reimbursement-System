package com.revature.DAO;

import java.util.List;

import com.revature.models.ErsReimbursement;

public interface ErsReimbursementDAO {

	List<ErsReimbursement> getAllReimbursements();
	List<ErsReimbursement> getReimbursementByStatus(String status);
	ErsReimbursement getReimbursementById(int id);
	boolean addReimbursement(ErsReimbursement reimbursement);
	boolean updateReimbursement(ErsReimbursement reimbursement);
	boolean deleteReimbursement(ErsReimbursement reimbursement);
}
