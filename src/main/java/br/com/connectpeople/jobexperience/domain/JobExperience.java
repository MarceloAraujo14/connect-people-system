package br.com.connectpeople.jobexperience.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobExperience {
    private String title;
    private String company;
    private int startMonth;
    private int startYear;
    private boolean isCurrentJob;
    private int endMonth;
    private int endYear;
    private String description;

}
