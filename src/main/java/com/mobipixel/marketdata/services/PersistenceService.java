package com.mobipixel.marketdata.services;

import org.hibernate.SessionFactory;

/**
 * Created by Kevin on 6/14/16.
 */
public interface PersistenceService {

    public SessionFactory getSessionFactory();
}
