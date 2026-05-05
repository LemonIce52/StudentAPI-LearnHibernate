package org.example;

import org.example.entities.Profile;
import org.example.entities.Student;
import org.example.service.ProfileService;
import org.example.service.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");

        StudentService studentService = context.getBean(StudentService.class);
        ProfileService profileService = context.getBean(ProfileService.class);

        Student one = new Student("Vasy", 20);
        Student two = new Student("Artem", 30);

        System.out.println(studentService.saveStudent(one));
        System.out.println(studentService.saveStudent(two));
        System.out.println(studentService.getStudent(1L));
        System.out.println(studentService.getAllStudents());
        one.setName("test");
        System.out.println(studentService.updateStudent(one));
        System.out.println(studentService.getAllStudents());
        studentService.deleteStudent(2L);
        System.out.println(studentService.getAllStudents());

        Profile profile = new Profile("test", one);

        System.out.println(profileService.saveProfile(profile));

        Profile getProfile = profileService.getProfile(1L);
        Student getStudent = studentService.getStudent(1L);

        String profileDesc = getStudent.getProfile().getDescription();

        System.out.println(profileDesc);

    }
}