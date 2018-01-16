package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.APService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.APDTO;
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
 * REST controller for managing AP.
 */
@RestController
@RequestMapping("/api")
public class APResource {

    private final Logger log = LoggerFactory.getLogger(APResource.class);

    private static final String ENTITY_NAME = "aP";

    private final APService aPService;

    public APResource(APService aPService) {
        this.aPService = aPService;
    }

    /**
     * POST  /aps : Create a new aP.
     *
     * @param aPDTO the aPDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aPDTO, or with status 400 (Bad Request) if the aP has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/aps")
    @Timed
    public ResponseEntity<APDTO> createAP(@Valid @RequestBody APDTO aPDTO) throws URISyntaxException {
        log.debug("REST request to save AP : {}", aPDTO);
        if (aPDTO.getId() != null) {
            throw new BadRequestAlertException("A new aP cannot already have an ID", ENTITY_NAME, "idexists");
        }
        APDTO result = aPService.save(aPDTO);
        return ResponseEntity.created(new URI("/api/aps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /aps : Updates an existing aP.
     *
     * @param aPDTO the aPDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aPDTO,
     * or with status 400 (Bad Request) if the aPDTO is not valid,
     * or with status 500 (Internal Server Error) if the aPDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/aps")
    @Timed
    public ResponseEntity<APDTO> updateAP(@Valid @RequestBody APDTO aPDTO) throws URISyntaxException {
        log.debug("REST request to update AP : {}", aPDTO);
        if (aPDTO.getId() == null) {
            return createAP(aPDTO);
        }
        APDTO result = aPService.save(aPDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aPDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /aps : get all the aPS.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of aPS in body
     */
    @GetMapping("/aps")
    @Timed
    public List<APDTO> getAllAPS() {
        log.debug("REST request to get all APS");
        return aPService.findAll();
        }

    /**
     * GET  /aps/:id : get the "id" aP.
     *
     * @param id the id of the aPDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aPDTO, or with status 404 (Not Found)
     */
    @GetMapping("/aps/{id}")
    @Timed
    public ResponseEntity<APDTO> getAP(@PathVariable Long id) {
        log.debug("REST request to get AP : {}", id);
        APDTO aPDTO = aPService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(aPDTO));
    }

    /**
     * DELETE  /aps/:id : delete the "id" aP.
     *
     * @param id the id of the aPDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/aps/{id}")
    @Timed
    public ResponseEntity<Void> deleteAP(@PathVariable Long id) {
        log.debug("REST request to delete AP : {}", id);
        aPService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
