package com.anproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.anproject.config.HibernateConfig;
import com.anproject.entity.AppUser;

public class AppUserDAO {

	public void saveAppUser(AppUser appUser) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(appUser);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updateAppUser(AppUser appUser) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.merge(appUser);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteAppUser(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			AppUser Appuser = session.find(AppUser.class, id);
			if (Appuser != null) {
				session.remove(Appuser);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public AppUser getAppUserById(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			return session.get(AppUser.class, id);
		} finally {
			session.close();
		}
	}

	public List<AppUser> getAllAppUsers() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			Query<AppUser> query = session.createQuery("FROM AppUser", AppUser.class);
			return query.getResultList();
		} finally {
			session.close();
		}
	}
}
