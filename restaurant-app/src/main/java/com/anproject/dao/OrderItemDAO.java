package com.anproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.anproject.config.HibernateConfig;
import com.anproject.entity.OrderItem;

public class OrderItemDAO {

	public void saveOrderItem(OrderItem orderItem) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(orderItem);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updateOrderItem(OrderItem orderItem) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.merge(orderItem);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteOrderItem(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			OrderItem orderItem = session.find(OrderItem.class, id);
			if (orderItem != null) {
				session.remove(orderItem);
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

	public OrderItem getOrderItemById(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			return session.get(OrderItem.class, id);
		} finally {
			session.close();
		}
	}

	public List<OrderItem> getAllOrderItems() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			Query<OrderItem> query = session.createQuery("FROM OrderItem", OrderItem.class);
			return query.getResultList();
		} finally {
			session.close();
		}
	}
	
}