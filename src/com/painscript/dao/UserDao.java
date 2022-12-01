package com.painscript.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.painscript.model.User;
import com.painscript.util.HibernateUtil;


public class UserDao {

	public void saveUser(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
		
			session.save(user);
		
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public boolean validateEmail(String emailId) {

		Transaction transaction = null;
		User user = null;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		
			transaction = session.beginTransaction();
		
			user = (User) session.createQuery("FROM User U WHERE U.emailId = :emailId").setParameter("emailId",emailId)
					.uniqueResult();
			
			if(emailId != null && user.getEmailId().equals(emailId) ){
				return true;
			}
		
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
	}
		return true;
	}

}
