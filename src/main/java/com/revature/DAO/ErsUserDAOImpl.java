package com.revature.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.ErsUser;
import com.revature.utils.HibernateUtil;

public class ErsUserDAOImpl implements ErsUserDAO{

	@Override
	public ErsUser getUser(String username) {
		Session session = HibernateUtil.getSession();
		List<ErsUser> ersUser = session.createQuery("FROM ErsUser WHERE ersusername = '" +username+ "'").list();
		return ersUser.get(0);

	}

	@Override
	public boolean addUser(ErsUser user) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(user);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(ErsUser user) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		}
		catch(HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}
