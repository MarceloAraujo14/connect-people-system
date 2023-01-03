package br.com.connectpeople.resume.domain;

import br.com.connectpeople.adapters.repository.entity.ResumeEntity;
import br.com.connectpeople.resume.domain.enums.Gender;
import br.com.connectpeople.resume.domain.enums.Schooling;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
public class Resume {
    private String cid;

    private String firstName;

    private String lastName;

    private String gender;

    private String birthDate;

    private String phone;

    private String cellPhone;

    private String email;

    private String linkedin;

    private String postalCode;

    private String district;

    private String city;

    private List<JobExperience> jobExperiences;

    private String schooling;

    private List<SuperiorCourse> superiorCourses;

    private List<Course> courses;

    private String jobOptionOne;

    private String jobOptionTwo;

    private String jobOptionThree;

    public ResumeEntity toEntity() {
        return ResumeEntity.builder()
                .cid(this.cid)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .birthDate(LocalDate.parse(this.birthDate))
                .gender(Gender.valueOf(this.gender))
                .phone(this.phone)
                .cellPhone(this.cellPhone)
                .email(this.email)
                .linkedin(this.linkedin)
                .postalCode(this.postalCode)
                .district(this.district)
                .schooling(Schooling.valueOf(this.schooling))
                .jobOptionOne(this.jobOptionOne)
                .jobOptionTwo(this.jobOptionTwo)
                .jobOptionThree(this.jobOptionThree)
                .build();
    }
}
