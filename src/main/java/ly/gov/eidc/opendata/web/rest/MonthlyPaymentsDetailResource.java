package ly.gov.eidc.opendata.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.opendata.repository.MonthlyPaymentsDetailRepository;
import ly.gov.eidc.opendata.service.MonthlyPaymentsDetailQueryService;
import ly.gov.eidc.opendata.service.MonthlyPaymentsDetailService;
import ly.gov.eidc.opendata.service.criteria.MonthlyPaymentsDetailCriteria;
import ly.gov.eidc.opendata.service.dto.MonthlyPaymentsDetailDTO;
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
 * REST controller for managing {@link ly.gov.eidc.opendata.domain.MonthlyPaymentsDetail}.
 */
@RestController
@RequestMapping("/api/monthly-payments-details")
public class MonthlyPaymentsDetailResource {

    private final Logger log = LoggerFactory.getLogger(MonthlyPaymentsDetailResource.class);

    private static final String ENTITY_NAME = "monthlyPaymentsDetail";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MonthlyPaymentsDetailService monthlyPaymentsDetailService;

    private final MonthlyPaymentsDetailRepository monthlyPaymentsDetailRepository;

    private final MonthlyPaymentsDetailQueryService monthlyPaymentsDetailQueryService;

    public MonthlyPaymentsDetailResource(
        MonthlyPaymentsDetailService monthlyPaymentsDetailService,
        MonthlyPaymentsDetailRepository monthlyPaymentsDetailRepository,
        MonthlyPaymentsDetailQueryService monthlyPaymentsDetailQueryService
    ) {
        this.monthlyPaymentsDetailService = monthlyPaymentsDetailService;
        this.monthlyPaymentsDetailRepository = monthlyPaymentsDetailRepository;
        this.monthlyPaymentsDetailQueryService = monthlyPaymentsDetailQueryService;
    }

    /**
     * {@code POST  /monthly-payments-details} : Create a new monthlyPaymentsDetail.
     *
     * @param monthlyPaymentsDetailDTO the monthlyPaymentsDetailDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new monthlyPaymentsDetailDTO, or with status {@code 400 (Bad Request)} if the monthlyPaymentsDetail has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<MonthlyPaymentsDetailDTO> createMonthlyPaymentsDetail(
        @RequestBody MonthlyPaymentsDetailDTO monthlyPaymentsDetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to save MonthlyPaymentsDetail : {}", monthlyPaymentsDetailDTO);
        if (monthlyPaymentsDetailDTO.getId() != null) {
            throw new BadRequestAlertException("A new monthlyPaymentsDetail cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MonthlyPaymentsDetailDTO result = monthlyPaymentsDetailService.save(monthlyPaymentsDetailDTO);
        return ResponseEntity
            .created(new URI("/api/monthly-payments-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /monthly-payments-details/:id} : Updates an existing monthlyPaymentsDetail.
     *
     * @param id the id of the monthlyPaymentsDetailDTO to save.
     * @param monthlyPaymentsDetailDTO the monthlyPaymentsDetailDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyPaymentsDetailDTO,
     * or with status {@code 400 (Bad Request)} if the monthlyPaymentsDetailDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the monthlyPaymentsDetailDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MonthlyPaymentsDetailDTO> updateMonthlyPaymentsDetail(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MonthlyPaymentsDetailDTO monthlyPaymentsDetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to update MonthlyPaymentsDetail : {}, {}", id, monthlyPaymentsDetailDTO);
        if (monthlyPaymentsDetailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, monthlyPaymentsDetailDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!monthlyPaymentsDetailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MonthlyPaymentsDetailDTO result = monthlyPaymentsDetailService.update(monthlyPaymentsDetailDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, monthlyPaymentsDetailDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /monthly-payments-details/:id} : Partial updates given fields of an existing monthlyPaymentsDetail, field will ignore if it is null
     *
     * @param id the id of the monthlyPaymentsDetailDTO to save.
     * @param monthlyPaymentsDetailDTO the monthlyPaymentsDetailDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyPaymentsDetailDTO,
     * or with status {@code 400 (Bad Request)} if the monthlyPaymentsDetailDTO is not valid,
     * or with status {@code 404 (Not Found)} if the monthlyPaymentsDetailDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the monthlyPaymentsDetailDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MonthlyPaymentsDetailDTO> partialUpdateMonthlyPaymentsDetail(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody MonthlyPaymentsDetailDTO monthlyPaymentsDetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update MonthlyPaymentsDetail partially : {}, {}", id, monthlyPaymentsDetailDTO);
        if (monthlyPaymentsDetailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, monthlyPaymentsDetailDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!monthlyPaymentsDetailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MonthlyPaymentsDetailDTO> result = monthlyPaymentsDetailService.partialUpdate(monthlyPaymentsDetailDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, monthlyPaymentsDetailDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /monthly-payments-details} : get all the monthlyPaymentsDetails.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of monthlyPaymentsDetails in body.
     */
    @GetMapping("")
    public ResponseEntity<List<MonthlyPaymentsDetailDTO>> getAllMonthlyPaymentsDetails(
        MonthlyPaymentsDetailCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get MonthlyPaymentsDetails by criteria: {}", criteria);

        Page<MonthlyPaymentsDetailDTO> page = monthlyPaymentsDetailQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /monthly-payments-details/count} : count all the monthlyPaymentsDetails.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countMonthlyPaymentsDetails(MonthlyPaymentsDetailCriteria criteria) {
        log.debug("REST request to count MonthlyPaymentsDetails by criteria: {}", criteria);
        return ResponseEntity.ok().body(monthlyPaymentsDetailQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /monthly-payments-details/:id} : get the "id" monthlyPaymentsDetail.
     *
     * @param id the id of the monthlyPaymentsDetailDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the monthlyPaymentsDetailDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MonthlyPaymentsDetailDTO> getMonthlyPaymentsDetail(@PathVariable Long id) {
        log.debug("REST request to get MonthlyPaymentsDetail : {}", id);
        Optional<MonthlyPaymentsDetailDTO> monthlyPaymentsDetailDTO = monthlyPaymentsDetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(monthlyPaymentsDetailDTO);
    }

    /**
     * {@code DELETE  /monthly-payments-details/:id} : delete the "id" monthlyPaymentsDetail.
     *
     * @param id the id of the monthlyPaymentsDetailDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonthlyPaymentsDetail(@PathVariable Long id) {
        log.debug("REST request to delete MonthlyPaymentsDetail : {}", id);
        monthlyPaymentsDetailService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
