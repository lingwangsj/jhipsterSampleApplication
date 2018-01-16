package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Network;
import io.github.jhipster.application.repository.NetworkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Network.
 */
@Service
@Transactional
public class NetworkService {

    private final Logger log = LoggerFactory.getLogger(NetworkService.class);

    private final NetworkRepository networkRepository;

    public NetworkService(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    /**
     * Save a network.
     *
     * @param network the entity to save
     * @return the persisted entity
     */
    public Network save(Network network) {
        log.debug("Request to save Network : {}", network);
        return networkRepository.save(network);
    }

    /**
     * Get all the networks.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Network> findAll() {
        log.debug("Request to get all Networks");
        return networkRepository.findAllWithEagerRelationships();
    }

    /**
     * Get one network by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Network findOne(Long id) {
        log.debug("Request to get Network : {}", id);
        return networkRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the network by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Network : {}", id);
        networkRepository.delete(id);
    }
}
