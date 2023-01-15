package br.com.connectpeople.security.api;

import br.com.connectpeople.security.usecase.AuthenticateUseCase;
import br.com.connectpeople.security.usecase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationResource {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticateUseCase authenticateUseCase;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody AuthenticationRequest user){
        return ResponseEntity.ok(registerUserUseCase.execute(user.toUser()));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest user){
        return ResponseEntity.ok(authenticateUseCase.execute(user.toUser()));
    }
}
