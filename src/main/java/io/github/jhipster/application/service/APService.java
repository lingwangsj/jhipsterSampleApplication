package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.AP;
import io.github.jhipster.application.repository.APRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing AP.
 */
@Service
@Transactional
public class APService {

    private final Logger log = LoggerFactory.getLogger(APService.class);

    private final APRepository aPRepository;

    public APService(APRepository aPRepository) {
        this.aPRepository = aPRepository;
    }

    /**
     * Save a aP.
     *
     * @param aP the entity to save
     * @return the persisted entity
     */
    public AP save(AP aP) {
        log.debug("Request to save AP : {}", aP);
        return aPRepository.save(aP);
    }

    /**
     * Get all the aPS.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<AP> findAll() {
        log.debug("Request to get all APS");
        return aPRepository.findAllWithEagerRelationships();
    }

    /**
     * Get one aP by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public AP findOne(Long id) {
        log.debug("Request to get AP : {}", id);
        return aPRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the aP by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete AP : {}", id);
        aPRepository.delete(id);
    }
}
