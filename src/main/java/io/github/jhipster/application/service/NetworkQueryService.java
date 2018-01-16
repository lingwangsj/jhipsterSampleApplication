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

import io.github.jhipster.application.domain.Network;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.NetworkRepository;
import io.github.jhipster.application.service.dto.NetworkCriteria;


/**
 * Service for executing complex queries for Network entities in the database.
 * The main input is a {@link NetworkCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Network} or a {@link Page} of {@link Network} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NetworkQueryService extends QueryService<Network> {

    private final Logger log = LoggerFactory.getLogger(NetworkQueryService.class);


    private final NetworkRepository networkRepository;

    public NetworkQueryService(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    /**
     * Return a {@link List} of {@link Network} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Network> findByCriteria(NetworkCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Network> specification = createSpecification(criteria);
        return networkRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Network} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Network> findByCriteria(NetworkCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Network> specification = createSpecification(criteria);
        return networkRepository.findAll(specification, page);
    }

    /**
     * Function to convert NetworkCriteria to a {@link Specifications}
     */
    private Specifications<Network> createSpecification(NetworkCriteria criteria) {
        Specifications<Network> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Network_.id));
            }
            if (criteria.getNetworkName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNetworkName(), Network_.networkName));
            }
            if (criteria.getSsid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSsid(), Network_.ssid));
            }
            if (criteria.getTagsId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTagsId(), Network_.tags, Tag_.id));
            }
            if (criteria.getTenantId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTenantId(), Network_.tenant, Tenant_.id));
            }
        }
        return specification;
    }

}
