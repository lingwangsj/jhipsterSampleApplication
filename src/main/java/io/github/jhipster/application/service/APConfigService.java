package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.APConfigDTO;
import java.util.List;

/**
 * Service Interface for managing APConfig.
 */
public interface APConfigService {

    /**
     * Save a aPConfig.
     *
     * @param aPConfigDTO the entity to save
     * @return the persisted entity
     */
    APConfigDTO save(APConfigDTO aPConfigDTO);

    /**
     * Get all the aPConfigs.
     *
     * @return the list of entities
     */
    List<APConfigDTO> findAll();

    /**
     * Get the "id" aPConfig.
     *
     * @param id the id of the entity
     * @return the entity
     */
    APConfigDTO findOne(Long id);

    /**
     * Delete the "id" aPConfig.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
