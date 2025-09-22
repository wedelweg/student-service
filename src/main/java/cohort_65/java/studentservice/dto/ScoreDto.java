package cohort_65.java.studentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDto {
    private String exam;   // название экзамена
    private Integer score; // балл
}
