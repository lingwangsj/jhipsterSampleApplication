package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.VenueService;
import io.github.jhipster.application.domain.Venue;
import io.github.jhipster.application.repository.VenueRepository;
import io.github.jhipster.application.service.dto.VenueDTO;
import io.github.jhipster.application.service.mapper.VenueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Venue.
 */
@Service
@Transactional
public class VenueServiceImpl implements VenueService {

    private final Logger log = LoggerFactory.getLogger(VenueServiceImpl.class);

    private final VenueRepository venueRepository;

    private final VenueMapper venueMapper;

    public VenueServiceImpl(VenueRepository venueRepository, VenueMapper venueMapper) {
        this.venueRepository = venueRepository;
        this.venueMapper = venueMapper;
    }

    /**
     * Save a venue.
     *
     * @param venueDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VenueDTO save(VenueDTO venueDTO) {
        log.debug("Request to save Venue : {}", venueDTO);
        Venue venue = venueMapper.toEntity(venueDTO);
        venue = venueRepository.save(venue);
        return venueMapper.toDto(venue);
    }

    /**
     * Get all the venues.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VenueDTO> findAll() {
        log.debug("Request to get all Venues");
        return venueRepository.findAll().stream()
            .map(venueMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one venue by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public VenueDTO findOne(Long id) {
        log.debug("Request to get Venue : {}", id);
        Venue venue = venueRepository.findOne(id);
        return venueMapper.toDto(venue);
    }

    /**
     * Delete the venue by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Venue : {}", id);
        venueRepository.delete(id);
    }
}
