package org.example.repository.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "name",
            nullable = false
    )
    private String name;

    @Column(
            name = "grad_year",
            nullable = false
    )
    private Long graduationYear;

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    public Group() {
    }

    public Group(String name, Long graduationYear) {
        this.name = name;
        this.graduationYear = graduationYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Long graduationYear) {
        this.graduationYear = graduationYear;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
