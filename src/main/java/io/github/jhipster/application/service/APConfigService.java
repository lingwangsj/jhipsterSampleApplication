package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.APConfig;
import io.github.jhipster.application.repository.APConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing APConfig.
 */
@Service
@Transactional
public class APConfigService {

    private final Logger log = LoggerFactory.getLogger(APConfigService.class);

    private final APConfigRepository aPConfigRepository;

    public APConfigService(APConfigRepository aPConfigRepository) {
        this.aPConfigRepository = aPConfigRepository;
    }

    /**
     * Save a aPConfig.
     *
     * @param aPConfig the entity to save
     * @return the persisted entity
     */
    public APConfig save(APConfig aPConfig) {
        log.debug("Request to save APConfig : {}", aPConfig);
        return aPConfigRepository.save(aPConfig);
    }

    /**
     * Get all the aPConfigs.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<APConfig> findAll() {
        log.debug("Request to get all APConfigs");
        return aPConfigRepository.findAllWithEagerRelationships();
    }

    /**
     * Get one aPConfig by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public APConfig findOne(Long id) {
        log.debug("Request to get APConfig : {}", id);
        return aPConfigRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the aPConfig by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete APConfig : {}", id);
        aPConfigRepository.delete(id);
    }
}
