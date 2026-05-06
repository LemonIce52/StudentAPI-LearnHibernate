package org.example;

import org.example.entities.Group;
import org.example.entities.Profile;
import org.example.entities.Student;
import org.example.service.GroupService;
import org.example.service.ProfileService;
import org.example.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        StudentService studentService = context.getBean(StudentService.class);
        ProfileService profileService = context.getBean(ProfileService.class);
        GroupService groupService = context.getBean(GroupService.class);

        Group group = new Group("1", 2024L);
        groupService.saveGroup(group);

        Student student1 = new Student("Nikita", 24, group);
        Student student2 = new Student("Artem", 20, group);

        studentService.saveStudent(student1);
        studentService.saveStudent(student2);

        Student getStudent = studentService.getStudent(1L);
        Group getGroup = groupService.getGroup(1L);
        Group getGroupWithStudents = groupService.getGroupWithStudents(1L);


    }
}