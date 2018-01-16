package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Venue;
import io.github.jhipster.application.service.VenueService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.VenueCriteria;
import io.github.jhipster.application.service.VenueQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Venue.
 */
@RestController
@RequestMapping("/api")
public class VenueResource {

    private final Logger log = LoggerFactory.getLogger(VenueResource.class);

    private static final String ENTITY_NAME = "venue";

    private final VenueService venueService;

    private final VenueQueryService venueQueryService;

    public VenueResource(VenueService venueService, VenueQueryService venueQueryService) {
        this.venueService = venueService;
        this.venueQueryService = venueQueryService;
    }

    /**
     * POST  /venues : Create a new venue.
     *
     * @param venue the venue to create
     * @return the ResponseEntity with status 201 (Created) and with body the new venue, or with status 400 (Bad Request) if the venue has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/venues")
    @Timed
    public ResponseEntity<Venue> createVenue(@Valid @RequestBody Venue venue) throws URISyntaxException {
        log.debug("REST request to save Venue : {}", venue);
        if (venue.getId() != null) {
            throw new BadRequestAlertException("A new venue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Venue result = venueService.save(venue);
        return ResponseEntity.created(new URI("/api/venues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /venues : Updates an existing venue.
     *
     * @param venue the venue to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated venue,
     * or with status 400 (Bad Request) if the venue is not valid,
     * or with status 500 (Internal Server Error) if the venue couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/venues")
    @Timed
    public ResponseEntity<Venue> updateVenue(@Valid @RequestBody Venue venue) throws URISyntaxException {
        log.debug("REST request to update Venue : {}", venue);
        if (venue.getId() == null) {
            return createVenue(venue);
        }
        Venue result = venueService.save(venue);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, venue.getId().toString()))
            .body(result);
    }

    /**
     * GET  /venues : get all the venues.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of venues in body
     */
    @GetMapping("/venues")
    @Timed
    public ResponseEntity<List<Venue>> getAllVenues(VenueCriteria criteria) {
        log.debug("REST request to get Venues by criteria: {}", criteria);
        List<Venue> entityList = venueQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  /venues/:id : get the "id" venue.
     *
     * @param id the id of the venue to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the venue, or with status 404 (Not Found)
     */
    @GetMapping("/venues/{id}")
    @Timed
    public ResponseEntity<Venue> getVenue(@PathVariable Long id) {
        log.debug("REST request to get Venue : {}", id);
        Venue venue = venueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(venue));
    }

    /**
     * DELETE  /venues/:id : delete the "id" venue.
     *
     * @param id the id of the venue to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/venues/{id}")
    @Timed
    public ResponseEntity<Void> deleteVenue(@PathVariable Long id) {
        log.debug("REST request to delete Venue : {}", id);
        venueService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
