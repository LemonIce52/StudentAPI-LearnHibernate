package org.example.web.service;

import org.example.repository.entities.Course;
import org.example.repository.entities.Student;
import org.example.repository.service.CourseDBService;
import org.example.repository.service.StudentDBService;
import org.example.web.converters.ConverterDTO;
import org.example.web.dto.AddStudentToCourseDTO;
import org.example.web.dto.CourseDTO;
import org.example.web.dto.CreateCourseDTO;
import org.example.web.dto.UpdateCourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseWebService {

    private final CourseDBService courseDBService;
    private final StudentDBService studentDBService;
    private final ConverterDTO converterDTO;

    public CourseWebService(CourseDBService courseDBService, StudentDBService studentDBService, ConverterDTO converterDTO) {
        this.courseDBService = courseDBService;
        this.studentDBService = studentDBService;
        this.converterDTO = converterDTO;
    }

    public CourseDTO getCourse(Long id) {
        if (id < 0) throw new IllegalArgumentException("id can't must be less zero!");
        return converterDTO.convertCourseToDto(courseDBService.getCourse(id));
    }

    public List<CourseDTO> getAllCourse() {
        List<Course> courseList = courseDBService.getAllCourse();
        return courseList
                .stream()
                .map(converterDTO::convertCourseToDto)
                .toList();
    }

    public CourseDTO createCourse(CreateCourseDTO createCourseDTO) {
        Course newCourse = new Course(createCourseDTO.name(), createCourseDTO.courseType());
        return converterDTO.convertCourseToDto(courseDBService.saveCourse(newCourse));
    }

    public CourseDTO addStudentsToCourse(AddStudentToCourseDTO addStudentToCourseDTO) {
        Course course = courseDBService.getCourse(addStudentToCourseDTO.courseId());

        addStudentToCourseDTO.studentsId().forEach( id -> {
                Student student = studentDBService.getStudent(id);
                course.getStudentsList().add(student);
        });

        Course updateCourse = courseDBService.updateCourse(course);

        return converterDTO.convertCourseToDto(updateCourse);
    }

    public CourseDTO updateCourse(UpdateCourseDTO updateCourseDTO) {
        Course course = courseDBService.getCourse(updateCourseDTO.id());

        if (updateCourseDTO.name() != null) {
            course.setName(updateCourseDTO.name());
        }

        if (updateCourseDTO.courseType() != null) {
            course.setCourseType(updateCourseDTO.courseType());
        }

        Course updatebaleCourse = courseDBService.updateCourse(course);

        return converterDTO.convertCourseToDto(updatebaleCourse);
    }

    public void deleteCourse(Long id) {
        if (id < 0) throw new IllegalArgumentException("id can't must be less zero!");
        courseDBService.deleteCourse(id);
    }
}
