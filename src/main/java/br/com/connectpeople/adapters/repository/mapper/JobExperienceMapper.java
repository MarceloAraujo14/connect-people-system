package br.com.connectpeople.adapters.repository.mapper;

import br.com.connectpeople.adapters.repository.entity.JobExperienceEntity;
import br.com.connectpeople.jobexperience.domain.JobExperience;

import java.util.List;

public class JobExperienceMapper {

    private JobExperienceMapper() {
    }

    public static JobExperienceEntity toJobExperienceEntity(JobExperience jobExperience) {
        return JobExperienceEntity.builder()
                .description(jobExperience.getDescription())
                .title(jobExperience.getTitle())
                .startMonth(jobExperience.getStartMonth())
                .startYear(jobExperience.getStartYear())
                .isCurrentJob(jobExperience.isCurrentJob())
                .endMonth(jobExperience.getEndMonth())
                .endYear(jobExperience.getEndYear())
                .build();
    }

    public static List<JobExperienceEntity> toJobExperienceList(List<JobExperience> jobExperiences) {
        return jobExperiences.stream().map(JobExperienceMapper::toJobExperienceEntity).toList();
    }

}