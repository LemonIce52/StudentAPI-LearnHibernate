package org.example.repository;

import org.example.repository.entities.Course;
import org.example.repository.entities.Group;
import org.example.repository.entities.Profile;
import org.example.repository.entities.Student;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();

        configuration
                .addPackage("org.example")
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Profile.class)
                .addAnnotatedClass(Group.class)
                .addAnnotatedClass(Course.class)
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "root")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop");

        return configuration.buildSessionFactory();
    }
}
