package cohort_65.java.studentservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "students")
public class Student {
    @Id
    private Integer id;
    private String firstName;
    private String lastName;

    private Map<String, Integer> scores = new HashMap<>();

    public boolean addScore(String subject, Integer score) {
        return scores.put(subject, score) == null;
    }
}
