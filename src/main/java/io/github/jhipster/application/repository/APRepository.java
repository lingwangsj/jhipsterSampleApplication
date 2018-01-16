package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AP;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the AP entity.
 */
@SuppressWarnings("unused")
@Repository
public interface APRepository extends JpaRepository<AP, Long>, JpaSpecificationExecutor<AP> {
    @Query("select distinct ap from AP ap left join fetch ap.tags")
    List<AP> findAllWithEagerRelationships();

    @Query("select ap from AP ap left join fetch ap.tags where ap.id =:id")
    AP findOneWithEagerRelationships(@Param("id") Long id);

}
