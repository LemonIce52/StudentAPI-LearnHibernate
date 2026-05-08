package org.example.web.converters;

import jakarta.persistence.Persistence;
import org.example.repository.entities.Course;
import org.example.repository.entities.Group;
import org.example.repository.entities.Profile;
import org.example.repository.entities.Student;
import org.example.web.dto.CourseDTO;
import org.example.web.dto.GroupDTO;
import org.example.web.dto.ProfileDTO;
import org.example.web.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class ConverterDTO {

    public StudentDTO convertStudentToDto(Student studentDB) {
        ProfileDTO profileStudentDTO = studentDB.getProfile() != null
                ? convertProfileToDto(studentDB.getProfile()) : null;

        List<CourseDTO> courses = new ArrayList<>();

        boolean isLoadedCourses = Persistence.getPersistenceUtil().isLoaded(studentDB, "courseList");

        if (isLoadedCourses && studentDB.getCourseList() != null) {
            courses = studentDB.getCourseList()
                    .stream()
                    .map(this::convertCourseToDto)
                    .toList();
        }

        return new StudentDTO(
                studentDB.getId(),
                studentDB.getName(),
                studentDB.getAge(),
                profileStudentDTO,
                studentDB.getGroup().getId(),
                courses
        );
    }

    public ProfileDTO convertProfileToDto(Profile profileDB) {
        return new ProfileDTO(
                profileDB.getId(),
                profileDB.getDescription(),
                profileDB.getLastSeenProfile(),
                profileDB.getStudent().getId()
        );
    }

    public GroupDTO convertGroupToDto(Group savedGroup) {
        List<StudentDTO> studentList = new ArrayList<>();

        boolean isLoadedStudentsList = Persistence.getPersistenceUtil().isLoaded(savedGroup, "students");

        if (isLoadedStudentsList && savedGroup.getStudents() != null) {
            studentList = savedGroup.getStudents()
                    .stream()
                    .map(this::convertStudentToDto)
                    .toList();
        }

        return new GroupDTO(
                savedGroup.getId(),
                savedGroup.getName(),
                savedGroup.getGraduationYear(),
                studentList
        );
    }

    public CourseDTO convertCourseToDto(Course courseDB) {
        List<StudentDTO> studentList = new ArrayList<>();

        boolean isLoadedStudents = Persistence.getPersistenceUtil().isLoaded(courseDB, "studentsList");

        if (isLoadedStudents && courseDB.getStudentsList() != null) {
            studentList = courseDB.getStudentsList()
                    .stream()
                    .map(this::convertStudentToDto)
                    .toList();
        }

        return new CourseDTO(
                courseDB.getId(),
                courseDB.getName(),
                courseDB.getCourseType(),
                studentList
        );
    }
}
