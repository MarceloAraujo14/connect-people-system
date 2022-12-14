package br.com.connectpeople.resume.usecase;

import br.com.connectpeople.adapters.repository.ResumeJpaRepository;
import br.com.connectpeople.adapters.repository.entity.ResumeEntity;
import br.com.connectpeople.resume.domain.Resume;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterResume {

    private final ResumeJpaRepository resumeJpaRepository;

    public Resume execute(Resume resume){
        ResumeEntity resumeEntity = resumeJpaRepository.save(resume.toEntity());
        return resumeEntity.toResume();
    }

}
