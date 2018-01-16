package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.APConfigService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.APConfigDTO;
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
 * REST controller for managing APConfig.
 */
@RestController
@RequestMapping("/api")
public class APConfigResource {

    private final Logger log = LoggerFactory.getLogger(APConfigResource.class);

    private static final String ENTITY_NAME = "aPConfig";

    private final APConfigService aPConfigService;

    public APConfigResource(APConfigService aPConfigService) {
        this.aPConfigService = aPConfigService;
    }

    /**
     * POST  /ap-configs : Create a new aPConfig.
     *
     * @param aPConfigDTO the aPConfigDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aPConfigDTO, or with status 400 (Bad Request) if the aPConfig has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ap-configs")
    @Timed
    public ResponseEntity<APConfigDTO> createAPConfig(@Valid @RequestBody APConfigDTO aPConfigDTO) throws URISyntaxException {
        log.debug("REST request to save APConfig : {}", aPConfigDTO);
        if (aPConfigDTO.getId() != null) {
            throw new BadRequestAlertException("A new aPConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        APConfigDTO result = aPConfigService.save(aPConfigDTO);
        return ResponseEntity.created(new URI("/api/ap-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ap-configs : Updates an existing aPConfig.
     *
     * @param aPConfigDTO the aPConfigDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aPConfigDTO,
     * or with status 400 (Bad Request) if the aPConfigDTO is not valid,
     * or with status 500 (Internal Server Error) if the aPConfigDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ap-configs")
    @Timed
    public ResponseEntity<APConfigDTO> updateAPConfig(@Valid @RequestBody APConfigDTO aPConfigDTO) throws URISyntaxException {
        log.debug("REST request to update APConfig : {}", aPConfigDTO);
        if (aPConfigDTO.getId() == null) {
            return createAPConfig(aPConfigDTO);
        }
        APConfigDTO result = aPConfigService.save(aPConfigDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aPConfigDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ap-configs : get all the aPConfigs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of aPConfigs in body
     */
    @GetMapping("/ap-configs")
    @Timed
    public List<APConfigDTO> getAllAPConfigs() {
        log.debug("REST request to get all APConfigs");
        return aPConfigService.findAll();
        }

    /**
     * GET  /ap-configs/:id : get the "id" aPConfig.
     *
     * @param id the id of the aPConfigDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aPConfigDTO, or with status 404 (Not Found)
     */
    @GetMapping("/ap-configs/{id}")
    @Timed
    public ResponseEntity<APConfigDTO> getAPConfig(@PathVariable Long id) {
        log.debug("REST request to get APConfig : {}", id);
        APConfigDTO aPConfigDTO = aPConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(aPConfigDTO));
    }

    /**
     * DELETE  /ap-configs/:id : delete the "id" aPConfig.
     *
     * @param id the id of the aPConfigDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ap-configs/{id}")
    @Timed
    public ResponseEntity<Void> deleteAPConfig(@PathVariable Long id) {
        log.debug("REST request to delete APConfig : {}", id);
        aPConfigService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
