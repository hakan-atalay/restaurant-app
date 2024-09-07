package com.anproject.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

	private static volatile SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			synchronized (HibernateConfig.class) {
				if (sessionFactory == null) {
					sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
				}
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}
}
