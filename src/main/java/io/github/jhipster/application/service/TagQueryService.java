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

import io.github.jhipster.application.domain.Tag;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.TagRepository;
import io.github.jhipster.application.service.dto.TagCriteria;


/**
 * Service for executing complex queries for Tag entities in the database.
 * The main input is a {@link TagCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Tag} or a {@link Page} of {@link Tag} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TagQueryService extends QueryService<Tag> {

    private final Logger log = LoggerFactory.getLogger(TagQueryService.class);


    private final TagRepository tagRepository;

    public TagQueryService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    /**
     * Return a {@link List} of {@link Tag} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Tag> findByCriteria(TagCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Tag> specification = createSpecification(criteria);
        return tagRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Tag} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Tag> findByCriteria(TagCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Tag> specification = createSpecification(criteria);
        return tagRepository.findAll(specification, page);
    }

    /**
     * Function to convert TagCriteria to a {@link Specifications}
     */
    private Specifications<Tag> createSpecification(TagCriteria criteria) {
        Specifications<Tag> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Tag_.id));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), Tag_.type));
            }
            if (criteria.getLabel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLabel(), Tag_.label));
            }
            if (criteria.getTenantId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTenantId(), Tag_.tenant, Tenant_.id));
            }
            if (criteria.getApId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getApId(), Tag_.aps, AP_.id));
            }
            if (criteria.getNetworkId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getNetworkId(), Tag_.networks, Network_.id));
            }
            if (criteria.getApConfigId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getApConfigId(), Tag_.apConfigs, APConfig_.id));
            }
        }
        return specification;
    }

}
