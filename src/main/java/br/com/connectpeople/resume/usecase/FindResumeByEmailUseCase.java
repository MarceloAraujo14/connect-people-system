package br.com.connectpeople.resume.usecase;

import br.com.connectpeople.adapters.repository.ResumeJpaRepository;
import br.com.connectpeople.resume.domain.Resume;
import br.com.connectpeople.resume.domain.exception.ResumeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FindResumeByEmailUseCase {

    private final ResumeJpaRepository resumeJpaRepository;

    public Resume execute(String email){
        return resumeJpaRepository.findByEmail(email).orElseThrow(ResumeNotFoundException::new).toResume();
    }

}
