package org.example.repository.service;

import org.example.repository.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDBService {

    private final TransactionalHelperService transactionalHelperService;
    private final NoModifySessionHelper noModifySessionHelper;

    public StudentDBService(TransactionalHelperService transactionalHelperService, NoModifySessionHelper noModifySessionHelper) {
        this.transactionalHelperService = transactionalHelperService;
        this.noModifySessionHelper = noModifySessionHelper;
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
        return noModifySessionHelper.applySession(session -> {
            return session.createQuery(
                    "select s from Student s left join fetch s.profile left join fetch s.group left join fetch s.courseList where s.id = :id",
                    Student.class
            ).setParameter("id", id).getSingleResult();
        });
    }

    public List<Student> getAllStudents() {
        return noModifySessionHelper.applySession(session -> {
            return session.createQuery(
                    "select s from Student s left join fetch s.profile left join fetch s.group left join fetch s.courseList",
                    Student.class
            ).list();
        });
    }

}
