package br.com.connectpeople.resume.domain;

import br.com.connectpeople.resume.domain.enums.SuperiorCourseStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuperiorCourse {

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String institution;
    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String course;
    private SuperiorCourseStatus status;
    private int conclusionYear;

}
