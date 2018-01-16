package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.NetworkDTO;
import java.util.List;

/**
 * Service Interface for managing Network.
 */
public interface NetworkService {

    /**
     * Save a network.
     *
     * @param networkDTO the entity to save
     * @return the persisted entity
     */
    NetworkDTO save(NetworkDTO networkDTO);

    /**
     * Get all the networks.
     *
     * @return the list of entities
     */
    List<NetworkDTO> findAll();

    /**
     * Get the "id" network.
     *
     * @param id the id of the entity
     * @return the entity
     */
    NetworkDTO findOne(Long id);

    /**
     * Delete the "id" network.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
