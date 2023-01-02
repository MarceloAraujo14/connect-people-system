package br.com.connectpeople.resume.domain;

import br.com.connectpeople.resume.domain.enums.SuperiorCourseStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class SuperiorCourse {

    private Long id;
    private String institution;
    private String course;
    private SuperiorCourseStatus status;
    private LocalDate conclusionYear;

}
