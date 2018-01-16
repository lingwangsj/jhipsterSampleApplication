package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.APDTO;
import java.util.List;

/**
 * Service Interface for managing AP.
 */
public interface APService {

    /**
     * Save a aP.
     *
     * @param aPDTO the entity to save
     * @return the persisted entity
     */
    APDTO save(APDTO aPDTO);

    /**
     * Get all the aPS.
     *
     * @return the list of entities
     */
    List<APDTO> findAll();

    /**
     * Get the "id" aP.
     *
     * @param id the id of the entity
     * @return the entity
     */
    APDTO findOne(Long id);

    /**
     * Delete the "id" aP.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
