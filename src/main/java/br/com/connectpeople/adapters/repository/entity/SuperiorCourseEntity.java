package br.com.connectpeople.adapters.repository.entity;

import br.com.connectpeople.resume.domain.Course;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class SuperiorCourseEntity extends Course {

    private String institution;
    private String course;
    private String status;
    private String conclusionYear;

}
