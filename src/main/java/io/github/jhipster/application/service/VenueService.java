package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.VenueDTO;
import java.util.List;

/**
 * Service Interface for managing Venue.
 */
public interface VenueService {

    /**
     * Save a venue.
     *
     * @param venueDTO the entity to save
     * @return the persisted entity
     */
    VenueDTO save(VenueDTO venueDTO);

    /**
     * Get all the venues.
     *
     * @return the list of entities
     */
    List<VenueDTO> findAll();

    /**
     * Get the "id" venue.
     *
     * @param id the id of the entity
     * @return the entity
     */
    VenueDTO findOne(Long id);

    /**
     * Delete the "id" venue.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
