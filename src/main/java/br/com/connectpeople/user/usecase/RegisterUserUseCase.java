package br.com.connectpeople.user.usecase;

import br.com.connectpeople.security.JwtService;
import br.com.connectpeople.user.api.AuthenticationResponse;
import br.com.connectpeople.user.repository.UserRepository;
import br.com.connectpeople.commons.exception.RegisterAlreadyExistsException;
import br.com.connectpeople.commons.exception.WeakPasswordException;
import br.com.connectpeople.user.domain.Role;
import br.com.connectpeople.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_EMAIL_ALREADY_REGISTER;
import static br.com.connectpeople.commons.constants.Constants.ErrorMessage.ERROR_MSG_WEAK_PASSWORD;

@Service
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse execute(User user){

        duplicatedEmailValidate(user.getEmail());
        weakPasswordValidate(user.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user.toEntity());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    private void duplicatedEmailValidate(String email){
       if(userRepository.findById(email).isPresent())
           throw new RegisterAlreadyExistsException("email", ERROR_MSG_EMAIL_ALREADY_REGISTER);
    }

    private void weakPasswordValidate(String password){
        /*
        6 characters length
        1 letters in Upper Case
        1 Special Character (!@#$&*)
        1 numerals (0-9)
        1 letters in Lower Case
         */
        if (password.matches("^(?=.[a-z])(?=.[A-Z])(?=.\\d)(?=.[!@#$%^&*]).{6,}"))
            throw new WeakPasswordException("password", ERROR_MSG_WEAK_PASSWORD);
    }

}
