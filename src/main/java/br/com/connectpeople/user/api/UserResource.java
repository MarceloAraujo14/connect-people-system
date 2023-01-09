package br.com.connectpeople.user.api;

import br.com.connectpeople.user.domain.User;
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

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user){
        return ResponseEntity.ok(registerUserUseCase.execute(user));
    }
}
