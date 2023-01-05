package br.com.connectpeople.resume.usecase.resume.executor;

import br.com.connectpeople.resume.domain.Course;
import br.com.connectpeople.resume.domain.JobExperience;
import br.com.connectpeople.resume.domain.SuperiorCourse;
import br.com.connectpeople.resume.domain.enums.Gender;
import br.com.connectpeople.resume.domain.enums.Schooling;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Builder
@Data
public class ResumePayload {

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

    private Map<String, String> errors;

    public void putError(String error, String message) {
        if (Objects.isNull(errors)) {
            this.errors = new HashMap<>();
            this.errors.put(error, message);
        } else {
            this.errors.put(error, message);
        }
    }

}
