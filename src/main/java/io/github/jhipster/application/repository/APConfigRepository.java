package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.APConfig;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the APConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface APConfigRepository extends JpaRepository<APConfig, Long> {
    @Query("select distinct ap_config from APConfig ap_config left join fetch ap_config.tags")
    List<APConfig> findAllWithEagerRelationships();

    @Query("select ap_config from APConfig ap_config left join fetch ap_config.tags where ap_config.id =:id")
    APConfig findOneWithEagerRelationships(@Param("id") Long id);

}
