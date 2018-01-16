package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.APConfig;
import io.github.jhipster.application.domain.Tag;
import io.github.jhipster.application.repository.APConfigRepository;
import io.github.jhipster.application.service.APConfigService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.APConfigCriteria;
import io.github.jhipster.application.service.APConfigQueryService;

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
 * Test class for the APConfigResource REST controller.
 *
 * @see APConfigResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class APConfigResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private APConfigRepository aPConfigRepository;

    @Autowired
    private APConfigService aPConfigService;

    @Autowired
    private APConfigQueryService aPConfigQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAPConfigMockMvc;

    private APConfig aPConfig;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final APConfigResource aPConfigResource = new APConfigResource(aPConfigService, aPConfigQueryService);
        this.restAPConfigMockMvc = MockMvcBuilders.standaloneSetup(aPConfigResource)
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
    public static APConfig createEntity(EntityManager em) {
        APConfig aPConfig = new APConfig()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION);
        return aPConfig;
    }

    @Before
    public void initTest() {
        aPConfig = createEntity(em);
    }

    @Test
    @Transactional
    public void createAPConfig() throws Exception {
        int databaseSizeBeforeCreate = aPConfigRepository.findAll().size();

        // Create the APConfig
        restAPConfigMockMvc.perform(post("/api/ap-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aPConfig)))
            .andExpect(status().isCreated());

        // Validate the APConfig in the database
        List<APConfig> aPConfigList = aPConfigRepository.findAll();
        assertThat(aPConfigList).hasSize(databaseSizeBeforeCreate + 1);
        APConfig testAPConfig = aPConfigList.get(aPConfigList.size() - 1);
        assertThat(testAPConfig.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAPConfig.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createAPConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = aPConfigRepository.findAll().size();

        // Create the APConfig with an existing ID
        aPConfig.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAPConfigMockMvc.perform(post("/api/ap-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aPConfig)))
            .andExpect(status().isBadRequest());

        // Validate the APConfig in the database
        List<APConfig> aPConfigList = aPConfigRepository.findAll();
        assertThat(aPConfigList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = aPConfigRepository.findAll().size();
        // set the field null
        aPConfig.setName(null);

        // Create the APConfig, which fails.

        restAPConfigMockMvc.perform(post("/api/ap-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aPConfig)))
            .andExpect(status().isBadRequest());

        List<APConfig> aPConfigList = aPConfigRepository.findAll();
        assertThat(aPConfigList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAPConfigs() throws Exception {
        // Initialize the database
        aPConfigRepository.saveAndFlush(aPConfig);

        // Get all the aPConfigList
        restAPConfigMockMvc.perform(get("/api/ap-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aPConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getAPConfig() throws Exception {
        // Initialize the database
        aPConfigRepository.saveAndFlush(aPConfig);

        // Get the aPConfig
        restAPConfigMockMvc.perform(get("/api/ap-configs/{id}", aPConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aPConfig.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getAllAPConfigsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        aPConfigRepository.saveAndFlush(aPConfig);

        // Get all the aPConfigList where name equals to DEFAULT_NAME
        defaultAPConfigShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the aPConfigList where name equals to UPDATED_NAME
        defaultAPConfigShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAPConfigsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        aPConfigRepository.saveAndFlush(aPConfig);

        // Get all the aPConfigList where name in DEFAULT_NAME or UPDATED_NAME
        defaultAPConfigShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the aPConfigList where name equals to UPDATED_NAME
        defaultAPConfigShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllAPConfigsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        aPConfigRepository.saveAndFlush(aPConfig);

        // Get all the aPConfigList where name is not null
        defaultAPConfigShouldBeFound("name.specified=true");

        // Get all the aPConfigList where name is null
        defaultAPConfigShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllAPConfigsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        aPConfigRepository.saveAndFlush(aPConfig);

        // Get all the aPConfigList where description equals to DEFAULT_DESCRIPTION
        defaultAPConfigShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the aPConfigList where description equals to UPDATED_DESCRIPTION
        defaultAPConfigShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllAPConfigsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        aPConfigRepository.saveAndFlush(aPConfig);

        // Get all the aPConfigList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultAPConfigShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the aPConfigList where description equals to UPDATED_DESCRIPTION
        defaultAPConfigShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllAPConfigsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        aPConfigRepository.saveAndFlush(aPConfig);

        // Get all the aPConfigList where description is not null
        defaultAPConfigShouldBeFound("description.specified=true");

        // Get all the aPConfigList where description is null
        defaultAPConfigShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllAPConfigsByTagsIsEqualToSomething() throws Exception {
        // Initialize the database
        Tag tags = TagResourceIntTest.createEntity(em);
        em.persist(tags);
        em.flush();
        aPConfig.addTags(tags);
        aPConfigRepository.saveAndFlush(aPConfig);
        Long tagsId = tags.getId();

        // Get all the aPConfigList where tags equals to tagsId
        defaultAPConfigShouldBeFound("tagsId.equals=" + tagsId);

        // Get all the aPConfigList where tags equals to tagsId + 1
        defaultAPConfigShouldNotBeFound("tagsId.equals=" + (tagsId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultAPConfigShouldBeFound(String filter) throws Exception {
        restAPConfigMockMvc.perform(get("/api/ap-configs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aPConfig.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultAPConfigShouldNotBeFound(String filter) throws Exception {
        restAPConfigMockMvc.perform(get("/api/ap-configs?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingAPConfig() throws Exception {
        // Get the aPConfig
        restAPConfigMockMvc.perform(get("/api/ap-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAPConfig() throws Exception {
        // Initialize the database
        aPConfigService.save(aPConfig);

        int databaseSizeBeforeUpdate = aPConfigRepository.findAll().size();

        // Update the aPConfig
        APConfig updatedAPConfig = aPConfigRepository.findOne(aPConfig.getId());
        // Disconnect from session so that the updates on updatedAPConfig are not directly saved in db
        em.detach(updatedAPConfig);
        updatedAPConfig
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION);

        restAPConfigMockMvc.perform(put("/api/ap-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAPConfig)))
            .andExpect(status().isOk());

        // Validate the APConfig in the database
        List<APConfig> aPConfigList = aPConfigRepository.findAll();
        assertThat(aPConfigList).hasSize(databaseSizeBeforeUpdate);
        APConfig testAPConfig = aPConfigList.get(aPConfigList.size() - 1);
        assertThat(testAPConfig.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAPConfig.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingAPConfig() throws Exception {
        int databaseSizeBeforeUpdate = aPConfigRepository.findAll().size();

        // Create the APConfig

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAPConfigMockMvc.perform(put("/api/ap-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aPConfig)))
            .andExpect(status().isCreated());

        // Validate the APConfig in the database
        List<APConfig> aPConfigList = aPConfigRepository.findAll();
        assertThat(aPConfigList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAPConfig() throws Exception {
        // Initialize the database
        aPConfigService.save(aPConfig);

        int databaseSizeBeforeDelete = aPConfigRepository.findAll().size();

        // Get the aPConfig
        restAPConfigMockMvc.perform(delete("/api/ap-configs/{id}", aPConfig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<APConfig> aPConfigList = aPConfigRepository.findAll();
        assertThat(aPConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(APConfig.class);
        APConfig aPConfig1 = new APConfig();
        aPConfig1.setId(1L);
        APConfig aPConfig2 = new APConfig();
        aPConfig2.setId(aPConfig1.getId());
        assertThat(aPConfig1).isEqualTo(aPConfig2);
        aPConfig2.setId(2L);
        assertThat(aPConfig1).isNotEqualTo(aPConfig2);
        aPConfig1.setId(null);
        assertThat(aPConfig1).isNotEqualTo(aPConfig2);
    }
}
