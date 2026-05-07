package org.example.web.controllers;

import org.example.web.dto.CreateStudentDTO;
import org.example.web.dto.StudentDTO;
import org.example.web.service.StudentWebService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("student/")
public class StudentController {

    private final StudentWebService studentWebService;

    public StudentController(StudentWebService studentWebService) {
        this.studentWebService = studentWebService;
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(
            @PathVariable("id") Long id
    ) {
        return studentWebService.getStudent(id);
    }

    @PostMapping("/createStudent")
    public StudentDTO createStudent(
            @RequestBody CreateStudentDTO student
    ) {

    }

}
