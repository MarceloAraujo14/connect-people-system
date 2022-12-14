package br.com.connectpeople.resume.usecase.jobexperience;

import br.com.connectpeople.adapters.repository.JobExperienceJpaRepository;
import br.com.connectpeople.resume.domain.JobExperience;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.connectpeople.adapters.repository.mapper.JobExperienceMapper.toJobExperienceList;

@Service
@AllArgsConstructor
public class RegisterJobExperienceUseCase {

    private final JobExperienceJpaRepository jobExperienceJpaRepository;

    public void execute(String cid, List<JobExperience> jobExperiences){
        var jobExperienceEntities = toJobExperienceList(jobExperiences);
        jobExperienceEntities.forEach(job -> job.setCid(cid));
        jobExperienceJpaRepository.saveAll(jobExperienceEntities);

    }

}
