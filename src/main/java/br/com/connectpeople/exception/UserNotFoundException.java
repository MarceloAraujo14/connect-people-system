package br.com.connectpeople.exception;

import lombok.Getter;

import static br.com.connectpeople.constants.Constants.ErrorMessage.ERROR_MSG_USER_NOT_FOUND;

@Getter
public class UserNotFoundException extends RuntimeException{

    private final String message;

    public UserNotFoundException() {
        this.message = ERROR_MSG_USER_NOT_FOUND;
    }
}
