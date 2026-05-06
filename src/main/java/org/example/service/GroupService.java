package org.example.service;

import org.example.entities.Group;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final TransactionalHelperService transactionalHelperService;
    private final SessionFactory sessionFactory;

    public GroupService(TransactionalHelperService transactionalHelperService, SessionFactory sessionFactory) {
        this.transactionalHelperService = transactionalHelperService;
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
        try (Session session = sessionFactory.openSession()) {
            return session.find(Group.class, id);
        }
    }

    public List<Group> getAllGroup() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "select g from Group g",
                    Group.class
            ).list();
        }
    }

    public Group getGroupWithStudents(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "select g from Group g join fetch g.students where g.id = :id",
                    Group.class
            ).setParameter("id", id).getSingleResult();
        }
    }

}
