package cohort_65.java.studentservice.service;

import cohort_65.java.studentservice.dao.StudentRepository;
import cohort_65.java.studentservice.dto.NewStudentDto;
import cohort_65.java.studentservice.dto.StudentDto;
import cohort_65.java.studentservice.dto.UpdateStudentDto;
import cohort_65.java.studentservice.dto.exception.StudentNotFoundException;
import cohort_65.java.studentservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public boolean addStudent(NewStudentDto newStudent) {
        if (studentRepository.findById(newStudent.getId()).isPresent()) {
            return false;
        }
        Student student = new Student(newStudent.getId(), newStudent.getFirstName(), newStudent.getLastName(), null);
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
        student.setFirstName(updateStudent.getFirstName());
        student.setLastName(updateStudent.getLastName());
        studentRepository.save(student);
        return new StudentDto(student.getId(), student.getFirstName(), student.getLastName());
    }
}
