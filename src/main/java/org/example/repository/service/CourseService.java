package org.example.repository.service;

import org.example.repository.entities.Course;
import org.example.repository.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final TransactionalHelperService transactionalHelperService;
    private final NoModifySessionHelper noModifySessionHelper;

    public CourseService(TransactionalHelperService transactionalHelperService, NoModifySessionHelper noModifySessionHelper) {
        this.transactionalHelperService = transactionalHelperService;
        this.noModifySessionHelper = noModifySessionHelper;
    }

    public Course saveCourse(Course course) {
        return transactionalHelperService.applyTransactional(session -> {
            session.persist(course);
            return course;
        });
    }

    public Course updateCourse(Course course) {
        return transactionalHelperService.applyTransactional(session -> {
            return session.merge(course);
        });
    }

    public void deleteCourse(Long id) {
        transactionalHelperService.applyTransactional(session -> {
            session.remove(session.find(Course.class, id));
        });
    }

    public Course getCourse(Long id) {
        return noModifySessionHelper.applySession(session -> {
           return session.createQuery(
                   "select c from Course c left join fetch c.studentsList where c.id = :id",
                   Course.class
           ).setParameter("id", id).getSingleResult();
        });
    }

    public List<Course> getAllCourse() {
        return noModifySessionHelper.applySession(session -> {
            return session.createQuery(
                    "select c from Course c left join fetch c.studentsList",
                    Course.class
            ).list();
        });
    }
}
