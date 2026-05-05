package org.example.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @OneToOne
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id"
    )
    private Student student;

    public Profile() {}

    public Profile(String description, Student student) {
        this.description = description;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
