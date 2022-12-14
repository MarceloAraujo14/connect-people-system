package br.com.connectpeople.api.request;

import br.com.connectpeople.resume.domain.JobExperience;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.domain.enums.Gender;
import br.com.connectpeople.resume.domain.enums.Schooling;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
@Data
public class ResumeRequest {

    private String name;
    private String birthDate;
    private Gender gender;
    private String phone;
    private String cellPhone;
    private String email;
    private String cep;
    private String district;
    private String jobOptionOne;
    private String jobOptionTwo;
    private String jobOptionThree;
    private List<JobExperience> jobExperiences;
    private Schooling schooling;

    public Resume toResume(){
        List<JobExperience> jobList = new ArrayList<>();
        jobExperiences.forEach(job -> {
            if(Objects.nonNull(job)) jobList.add(job);
        });

        return Resume.builder()
                .name(this.name)
                .birthDate(LocalDate.parse(this.birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .gender(this.gender)
                .phone(this.phone)
                .cellPhone(this.cellPhone)
                .email(this.email)
                .postalCode(this.cep)
                .district(this.district)
                .jobOptionOne(this.jobOptionOne)
                .jobOptionTwo(this.jobOptionTwo)
                .jobOptionThree(this.jobOptionThree)
                .jobExperiences(jobList)
                .schooling(this.schooling)
                .build();
    }
}
