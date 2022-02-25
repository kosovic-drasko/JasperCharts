package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Chart;
import com.mycompany.myapp.repository.ChartRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Chart}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ChartResource {

    private final Logger log = LoggerFactory.getLogger(ChartResource.class);

    private final ChartRepository chartRepository;

    public ChartResource(ChartRepository chartRepository) {
        this.chartRepository = chartRepository;
    }

    /**
     * {@code GET  /charts} : get all the charts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of charts in body.
     */
    @GetMapping("/charts")
    public List<Chart> getAllCharts() {
        log.debug("REST request to get all Charts");
        return chartRepository.findAll();
    }

    /**
     * {@code GET  /charts/:id} : get the "id" chart.
     *
     * @param id the id of the chart to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chart, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/charts/{id}")
    public ResponseEntity<Chart> getChart(@PathVariable Long id) {
        log.debug("REST request to get Chart : {}", id);
        Optional<Chart> chart = chartRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(chart);
    }
}
