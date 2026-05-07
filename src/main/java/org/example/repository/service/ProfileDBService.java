package org.example.repository.service;

import org.example.repository.entities.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileDBService {

    private final TransactionalHelperService transactionalHelperService;
    private final NoModifySessionHelper noModifySessionHelper;

    public ProfileDBService(TransactionalHelperService transactionalHelperService, NoModifySessionHelper noModifySessionHelper) {
        this.transactionalHelperService = transactionalHelperService;
        this.noModifySessionHelper = noModifySessionHelper;
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
        return noModifySessionHelper.applySession(session -> {
           return session.find(Profile.class, id);
        });
    }

    public List<Profile> getAllProfiles() {
        return noModifySessionHelper.applySession(session -> {
           return session.createQuery(
                   "select p from Profile p",
                   Profile.class
           ).list();
        });
    }
}
