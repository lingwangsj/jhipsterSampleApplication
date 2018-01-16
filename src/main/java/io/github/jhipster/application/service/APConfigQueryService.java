package io.github.jhipster.application.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.github.jhipster.application.domain.APConfig;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.APConfigRepository;
import io.github.jhipster.application.service.dto.APConfigCriteria;


/**
 * Service for executing complex queries for APConfig entities in the database.
 * The main input is a {@link APConfigCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link APConfig} or a {@link Page} of {@link APConfig} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class APConfigQueryService extends QueryService<APConfig> {

    private final Logger log = LoggerFactory.getLogger(APConfigQueryService.class);


    private final APConfigRepository aPConfigRepository;

    public APConfigQueryService(APConfigRepository aPConfigRepository) {
        this.aPConfigRepository = aPConfigRepository;
    }

    /**
     * Return a {@link List} of {@link APConfig} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<APConfig> findByCriteria(APConfigCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<APConfig> specification = createSpecification(criteria);
        return aPConfigRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link APConfig} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<APConfig> findByCriteria(APConfigCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<APConfig> specification = createSpecification(criteria);
        return aPConfigRepository.findAll(specification, page);
    }

    /**
     * Function to convert APConfigCriteria to a {@link Specifications}
     */
    private Specifications<APConfig> createSpecification(APConfigCriteria criteria) {
        Specifications<APConfig> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), APConfig_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), APConfig_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), APConfig_.description));
            }
            if (criteria.getTagsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTagsId(), APConfig_.tags, Tag_.id));
            }
        }
        return specification;
    }

}
