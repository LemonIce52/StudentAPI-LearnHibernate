package org.example.web.controllers;

import jakarta.validation.Valid;
import org.example.exceptions.GlobalExceptionHandler;
import org.example.web.dto.AddStudentToCourseDTO;
import org.example.web.dto.CourseDTO;
import org.example.web.dto.CreateCourseDTO;
import org.example.web.dto.UpdateCourseDTO;
import org.example.web.service.CourseWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final Logger logger = LoggerFactory.getLogger(CourseController.class);
    private final CourseWebService courseWebService;

    public CourseController(CourseWebService courseWebService) {
        this.courseWebService = courseWebService;
    }

    @GetMapping
    public List<CourseDTO> getAllCourse() {
        logger.info("Call method get all course");
        return courseWebService.getAllCourse();
    }

    @GetMapping("/{id}")
    public CourseDTO getCourse(
            @PathVariable("id") Long id
    ) {
        logger.info("Call method get course id={}", id);
        return courseWebService.getCourse(id);
    }

    @PostMapping("/create")
    public CourseDTO createCourse(
            @Valid @RequestBody CreateCourseDTO createCourseDTO
    ) {
        logger.info("Call method create course createCourseDTO={}", createCourseDTO);
       return courseWebService.createCourse(createCourseDTO);
    }

    @PutMapping("/addStudents")
    public CourseDTO addStudentsToCourse(
            @Valid @RequestBody AddStudentToCourseDTO addStudentToCourseDTO
    ) {
        logger.info("Call method add student to course addStudentToCourse={}", addStudentToCourseDTO);
        return courseWebService.addStudentsToCourse(addStudentToCourseDTO);
    }

    @PutMapping("/update")
    public CourseDTO updateCourse(
            @Valid @RequestBody UpdateCourseDTO updateCourseDTO
    ) {
        logger.info("Call method update course updateCourse={}", updateCourseDTO);
        return courseWebService.updateCourse(updateCourseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCourse(
            @PathVariable("id") Long id
    ) {
        logger.info("Call method delete course id={}", id);
        courseWebService.deleteCourse(id);
    }
}
