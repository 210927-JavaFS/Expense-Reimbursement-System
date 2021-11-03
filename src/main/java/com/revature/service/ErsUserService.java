package com.revature.service;

import com.revature.DAO.ErsUserDAO;
import com.revature.DAO.ErsUserDAOImpl;
import com.revature.models.ErsUser;
import com.revature.models.UserDTO;

public class ErsUserService {
	
	private static ErsUserDAO ersUserDAO = new ErsUserDAOImpl();
	
	public ErsUser getUser(String username) {
		return ersUserDAO.getUser(username);
	}
	
	public boolean addUser(ErsUser user) {
		return ersUserDAO.addUser(user);
	}

	public boolean deleteUser(ErsUser user) {
		return ersUserDAO.deleteUser(user);
	}
	
	public static boolean login(UserDTO userDto) {
		ErsUser user = ersUserDAO.getUser(userDto.username);
		
		if(user!=null && (userDto.password==user.getErsPassword())) {
			return true;
		}
		
		return false;
	}
	
}
