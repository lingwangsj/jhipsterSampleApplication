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

import io.github.jhipster.application.domain.Venue;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.VenueRepository;
import io.github.jhipster.application.service.dto.VenueCriteria;


/**
 * Service for executing complex queries for Venue entities in the database.
 * The main input is a {@link VenueCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Venue} or a {@link Page} of {@link Venue} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VenueQueryService extends QueryService<Venue> {

    private final Logger log = LoggerFactory.getLogger(VenueQueryService.class);


    private final VenueRepository venueRepository;

    public VenueQueryService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    /**
     * Return a {@link List} of {@link Venue} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Venue> findByCriteria(VenueCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Venue> specification = createSpecification(criteria);
        return venueRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Venue} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Venue> findByCriteria(VenueCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Venue> specification = createSpecification(criteria);
        return venueRepository.findAll(specification, page);
    }

    /**
     * Function to convert VenueCriteria to a {@link Specifications}
     */
    private Specifications<Venue> createSpecification(VenueCriteria criteria) {
        Specifications<Venue> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Venue_.id));
            }
            if (criteria.getVenueName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVenueName(), Venue_.venueName));
            }
            if (criteria.getOwnedAPId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOwnedAPId(), Venue_.ownedAPS, AP_.id));
            }
            if (criteria.getTenantId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTenantId(), Venue_.tenant, Tenant_.id));
            }
        }
        return specification;
    }

}
