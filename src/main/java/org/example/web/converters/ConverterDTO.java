package org.example.web.converters;

import org.example.repository.entities.Course;
import org.example.repository.entities.Group;
import org.example.repository.entities.Profile;
import org.example.repository.entities.Student;
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

        List<String> courses = new ArrayList<>();

        if (studentDB.getCourseList() != null) {
            for (Course course : studentDB.getCourseList()) {
                courses.add(course.getName());
            }
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

        if (savedGroup.getStudents() != null) {
            for (Student student : savedGroup.getStudents()) {
                studentList.add(convertStudentToDto(student));
            }
        }

        return new GroupDTO(
                savedGroup.getId(),
                savedGroup.getName(),
                savedGroup.getGraduationYear(),
                studentList
        );
    }
}
