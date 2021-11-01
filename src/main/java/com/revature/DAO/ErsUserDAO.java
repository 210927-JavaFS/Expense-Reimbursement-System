package com.revature.DAO;

import com.revature.models.ErsUser;

public interface ErsUserDAO {

	ErsUser getUser(String username);
	boolean addUser(ErsUser user);
	boolean deleteUser(ErsUser user);
}
