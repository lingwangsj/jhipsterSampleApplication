package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Network;
import io.github.jhipster.application.domain.Tag;
import io.github.jhipster.application.domain.Tenant;
import io.github.jhipster.application.repository.NetworkRepository;
import io.github.jhipster.application.service.NetworkService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.NetworkCriteria;
import io.github.jhipster.application.service.NetworkQueryService;

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
 * Test class for the NetworkResource REST controller.
 *
 * @see NetworkResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class NetworkResourceIntTest {

    private static final String DEFAULT_NETWORK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NETWORK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SSID = "AAAAAAAAAA";
    private static final String UPDATED_SSID = "BBBBBBBBBB";

    @Autowired
    private NetworkRepository networkRepository;

    @Autowired
    private NetworkService networkService;

    @Autowired
    private NetworkQueryService networkQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restNetworkMockMvc;

    private Network network;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final NetworkResource networkResource = new NetworkResource(networkService, networkQueryService);
        this.restNetworkMockMvc = MockMvcBuilders.standaloneSetup(networkResource)
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
    public static Network createEntity(EntityManager em) {
        Network network = new Network()
            .networkName(DEFAULT_NETWORK_NAME)
            .ssid(DEFAULT_SSID);
        return network;
    }

    @Before
    public void initTest() {
        network = createEntity(em);
    }

    @Test
    @Transactional
    public void createNetwork() throws Exception {
        int databaseSizeBeforeCreate = networkRepository.findAll().size();

        // Create the Network
        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(network)))
            .andExpect(status().isCreated());

        // Validate the Network in the database
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeCreate + 1);
        Network testNetwork = networkList.get(networkList.size() - 1);
        assertThat(testNetwork.getNetworkName()).isEqualTo(DEFAULT_NETWORK_NAME);
        assertThat(testNetwork.getSsid()).isEqualTo(DEFAULT_SSID);
    }

    @Test
    @Transactional
    public void createNetworkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = networkRepository.findAll().size();

        // Create the Network with an existing ID
        network.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(network)))
            .andExpect(status().isBadRequest());

        // Validate the Network in the database
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNetworkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = networkRepository.findAll().size();
        // set the field null
        network.setNetworkName(null);

        // Create the Network, which fails.

        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(network)))
            .andExpect(status().isBadRequest());

        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSsidIsRequired() throws Exception {
        int databaseSizeBeforeTest = networkRepository.findAll().size();
        // set the field null
        network.setSsid(null);

        // Create the Network, which fails.

        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(network)))
            .andExpect(status().isBadRequest());

        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllNetworks() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        // Get all the networkList
        restNetworkMockMvc.perform(get("/api/networks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(network.getId().intValue())))
            .andExpect(jsonPath("$.[*].networkName").value(hasItem(DEFAULT_NETWORK_NAME.toString())))
            .andExpect(jsonPath("$.[*].ssid").value(hasItem(DEFAULT_SSID.toString())));
    }

    @Test
    @Transactional
    public void getNetwork() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        // Get the network
        restNetworkMockMvc.perform(get("/api/networks/{id}", network.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(network.getId().intValue()))
            .andExpect(jsonPath("$.networkName").value(DEFAULT_NETWORK_NAME.toString()))
            .andExpect(jsonPath("$.ssid").value(DEFAULT_SSID.toString()));
    }

    @Test
    @Transactional
    public void getAllNetworksByNetworkNameIsEqualToSomething() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        // Get all the networkList where networkName equals to DEFAULT_NETWORK_NAME
        defaultNetworkShouldBeFound("networkName.equals=" + DEFAULT_NETWORK_NAME);

        // Get all the networkList where networkName equals to UPDATED_NETWORK_NAME
        defaultNetworkShouldNotBeFound("networkName.equals=" + UPDATED_NETWORK_NAME);
    }

    @Test
    @Transactional
    public void getAllNetworksByNetworkNameIsInShouldWork() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        // Get all the networkList where networkName in DEFAULT_NETWORK_NAME or UPDATED_NETWORK_NAME
        defaultNetworkShouldBeFound("networkName.in=" + DEFAULT_NETWORK_NAME + "," + UPDATED_NETWORK_NAME);

        // Get all the networkList where networkName equals to UPDATED_NETWORK_NAME
        defaultNetworkShouldNotBeFound("networkName.in=" + UPDATED_NETWORK_NAME);
    }

    @Test
    @Transactional
    public void getAllNetworksByNetworkNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        // Get all the networkList where networkName is not null
        defaultNetworkShouldBeFound("networkName.specified=true");

        // Get all the networkList where networkName is null
        defaultNetworkShouldNotBeFound("networkName.specified=false");
    }

    @Test
    @Transactional
    public void getAllNetworksBySsidIsEqualToSomething() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        // Get all the networkList where ssid equals to DEFAULT_SSID
        defaultNetworkShouldBeFound("ssid.equals=" + DEFAULT_SSID);

        // Get all the networkList where ssid equals to UPDATED_SSID
        defaultNetworkShouldNotBeFound("ssid.equals=" + UPDATED_SSID);
    }

    @Test
    @Transactional
    public void getAllNetworksBySsidIsInShouldWork() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        // Get all the networkList where ssid in DEFAULT_SSID or UPDATED_SSID
        defaultNetworkShouldBeFound("ssid.in=" + DEFAULT_SSID + "," + UPDATED_SSID);

        // Get all the networkList where ssid equals to UPDATED_SSID
        defaultNetworkShouldNotBeFound("ssid.in=" + UPDATED_SSID);
    }

    @Test
    @Transactional
    public void getAllNetworksBySsidIsNullOrNotNull() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);

        // Get all the networkList where ssid is not null
        defaultNetworkShouldBeFound("ssid.specified=true");

        // Get all the networkList where ssid is null
        defaultNetworkShouldNotBeFound("ssid.specified=false");
    }

    @Test
    @Transactional
    public void getAllNetworksByTagsIsEqualToSomething() throws Exception {
        // Initialize the database
        Tag tags = TagResourceIntTest.createEntity(em);
        em.persist(tags);
        em.flush();
        network.addTags(tags);
        networkRepository.saveAndFlush(network);
        Long tagsId = tags.getId();

        // Get all the networkList where tags equals to tagsId
        defaultNetworkShouldBeFound("tagsId.equals=" + tagsId);

        // Get all the networkList where tags equals to tagsId + 1
        defaultNetworkShouldNotBeFound("tagsId.equals=" + (tagsId + 1));
    }


    @Test
    @Transactional
    public void getAllNetworksByTenantIsEqualToSomething() throws Exception {
        // Initialize the database
        Tenant tenant = TenantResourceIntTest.createEntity(em);
        em.persist(tenant);
        em.flush();
        network.setTenant(tenant);
        networkRepository.saveAndFlush(network);
        Long tenantId = tenant.getId();

        // Get all the networkList where tenant equals to tenantId
        defaultNetworkShouldBeFound("tenantId.equals=" + tenantId);

        // Get all the networkList where tenant equals to tenantId + 1
        defaultNetworkShouldNotBeFound("tenantId.equals=" + (tenantId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultNetworkShouldBeFound(String filter) throws Exception {
        restNetworkMockMvc.perform(get("/api/networks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(network.getId().intValue())))
            .andExpect(jsonPath("$.[*].networkName").value(hasItem(DEFAULT_NETWORK_NAME.toString())))
            .andExpect(jsonPath("$.[*].ssid").value(hasItem(DEFAULT_SSID.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultNetworkShouldNotBeFound(String filter) throws Exception {
        restNetworkMockMvc.perform(get("/api/networks?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingNetwork() throws Exception {
        // Get the network
        restNetworkMockMvc.perform(get("/api/networks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNetwork() throws Exception {
        // Initialize the database
        networkService.save(network);

        int databaseSizeBeforeUpdate = networkRepository.findAll().size();

        // Update the network
        Network updatedNetwork = networkRepository.findOne(network.getId());
        // Disconnect from session so that the updates on updatedNetwork are not directly saved in db
        em.detach(updatedNetwork);
        updatedNetwork
            .networkName(UPDATED_NETWORK_NAME)
            .ssid(UPDATED_SSID);

        restNetworkMockMvc.perform(put("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedNetwork)))
            .andExpect(status().isOk());

        // Validate the Network in the database
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeUpdate);
        Network testNetwork = networkList.get(networkList.size() - 1);
        assertThat(testNetwork.getNetworkName()).isEqualTo(UPDATED_NETWORK_NAME);
        assertThat(testNetwork.getSsid()).isEqualTo(UPDATED_SSID);
    }

    @Test
    @Transactional
    public void updateNonExistingNetwork() throws Exception {
        int databaseSizeBeforeUpdate = networkRepository.findAll().size();

        // Create the Network

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNetworkMockMvc.perform(put("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(network)))
            .andExpect(status().isCreated());

        // Validate the Network in the database
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNetwork() throws Exception {
        // Initialize the database
        networkService.save(network);

        int databaseSizeBeforeDelete = networkRepository.findAll().size();

        // Get the network
        restNetworkMockMvc.perform(delete("/api/networks/{id}", network.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Network.class);
        Network network1 = new Network();
        network1.setId(1L);
        Network network2 = new Network();
        network2.setId(network1.getId());
        assertThat(network1).isEqualTo(network2);
        network2.setId(2L);
        assertThat(network1).isNotEqualTo(network2);
        network1.setId(null);
        assertThat(network1).isNotEqualTo(network2);
    }
}
