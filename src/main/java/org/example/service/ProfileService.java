package org.example.service;

import org.example.entities.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final SessionFactory sessionFactory;
    private final TransactionalHelperService transactionalHelperService;

    public ProfileService(SessionFactory sessionFactory, TransactionalHelperService transactionalHelperService) {
        this.sessionFactory = sessionFactory;
        this.transactionalHelperService = transactionalHelperService;
    }

    public Profile saveProfile(Profile profile) {
        return transactionalHelperService.applyTransactional(session -> {
            session.persist(profile);
            return profile;
        });
    }

    public void deleteProfile(Long id) {
        transactionalHelperService.applyTransactional(session -> {
            Profile profile = session.find(Profile.class, id);
            session.remove(profile);
        });
    }

    public Profile updateProfile(Profile profile) {
        return transactionalHelperService.applyTransactional(session -> {
            return session.merge(profile);
        });
    }

    public Profile getProfile(Long id) {
        Session session = sessionFactory.openSession();
        Profile profile = session.find(Profile.class, id);
        session.close();
        return profile;
    }

    public List<Profile> getAllProfiles() {
        Session session = sessionFactory.openSession();
        List<Profile> profiles = session.createQuery(
                "select p from Profile p",
                Profile.class
        ).list();
        session.close();
        return profiles;
    }
}
