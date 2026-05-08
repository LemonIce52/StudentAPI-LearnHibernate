package org.example.web.controllers;

import jakarta.validation.Valid;
import org.example.repository.entities.Student;
import org.example.web.dto.CreateStudentDTO;
import org.example.web.dto.StudentDTO;
import org.example.web.dto.UpdateStudentDTO;
import org.example.web.service.StudentWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);
    private final StudentWebService studentWebService;

    public StudentController(StudentWebService studentWebService) {
        this.studentWebService = studentWebService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        logger.info("Call method get all student");
        return studentWebService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(
            @PathVariable("id") Long id
    ) {
        logger.info("Call method get student id={}", id);
        return studentWebService.getStudent(id);
    }

    @PostMapping("/create")
    public StudentDTO createStudent(
            @Valid @RequestBody CreateStudentDTO student
    ) {
        logger.info("Call method create student student={}", student);
        return studentWebService.createStudent(student);
    }

    @PutMapping("/update")
    public StudentDTO updateStudent(
            @Valid @RequestBody UpdateStudentDTO updateStudent
    ) {
        logger.info("Call method update student updateStudent={}", updateStudent);
        return studentWebService.updateStudent(updateStudent);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(
            @PathVariable("id") Long id
    ) {
        logger.info("Call method delete student id={}", id);
        studentWebService.deleteStudent(id);
    }

}
