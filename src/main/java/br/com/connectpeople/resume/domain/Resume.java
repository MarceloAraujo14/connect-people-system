package br.com.connectpeople.resume.domain;

import br.com.connectpeople.adapters.repository.entity.ResumeEntity;
import br.com.connectpeople.jobexperience.domain.JobExperience;
import br.com.connectpeople.resume.domain.enums.Gender;
import br.com.connectpeople.resume.domain.enums.Schooling;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Data
public class Resume {
    private String cid;
    private String name;
    private String birthDate;
    private Gender gender;
    private String phone;
    private String cellPhone;
    private String email;
    private String postalCode;
    private String district;
    private String jobOptionOne;
    private String jobOptionTwo;
    private String jobOptionThree;
    private List<JobExperience> jobExperiences;
    private Schooling schooling;

    public ResumeEntity toEntity() {
        return ResumeEntity.builder()
                .cid(this.cid)
                .name(this.name)
                .birthDate(LocalDate.parse(this.birthDate, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .gender(this.gender)
                .phone(this.phone)
                .cellPhone(this.cellPhone)
                .email(this.email)
                .postalCode(this.postalCode)
                .district(this.district)
                .jobOptionOne(this.jobOptionOne)
                .jobOptionTwo(this.jobOptionTwo)
                .jobOptionThree(this.jobOptionThree)
                .schooling(this.schooling)
                .build();
    }
}
