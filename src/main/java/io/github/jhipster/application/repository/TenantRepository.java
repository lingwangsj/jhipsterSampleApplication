package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Tenant;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Tenant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long>, JpaSpecificationExecutor<Tenant> {

}
