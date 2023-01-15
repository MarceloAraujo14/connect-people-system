package br.com.connectpeople.user.usecase;

import br.com.connectpeople.security.usecase.JwtService;
import br.com.connectpeople.security.repository.UserRepository;
import br.com.connectpeople.security.repository.entity.UserEntity;
import br.com.connectpeople.security.domain.Role;
import br.com.connectpeople.security.domain.User;
import br.com.connectpeople.security.usecase.RegisterUserUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterUserUseCaseTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    JwtService jwtService;
    @InjectMocks
    RegisterUserUseCase registerUserUseCase;
    @Captor
    ArgumentCaptor<UserEntity> userEntityCaptor;

    @Test
    void shouldValidateAndSaveUser(){
        var user = getUser();
        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());
        when(userRepository.findById(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(user.toEntity());

        registerUserUseCase.execute(user);

        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verify(userRepository, times(1)).findById(user.getEmail());
        verify(userRepository, times(1)).save(userEntityCaptor.capture());
        assertEquals(Role.USER, userEntityCaptor.getValue().getRole());

    }

    public static User getUser(){
        return User.builder()
                .email("jhondoe@gmail.com")
                .password("Abc@123")
                .build();
    }

}