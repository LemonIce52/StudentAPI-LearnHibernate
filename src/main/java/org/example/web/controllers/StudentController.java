package org.example.web.controllers;

import org.example.web.dto.CreateStudentDTO;
import org.example.web.dto.StudentDTO;
import org.example.web.service.StudentWebService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentWebService studentWebService;

    public StudentController(StudentWebService studentWebService) {
        this.studentWebService = studentWebService;
    }

    @GetMapping
    public List<StudentDTO> getVoid() {
        return studentWebService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(
            @PathVariable("id") Long id
    ) {
        return studentWebService.getStudent(id);
    }

    @PostMapping("/create")
    public StudentDTO createStudent(
            @RequestBody CreateStudentDTO student
    ) {
        return studentWebService.createStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(
            @PathVariable("id") Long id
    ) {
        studentWebService.deleteStudent(id);
    }

}
