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

import io.github.jhipster.application.domain.AP;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.APRepository;
import io.github.jhipster.application.service.dto.APCriteria;


/**
 * Service for executing complex queries for AP entities in the database.
 * The main input is a {@link APCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AP} or a {@link Page} of {@link AP} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class APQueryService extends QueryService<AP> {

    private final Logger log = LoggerFactory.getLogger(APQueryService.class);


    private final APRepository aPRepository;

    public APQueryService(APRepository aPRepository) {
        this.aPRepository = aPRepository;
    }

    /**
     * Return a {@link List} of {@link AP} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AP> findByCriteria(APCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<AP> specification = createSpecification(criteria);
        return aPRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link AP} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AP> findByCriteria(APCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<AP> specification = createSpecification(criteria);
        return aPRepository.findAll(specification, page);
    }

    /**
     * Function to convert APCriteria to a {@link Specifications}
     */
    private Specifications<AP> createSpecification(APCriteria criteria) {
        Specifications<AP> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AP_.id));
            }
            if (criteria.getSerialNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerialNumber(), AP_.serialNumber));
            }
            if (criteria.getApName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getApName(), AP_.apName));
            }
            if (criteria.getTagsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTagsId(), AP_.tags, Tag_.id));
            }
            if (criteria.getVenueId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getVenueId(), AP_.venue, Venue_.id));
            }
        }
        return specification;
    }

}
