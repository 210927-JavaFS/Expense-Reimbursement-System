package com.revature;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.revature.DAO.ErsReimbursementDAO;
import com.revature.DAO.ErsReimbursementDAOImpl;
import com.revature.DAO.ErsUserDAO;
import com.revature.DAO.ErsUserDAOImpl;
import com.revature.models.ErsReimbursement;
import com.revature.models.ErsReimbursementStatus;
import com.revature.models.ErsReimbursementType;
import com.revature.models.ErsUser;
import com.revature.models.ErsUserRole;
import com.revature.models.UserDTO;
import com.revature.service.ErsUserService;

public class DaoTest {
	public static ErsReimbursementDAO ersReimbursementDao= new ErsReimbursementDAOImpl();
	public static ErsUserDAO ersUserDao = new ErsUserDAOImpl();
	public static ErsUserService ersUserService = new ErsUserService();
	public static void main(String[] args) {
		
		//ersUserDao.addUser(new ErsUser("username", "pass", "a","a","a@a",new ErsUserRole(1,"Employee")));
		//System.out.println(ersUserDao.getUser("employee").toString());
		//System.out.println(ersReimbursementDao.getAllReimbursements().toString());
		//ersReimbursementDao.addReimbursement(new ErsReimbursement(
				//1000.0, Timestamp.valueOf(LocalDateTime.now()), null, "description", ersUserDao.getUser("employee"), 
				//null, {1,"Pending"}, new ErsReimbursementType(1,"LODGING")));
		
	
		//ersReimbursementDao.updateReimbursement(new ErsReimbursement(
				//2,3000.0, Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), "description", ersUserDao.getUser("username"), 
				//ersUserDao.getUser("username"), new ErsReimbursementStatus(1,"Pending"), new ErsReimbursementType(1,"LODGING")));
		//System.out.println(ersReimbursementDao.getReimbursementById(1));
		//System.out.println(ersReimbursementDao.getReimbursementByStatus("Pending").toString());
		//System.out.println(ersUserService.login(new UserDTO("employee","passing")));
	}
	
	
	
}
