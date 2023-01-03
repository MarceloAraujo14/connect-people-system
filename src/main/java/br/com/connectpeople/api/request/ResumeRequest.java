package br.com.connectpeople.api.request;

import br.com.connectpeople.resume.domain.Course;
import br.com.connectpeople.resume.domain.JobExperience;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.domain.SuperiorCourse;
import br.com.connectpeople.resume.domain.enums.Gender;
import br.com.connectpeople.resume.domain.enums.Schooling;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_EMAIL_INVALID;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_SELECT_OPTION;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeRequest {

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String firstName;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String lastName;

    @NotBlank(message = ERROR_MSG_SELECT_OPTION)
    private String gender;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String birthDate;

    private String phone;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String cellPhone;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    @Email(message = ERROR_MSG_EMAIL_INVALID)
    private String email;

    private String linkedin;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String postalCode;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String district;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String city;

    private List<JobExperience> jobExperiences;

    @NotBlank(message = ERROR_MSG_SELECT_OPTION)
    private String schooling;

    private List<SuperiorCourse> superiorCourses;

    private List<Course> courses;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String jobOptionOne;

    private String jobOptionTwo;

    private String jobOptionThree;



    public Resume toResume() {
        List<JobExperience> jobList = new ArrayList<>();
        jobExperiences.forEach(job -> {
            if (Objects.nonNull(job)) jobList.add(job);
        });

        return Resume.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .birthDate(this.birthDate)
                .gender(this.gender)
                .phone(this.phone)
                .cellPhone(this.cellPhone)
                .email(this.email)
                .linkedin(this.linkedin)
                .postalCode(this.postalCode)
                .district(this.district)
                .city(this.city)
                .jobExperiences(jobList)
                .schooling(this.schooling)
                .courses(this.courses)
                .superiorCourses(this.superiorCourses)
                .jobOptionOne(this.jobOptionOne)
                .jobOptionTwo(this.jobOptionTwo)
                .jobOptionThree(this.jobOptionThree)
                .build();
    }

}
