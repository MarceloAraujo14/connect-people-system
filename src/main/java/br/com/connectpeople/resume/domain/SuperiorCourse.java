package br.com.connectpeople.resume.domain;

import br.com.connectpeople.resume.domain.enums.SuperiorCourseStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;

@Builder
@Data
public class SuperiorCourse {

    private Long id;
    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String institution;
    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String course;
    private SuperiorCourseStatus status;
    private LocalDate conclusionYear;

}
