package com.anproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.anproject.config.HibernateConfig;
import com.anproject.entity.Category;

public class CategoryDAO {

	public void saveCategory(Category category) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (category.getParentCategory() != null) {
				Category parentCategory = session.get(Category.class, category.getParentCategory().getId());
				category.setParentCategory(parentCategory);
			}
			session.persist(category);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void updateCategory(Category category) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if (category.getParentCategory() != null) {
				Category parentCategory = session.get(Category.class, category.getParentCategory().getId());
				category.setParentCategory(parentCategory);
			}
			session.merge(category);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteCategory(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Category category = session.find(Category.class, id);
			if (category != null) {
				session.remove(category);
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

	public Category getCategoryById(int id) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			return session.get(Category.class, id);
		} finally {
			session.close();
		}
	}

	public List<Category> getAllCategories() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		try {
			Query<Category> query = session.createQuery("FROM Category", Category.class);
			return query.getResultList();
		} finally {
			session.close();
		}
	}
}
