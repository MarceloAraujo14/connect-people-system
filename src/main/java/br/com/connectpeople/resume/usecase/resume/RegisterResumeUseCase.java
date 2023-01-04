package br.com.connectpeople.resume.usecase.resume;

import br.com.connectpeople.adapters.repository.CourseRepository;
import br.com.connectpeople.adapters.repository.JobExperienceJpaRepository;
import br.com.connectpeople.adapters.repository.ResumeJpaRepository;
import br.com.connectpeople.adapters.repository.SuperiorCourseRepository;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.usecase.resume.chain.ErrorHandler;
import br.com.connectpeople.resume.usecase.resume.chain.ValidateAlreadyRegister;
import br.com.connectpeople.resume.usecase.resume.chain.ValidateBirthDate;
import br.com.connectpeople.resume.usecase.resume.chain.ValidateCity;
import br.com.connectpeople.resume.usecase.resume.chain.ValidateDistrict;
import br.com.connectpeople.resume.usecase.resume.chain.ValidateJobExperience;
import br.com.connectpeople.resume.usecase.resume.chain.ValidateName;
import br.com.connectpeople.resume.usecase.resume.chain.ValidatePhone;
import br.com.connectpeople.resume.usecase.resume.chain.ValidatePostalCode;
import br.com.connectpeople.resume.usecase.resume.chain.ValidateSchooling;
import br.com.connectpeople.resume.usecase.resume.executor.Executor;
import br.com.connectpeople.resume.usecase.resume.executor.ResumePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.connectpeople.adapters.repository.mapper.CourseMapper.toCourseEntityList;
import static br.com.connectpeople.adapters.repository.mapper.JobExperienceMapper.toJobExperienceList;
import static br.com.connectpeople.adapters.repository.mapper.SuperiorCourseMapper.toSuperiorCourseEntityList;
import static br.com.connectpeople.util.IdGenerator.generateId;

@Service
@RequiredArgsConstructor
public class RegisterResumeUseCase {

    private final ResumeJpaRepository resumeJpaRepository;
    private final JobExperienceJpaRepository jobExperienceJpaRepository;
    private final SuperiorCourseRepository superiorCourseRepository;
    private final CourseRepository courseRepository;

    private final ValidateAlreadyRegister validateAlreadyRegister;
    private final ValidateName validateName;
    private final ValidateBirthDate validateBirthDate;
    private final ValidatePhone validatePhone;
    private final ValidatePostalCode validatePostalCode;
    private final ValidateDistrict validateDistrict;
    private final ValidateCity validateCity;
    private final ValidateJobExperience validateJobExperience;
    private final ValidateSchooling validateSchooling;
    private final ErrorHandler errorHandler;

    @Transactional
    public Resume execute(Resume resume) {
        validateInput(buildInput(resume));
        String generatedId = getGeneratedId();
        resume.setCid(generatedId);
        resumeJpaRepository.save(resume.toEntity());
        saveJobExperience(resume);
        saveSuperiorCourse(resume);
        saveCourse(resume);
        return resume;
    }

    private void saveJobExperience(Resume resume) {
        var jobExperienceEntities = toJobExperienceList(resume.getJobExperiences());
        jobExperienceEntities.forEach(job -> job.setCid(resume.getCid()));
        jobExperienceJpaRepository.saveAll(jobExperienceEntities);
    }

    private void saveSuperiorCourse(Resume resume){
        var entityList = toSuperiorCourseEntityList(resume.getSuperiorCourses());
        entityList.forEach(course -> course.setCid(resume.getCid()));
        superiorCourseRepository.saveAll(entityList);
    }

    private void saveCourse(Resume resume){
        var entityList = toCourseEntityList(resume.getCourses());
        entityList.forEach(course -> course.setCid(resume.getCid()));
        courseRepository.saveAll(entityList);
    }

    private void validateInput(ResumePayload payload) {
        Executor<ResumePayload> executor = new Executor<>(payload);
        executor.chain(validateAlreadyRegister)
                .chain(validateName)
                .chain(validateBirthDate)
                .chain(validatePhone)
                .chain(validatePostalCode)
                .chain(validateDistrict)
                .chain(validateCity)
                .chain(validateJobExperience)
                .chain(validateSchooling)
                .chain(errorHandler);
    }

    private ResumePayload buildInput(Resume resume) {
        return ResumePayload.builder()
                .firstName(resume.getFirstName())
                .lastName(resume.getLastName())
                .birthDate(resume.getBirthDate())
                .gender(resume.getGender())
                .phone(resume.getPhone())
                .cellPhone(resume.getCellPhone())
                .email(resume.getEmail())
                .linkedin(resume.getLinkedin())
                .postalCode(resume.getPostalCode())
                .district(resume.getDistrict())
                .city(resume.getCity())
                .jobExperiences(resume.getJobExperiences())
                .schooling(resume.getSchooling())
                .superiorCourses(resume.getSuperiorCourses())
                .courses(resume.getCourses())
                .jobOptionOne(resume.getJobOptionOne())
                .jobOptionTwo(resume.getJobOptionTwo())
                .jobOptionThree(resume.getJobOptionThree())
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
