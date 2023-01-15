package br.com.connectpeople.security.api;

import br.com.connectpeople.security.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_EMAIL_INVALID;
import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_FIELD_CANNOT_BE_EMPTY;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    @Email(message = ERROR_MSG_EMAIL_INVALID)
    private String email;
    @NotBlank(message = ERROR_MSG_FIELD_CANNOT_BE_EMPTY)
    private String password;

    public User toUser(){
        return User.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }

}
