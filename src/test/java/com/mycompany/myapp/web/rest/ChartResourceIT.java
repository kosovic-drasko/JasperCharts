package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Chart;
import com.mycompany.myapp.repository.ChartRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ChartResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ChartResourceIT {

    private static final String ENTITY_API_URL = "/api/charts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ChartRepository chartRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restChartMockMvc;

    private Chart chart;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chart createEntity(EntityManager em) {
        Chart chart = new Chart();
        return chart;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Chart createUpdatedEntity(EntityManager em) {
        Chart chart = new Chart();
        return chart;
    }

    @BeforeEach
    public void initTest() {
        chart = createEntity(em);
    }

    @Test
    @Transactional
    void getAllCharts() throws Exception {
        // Initialize the database
        chartRepository.saveAndFlush(chart);

        // Get all the chartList
        restChartMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chart.getId().intValue())));
    }

    @Test
    @Transactional
    void getChart() throws Exception {
        // Initialize the database
        chartRepository.saveAndFlush(chart);

        // Get the chart
        restChartMockMvc
            .perform(get(ENTITY_API_URL_ID, chart.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(chart.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingChart() throws Exception {
        // Get the chart
        restChartMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
