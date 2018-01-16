package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.APConfig;
import io.github.jhipster.application.service.APConfigService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.APConfigCriteria;
import io.github.jhipster.application.service.APConfigQueryService;
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

    private final APConfigQueryService aPConfigQueryService;

    public APConfigResource(APConfigService aPConfigService, APConfigQueryService aPConfigQueryService) {
        this.aPConfigService = aPConfigService;
        this.aPConfigQueryService = aPConfigQueryService;
    }

    /**
     * POST  /ap-configs : Create a new aPConfig.
     *
     * @param aPConfig the aPConfig to create
     * @return the ResponseEntity with status 201 (Created) and with body the new aPConfig, or with status 400 (Bad Request) if the aPConfig has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ap-configs")
    @Timed
    public ResponseEntity<APConfig> createAPConfig(@Valid @RequestBody APConfig aPConfig) throws URISyntaxException {
        log.debug("REST request to save APConfig : {}", aPConfig);
        if (aPConfig.getId() != null) {
            throw new BadRequestAlertException("A new aPConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        APConfig result = aPConfigService.save(aPConfig);
        return ResponseEntity.created(new URI("/api/ap-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ap-configs : Updates an existing aPConfig.
     *
     * @param aPConfig the aPConfig to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated aPConfig,
     * or with status 400 (Bad Request) if the aPConfig is not valid,
     * or with status 500 (Internal Server Error) if the aPConfig couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ap-configs")
    @Timed
    public ResponseEntity<APConfig> updateAPConfig(@Valid @RequestBody APConfig aPConfig) throws URISyntaxException {
        log.debug("REST request to update APConfig : {}", aPConfig);
        if (aPConfig.getId() == null) {
            return createAPConfig(aPConfig);
        }
        APConfig result = aPConfigService.save(aPConfig);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, aPConfig.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ap-configs : get all the aPConfigs.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of aPConfigs in body
     */
    @GetMapping("/ap-configs")
    @Timed
    public ResponseEntity<List<APConfig>> getAllAPConfigs(APConfigCriteria criteria) {
        log.debug("REST request to get APConfigs by criteria: {}", criteria);
        List<APConfig> entityList = aPConfigQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * GET  /ap-configs/:id : get the "id" aPConfig.
     *
     * @param id the id of the aPConfig to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the aPConfig, or with status 404 (Not Found)
     */
    @GetMapping("/ap-configs/{id}")
    @Timed
    public ResponseEntity<APConfig> getAPConfig(@PathVariable Long id) {
        log.debug("REST request to get APConfig : {}", id);
        APConfig aPConfig = aPConfigService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(aPConfig));
    }

    /**
     * DELETE  /ap-configs/:id : delete the "id" aPConfig.
     *
     * @param id the id of the aPConfig to delete
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
