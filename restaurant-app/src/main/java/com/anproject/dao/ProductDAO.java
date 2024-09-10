package com.anproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.anproject.config.HibernateConfig;
import com.anproject.entity.Product;

public class ProductDAO {

	public void saveProduct(Product product) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.merge(product);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updateProduct(Product product) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.merge(product);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteProduct(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Product product = session.find(Product.class, id);
			if (product != null) {
				session.remove(product);
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

	public Product getProductById(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			return session.get(Product.class, id);
		} finally {
			session.close();
		}
	}

	public List<Product> getAllProducts() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			Query<Product> query = session.createQuery("FROM Product", Product.class);
			return query.getResultList();
		} finally {
			session.close();
		}
	}
	
}
