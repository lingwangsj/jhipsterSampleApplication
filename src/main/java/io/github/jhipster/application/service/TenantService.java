package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.TenantDTO;
import java.util.List;

/**
 * Service Interface for managing Tenant.
 */
public interface TenantService {

    /**
     * Save a tenant.
     *
     * @param tenantDTO the entity to save
     * @return the persisted entity
     */
    TenantDTO save(TenantDTO tenantDTO);

    /**
     * Get all the tenants.
     *
     * @return the list of entities
     */
    List<TenantDTO> findAll();

    /**
     * Get the "id" tenant.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TenantDTO findOne(Long id);

    /**
     * Delete the "id" tenant.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
