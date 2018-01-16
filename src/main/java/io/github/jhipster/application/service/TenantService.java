package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Tenant;
import io.github.jhipster.application.repository.TenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Tenant.
 */
@Service
@Transactional
public class TenantService {

    private final Logger log = LoggerFactory.getLogger(TenantService.class);

    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    /**
     * Save a tenant.
     *
     * @param tenant the entity to save
     * @return the persisted entity
     */
    public Tenant save(Tenant tenant) {
        log.debug("Request to save Tenant : {}", tenant);
        return tenantRepository.save(tenant);
    }

    /**
     * Get all the tenants.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Tenant> findAll() {
        log.debug("Request to get all Tenants");
        return tenantRepository.findAll();
    }

    /**
     * Get one tenant by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Tenant findOne(Long id) {
        log.debug("Request to get Tenant : {}", id);
        return tenantRepository.findOne(id);
    }

    /**
     * Delete the tenant by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Tenant : {}", id);
        tenantRepository.delete(id);
    }
}
