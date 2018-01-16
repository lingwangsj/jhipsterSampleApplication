package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Network;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Network entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NetworkRepository extends JpaRepository<Network, Long> {
    @Query("select distinct network from Network network left join fetch network.tags")
    List<Network> findAllWithEagerRelationships();

    @Query("select network from Network network left join fetch network.tags where network.id =:id")
    Network findOneWithEagerRelationships(@Param("id") Long id);

}
