package br.com.connectpeople.user.api;

import br.com.connectpeople.user.domain.User;
import br.com.connectpeople.user.usecase.AuthenticateUseCase;
import br.com.connectpeople.user.usecase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserResource {

    private final RegisterUserUseCase registerUserUseCase;

    private final AuthenticateUseCase authenticateUseCase;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody User user){
        return ResponseEntity.ok(registerUserUseCase.execute(user));
    }

    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody User user){
        return ResponseEntity.ok(authenticateUseCase.execute(user));
    }
}
