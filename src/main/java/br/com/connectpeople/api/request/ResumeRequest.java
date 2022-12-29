package br.com.connectpeople.api.request;

import br.com.connectpeople.jobexperience.domain.JobExperience;
import br.com.connectpeople.resume.domain.Resume;
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
    private String name;
    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String birthDate;
    @NotBlank(message = ERROR_MSG_SELECT_OPTION)
    private String gender;
    private String phone;
    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String cellPhone;
    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    @Email(message = ERROR_MSG_EMAIL_INVALID)
    private String email;
    private String linkedin;
    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String postalCode;
    private String district;
    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String jobOptionOne;
    private String jobOptionTwo;
    private String jobOptionThree;
    private List<JobExperience> jobExperiences;
    @NotBlank(message = ERROR_MSG_SELECT_OPTION)
    private String schooling;


    public Resume toResume() {
        List<JobExperience> jobList = new ArrayList<>();
        jobExperiences.forEach(job -> {
            if (Objects.nonNull(job)) jobList.add(job);
        });

        return Resume.builder()
                .name(this.name)
                .birthDate(this.birthDate)
                .gender(Gender.valueOf(this.gender))
                .phone(this.phone)
                .cellPhone(this.cellPhone)
                .email(this.email)
                .postalCode(this.postalCode)
                .district(this.district)
                .jobOptionOne(this.jobOptionOne)
                .jobOptionTwo(this.jobOptionTwo)
                .jobOptionThree(this.jobOptionThree)
                .jobExperiences(jobList)
                .schooling(Schooling.valueOf(this.schooling))
                .build();
    }

}
