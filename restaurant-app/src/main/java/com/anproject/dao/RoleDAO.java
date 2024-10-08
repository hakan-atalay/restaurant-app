package com.anproject.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.anproject.config.HibernateConfig;
import com.anproject.entity.Role;

import java.util.List;

public class RoleDAO extends HibernateConfig{

	public void saveRole(Role role) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.persist(role);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();		
		}
	}

	public void updateRole(Role role) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try{
			transaction = session.beginTransaction();
			session.merge(role);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	public void deleteRole(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Role role = session.find(Role.class, id);
			if (role != null) {
				session.remove(role);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	public Role getRoleById(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			return session.get(Role.class, id);
		}finally {
			session.close();
		}
	}

	public List<Role> getAllRoles() {
		Session session =  HibernateConfig.getSessionFactory().openSession();
		try {
			Query<Role> query = session.createQuery("FROM Role", Role.class);
			return query.getResultList();
		}finally {
			session.close();
		}
	}
}