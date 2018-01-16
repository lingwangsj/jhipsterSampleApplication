package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.NetworkService;
import io.github.jhipster.application.domain.Network;
import io.github.jhipster.application.repository.NetworkRepository;
import io.github.jhipster.application.service.dto.NetworkDTO;
import io.github.jhipster.application.service.mapper.NetworkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Network.
 */
@Service
@Transactional
public class NetworkServiceImpl implements NetworkService {

    private final Logger log = LoggerFactory.getLogger(NetworkServiceImpl.class);

    private final NetworkRepository networkRepository;

    private final NetworkMapper networkMapper;

    public NetworkServiceImpl(NetworkRepository networkRepository, NetworkMapper networkMapper) {
        this.networkRepository = networkRepository;
        this.networkMapper = networkMapper;
    }

    /**
     * Save a network.
     *
     * @param networkDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NetworkDTO save(NetworkDTO networkDTO) {
        log.debug("Request to save Network : {}", networkDTO);
        Network network = networkMapper.toEntity(networkDTO);
        network = networkRepository.save(network);
        return networkMapper.toDto(network);
    }

    /**
     * Get all the networks.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NetworkDTO> findAll() {
        log.debug("Request to get all Networks");
        return networkRepository.findAllWithEagerRelationships().stream()
            .map(networkMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one network by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public NetworkDTO findOne(Long id) {
        log.debug("Request to get Network : {}", id);
        Network network = networkRepository.findOneWithEagerRelationships(id);
        return networkMapper.toDto(network);
    }

    /**
     * Delete the network by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Network : {}", id);
        networkRepository.delete(id);
    }
}
