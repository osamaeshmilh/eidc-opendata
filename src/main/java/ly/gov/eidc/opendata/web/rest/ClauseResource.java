package ly.gov.eidc.opendata.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import ly.gov.eidc.opendata.repository.ClauseRepository;
import ly.gov.eidc.opendata.service.ClauseQueryService;
import ly.gov.eidc.opendata.service.ClauseService;
import ly.gov.eidc.opendata.service.criteria.ClauseCriteria;
import ly.gov.eidc.opendata.service.dto.ClauseDTO;
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
 * REST controller for managing {@link ly.gov.eidc.opendata.domain.Clause}.
 */
@RestController
@RequestMapping("/api/clauses")
public class ClauseResource {

    private final Logger log = LoggerFactory.getLogger(ClauseResource.class);

    private static final String ENTITY_NAME = "clause";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClauseService clauseService;

    private final ClauseRepository clauseRepository;

    private final ClauseQueryService clauseQueryService;

    public ClauseResource(ClauseService clauseService, ClauseRepository clauseRepository, ClauseQueryService clauseQueryService) {
        this.clauseService = clauseService;
        this.clauseRepository = clauseRepository;
        this.clauseQueryService = clauseQueryService;
    }

    /**
     * {@code POST  /clauses} : Create a new clause.
     *
     * @param clauseDTO the clauseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clauseDTO, or with status {@code 400 (Bad Request)} if the clause has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ClauseDTO> createClause(@RequestBody ClauseDTO clauseDTO) throws URISyntaxException {
        log.debug("REST request to save Clause : {}", clauseDTO);
        if (clauseDTO.getId() != null) {
            throw new BadRequestAlertException("A new clause cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClauseDTO result = clauseService.save(clauseDTO);
        return ResponseEntity
            .created(new URI("/api/clauses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /clauses/:id} : Updates an existing clause.
     *
     * @param id the id of the clauseDTO to save.
     * @param clauseDTO the clauseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clauseDTO,
     * or with status {@code 400 (Bad Request)} if the clauseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clauseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ClauseDTO> updateClause(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClauseDTO clauseDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Clause : {}, {}", id, clauseDTO);
        if (clauseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clauseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clauseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClauseDTO result = clauseService.update(clauseDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, clauseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /clauses/:id} : Partial updates given fields of an existing clause, field will ignore if it is null
     *
     * @param id the id of the clauseDTO to save.
     * @param clauseDTO the clauseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clauseDTO,
     * or with status {@code 400 (Bad Request)} if the clauseDTO is not valid,
     * or with status {@code 404 (Not Found)} if the clauseDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the clauseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClauseDTO> partialUpdateClause(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClauseDTO clauseDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Clause partially : {}, {}", id, clauseDTO);
        if (clauseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clauseDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clauseRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClauseDTO> result = clauseService.partialUpdate(clauseDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, clauseDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /clauses} : get all the clauses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clauses in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ClauseDTO>> getAllClauses(
        ClauseCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Clauses by criteria: {}", criteria);

        Page<ClauseDTO> page = clauseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /clauses/count} : count all the clauses.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countClauses(ClauseCriteria criteria) {
        log.debug("REST request to count Clauses by criteria: {}", criteria);
        return ResponseEntity.ok().body(clauseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /clauses/:id} : get the "id" clause.
     *
     * @param id the id of the clauseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clauseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClauseDTO> getClause(@PathVariable Long id) {
        log.debug("REST request to get Clause : {}", id);
        Optional<ClauseDTO> clauseDTO = clauseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clauseDTO);
    }

    /**
     * {@code DELETE  /clauses/:id} : delete the "id" clause.
     *
     * @param id the id of the clauseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClause(@PathVariable Long id) {
        log.debug("REST request to delete Clause : {}", id);
        clauseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
