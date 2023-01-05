package br.com.connectpeople.resume.repository;

import br.com.connectpeople.resume.repository.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResumeJpaRepository extends JpaRepository<ResumeEntity, Long> {

    boolean existsByCid(String cid);

    Optional<ResumeEntity> findByEmail(String email);

    boolean existsByEmail(String email);

}
