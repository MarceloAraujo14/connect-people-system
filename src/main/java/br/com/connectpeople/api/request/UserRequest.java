package br.com.connectpeople.api.request;

import br.com.connectpeople.user.domain.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_EMAIL_INVALID;
import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;

@Builder
@Data
public class UserRequest {

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    @Email(message = ERROR_MSG_EMAIL_INVALID)
    private String email;
    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String password;
    private Role role;

}
