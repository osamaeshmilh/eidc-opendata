package ly.gov.eidc.opendata.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.opendata.repository.MonthlyRevenueRepository;
import ly.gov.eidc.opendata.service.MonthlyRevenueQueryService;
import ly.gov.eidc.opendata.service.MonthlyRevenueService;
import ly.gov.eidc.opendata.service.criteria.MonthlyRevenueCriteria;
import ly.gov.eidc.opendata.service.dto.MonthlyRevenueDTO;
import ly.gov.eidc.opendata.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ly.gov.eidc.opendata.domain.MonthlyRevenue}.
 */
@RestController
@RequestMapping("/api/monthly-revenues")
public class MonthlyRevenueResource {

    private final Logger log = LoggerFactory.getLogger(MonthlyRevenueResource.class);

    private static final String ENTITY_NAME = "monthlyRevenue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MonthlyRevenueService monthlyRevenueService;

    private final MonthlyRevenueRepository monthlyRevenueRepository;

    private final MonthlyRevenueQueryService monthlyRevenueQueryService;

    public MonthlyRevenueResource(
        MonthlyRevenueService monthlyRevenueService,
        MonthlyRevenueRepository monthlyRevenueRepository,
        MonthlyRevenueQueryService monthlyRevenueQueryService
    ) {
        this.monthlyRevenueService = monthlyRevenueService;
        this.monthlyRevenueRepository = monthlyRevenueRepository;
        this.monthlyRevenueQueryService = monthlyRevenueQueryService;
    }

    /**
     * {@code POST  /monthly-revenues} : Create a new monthlyRevenue.
     *
     * @param monthlyRevenueDTO the monthlyRevenueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new monthlyRevenueDTO, or with status {@code 400 (Bad Request)} if the monthlyRevenue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MonthlyRevenueDTO> createMonthlyRevenue(@RequestBody MonthlyRevenueDTO monthlyRevenueDTO)
        throws URISyntaxException {
        log.debug("REST request to save MonthlyRevenue : {}", monthlyRevenueDTO);
        if (monthlyRevenueDTO.getId() != null) {
            throw new BadRequestAlertException("A new monthlyRevenue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MonthlyRevenueDTO result = monthlyRevenueService.save(monthlyRevenueDTO);
        return ResponseEntity
            .created(new URI("/api/monthly-revenues/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /monthly-revenues/:id} : Updates an existing monthlyRevenue.
     *
     * @param id the id of the monthlyRevenueDTO to save.
     * @param monthlyRevenueDTO the monthlyRevenueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyRevenueDTO,
     * or with status {@code 400 (Bad Request)} if the monthlyRevenueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the monthlyRevenueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MonthlyRevenueDTO> updateMonthlyRevenue(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MonthlyRevenueDTO monthlyRevenueDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MonthlyRevenue : {}, {}", id, monthlyRevenueDTO);
        if (monthlyRevenueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, monthlyRevenueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!monthlyRevenueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MonthlyRevenueDTO result = monthlyRevenueService.update(monthlyRevenueDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, monthlyRevenueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /monthly-revenues/:id} : Partial updates given fields of an existing monthlyRevenue, field will ignore if it is null
     *
     * @param id the id of the monthlyRevenueDTO to save.
     * @param monthlyRevenueDTO the monthlyRevenueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyRevenueDTO,
     * or with status {@code 400 (Bad Request)} if the monthlyRevenueDTO is not valid,
     * or with status {@code 404 (Not Found)} if the monthlyRevenueDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the monthlyRevenueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MonthlyRevenueDTO> partialUpdateMonthlyRevenue(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MonthlyRevenueDTO monthlyRevenueDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MonthlyRevenue partially : {}, {}", id, monthlyRevenueDTO);
        if (monthlyRevenueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, monthlyRevenueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!monthlyRevenueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MonthlyRevenueDTO> result = monthlyRevenueService.partialUpdate(monthlyRevenueDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, monthlyRevenueDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /monthly-revenues} : get all the monthlyRevenues.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of monthlyRevenues in body.
     */
    @GetMapping("")
    public ResponseEntity<List<MonthlyRevenueDTO>> getAllMonthlyRevenues(
        MonthlyRevenueCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get MonthlyRevenues by criteria: {}", criteria);

        Page<MonthlyRevenueDTO> page = monthlyRevenueQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /monthly-revenues/count} : count all the monthlyRevenues.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countMonthlyRevenues(MonthlyRevenueCriteria criteria) {
        log.debug("REST request to count MonthlyRevenues by criteria: {}", criteria);
        return ResponseEntity.ok().body(monthlyRevenueQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /monthly-revenues/:id} : get the "id" monthlyRevenue.
     *
     * @param id the id of the monthlyRevenueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the monthlyRevenueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MonthlyRevenueDTO> getMonthlyRevenue(@PathVariable Long id) {
        log.debug("REST request to get MonthlyRevenue : {}", id);
        Optional<MonthlyRevenueDTO> monthlyRevenueDTO = monthlyRevenueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monthlyRevenueDTO);
    }

    /**
     * {@code DELETE  /monthly-revenues/:id} : delete the "id" monthlyRevenue.
     *
     * @param id the id of the monthlyRevenueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonthlyRevenue(@PathVariable Long id) {
        log.debug("REST request to delete MonthlyRevenue : {}", id);
        monthlyRevenueService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
