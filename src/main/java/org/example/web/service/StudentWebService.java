package org.example.web.service;

import org.example.repository.entities.Group;
import org.example.repository.entities.Student;
import org.example.repository.service.GroupDBService;
import org.example.repository.service.StudentDBService;
import org.example.web.converters.ConverterDTO;
import org.example.web.dto.CreateStudentDTO;
import org.example.web.dto.StudentDTO;
import org.example.web.dto.UpdateStudentDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentWebService {

    private final StudentDBService studentDBService;
    private final GroupDBService groupDBService;
    private final ConverterDTO converterDTO;

    public StudentWebService(StudentDBService studentDBService, GroupDBService groupDBService, ConverterDTO converterDTO) {
        this.studentDBService = studentDBService;
        this.groupDBService = groupDBService;
        this.converterDTO = converterDTO;
    }

    public StudentDTO getStudent(Long id) {
        if (id < 0) throw new IllegalArgumentException("id can't must be less zero!");
        Student studentDB = studentDBService.getStudent(id);
        return converterDTO.convertStudentToDto(studentDB);
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> studentDbList = studentDBService.getAllStudents();
        return studentDbList
                .stream()
                .map(converterDTO::convertStudentToDto)
                .toList();
    }

    public StudentDTO createStudent(CreateStudentDTO createStudentDTO) {
        Group group = groupDBService.getGroup(createStudentDTO.groupId());
        Student student = new Student(createStudentDTO.name(), createStudentDTO.age(), group);
        Student savedStudent = studentDBService.saveStudent(student);
        return converterDTO.convertStudentToDto(savedStudent);
    }

    public void deleteStudent(Long id) {
        if (id < 0) throw new IllegalArgumentException("id can't must be less zero!");
        studentDBService.deleteStudent(id);
    }

    public StudentDTO updateStudent(UpdateStudentDTO updateStudent) {
        Student student = studentDBService.getStudent(updateStudent.id());

        if (updateStudent.name() != null) {
            student.setName(updateStudent.name());
        }

        if (updateStudent.age() != null) {
            student.setAge(updateStudent.age());
        }

        if (updateStudent.groupId() != null) {
            Group groupStudent = groupDBService.getGroup(updateStudent.groupId());
            student.setGroup(groupStudent);
        }

        Student updatebleStudent = studentDBService.updateStudent(student);
        return converterDTO.convertStudentToDto(updatebleStudent);
    }
}
