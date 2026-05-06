package org.example.repository.service;

import org.example.repository.entities.Group;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final TransactionalHelperService transactionalHelperService;
    private final NoModifySessionHelper noModifySessionHelper;
    private final SessionFactory sessionFactory;

    public GroupService(TransactionalHelperService transactionalHelperService, NoModifySessionHelper noModifySessionHelper, SessionFactory sessionFactory) {
        this.transactionalHelperService = transactionalHelperService;
        this.noModifySessionHelper = noModifySessionHelper;
        this.sessionFactory = sessionFactory;
    }

    public Group saveGroup(Group group) {
        return transactionalHelperService.applyTransactional(session -> {
           session.persist(group);
           return group;
        });
    }

    public void deleteGroup(Long id) {
        transactionalHelperService.applyTransactional(session -> {
            Group group = session.find(Group.class, id);
            session.remove(group);
        });
    }

    public Group updateGroup(Group group) {
        return transactionalHelperService.applyTransactional(session -> {
            return session.merge(group);
        });
    }

    public Group getGroup(Long id) {
        return noModifySessionHelper.applySession(session -> {
            return session.createQuery(
                    "select g from Group g left join fetch g.students s left join fetch s.profile where g.id = :id",
                    Group.class
            ).setParameter("id", id).getSingleResult();
        });
    }

    public List<Group> getAllGroup() {
        return noModifySessionHelper.applySession(session -> {
            return session.createQuery(
                    "select g from Group g",
                    Group.class
            ).list();
        });
    }
}
