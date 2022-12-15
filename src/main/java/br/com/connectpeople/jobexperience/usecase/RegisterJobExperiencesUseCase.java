package br.com.connectpeople.jobexperience.usecase;

import br.com.connectpeople.adapters.repository.JobExperienceJpaRepository;
import br.com.connectpeople.jobexperience.domain.JobExperience;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.connectpeople.adapters.repository.mapper.JobExperienceMapper.toJobExperienceList;

@Service
@AllArgsConstructor
public class RegisterJobExperiencesUseCase {

    private final JobExperienceJpaRepository jobExperienceJpaRepository;

    public List<JobExperience> execute(String cid, List<JobExperience> jobExperiences) {
        var jobExperienceEntities = toJobExperienceList(jobExperiences);
        jobExperienceEntities.forEach(job -> job.setCid(cid));
        jobExperienceJpaRepository.saveAll(jobExperienceEntities);
        return jobExperiences;
    }

}
