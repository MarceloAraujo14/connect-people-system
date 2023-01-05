package br.com.connectpeople.resume.repository;

import br.com.connectpeople.resume.repository.entity.JobExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobExperienceJpaRepository extends JpaRepository<JobExperienceEntity, Long> {

    List<JobExperienceEntity> findByCid(Long cid);

}
