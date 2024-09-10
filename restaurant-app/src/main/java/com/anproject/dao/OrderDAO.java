package com.anproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.anproject.config.HibernateConfig;
import com.anproject.entity.Order;

public class OrderDAO {

	public void saveOrder(Order order) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(order);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updateOrder(Order order) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.merge(order);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteOrder(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Order order = session.find(Order.class, id);
			if (order != null) {
				session.remove(order);
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

	public Order getOrderById(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			return session.get(Order.class, id);
		} finally {
			session.close();
		}
	}

	public List<Order> getAllOrders() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			Query<Order> query = session.createQuery("FROM Order", Order.class);
			return query.getResultList();
		} finally {
			session.close();
		}
	}
	
}
