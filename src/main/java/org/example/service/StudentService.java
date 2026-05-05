package org.example.service;

import org.example.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final SessionFactory sessionFactory;
    private final TransactionalHelperService transactionalHelperService;

    public StudentService(SessionFactory sessionFactory, TransactionalHelperService transactionalHelperService) {
        this.sessionFactory = sessionFactory;
        this.transactionalHelperService = transactionalHelperService;
    }

    public Student saveStudent(Student student) {
        return transactionalHelperService.applyTransactional(session -> {
            session.persist(student);
            return student;
        });
    }

    public void deleteStudent(Long id) {
        transactionalHelperService.applyTransactional(session -> {
            Student student = session.find(Student.class, id);
            session.remove(student);
        });
    }

    public Student updateStudent(Student student) {
        return transactionalHelperService.applyTransactional(session -> {
            return session.merge(student);
        });
    }

    public Student getStudent(Long id) {
        Session session = sessionFactory.openSession();
        Student student = session.find(Student.class, id);
        session.close();
        return student;
    }

    public List<Student> getAllStudents() {
        Session session = sessionFactory.openSession();
        List<Student> allStudents = session.createQuery(
                "select s from Student s",
                Student.class
                ).list();
        session.close();
        return allStudents;
    }

}
