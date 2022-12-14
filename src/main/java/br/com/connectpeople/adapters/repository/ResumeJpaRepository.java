package br.com.connectpeople.adapters.repository;

import br.com.connectpeople.adapters.repository.entity.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeJpaRepository extends JpaRepository<ResumeEntity, Long> {

    boolean existsById(String id);

}
