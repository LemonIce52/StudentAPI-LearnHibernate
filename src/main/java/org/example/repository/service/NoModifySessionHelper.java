package org.example.repository.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class NoModifySessionHelper {

    private final SessionFactory sessionFactory;

    public NoModifySessionHelper(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public<T> T applySession(Function<Session, T> function) {
        try (Session session = sessionFactory.openSession()) {
            return function.apply(session);
        }
    }
}
