package org.apathinternational.faithpathrestful.config;

import org.apathinternational.faithpathrestful.config.listener.AuditListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;


@Component
public class HibernateListenerResgister {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    @Autowired
    private AuditListener auditListener;

    @PostConstruct
    public void registerListeners() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener((AuditListener) auditListener);
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener((AuditListener) auditListener);
    }
}
