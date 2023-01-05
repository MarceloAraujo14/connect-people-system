package br.com.connectpeople.api.request;

import br.com.connectpeople.resume.domain.Course;
import br.com.connectpeople.resume.domain.JobExperience;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.domain.SuperiorCourse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(max = 20, message = "Número de caracteres máximo excedido.")
    private String firstName;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    @Size(max = 60, message = "Número de caracteres máximo excedido.")
    private String lastName;

    @NotBlank(message = ERROR_MSG_SELECT_OPTION)
    private String gender;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String birthDate;

    private String phone;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String cellPhone;

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    @Size(max = 80, message = "Número de caracteres máximo excedido.")
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

    @NotBlank(message = ERROR_MSG_SELECT_OPTION)
    private String jobOptionOne;

    private String jobOptionTwo;

    private String jobOptionThree;

    public Resume toResume() {
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
                .jobExperiences(this.jobExperiences)
                .schooling(this.schooling)
                .courses(this.courses)
                .superiorCourses(this.superiorCourses)
                .jobOptionOne(this.jobOptionOne)
                .jobOptionTwo(this.jobOptionTwo)
                .jobOptionThree(this.jobOptionThree)
                .build();
    }
}
