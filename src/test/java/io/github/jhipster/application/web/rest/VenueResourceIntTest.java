package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Venue;
import io.github.jhipster.application.domain.AP;
import io.github.jhipster.application.domain.Tenant;
import io.github.jhipster.application.repository.VenueRepository;
import io.github.jhipster.application.service.VenueService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.VenueCriteria;
import io.github.jhipster.application.service.VenueQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VenueResource REST controller.
 *
 * @see VenueResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class VenueResourceIntTest {

    private static final String DEFAULT_VENUE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VENUE_NAME = "BBBBBBBBBB";

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private VenueService venueService;

    @Autowired
    private VenueQueryService venueQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restVenueMockMvc;

    private Venue venue;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final VenueResource venueResource = new VenueResource(venueService, venueQueryService);
        this.restVenueMockMvc = MockMvcBuilders.standaloneSetup(venueResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Venue createEntity(EntityManager em) {
        Venue venue = new Venue()
            .venueName(DEFAULT_VENUE_NAME);
        return venue;
    }

    @Before
    public void initTest() {
        venue = createEntity(em);
    }

    @Test
    @Transactional
    public void createVenue() throws Exception {
        int databaseSizeBeforeCreate = venueRepository.findAll().size();

        // Create the Venue
        restVenueMockMvc.perform(post("/api/venues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venue)))
            .andExpect(status().isCreated());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeCreate + 1);
        Venue testVenue = venueList.get(venueList.size() - 1);
        assertThat(testVenue.getVenueName()).isEqualTo(DEFAULT_VENUE_NAME);
    }

    @Test
    @Transactional
    public void createVenueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = venueRepository.findAll().size();

        // Create the Venue with an existing ID
        venue.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVenueMockMvc.perform(post("/api/venues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venue)))
            .andExpect(status().isBadRequest());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkVenueNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = venueRepository.findAll().size();
        // set the field null
        venue.setVenueName(null);

        // Create the Venue, which fails.

        restVenueMockMvc.perform(post("/api/venues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venue)))
            .andExpect(status().isBadRequest());

        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVenues() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        // Get all the venueList
        restVenueMockMvc.perform(get("/api/venues?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(venue.getId().intValue())))
            .andExpect(jsonPath("$.[*].venueName").value(hasItem(DEFAULT_VENUE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getVenue() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        // Get the venue
        restVenueMockMvc.perform(get("/api/venues/{id}", venue.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(venue.getId().intValue()))
            .andExpect(jsonPath("$.venueName").value(DEFAULT_VENUE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getAllVenuesByVenueNameIsEqualToSomething() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        // Get all the venueList where venueName equals to DEFAULT_VENUE_NAME
        defaultVenueShouldBeFound("venueName.equals=" + DEFAULT_VENUE_NAME);

        // Get all the venueList where venueName equals to UPDATED_VENUE_NAME
        defaultVenueShouldNotBeFound("venueName.equals=" + UPDATED_VENUE_NAME);
    }

    @Test
    @Transactional
    public void getAllVenuesByVenueNameIsInShouldWork() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        // Get all the venueList where venueName in DEFAULT_VENUE_NAME or UPDATED_VENUE_NAME
        defaultVenueShouldBeFound("venueName.in=" + DEFAULT_VENUE_NAME + "," + UPDATED_VENUE_NAME);

        // Get all the venueList where venueName equals to UPDATED_VENUE_NAME
        defaultVenueShouldNotBeFound("venueName.in=" + UPDATED_VENUE_NAME);
    }

    @Test
    @Transactional
    public void getAllVenuesByVenueNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        venueRepository.saveAndFlush(venue);

        // Get all the venueList where venueName is not null
        defaultVenueShouldBeFound("venueName.specified=true");

        // Get all the venueList where venueName is null
        defaultVenueShouldNotBeFound("venueName.specified=false");
    }

    @Test
    @Transactional
    public void getAllVenuesByOwnedAPIsEqualToSomething() throws Exception {
        // Initialize the database
        AP ownedAP = APResourceIntTest.createEntity(em);
        em.persist(ownedAP);
        em.flush();
        venue.addOwnedAP(ownedAP);
        venueRepository.saveAndFlush(venue);
        Long ownedAPId = ownedAP.getId();

        // Get all the venueList where ownedAP equals to ownedAPId
        defaultVenueShouldBeFound("ownedAPId.equals=" + ownedAPId);

        // Get all the venueList where ownedAP equals to ownedAPId + 1
        defaultVenueShouldNotBeFound("ownedAPId.equals=" + (ownedAPId + 1));
    }


    @Test
    @Transactional
    public void getAllVenuesByTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        Tenant tenant = TenantResourceIntTest.createEntity(em);
        em.persist(tenant);
        em.flush();
        venue.setTenant(tenant);
        venueRepository.saveAndFlush(venue);
        Long tenantId = tenant.getId();

        // Get all the venueList where tenant equals to tenantId
        defaultVenueShouldBeFound("tenantId.equals=" + tenantId);

        // Get all the venueList where tenant equals to tenantId + 1
        defaultVenueShouldNotBeFound("tenantId.equals=" + (tenantId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultVenueShouldBeFound(String filter) throws Exception {
        restVenueMockMvc.perform(get("/api/venues?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(venue.getId().intValue())))
            .andExpect(jsonPath("$.[*].venueName").value(hasItem(DEFAULT_VENUE_NAME.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultVenueShouldNotBeFound(String filter) throws Exception {
        restVenueMockMvc.perform(get("/api/venues?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingVenue() throws Exception {
        // Get the venue
        restVenueMockMvc.perform(get("/api/venues/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVenue() throws Exception {
        // Initialize the database
        venueService.save(venue);

        int databaseSizeBeforeUpdate = venueRepository.findAll().size();

        // Update the venue
        Venue updatedVenue = venueRepository.findOne(venue.getId());
        // Disconnect from session so that the updates on updatedVenue are not directly saved in db
        em.detach(updatedVenue);
        updatedVenue
            .venueName(UPDATED_VENUE_NAME);

        restVenueMockMvc.perform(put("/api/venues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedVenue)))
            .andExpect(status().isOk());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate);
        Venue testVenue = venueList.get(venueList.size() - 1);
        assertThat(testVenue.getVenueName()).isEqualTo(UPDATED_VENUE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingVenue() throws Exception {
        int databaseSizeBeforeUpdate = venueRepository.findAll().size();

        // Create the Venue

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restVenueMockMvc.perform(put("/api/venues")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(venue)))
            .andExpect(status().isCreated());

        // Validate the Venue in the database
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteVenue() throws Exception {
        // Initialize the database
        venueService.save(venue);

        int databaseSizeBeforeDelete = venueRepository.findAll().size();

        // Get the venue
        restVenueMockMvc.perform(delete("/api/venues/{id}", venue.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Venue> venueList = venueRepository.findAll();
        assertThat(venueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Venue.class);
        Venue venue1 = new Venue();
        venue1.setId(1L);
        Venue venue2 = new Venue();
        venue2.setId(venue1.getId());
        assertThat(venue1).isEqualTo(venue2);
        venue2.setId(2L);
        assertThat(venue1).isNotEqualTo(venue2);
        venue1.setId(null);
        assertThat(venue1).isNotEqualTo(venue2);
    }
}
