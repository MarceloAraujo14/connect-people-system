package br.com.connectpeople.resume.usecase.resume;

import br.com.connectpeople.adapters.repository.ResumeJpaRepository;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.usecase.executor.Executor;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import br.com.connectpeople.resume.usecase.jobexperience.RegisterJobExperienceUseCase;
import br.com.connectpeople.resume.usecase.resume.chain.ErrorHandler;
import br.com.connectpeople.resume.usecase.resume.chain.ValidateBirthDate;
import br.com.connectpeople.resume.usecase.resume.chain.ValidateName;
import br.com.connectpeople.resume.usecase.resume.chain.ValidatePhone;
import br.com.connectpeople.resume.usecase.resume.chain.ValidatePostalCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

import static br.com.connectpeople.util.IdGenerator.generateId;

@Service
@AllArgsConstructor
public class RegisterResumeUseCase {

    private final ResumeJpaRepository resumeJpaRepository;
    private final RegisterJobExperienceUseCase registerJobExperienceUseCase;
    private final ValidateName validateName;
    private final ValidateBirthDate validateBirthDate;
    private final ValidatePhone validatePhone;
    private final ValidatePostalCode validatePostalCode;
    private final ErrorHandler errorHandler;

    @Transactional
    public Resume execute(Resume resume){
        validateInput(buildInput(resume));
        resume.setCid(getGeneratedId());
        resumeJpaRepository.save(resume.toEntity());
        registerJobExperienceUseCase.execute(resume.getCid(), resume.getJobExperiences());
        return resume;
    }

    private void validateInput(ResumePayload payload){
        Executor<ResumePayload> executor = new Executor<>(payload);
        executor.chain(validateName)
                .chain(validateBirthDate)
                .chain(validatePhone)
                .chain(validatePostalCode)
                .chain(errorHandler);
    }

    private String getGeneratedId(){
        String id = generateId("CID");
        while (resumeJpaRepository.existsByCid(id)){
            id = generateId("CID");
        }
        return id;
    }

    private ResumePayload buildInput(Resume resume){
        return ResumePayload.builder()
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
