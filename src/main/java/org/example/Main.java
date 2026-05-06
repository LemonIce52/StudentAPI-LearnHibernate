package org.example;

import org.example.repository.entities.Course;
import org.example.repository.entities.Group;
import org.example.repository.entities.Student;
import org.example.repository.service.CourseService;
import org.example.repository.service.GroupService;
import org.example.repository.service.ProfileService;
import org.example.repository.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        StudentService studentService = context.getBean(StudentService.class);
        ProfileService profileService = context.getBean(ProfileService.class);
        GroupService groupService = context.getBean(GroupService.class);
        CourseService courseService = context.getBean(CourseService.class);

        Group group = new Group("1", 2024L);
        groupService.saveGroup(group);

        Student student1 = new Student("Nikita", 24, group);
        Student student2 = new Student("Artem", 20, group);

        studentService.saveStudent(student1);
        studentService.saveStudent(student2);

        Course course1 = new Course("it", "conf");
        Course course2 = new Course("math", "conf");

        courseService.saveCourse(course1);
        courseService.saveCourse(course2);

        Student getStudent = studentService.getStudent(1L);
        Course getCourse = courseService.getCourse(1L);

        getCourse.getStudentsList().add(student1);
        getCourse.getStudentsList().add(student2);

        courseService.updateCourse(getCourse);

    }
}