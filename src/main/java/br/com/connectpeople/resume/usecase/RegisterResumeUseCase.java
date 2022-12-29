package br.com.connectpeople.resume.usecase;

import br.com.connectpeople.adapters.repository.ResumeJpaRepository;
import br.com.connectpeople.jobexperience.usecase.RegisterJobExperiencesUseCase;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.usecase.chain.ErrorHandler;
import br.com.connectpeople.resume.usecase.chain.ValidateAlreadyRegister;
import br.com.connectpeople.resume.usecase.chain.ValidateBirthDate;
import br.com.connectpeople.resume.usecase.chain.ValidateDistrict;
import br.com.connectpeople.resume.usecase.chain.ValidateName;
import br.com.connectpeople.resume.usecase.chain.ValidatePhone;
import br.com.connectpeople.resume.usecase.chain.ValidatePostalCode;
import br.com.connectpeople.resume.usecase.executor.Executor;
import br.com.connectpeople.resume.usecase.executor.ResumePayload;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.connectpeople.util.IdGenerator.generateId;

@Service
@AllArgsConstructor
public class RegisterResumeUseCase {

    private final ResumeJpaRepository resumeJpaRepository;
    private final RegisterJobExperiencesUseCase registerJobExperienceUseCase;

    private final ValidateAlreadyRegister validateAlreadyRegister;
    private final ValidateName validateName;
    private final ValidateBirthDate validateBirthDate;
    private final ValidatePhone validatePhone;
    private final ValidatePostalCode validatePostalCode;
    private final ValidateDistrict validateDistrict;
    private final ErrorHandler errorHandler;

    @Transactional
    public Resume execute(Resume resume) {
        validateInput(buildInput(resume));
        String generatedId = getGeneratedId();
        resume.setCid(generatedId);
        resumeJpaRepository.save(resume.toEntity());
        registerJobExperienceUseCase.execute(generatedId, resume.getJobExperiences());
        return resume;
    }

    private void validateInput(ResumePayload payload) {
        Executor<ResumePayload> executor = new Executor<>(payload);
        executor.chain(validateAlreadyRegister)
                .chain(validateName)
                .chain(validateBirthDate)
                .chain(validatePhone)
                .chain(validatePostalCode)
                .chain(validateDistrict)
                .chain(errorHandler);
    }

    private ResumePayload buildInput(Resume resume) {
        return ResumePayload.builder()
                .name(resume.getName())
                .birthDate(resume.getBirthDate())
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

    private String getGeneratedId() {
        String id = generateId("CID");
        while (resumeJpaRepository.existsByCid(id)) {
            id = generateId("CID");
        }
        return id;
    }

}
