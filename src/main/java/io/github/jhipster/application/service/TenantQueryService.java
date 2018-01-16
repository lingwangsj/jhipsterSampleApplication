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

import io.github.jhipster.application.domain.Tenant;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.TenantRepository;
import io.github.jhipster.application.service.dto.TenantCriteria;


/**
 * Service for executing complex queries for Tenant entities in the database.
 * The main input is a {@link TenantCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Tenant} or a {@link Page} of {@link Tenant} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TenantQueryService extends QueryService<Tenant> {

    private final Logger log = LoggerFactory.getLogger(TenantQueryService.class);


    private final TenantRepository tenantRepository;

    public TenantQueryService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    /**
     * Return a {@link List} of {@link Tenant} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Tenant> findByCriteria(TenantCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Tenant> specification = createSpecification(criteria);
        return tenantRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Tenant} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Tenant> findByCriteria(TenantCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Tenant> specification = createSpecification(criteria);
        return tenantRepository.findAll(specification, page);
    }

    /**
     * Function to convert TenantCriteria to a {@link Specifications}
     */
    private Specifications<Tenant> createSpecification(TenantCriteria criteria) {
        Specifications<Tenant> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Tenant_.id));
            }
            if (criteria.getTenantName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenantName(), Tenant_.tenantName));
            }
            if (criteria.getOwnedVenueId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOwnedVenueId(), Tenant_.ownedVenues, Venue_.id));
            }
            if (criteria.getOwnedNetworkId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOwnedNetworkId(), Tenant_.ownedNetworks, Network_.id));
            }
            if (criteria.getOwnedTagId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getOwnedTagId(), Tenant_.ownedTags, Tag_.id));
            }
        }
        return specification;
    }

}
