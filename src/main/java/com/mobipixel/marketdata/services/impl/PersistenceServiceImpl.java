package com.mobipixel.marketdata.services.impl;

import com.mobipixel.marketdata.services.PersistenceService;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by Kevin on 6/14/16.
 */
@Service("PersistenceServiceImpl")
public class PersistenceServiceImpl implements PersistenceService {
    SessionFactory sessionFactory = null;

    public PersistenceServiceImpl() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            this.sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }
}
