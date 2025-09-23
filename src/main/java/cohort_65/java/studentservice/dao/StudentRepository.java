package cohort_65.java.studentservice.dao;

import cohort_65.java.studentservice.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends MongoRepository<Student, Integer> {

    // Поиск студентов, у которых firstName или lastName входит в заданный список
    List<Student> findByFirstNameInOrLastNameIn(Set<String> firstNames, Set<String> lastNames);

    // Поиск студентов, у которых оценка по экзамену >= minScore
    // Используем MongoDB-синтаксис для поиска внутри Map
    @Query("{ 'scores.?0': { $gte: ?1 } }")
    List<Student> findByExamScoreGreaterThanEqual(String exam, Integer minScore);
}
