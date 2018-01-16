package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.AP;
import io.github.jhipster.application.repository.APRepository;
import io.github.jhipster.application.service.APService;
import io.github.jhipster.application.service.dto.APDTO;
import io.github.jhipster.application.service.mapper.APMapper;
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
 * Test class for the APResource REST controller.
 *
 * @see APResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class APResourceIntTest {

    private static final String DEFAULT_SERIAL_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_AP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AP_NAME = "BBBBBBBBBB";

    @Autowired
    private APRepository aPRepository;

    @Autowired
    private APMapper aPMapper;

    @Autowired
    private APService aPService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAPMockMvc;

    private AP aP;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final APResource aPResource = new APResource(aPService);
        this.restAPMockMvc = MockMvcBuilders.standaloneSetup(aPResource)
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
    public static AP createEntity(EntityManager em) {
        AP aP = new AP()
            .serialNumber(DEFAULT_SERIAL_NUMBER)
            .apName(DEFAULT_AP_NAME);
        return aP;
    }

    @Before
    public void initTest() {
        aP = createEntity(em);
    }

    @Test
    @Transactional
    public void createAP() throws Exception {
        int databaseSizeBeforeCreate = aPRepository.findAll().size();

        // Create the AP
        APDTO aPDTO = aPMapper.toDto(aP);
        restAPMockMvc.perform(post("/api/aps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aPDTO)))
            .andExpect(status().isCreated());

        // Validate the AP in the database
        List<AP> aPList = aPRepository.findAll();
        assertThat(aPList).hasSize(databaseSizeBeforeCreate + 1);
        AP testAP = aPList.get(aPList.size() - 1);
        assertThat(testAP.getSerialNumber()).isEqualTo(DEFAULT_SERIAL_NUMBER);
        assertThat(testAP.getApName()).isEqualTo(DEFAULT_AP_NAME);
    }

    @Test
    @Transactional
    public void createAPWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aPRepository.findAll().size();

        // Create the AP with an existing ID
        aP.setId(1L);
        APDTO aPDTO = aPMapper.toDto(aP);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAPMockMvc.perform(post("/api/aps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aPDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AP in the database
        List<AP> aPList = aPRepository.findAll();
        assertThat(aPList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSerialNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = aPRepository.findAll().size();
        // set the field null
        aP.setSerialNumber(null);

        // Create the AP, which fails.
        APDTO aPDTO = aPMapper.toDto(aP);

        restAPMockMvc.perform(post("/api/aps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aPDTO)))
            .andExpect(status().isBadRequest());

        List<AP> aPList = aPRepository.findAll();
        assertThat(aPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = aPRepository.findAll().size();
        // set the field null
        aP.setApName(null);

        // Create the AP, which fails.
        APDTO aPDTO = aPMapper.toDto(aP);

        restAPMockMvc.perform(post("/api/aps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aPDTO)))
            .andExpect(status().isBadRequest());

        List<AP> aPList = aPRepository.findAll();
        assertThat(aPList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAPS() throws Exception {
        // Initialize the database
        aPRepository.saveAndFlush(aP);

        // Get all the aPList
        restAPMockMvc.perform(get("/api/aps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aP.getId().intValue())))
            .andExpect(jsonPath("$.[*].serialNumber").value(hasItem(DEFAULT_SERIAL_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].apName").value(hasItem(DEFAULT_AP_NAME.toString())));
    }

    @Test
    @Transactional
    public void getAP() throws Exception {
        // Initialize the database
        aPRepository.saveAndFlush(aP);

        // Get the aP
        restAPMockMvc.perform(get("/api/aps/{id}", aP.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aP.getId().intValue()))
            .andExpect(jsonPath("$.serialNumber").value(DEFAULT_SERIAL_NUMBER.toString()))
            .andExpect(jsonPath("$.apName").value(DEFAULT_AP_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAP() throws Exception {
        // Get the aP
        restAPMockMvc.perform(get("/api/aps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAP() throws Exception {
        // Initialize the database
        aPRepository.saveAndFlush(aP);
        int databaseSizeBeforeUpdate = aPRepository.findAll().size();

        // Update the aP
        AP updatedAP = aPRepository.findOne(aP.getId());
        // Disconnect from session so that the updates on updatedAP are not directly saved in db
        em.detach(updatedAP);
        updatedAP
            .serialNumber(UPDATED_SERIAL_NUMBER)
            .apName(UPDATED_AP_NAME);
        APDTO aPDTO = aPMapper.toDto(updatedAP);

        restAPMockMvc.perform(put("/api/aps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aPDTO)))
            .andExpect(status().isOk());

        // Validate the AP in the database
        List<AP> aPList = aPRepository.findAll();
        assertThat(aPList).hasSize(databaseSizeBeforeUpdate);
        AP testAP = aPList.get(aPList.size() - 1);
        assertThat(testAP.getSerialNumber()).isEqualTo(UPDATED_SERIAL_NUMBER);
        assertThat(testAP.getApName()).isEqualTo(UPDATED_AP_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingAP() throws Exception {
        int databaseSizeBeforeUpdate = aPRepository.findAll().size();

        // Create the AP
        APDTO aPDTO = aPMapper.toDto(aP);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAPMockMvc.perform(put("/api/aps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aPDTO)))
            .andExpect(status().isCreated());

        // Validate the AP in the database
        List<AP> aPList = aPRepository.findAll();
        assertThat(aPList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAP() throws Exception {
        // Initialize the database
        aPRepository.saveAndFlush(aP);
        int databaseSizeBeforeDelete = aPRepository.findAll().size();

        // Get the aP
        restAPMockMvc.perform(delete("/api/aps/{id}", aP.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AP> aPList = aPRepository.findAll();
        assertThat(aPList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AP.class);
        AP aP1 = new AP();
        aP1.setId(1L);
        AP aP2 = new AP();
        aP2.setId(aP1.getId());
        assertThat(aP1).isEqualTo(aP2);
        aP2.setId(2L);
        assertThat(aP1).isNotEqualTo(aP2);
        aP1.setId(null);
        assertThat(aP1).isNotEqualTo(aP2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(APDTO.class);
        APDTO aPDTO1 = new APDTO();
        aPDTO1.setId(1L);
        APDTO aPDTO2 = new APDTO();
        assertThat(aPDTO1).isNotEqualTo(aPDTO2);
        aPDTO2.setId(aPDTO1.getId());
        assertThat(aPDTO1).isEqualTo(aPDTO2);
        aPDTO2.setId(2L);
        assertThat(aPDTO1).isNotEqualTo(aPDTO2);
        aPDTO1.setId(null);
        assertThat(aPDTO1).isNotEqualTo(aPDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(aPMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(aPMapper.fromId(null)).isNull();
    }
}
