package br.com.connectpeople.resume.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SuperiorCourse {

    private String institution;
    private String course;
    private String status;
    private String conclusionYear;

}
