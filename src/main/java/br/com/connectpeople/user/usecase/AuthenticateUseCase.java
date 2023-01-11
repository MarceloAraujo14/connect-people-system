package br.com.connectpeople.user.usecase;

import br.com.connectpeople.commons.exception.UserNotFoundException;
import br.com.connectpeople.security.JwtService;
import br.com.connectpeople.user.api.AuthenticationResponse;

import br.com.connectpeople.user.domain.User;
import br.com.connectpeople.user.repository.UserRepository;
import br.com.connectpeople.user.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateUseCase {

    private final JwtService jwtService;
    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse execute(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findById(request.getEmail()).map(UserEntity::toUser)
                .orElseThrow(UserNotFoundException::new);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
