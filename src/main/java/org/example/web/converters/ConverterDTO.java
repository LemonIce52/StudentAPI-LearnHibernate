package org.example.web.converters;

import org.example.repository.entities.Course;
import org.example.repository.entities.Profile;
import org.example.repository.entities.Student;
import org.example.web.dto.ProfileDTO;
import org.example.web.dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterDTO {

    public StudentDTO convertStudentToDto(Student studentDB) {
        ProfileDTO profileStudentDTO = studentDB.getProfile() != null
                ? convertProfileToDto(studentDB.getProfile()) : null;

        List<String> courses = new ArrayList<>();

        for (Course course : studentDB.getCourseList()) {
            courses.add(course.getName());
        }

        return new StudentDTO(
                studentDB.getId(),
                studentDB.getName(),
                studentDB.getAge(),
                profileStudentDTO,
                studentDB.getGroup().getName(),
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
}
