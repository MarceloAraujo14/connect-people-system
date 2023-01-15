package br.com.connectpeople.security.usecase;

import br.com.connectpeople.security.domain.User;
import br.com.connectpeople.security.repository.UserRepository;
import br.com.connectpeople.commons.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserByEmailUseCase {

    private final UserRepository userRepository;

    public User execute(String email){
        return userRepository.findById(email)
                .orElseThrow(UserNotFoundException::new).toUser();
    }
}
