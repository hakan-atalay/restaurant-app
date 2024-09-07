package com.anproject.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.anproject.config.HibernateConfig;
import com.anproject.entity.Role;

import java.util.List;

public class RoleDAO extends HibernateConfig{

	public void saveRole(Role role) {
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.persist(role);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public void updateRole(Role role) {
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.merge(role);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}
	}

	public void deleteRole(int id) {
		Transaction transaction = null;
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {
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
		}
	}

	public Role getRoleById(int id) {
		try (Session session = HibernateConfig.getSessionFactory().openSession()) {
			return session.get(Role.class, id);
		}
	}

	public List<Role> getAllRoles() {
		try (Session session =  HibernateConfig.getSessionFactory().openSession()) {
			Query<Role> query = session.createQuery("FROM Role", Role.class);
			return query.getResultList();
		}
	}
}