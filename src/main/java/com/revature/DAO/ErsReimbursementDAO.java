package com.revature.DAO;

import java.util.List;

import com.revature.models.ErsReimbursement;

public interface ErsReimbursementDAO {

	List<ErsReimbursement> getAllReimbursements();
	List<ErsReimbursement> getReimbursementByStatus(String status);
	List<ErsReimbursement> getReimbursementById(int id);
	List<ErsReimbursement> getMyReimbursement(String username);
	boolean addReimbursement(ErsReimbursement reimbursement);
	boolean updateReimbursement(ErsReimbursement reimbursement);
	boolean deleteReimbursement(ErsReimbursement reimbursement);
}
