package cohort_65.java.studentservice.service;

import cohort_65.java.studentservice.dao.StudentRepository;
import cohort_65.java.studentservice.dto.*;
import cohort_65.java.studentservice.dto.exception.StudentNotFoundException;
import cohort_65.java.studentservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean addStudent(NewStudentDto newStudent) {
        if (studentRepository.findById(newStudent.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(newStudent.getId(),
                newStudent.getFirstName(),
                newStudent.getLastName(),
                new java.util.HashMap<>());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    @Override
    public StudentDto removeStudent(int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        studentRepository.deleteById(id);
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    @Override
    public StudentDto updateStudent(UpdateStudentDto updateStudent, int id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        if (updateStudent.getFirstName() != null) {
            student.setFirstName(updateStudent.getFirstName());
        }
        if (updateStudent.getLastName() != null) {
            student.setLastName(updateStudent.getLastName());
        }
        studentRepository.save(student);
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }

    @Override
    public boolean addScore(int id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        student.getScores().put(scoreDto.getExam(), scoreDto.getScore());
        studentRepository.save(student);
        return true;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        return studentRepository.findAll().stream()
                .filter(s -> s.getFirstName().equalsIgnoreCase(name) || s.getLastName().equalsIgnoreCase(name))
                .map(s -> new StudentDto(s.getId(), s.getFirstName(), s.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getStudentsNamesQuantity(Set<String> names) {
        return (int) studentRepository.findAll().stream()
                .filter(s -> names.contains(s.getFirstName()) || names.contains(s.getLastName()))
                .count();
    }

    @Override
    public List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore) {
        return studentRepository.findAll().stream()
                .filter(s -> s.getScores().getOrDefault(exam, 0) >= minScore)
                .map(s -> new StudentDto(s.getId(), s.getFirstName(), s.getLastName()))
                .collect(Collectors.toList());
    }
}
