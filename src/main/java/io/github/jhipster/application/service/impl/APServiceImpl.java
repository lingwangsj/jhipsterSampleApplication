package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.APService;
import io.github.jhipster.application.domain.AP;
import io.github.jhipster.application.repository.APRepository;
import io.github.jhipster.application.service.dto.APDTO;
import io.github.jhipster.application.service.mapper.APMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing AP.
 */
@Service
@Transactional
public class APServiceImpl implements APService {

    private final Logger log = LoggerFactory.getLogger(APServiceImpl.class);

    private final APRepository aPRepository;

    private final APMapper aPMapper;

    public APServiceImpl(APRepository aPRepository, APMapper aPMapper) {
        this.aPRepository = aPRepository;
        this.aPMapper = aPMapper;
    }

    /**
     * Save a aP.
     *
     * @param aPDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public APDTO save(APDTO aPDTO) {
        log.debug("Request to save AP : {}", aPDTO);
        AP aP = aPMapper.toEntity(aPDTO);
        aP = aPRepository.save(aP);
        return aPMapper.toDto(aP);
    }

    /**
     * Get all the aPS.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<APDTO> findAll() {
        log.debug("Request to get all APS");
        return aPRepository.findAllWithEagerRelationships().stream()
            .map(aPMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one aP by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public APDTO findOne(Long id) {
        log.debug("Request to get AP : {}", id);
        AP aP = aPRepository.findOneWithEagerRelationships(id);
        return aPMapper.toDto(aP);
    }

    /**
     * Delete the aP by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AP : {}", id);
        aPRepository.delete(id);
    }
}
