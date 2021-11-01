package com.revature.service;

import java.util.List;

import com.revature.DAO.ErsReimbursementDAO;
import com.revature.DAO.ErsReimbursementDAOImpl;
import com.revature.models.ErsReimbursement;

public class ErsReimbursementService {

	private ErsReimbursementDAO ersReimbursementDAO = new ErsReimbursementDAOImpl();
	
	public List<ErsReimbursement> getAllReimbursements(){
		return ersReimbursementDAO.getAllReimbursements();
	}
	
	public List<ErsReimbursement> getReimbursementByStatus(int reimbStatusId){
		return ersReimbursementDAO.getReimbursementByStatus(reimbStatusId);
	}
	
	public ErsReimbursement getReimbursementById(int id){
		return ersReimbursementDAO.getReimbursementById(id);
	}
	
	public boolean addReimbursement(ErsReimbursement reimbursement) {
		return ersReimbursementDAO.addReimbursement(reimbursement);
	}
	
	public boolean updateReimbursement(ErsReimbursement reimbursement) {
		return ersReimbursementDAO.updateReimbursement(reimbursement);
	}
	
	public boolean deleteReimbursement(ErsReimbursement reimbursement) {
		return ersReimbursementDAO.deleteReimbursement(reimbursement);
	}
}
