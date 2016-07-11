package com.tnt.integrationframework.activityservice.shipmentpersistance;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tnt.integration.service.ServiceException;

public class SessionFactoryProducer {

	final static Logger LOGGER = LoggerFactory.getLogger(SessionFactoryProducer.class);

	private static final int ANN_CONFIG_EXP = 992;
	private static final String ANN_CONFIG_EXP_MSG = "Exception in Hibernate Annotation Configuration";

	private static final int SESS_FACTORY_EXP = 991;
	private static final String SESS_FACTORY_EXP_MSG = "Exception in building Session Factory";

	private static final int SESS_CREATION_EXP = 990;
	private static final String SESS_CREATION_EXP_MSG = "Exception in creating Hibernate Session";

	private static AnnotationConfiguration configuration;
	private static SessionFactory sessionFactory;

	@Produces
	public Session getSession() throws ServiceException {
		try {
			Session session = getSessionFactory().openSession();
			LOGGER.info("Hibernate Session Started {}", session);
			return session;

		} catch (HibernateException exp) {
			LOGGER.error(SESS_CREATION_EXP_MSG, exp);
			throw new ServiceException(SESS_CREATION_EXP, SESS_CREATION_EXP_MSG);
		}
	}

	public void closeSession(@Disposes Session session) {
		try {
			if (session.isOpen()) {
				session.close();
				LOGGER.info("Hibernate Closed session: {}", session);
			}
		} catch (HibernateException exp) {
			exp.printStackTrace();
			LOGGER.error("SessionFactoryProducer Error while closng session: ", exp);
		}
	}

	private SessionFactory getSessionFactory() throws ServiceException {
		
		try {
			if (sessionFactory == null) {
				LOGGER.info("Hibernate SessionFactory: creating SessionFactory");
				sessionFactory = getAnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
			}
		} catch (HibernateException exp) {
			LOGGER.error(SESS_FACTORY_EXP_MSG, exp);
			throw new ServiceException(SESS_FACTORY_EXP, SESS_FACTORY_EXP_MSG);
		}
		return sessionFactory;
	}

	private AnnotationConfiguration getAnnotationConfiguration() throws ServiceException {
		
		try {
			if (configuration == null) {
				LOGGER.info("Hibernate AnnotationConfiguration: creating AnnotationConfiguration");
				configuration = new AnnotationConfiguration();
			}
		} catch (MappingException exp) {
			LOGGER.error(ANN_CONFIG_EXP_MSG, exp);
			throw new ServiceException(ANN_CONFIG_EXP, ANN_CONFIG_EXP_MSG);
		}
		return configuration;
	}

}
