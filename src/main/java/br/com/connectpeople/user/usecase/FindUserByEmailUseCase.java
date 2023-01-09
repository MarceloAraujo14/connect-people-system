package br.com.connectpeople.user.usecase;

import br.com.connectpeople.user.repository.UserRepository;
import br.com.connectpeople.commons.exception.UserNotFoundException;
import br.com.connectpeople.user.domain.User;
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
