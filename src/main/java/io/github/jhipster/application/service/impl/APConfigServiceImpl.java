package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.APConfigService;
import io.github.jhipster.application.domain.APConfig;
import io.github.jhipster.application.repository.APConfigRepository;
import io.github.jhipster.application.service.dto.APConfigDTO;
import io.github.jhipster.application.service.mapper.APConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing APConfig.
 */
@Service
@Transactional
public class APConfigServiceImpl implements APConfigService {

    private final Logger log = LoggerFactory.getLogger(APConfigServiceImpl.class);

    private final APConfigRepository aPConfigRepository;

    private final APConfigMapper aPConfigMapper;

    public APConfigServiceImpl(APConfigRepository aPConfigRepository, APConfigMapper aPConfigMapper) {
        this.aPConfigRepository = aPConfigRepository;
        this.aPConfigMapper = aPConfigMapper;
    }

    /**
     * Save a aPConfig.
     *
     * @param aPConfigDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public APConfigDTO save(APConfigDTO aPConfigDTO) {
        log.debug("Request to save APConfig : {}", aPConfigDTO);
        APConfig aPConfig = aPConfigMapper.toEntity(aPConfigDTO);
        aPConfig = aPConfigRepository.save(aPConfig);
        return aPConfigMapper.toDto(aPConfig);
    }

    /**
     * Get all the aPConfigs.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<APConfigDTO> findAll() {
        log.debug("Request to get all APConfigs");
        return aPConfigRepository.findAllWithEagerRelationships().stream()
            .map(aPConfigMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one aPConfig by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public APConfigDTO findOne(Long id) {
        log.debug("Request to get APConfig : {}", id);
        APConfig aPConfig = aPConfigRepository.findOneWithEagerRelationships(id);
        return aPConfigMapper.toDto(aPConfig);
    }

    /**
     * Delete the aPConfig by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete APConfig : {}", id);
        aPConfigRepository.delete(id);
    }
}
