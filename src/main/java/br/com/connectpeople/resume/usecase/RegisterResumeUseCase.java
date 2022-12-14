package br.com.connectpeople.resume.usecase;

import br.com.connectpeople.adapters.repository.JobExperienceJpaRepository;
import br.com.connectpeople.adapters.repository.ResumeJpaRepository;
import br.com.connectpeople.adapters.repository.entity.ResumeEntity;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.usecase.chain.ErrorHandler;
import br.com.connectpeople.resume.usecase.chain.Executor;
import br.com.connectpeople.resume.usecase.chain.ValidateBirthDate;
import br.com.connectpeople.resume.usecase.chain.ValidateName;
import br.com.connectpeople.resume.usecase.chain.ValidatePhone;
import br.com.connectpeople.resume.usecase.chain.ValidatePostalCode;
import br.com.connectpeople.resume.usecase.input.RegisterResumeInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

import static br.com.connectpeople.mapper.JobExperienceMapper.toJobExperienceList;
import static br.com.connectpeople.util.IdGenerator.generateId;

@Service
@AllArgsConstructor
public class RegisterResumeUseCase {

    private final ResumeJpaRepository resumeJpaRepository;
    private final JobExperienceJpaRepository jobExperienceJpaRepository;
    private final ValidateName validateName;
    private final ValidateBirthDate validateBirthDate;
    private final ValidatePhone validatePhone;
    private final ValidatePostalCode validatePostalCode;
    private final ErrorHandler errorHandler;

    @Transactional
    public Resume execute(Resume resume){
        validateInput(buildInput(resume));
        resume.setCid(getId());
        resume.getJobExperiences().forEach(job -> job.setCid(resume.getCid()));
        resumeJpaRepository.save(resume.toEntity());
        jobExperienceJpaRepository.saveAll(toJobExperienceList(resume.getJobExperiences()));
        return resume;
    }

    private void validateInput(RegisterResumeInput payload){
        Executor<RegisterResumeInput> executor = new Executor<>(payload);
        executor.chain(validateName)
                .chain(validateBirthDate)
                .chain(validatePhone)
                .chain(validatePostalCode)
                .chain(errorHandler);
    }

    private String getId(){
        String id = generateId("CID");
        while (resumeJpaRepository.existsById(id)){
            id = generateId("CID");
        }
        return id;
    }

    private RegisterResumeInput buildInput(Resume resume){
        return RegisterResumeInput.builder()
                .name(resume.getName())
                .birthDate(resume.getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .gender(resume.getGender())
                .phone(resume.getPhone())
                .cellPhone(resume.getCellPhone())
                .email(resume.getEmail())
                .postalCode(resume.getPostalCode())
                .district(resume.getDistrict())
                .jobOptionOne(resume.getJobOptionOne())
                .jobOptionTwo(resume.getJobOptionTwo())
                .jobOptionThree(resume.getJobOptionThree())
                .jobExperiences(resume.getJobExperiences())
                .schooling(resume.getSchooling())
                .build();
    }


}
