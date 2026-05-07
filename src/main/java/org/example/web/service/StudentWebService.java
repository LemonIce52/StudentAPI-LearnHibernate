package org.example.web.service;

import org.example.repository.entities.Group;
import org.example.repository.entities.Student;
import org.example.repository.service.GroupDBService;
import org.example.repository.service.StudentDBService;
import org.example.web.converters.ConverterDTO;
import org.example.web.dto.CreateStudentDTO;
import org.example.web.dto.StudentDTO;
import org.springframework.stereotype.Service;

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
        Student studentDB = studentDBService.getStudent(id);
        return converterDTO.convertStudentToDto(studentDB);
    }

    public StudentDTO createStudent(CreateStudentDTO createStudentDTO) {
        Group group = groupDBService.getGroup(createStudentDTO.groupId());
        Student student = new Student(createStudentDTO.name(), createStudentDTO.age(), group);
        Student savedStudent = studentDBService.saveStudent(student);
        return converterDTO.convertStudentToDto(savedStudent);
    }
}
