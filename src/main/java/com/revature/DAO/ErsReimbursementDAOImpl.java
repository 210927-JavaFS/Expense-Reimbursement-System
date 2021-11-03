package com.revature.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.ErsReimbursement;
import com.revature.models.ErsReimbursementStatus;
import com.revature.models.ErsUser;
import com.revature.utils.HibernateUtil;

public class ErsReimbursementDAOImpl implements ErsReimbursementDAO{

	@Override
	public List<ErsReimbursement> getAllReimbursements() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM ErsReimbursement").list();
	}

	@Override
	public List<ErsReimbursement> getReimbursementByStatus(String status) {
		Session session = HibernateUtil.getSession();
		List<ErsReimbursementStatus> statusList = session.createQuery("FROM ErsReimbursementStatus WHERE status = '" + status + "'").list();
		List<ErsReimbursement> ersReimbursement = new ArrayList<>();
		for(ErsReimbursementStatus s : statusList ) {
			ersReimbursement.add(this.getReimbursementById(s.getReimbStatusId()));
		}
		return ersReimbursement;
		
	}

	@Override
	public ErsReimbursement getReimbursementById(int id) {
		Session session = HibernateUtil.getSession();
		List<ErsReimbursement> ersReimbursement = session.createQuery("FROM ErsReimbursement WHERE reimbid = '" +id+ "'").list();
		return ersReimbursement.get(0);
	}

	@Override
	public boolean addReimbursement(ErsReimbursement reimbursement) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(reimbursement);
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
	public boolean updateReimbursement(ErsReimbursement reimbursement) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(reimbursement);
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
	public boolean deleteReimbursement(ErsReimbursement reimbursement) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(reimbursement);
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
