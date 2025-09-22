package cohort_65.java.studentservice.service;

import cohort_65.java.studentservice.dto.*;
import java.util.List;
import java.util.Set;

public interface StudentService {
    boolean addStudent(NewStudentDto newStudent);
    StudentDto findStudent(int id);
    StudentDto removeStudent(int id);
    StudentDto updateStudent(UpdateStudentDto updateStudent, int id);

    boolean addScore(int id, ScoreDto scoreDto);
    List<StudentDto> findStudentsByName(String name);
    Integer getStudentsNamesQuantity(Set<String> names);
    List<StudentDto> getStudentsByExamMinScore(String exam, Integer minScore);
}
