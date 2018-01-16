package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.Network;
import io.github.jhipster.application.repository.NetworkRepository;
import io.github.jhipster.application.service.NetworkService;
import io.github.jhipster.application.service.dto.NetworkDTO;
import io.github.jhipster.application.service.mapper.NetworkMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

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
    private NetworkMapper networkMapper;

    @Autowired
    private NetworkService networkService;

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
        final NetworkResource networkResource = new NetworkResource(networkService);
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
        NetworkDTO networkDTO = networkMapper.toDto(network);
        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
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
        NetworkDTO networkDTO = networkMapper.toDto(network);

        // An entity with an existing ID cannot be created, so this API call must fail
        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
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
        NetworkDTO networkDTO = networkMapper.toDto(network);

        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
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
        NetworkDTO networkDTO = networkMapper.toDto(network);

        restNetworkMockMvc.perform(post("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
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
    public void getNonExistingNetwork() throws Exception {
        // Get the network
        restNetworkMockMvc.perform(get("/api/networks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateNetwork() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);
        int databaseSizeBeforeUpdate = networkRepository.findAll().size();

        // Update the network
        Network updatedNetwork = networkRepository.findOne(network.getId());
        // Disconnect from session so that the updates on updatedNetwork are not directly saved in db
        em.detach(updatedNetwork);
        updatedNetwork
            .networkName(UPDATED_NETWORK_NAME)
            .ssid(UPDATED_SSID);
        NetworkDTO networkDTO = networkMapper.toDto(updatedNetwork);

        restNetworkMockMvc.perform(put("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
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
        NetworkDTO networkDTO = networkMapper.toDto(network);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restNetworkMockMvc.perform(put("/api/networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(networkDTO)))
            .andExpect(status().isCreated());

        // Validate the Network in the database
        List<Network> networkList = networkRepository.findAll();
        assertThat(networkList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteNetwork() throws Exception {
        // Initialize the database
        networkRepository.saveAndFlush(network);
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

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NetworkDTO.class);
        NetworkDTO networkDTO1 = new NetworkDTO();
        networkDTO1.setId(1L);
        NetworkDTO networkDTO2 = new NetworkDTO();
        assertThat(networkDTO1).isNotEqualTo(networkDTO2);
        networkDTO2.setId(networkDTO1.getId());
        assertThat(networkDTO1).isEqualTo(networkDTO2);
        networkDTO2.setId(2L);
        assertThat(networkDTO1).isNotEqualTo(networkDTO2);
        networkDTO1.setId(null);
        assertThat(networkDTO1).isNotEqualTo(networkDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(networkMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(networkMapper.fromId(null)).isNull();
    }
}
