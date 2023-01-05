package br.com.connectpeople.resume.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobExperience {
    private String title;
    private String company;
    private int startMonth;
    private int startYear;
    private boolean currentJob;
    private int endMonth;
    private int endYear;
    private String description;

}
