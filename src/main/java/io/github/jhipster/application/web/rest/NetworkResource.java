package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.NetworkService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.NetworkDTO;
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
 * REST controller for managing Network.
 */
@RestController
@RequestMapping("/api")
public class NetworkResource {

    private final Logger log = LoggerFactory.getLogger(NetworkResource.class);

    private static final String ENTITY_NAME = "network";

    private final NetworkService networkService;

    public NetworkResource(NetworkService networkService) {
        this.networkService = networkService;
    }

    /**
     * POST  /networks : Create a new network.
     *
     * @param networkDTO the networkDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new networkDTO, or with status 400 (Bad Request) if the network has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/networks")
    @Timed
    public ResponseEntity<NetworkDTO> createNetwork(@Valid @RequestBody NetworkDTO networkDTO) throws URISyntaxException {
        log.debug("REST request to save Network : {}", networkDTO);
        if (networkDTO.getId() != null) {
            throw new BadRequestAlertException("A new network cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NetworkDTO result = networkService.save(networkDTO);
        return ResponseEntity.created(new URI("/api/networks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /networks : Updates an existing network.
     *
     * @param networkDTO the networkDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated networkDTO,
     * or with status 400 (Bad Request) if the networkDTO is not valid,
     * or with status 500 (Internal Server Error) if the networkDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/networks")
    @Timed
    public ResponseEntity<NetworkDTO> updateNetwork(@Valid @RequestBody NetworkDTO networkDTO) throws URISyntaxException {
        log.debug("REST request to update Network : {}", networkDTO);
        if (networkDTO.getId() == null) {
            return createNetwork(networkDTO);
        }
        NetworkDTO result = networkService.save(networkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, networkDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /networks : get all the networks.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of networks in body
     */
    @GetMapping("/networks")
    @Timed
    public List<NetworkDTO> getAllNetworks() {
        log.debug("REST request to get all Networks");
        return networkService.findAll();
        }

    /**
     * GET  /networks/:id : get the "id" network.
     *
     * @param id the id of the networkDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the networkDTO, or with status 404 (Not Found)
     */
    @GetMapping("/networks/{id}")
    @Timed
    public ResponseEntity<NetworkDTO> getNetwork(@PathVariable Long id) {
        log.debug("REST request to get Network : {}", id);
        NetworkDTO networkDTO = networkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(networkDTO));
    }

    /**
     * DELETE  /networks/:id : delete the "id" network.
     *
     * @param id the id of the networkDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/networks/{id}")
    @Timed
    public ResponseEntity<Void> deleteNetwork(@PathVariable Long id) {
        log.debug("REST request to delete Network : {}", id);
        networkService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
