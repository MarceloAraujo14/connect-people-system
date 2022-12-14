package br.com.connectpeople.resume.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobExperience {
    private String cid;
    private String title;
    private int startMonth;
    private int startYear;
    private boolean isCurrentJob;
    private int endMonth;
    private int endYear;
    private String description;

}
