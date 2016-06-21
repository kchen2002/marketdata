package com.mobipixel.marketdata.services.impl;

import com.mobipixel.marketdata.entities.impl.UserImpl;
import com.mobipixel.marketdata.services.PersistenceService;
import com.mobipixel.marketdata.services.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kevin on 6/14/16.
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    PersistenceService persistenceService;

    public void createUser(UserImpl user) {
        Session session = this.persistenceService.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }
}
