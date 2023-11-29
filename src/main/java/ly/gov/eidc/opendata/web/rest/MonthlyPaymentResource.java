package ly.gov.eidc.opendata.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.opendata.repository.MonthlyPaymentRepository;
import ly.gov.eidc.opendata.service.MonthlyPaymentQueryService;
import ly.gov.eidc.opendata.service.MonthlyPaymentService;
import ly.gov.eidc.opendata.service.criteria.MonthlyPaymentCriteria;
import ly.gov.eidc.opendata.service.dto.MonthlyPaymentDTO;
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
 * REST controller for managing {@link ly.gov.eidc.opendata.domain.MonthlyPayment}.
 */
@RestController
@RequestMapping("/api/monthly-payments")
public class MonthlyPaymentResource {

    private final Logger log = LoggerFactory.getLogger(MonthlyPaymentResource.class);

    private static final String ENTITY_NAME = "monthlyPayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MonthlyPaymentService monthlyPaymentService;

    private final MonthlyPaymentRepository monthlyPaymentRepository;

    private final MonthlyPaymentQueryService monthlyPaymentQueryService;

    public MonthlyPaymentResource(
        MonthlyPaymentService monthlyPaymentService,
        MonthlyPaymentRepository monthlyPaymentRepository,
        MonthlyPaymentQueryService monthlyPaymentQueryService
    ) {
        this.monthlyPaymentService = monthlyPaymentService;
        this.monthlyPaymentRepository = monthlyPaymentRepository;
        this.monthlyPaymentQueryService = monthlyPaymentQueryService;
    }

    /**
     * {@code POST  /monthly-payments} : Create a new monthlyPayment.
     *
     * @param monthlyPaymentDTO the monthlyPaymentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new monthlyPaymentDTO, or with status {@code 400 (Bad Request)} if the monthlyPayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MonthlyPaymentDTO> createMonthlyPayment(@RequestBody MonthlyPaymentDTO monthlyPaymentDTO)
        throws URISyntaxException {
        log.debug("REST request to save MonthlyPayment : {}", monthlyPaymentDTO);
        if (monthlyPaymentDTO.getId() != null) {
            throw new BadRequestAlertException("A new monthlyPayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MonthlyPaymentDTO result = monthlyPaymentService.save(monthlyPaymentDTO);
        return ResponseEntity
            .created(new URI("/api/monthly-payments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /monthly-payments/:id} : Updates an existing monthlyPayment.
     *
     * @param id the id of the monthlyPaymentDTO to save.
     * @param monthlyPaymentDTO the monthlyPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the monthlyPaymentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the monthlyPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MonthlyPaymentDTO> updateMonthlyPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MonthlyPaymentDTO monthlyPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MonthlyPayment : {}, {}", id, monthlyPaymentDTO);
        if (monthlyPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, monthlyPaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!monthlyPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MonthlyPaymentDTO result = monthlyPaymentService.update(monthlyPaymentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, monthlyPaymentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /monthly-payments/:id} : Partial updates given fields of an existing monthlyPayment, field will ignore if it is null
     *
     * @param id the id of the monthlyPaymentDTO to save.
     * @param monthlyPaymentDTO the monthlyPaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyPaymentDTO,
     * or with status {@code 400 (Bad Request)} if the monthlyPaymentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the monthlyPaymentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the monthlyPaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MonthlyPaymentDTO> partialUpdateMonthlyPayment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MonthlyPaymentDTO monthlyPaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MonthlyPayment partially : {}, {}", id, monthlyPaymentDTO);
        if (monthlyPaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, monthlyPaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!monthlyPaymentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MonthlyPaymentDTO> result = monthlyPaymentService.partialUpdate(monthlyPaymentDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, monthlyPaymentDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /monthly-payments} : get all the monthlyPayments.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of monthlyPayments in body.
     */
    @GetMapping("")
    public ResponseEntity<List<MonthlyPaymentDTO>> getAllMonthlyPayments(
        MonthlyPaymentCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get MonthlyPayments by criteria: {}", criteria);

        Page<MonthlyPaymentDTO> page = monthlyPaymentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /monthly-payments/count} : count all the monthlyPayments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countMonthlyPayments(MonthlyPaymentCriteria criteria) {
        log.debug("REST request to count MonthlyPayments by criteria: {}", criteria);
        return ResponseEntity.ok().body(monthlyPaymentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /monthly-payments/:id} : get the "id" monthlyPayment.
     *
     * @param id the id of the monthlyPaymentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the monthlyPaymentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MonthlyPaymentDTO> getMonthlyPayment(@PathVariable Long id) {
        log.debug("REST request to get MonthlyPayment : {}", id);
        Optional<MonthlyPaymentDTO> monthlyPaymentDTO = monthlyPaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monthlyPaymentDTO);
    }

    /**
     * {@code DELETE  /monthly-payments/:id} : delete the "id" monthlyPayment.
     *
     * @param id the id of the monthlyPaymentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonthlyPayment(@PathVariable Long id) {
        log.debug("REST request to delete MonthlyPayment : {}", id);
        monthlyPaymentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
