package br.com.connectpeople.adapters.repository;

import br.com.connectpeople.adapters.repository.entity.JobExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobExperienceJpaRepository extends JpaRepository<JobExperienceEntity, Long> {

    List<JobExperienceEntity> findByResumeId(Long resumeId);

}
