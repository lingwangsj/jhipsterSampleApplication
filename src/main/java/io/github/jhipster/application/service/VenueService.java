package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Venue;
import io.github.jhipster.application.repository.VenueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Venue.
 */
@Service
@Transactional
public class VenueService {

    private final Logger log = LoggerFactory.getLogger(VenueService.class);

    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    /**
     * Save a venue.
     *
     * @param venue the entity to save
     * @return the persisted entity
     */
    public Venue save(Venue venue) {
        log.debug("Request to save Venue : {}", venue);
        return venueRepository.save(venue);
    }

    /**
     * Get all the venues.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Venue> findAll() {
        log.debug("Request to get all Venues");
        return venueRepository.findAll();
    }

    /**
     * Get one venue by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Venue findOne(Long id) {
        log.debug("Request to get Venue : {}", id);
        return venueRepository.findOne(id);
    }

    /**
     * Delete the venue by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Venue : {}", id);
        venueRepository.delete(id);
    }
}
